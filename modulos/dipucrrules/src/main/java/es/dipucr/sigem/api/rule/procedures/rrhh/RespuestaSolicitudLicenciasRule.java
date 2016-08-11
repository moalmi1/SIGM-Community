package es.dipucr.sigem.api.rule.procedures.rrhh;

import ieci.tdw.ispac.api.IEntitiesAPI;
import ieci.tdw.ispac.api.IInvesflowAPI;
import ieci.tdw.ispac.api.errors.ISPACRuleException;
import ieci.tdw.ispac.api.item.IItem;
import ieci.tdw.ispac.api.item.IItemCollection;
import ieci.tdw.ispac.api.rule.IRule;
import ieci.tdw.ispac.api.rule.IRuleContext;
import ieci.tdw.ispac.ispaclib.common.constants.SignStatesConstants;
import ieci.tdw.ispac.ispaclib.context.IClientContext;

import org.apache.log4j.Logger;

import es.dipucr.sigem.api.rule.common.utils.CircuitosUtil;
import es.dipucr.sigem.api.rule.common.utils.DocumentosUtil;
import es.dipucr.sigem.api.rule.common.utils.ExpedientesUtil;
import es.dipucr.webempleado.services.licencias.LicenciasWSProxy;


/**
 * [eCenpri-Felipe ticket #206]
 * Regla para enviar la respuesta de la firma al portal del empleado, notificar por comparece
 * y cerrar tanto el tr�mite como el expediente
 * @author Felipe
 * @since 28.02.2011
 */
public class RespuestaSolicitudLicenciasRule implements IRule 
{
	/** Logger de la clase. */
	private static final Logger logger = Logger.getLogger(RespuestaSolicitudLicenciasRule.class);
	
	protected static String _TRAMITE_FIRMAS = "Firmar licencias"; //TODO Constants
	protected static String _DOC_JUSTIFICANTE = "Justificante";
	protected static String _DOC_SOLICITUD = "RRHH - Solicitud de Licencias";
	
	public boolean init(IRuleContext rulectx) throws ISPACRuleException {
		return true;
	}
	
	public boolean validate(IRuleContext rulectx) throws ISPACRuleException {
		return true;
	}
	
	/**
	 * Generaci�n de la fase, el tr�mite y env�o al Jefe de departamento para firma
	 */
	public Object execute(IRuleContext rulectx) throws ISPACRuleException {

		try{
			
			//*********************************************
			IClientContext cct = rulectx.getClientContext();
	  	    IInvesflowAPI invesFlowAPI = cct.getAPI();
			IEntitiesAPI entitiesAPI = invesFlowAPI.getEntitiesAPI();
			//*********************************************
			
			//*********************************************
			//Respuesta al portal del empleado -> Se actualiza el estado
			//*********************************************
			//Vemos si la solicitud ha sido firmada o rechazada
			int idDoc = rulectx.getInt("ID_DOCUMENTO");
			IItem itemDocumento = DocumentosUtil.getDocumento(entitiesAPI, idDoc);
			String estado = itemDocumento.getString("ESTADOFIRMA");
			String strMotivo = null;
			boolean bFirmado = false;
			
			if (estado.equals(SignStatesConstants.FIRMADO)){
				bFirmado = true;
				strMotivo = itemDocumento.getString("MOTIVO_REPARO");
				//por si hay un reparo anterior [eCenpri-Felipe #601]
			}
			else if (estado.equals(SignStatesConstants.FIRMADO_CON_REPAROS)){
				bFirmado = true;
				strMotivo = itemDocumento.getString("MOTIVO_REPARO");
			}
			else if (estado.equals(SignStatesConstants.RECHAZADO)){
				bFirmado = false;
				strMotivo = itemDocumento.getString("MOTIVO_RECHAZO");
			}
			
			//Desglosamos el id de solicitud en NIF, a�o y N�Licencia
			String numexp = DocumentosUtil.getNumExp(entitiesAPI, idDoc);
			IItemCollection collection = entitiesAPI.getEntities("RRHH_LICENCIAS", numexp);
			IItem itemSolicitudLicencias = (IItem)collection.iterator().next();
			String strIdSolicitud = itemSolicitudLicencias.getString("ID_SOLICITUD");
			String [] arrIdSolicitud = strIdSolicitud.split("-");
			String strNif = arrIdSolicitud[0];
			int iAno = Integer.valueOf(arrIdSolicitud[1]);
			String strNlic = arrIdSolicitud[2];
			
			//[eCenpri-Felipe #601] Datos del circuito de firma
			String firmantes = CircuitosUtil.getFirmantesCircuito(rulectx);
			
			//Hacemos la petici�n al servicio web
			LicenciasWSProxy wsLicencias = new LicenciasWSProxy();
			//[eCenpri-Felipe #601] Firmantes de la licencia
//			wsLicencias.ponerLicenciaValidada(strNif, iAno, strNlic, bFirmado, strMotivo);
			wsLicencias.ponerLicenciaValidada(strNif, iAno, strNlic, bFirmado, 
					strMotivo, firmantes);
			
			//Cerramos el tr�mite y el expediente
			ExpedientesUtil.cerrarExpediente(cct, numexp);
		}
		catch (Exception e) {
			logger.error("Error en la generaci�n de los tr�mites y env�o a firma de las licencias. " + e.getMessage(), e);
			throw new ISPACRuleException("Error en la generaci�n de los tr�mites y env�o a firma de las licencias. " + e.getMessage(), e);
		}
		return null;
	}
	
	public void cancel(IRuleContext rulectx) throws ISPACRuleException {
	}

}

package es.ieci.tecdoc.isicres.admin.sbo.idoc.api;

import es.ieci.tecdoc.isicres.admin.base.dbex.DbConnection;
import es.ieci.tecdoc.isicres.admin.core.db.DBSessionManager;
import es.ieci.tecdoc.isicres.admin.core.db.DbConnectionConfig;
import es.ieci.tecdoc.isicres.admin.sbo.config.CfgMdoConfig;
import es.ieci.tecdoc.isicres.admin.sbo.idoc.api.FolderSearchQueryObject;
import es.ieci.tecdoc.isicres.admin.sbo.idoc.folder.base.FolderTokenFlds;
import es.ieci.tecdoc.isicres.admin.sbo.idoc.folder.search.FolderMdoSearch;
import es.ieci.tecdoc.isicres.admin.sbo.idoc.folder.search.FolderSearchResult;


/**
 * Gestor de b�squedas de carpetas
 */


public final class FolderSearch
{
   /** 
    * Configuraci�n de la base de datos donde se encuentra el modelo
    * de datos de invesdoc
    */
   DbConnectionConfig m_dbConnConfig;
   
   /** 
    * Directorio donde se encuentra el fichero con la configuraci�n de
    * la base de datos invesdoc
    */
   String             m_configDir;
   
   /**
    * Constructor
    * @throws Exception
    */
   public FolderSearch() throws Exception
   {
      m_dbConnConfig = null;
      m_configDir    = null;   
   }
   
   /**
    * Construtor
    * @param configDir configuraci�n de la base de datos invesdoc
    * @throws Exception
    */
   public FolderSearch(String configDir) throws Exception
   {
      m_dbConnConfig = null;
      m_configDir    = configDir;       
   }

   /**
    * Devuelve la configuraci�n de la base de datos donde se encuentran
    * usuarios invesdoc. 
    * @return
    * @throws Exception
    */   
   private DbConnectionConfig getDbConfig() throws Exception
   {
      if (m_dbConnConfig == null)
      {
         if (m_configDir == null)
         {
            m_dbConnConfig = CfgMdoConfig.getDbConfig();
         }
         else
         {
            m_dbConnConfig = CfgMdoConfig.getDbConfig(m_configDir);
         }
      }
      
      return  m_dbConnConfig;
   }
   
   /**
    * Establece una configuraci�n de base de datos. Esta configuraci�n
    * corresponder� con la base de datos invesdoc.
    * @param dbConnConfig Configuraci�n de base de datos
    * @throws Exception
    */
   public void setConnectionConfig(DbConnectionConfig dbConnConfig)
               throws Exception
   {
      m_dbConnConfig = dbConnConfig;
   }
   
   /**
    * Realiza una b�squeda de carpetas dentro de un archivador. 
    * @param acs este par�metro es opcional.
    * Contiene informaci�n del usuario invesdoc que realiza la b�squeda. Si es
    * pasado este par�metro, s�lo se obtienen carpetas que, adem�s de cumplir los filtros de la
    * b�squeda, sean visibles para el usuario invesdoc al cual se refiere el par�metro acs.
    * @param arch este par�metro contiene informaci�n del archivador sobre el cual se van a buscar 
    * carpetas. 
    * @param query contiene los filtros que se van a aplicar a la b�squeda
    * @return referencia a un objeto de tipo FolderSearchResult que cotiene los resultados de la 
    * b�squeda
    * @throws Exception si se produce alg�n error en la b�squeda de carpetas
    * @see AcsAccessObject
    * @see ArchiveObject
    * @see FolderSearchQueryObject
    * @see FolderSearchResult 
    */
   public FolderSearchResult executeQuery(AcsAccessObject acs,
                                          ArchiveObject arch,
                                          FolderSearchQueryObject query, String entidad)
                             throws Exception
   {
      
      FolderSearchResult rs = null;
      
      DbConnection dbConn=new DbConnection();
      try
      {
         
    	  dbConn.open(DBSessionManager.getSession());	
         
         if (acs == null)
            rs = FolderMdoSearch.executeQuery(null,
                                              arch.getArchiveToken(),
                                              query.getFolderSearchQuery(), entidad);
         else
            rs = FolderMdoSearch.executeQuery(acs.getAccessToken(),
                                              arch.getArchiveToken(),
                                              query.getFolderSearchQuery(), entidad);         
                                          
      }
  	catch(Exception e){
		throw e;
	}finally{
		dbConn.close();
	}
	return rs;
   }

   /**
    * Realiza una b�squeda de carpetas dentro de un archivador. 
    * @param acs este par�metro es opcional.
    * Contiene informaci�n del usuario invesdoc que realiza la b�squeda. Si es
    * pasado este par�metro, s�lo se obtienen carpetas que, adem�s de cumplir los filtros de la
    * b�squeda, sean visibles para el usuario invesdoc al cual se refiere el par�metro acs.
    * @param arch este par�metro contiene informaci�n del archivador sobre el cual se van a buscar 
    * carpetas. 
    * @param qual condici�n sql que define los filtros de b�squeda de carpetas
    * @return referencia a un objeto de tipo FolderSearchResult que cotiene los resultados de la 
    * b�squeda
    * @throws Exception si se produce alg�n error en la b�squeda de carpetas
    * @see AcsAccessObject
    * @see ArchiveObject
    * @see FolderSearchResult 
    */
   public FolderSearchResult executeQuery(AcsAccessObject acs,
                                          ArchiveObject arch,
                                          String qual, String entidad)
                             throws Exception
   {
      
      FolderSearchResult rs = null;
      
      DbConnection dbConn=new DbConnection();
      try
      {
         
    	  dbConn.open(DBSessionManager.getSession());	   
         
         if (acs == null)
            rs = FolderMdoSearch.executeQuery(null,
                                              arch.getArchiveToken(),
                                              qual, entidad);
         else
            rs = FolderMdoSearch.executeQuery(acs.getAccessToken(),
                                              arch.getArchiveToken(),
                                              qual, entidad);


                                          
      }
  	catch(Exception e){
		throw e;
	}finally{
		dbConn.close();
		
	}  
    return rs;    
   }
   
   /**
    * Devuelve los valores de los campos asociados a la carpeta i-�sima de una colecci�n de 
    * carpetas obtenida como resultado de una b�squeda dentro de un archivador.
    * @param arch este par�metro contiene informaci�n del archivador al cual pertenece
    * la carpeta
    * @param rs resultado de una b�squeda de carpetas sobre un archivador
    * @param idx �ndice i�simo de la carpeta sobre la cual obtener los valores de sus campos 
    * @return referencia a un objeto de tipo FolderFieldObjects que contiene los valores de los
    * campos de la carpeta
    * @throws Exception si se produce alg�n error en la obtenci�n de los valores de los campos
    * de una carpeta
    * @see FolderFieldObjects
    */
   public FolderFieldObjects getFolderValues(ArchiveObject arch, FolderSearchResult rs,
                                             int idx, String entidad)
                             throws Exception
   {
      FolderTokenFlds    values = null;
      FolderFieldObjects flds = null;
      
      DbConnection dbConn=new DbConnection();
      try
      {
         
    	  dbConn.open(DBSessionManager.getSession());	          

         values = FolderMdoSearch.getFolderValues(dbConn, arch.getArchiveToken(), rs, idx, entidad);

         flds = new FolderFieldObjects(values);
                                                  
      }
      catch (Exception e)
      {
    	  throw e;
      }finally{
    	  dbConn.close();
      }
      return flds;
      
   }
   
} // class

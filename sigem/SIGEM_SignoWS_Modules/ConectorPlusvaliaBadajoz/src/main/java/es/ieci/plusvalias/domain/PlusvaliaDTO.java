package es.ieci.plusvalias.domain;

// Generated 30-jun-2010 10:06:13 by Hibernate Tools 3.2.4.CR1

import java.util.Date;

/**
 * PlusvaliaDTO generated by hbm2java
 */
public class PlusvaliaDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer anyoejercicio;
	private Long numero;
	private Integer division;
	private Integer anyoexppro;
	private Integer numnotario;
	private Long numexppro;
	private String nombretrans;
	private String calletrans;
	private String poblaciontrans;
	private Integer cptrans;
	private String niftrans;
	private String nombreadqui;
	private String calleadqui;
	private String poblacionadqui;
	private Integer cpadqui;
	private String nifadqui;
	private String numvia;
	private String numcalle;
	private Integer km;
	private String bloque;
	private String portal;
	private String escalera;
	private String piso;
	private String puerta;
	private Integer cp;
	private Integer anyoliquidacion;
	private Double superficiesolar;
	private Double cuotaparti;
	private Integer partetrans1;
	private Integer partetrans2;
	private Double superficietr;
	private String refcatastral;
	private String fijocatastral;
	private Double valorunitario;
	private Double valorterreno;
	private Double valorterrenofin;
	private Integer derecho;
	private Double dedderecho;
	private Date fechatanterior;
	private Date fechatactual;
	private Integer numanyo;
	private Double porcentaje;
	private Double baseimponible;
	private Double tipoimpo;
	private Integer boniexen;
	private Double cuotaliquida;
	private Double cuotayaboniexen;
	private Double recargo;
	private Double intedemora;
	private Double totalliquida;
	private String monedaliquidacion;
	private String monedapago;
	private Date fechmeca;
	private Integer numsituacion;
	private Date fechnoti;
	private Date fechpago;
	private Integer cartapago;
	private Integer anycargo;
	private Integer numcargo;
	private Integer numlotenoti;
	private Integer numloteejec;
	private Integer numsituacionant;
	private Double sancion;
	private Integer tiposancion;
	private String localizacion;
	private Integer anyejercicioacta;
	private Long numliquidacionacta;
	private Integer divliquidacionacta;
	private Double totalliquidaacta;
	private Double cuotayaboniexenact;
	private Double intedemoraacta;
	private Double recargoacta;
	private Double sancionacta;
	private Long numordinal;
	private Integer anylotenoti;
	private Integer anyedicto;
	private Integer numedicto;
	private Integer numcausa;

	public PlusvaliaDTO() {
	}

	public PlusvaliaDTO(Long numero) {
		this.numero = numero;
	}

	public PlusvaliaDTO(Integer anyoejercicio, Long numero,
			Integer division, Integer anyoexppro, Integer numnotario,
			Long numexppro, String nombretrans, String calletrans,
			String poblaciontrans, Integer cptrans, String niftrans,
			String nombreadqui, String calleadqui, String poblacionadqui,
			Integer cpadqui, String nifadqui, String numvia, String numcalle,
			Integer km, String bloque, String portal, String escalera, String piso,
			String puerta, Integer cp, Integer anyoliquidacion, Double superficiesolar,
			Double cuotaparti, Integer partetrans1, Integer partetrans2,
			Double superficietr, String refcatastral, String fijocatastral,
			Double valorunitario, Double valorterreno, Double valorterrenofin,
			Integer derecho, Double dedderecho, Date fechatanterior,
			Date fechatactual, Integer numanyo, Double porcentaje,
			Double baseimponible, Double tipoimpo, Integer boniexen,
			Double cuotaliquida, Double cuotayaboniexen, Double recargo,
			Double intedemora, Double totalliquida, String monedaliquidacion,
			String monedapago, Date fechmeca, Integer numsituacion, Date fechnoti,
			Date fechpago, Integer cartapago, Integer anycargo, Integer numcargo,
			Integer numlotenoti, Integer numloteejec, Integer numsituacionant,
			Double sancion, Integer tiposancion, String localizacion,
			Integer anyejercicioacta, Long numliquidacionacta,
			Integer divliquidacionacta, Double totalliquidaacta,
			Double cuotayaboniexenact, Double intedemoraacta,
			Double recargoacta, Double sancionacta, Long numordinal,
			Integer anylotenoti, Integer anyedicto, Integer numedicto, Integer numcausa) {
		this.anyoejercicio = anyoejercicio;
		this.numero = numero;
		this.division = division;
		this.anyoexppro = anyoexppro;
		this.numnotario = numnotario;
		this.numexppro = numexppro;
		this.nombretrans = nombretrans;
		this.calletrans = calletrans;
		this.poblaciontrans = poblaciontrans;
		this.cptrans = cptrans;
		this.niftrans = niftrans;
		this.nombreadqui = nombreadqui;
		this.calleadqui = calleadqui;
		this.poblacionadqui = poblacionadqui;
		this.cpadqui = cpadqui;
		this.nifadqui = nifadqui;
		this.numvia = numvia;
		this.numcalle = numcalle;
		this.km = km;
		this.bloque = bloque;
		this.portal = portal;
		this.escalera = escalera;
		this.piso = piso;
		this.puerta = puerta;
		this.cp = cp;
		this.anyoliquidacion = anyoliquidacion;
		this.superficiesolar = superficiesolar;
		this.cuotaparti = cuotaparti;
		this.partetrans1 = partetrans1;
		this.partetrans2 = partetrans2;
		this.superficietr = superficietr;
		this.refcatastral = refcatastral;
		this.fijocatastral = fijocatastral;
		this.valorunitario = valorunitario;
		this.valorterreno = valorterreno;
		this.valorterrenofin = valorterrenofin;
		this.derecho = derecho;
		this.dedderecho = dedderecho;
		this.fechatanterior = fechatanterior;
		this.fechatactual = fechatactual;
		this.numanyo = numanyo;
		this.porcentaje = porcentaje;
		this.baseimponible = baseimponible;
		this.tipoimpo = tipoimpo;
		this.boniexen = boniexen;
		this.cuotaliquida = cuotaliquida;
		this.cuotayaboniexen = cuotayaboniexen;
		this.recargo = recargo;
		this.intedemora = intedemora;
		this.totalliquida = totalliquida;
		this.monedaliquidacion = monedaliquidacion;
		this.monedapago = monedapago;
		this.fechmeca = fechmeca;
		this.numsituacion = numsituacion;
		this.fechnoti = fechnoti;
		this.fechpago = fechpago;
		this.cartapago = cartapago;
		this.anycargo = anycargo;
		this.numcargo = numcargo;
		this.numlotenoti = numlotenoti;
		this.numloteejec = numloteejec;
		this.numsituacionant = numsituacionant;
		this.sancion = sancion;
		this.tiposancion = tiposancion;
		this.localizacion = localizacion;
		this.anyejercicioacta = anyejercicioacta;
		this.numliquidacionacta = numliquidacionacta;
		this.divliquidacionacta = divliquidacionacta;
		this.totalliquidaacta = totalliquidaacta;
		this.cuotayaboniexenact = cuotayaboniexenact;
		this.intedemoraacta = intedemoraacta;
		this.recargoacta = recargoacta;
		this.sancionacta = sancionacta;
		this.numordinal = numordinal;
		this.anylotenoti = anylotenoti;
		this.anyedicto = anyedicto;
		this.numedicto = numedicto;
		this.numcausa = numcausa;
	}

	public Integer getAnyoejercicio() {
		return this.anyoejercicio;
	}

	public void setAnyoejercicio(Integer anyoejercicio) {
		this.anyoejercicio = anyoejercicio;
	}

	public Long getNumero() {
		return this.numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Integer getDivision() {
		return this.division;
	}

	public void setDivision(Integer division) {
		this.division = division;
	}

	public Integer getAnyoexppro() {
		return this.anyoexppro;
	}

	public void setAnyoexppro(Integer anyoexppro) {
		this.anyoexppro = anyoexppro;
	}

	public Integer getNumnotario() {
		return this.numnotario;
	}

	public void setNumnotario(Integer numnotario) {
		this.numnotario = numnotario;
	}

	public Long getNumexppro() {
		return this.numexppro;
	}

	public void setNumexppro(Long numexppro) {
		this.numexppro = numexppro;
	}

	public String getNombretrans() {
		return this.nombretrans;
	}

	public void setNombretrans(String nombretrans) {
		this.nombretrans = nombretrans;
	}

	public String getCalletrans() {
		return this.calletrans;
	}

	public void setCalletrans(String calletrans) {
		this.calletrans = calletrans;
	}

	public String getPoblaciontrans() {
		return this.poblaciontrans;
	}

	public void setPoblaciontrans(String poblaciontrans) {
		this.poblaciontrans = poblaciontrans;
	}

	public Integer getCptrans() {
		return this.cptrans;
	}

	public void setCptrans(Integer cptrans) {
		this.cptrans = cptrans;
	}

	public String getNiftrans() {
		return this.niftrans;
	}

	public void setNiftrans(String niftrans) {
		this.niftrans = niftrans;
	}

	public String getNombreadqui() {
		return this.nombreadqui;
	}

	public void setNombreadqui(String nombreadqui) {
		this.nombreadqui = nombreadqui;
	}

	public String getCalleadqui() {
		return this.calleadqui;
	}

	public void setCalleadqui(String calleadqui) {
		this.calleadqui = calleadqui;
	}

	public String getPoblacionadqui() {
		return this.poblacionadqui;
	}

	public void setPoblacionadqui(String poblacionadqui) {
		this.poblacionadqui = poblacionadqui;
	}

	public Integer getCpadqui() {
		return this.cpadqui;
	}

	public void setCpadqui(Integer cpadqui) {
		this.cpadqui = cpadqui;
	}

	public String getNifadqui() {
		return this.nifadqui;
	}

	public void setNifadqui(String nifadqui) {
		this.nifadqui = nifadqui;
	}

	public String getNumvia() {
		return this.numvia;
	}

	public void setNumvia(String numvia) {
		this.numvia = numvia;
	}

	public String getNumcalle() {
		return this.numcalle;
	}

	public void setNumcalle(String numcalle) {
		this.numcalle = numcalle;
	}

	public Integer getKm() {
		return this.km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public String getBloque() {
		return this.bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public String getPortal() {
		return this.portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getEscalera() {
		return this.escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getPiso() {
		return this.piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getPuerta() {
		return this.puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public Integer getCp() {
		return this.cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public Integer getAnyoliquidacion() {
		return this.anyoliquidacion;
	}

	public void setAnyoliquidacion(Integer anyoliquidacion) {
		this.anyoliquidacion = anyoliquidacion;
	}

	public Double getSuperficiesolar() {
		return this.superficiesolar;
	}

	public void setSuperficiesolar(Double superficiesolar) {
		this.superficiesolar = superficiesolar;
	}

	public Double getCuotaparti() {
		return this.cuotaparti;
	}

	public void setCuotaparti(Double cuotaparti) {
		this.cuotaparti = cuotaparti;
	}

	public Integer getPartetrans1() {
		return this.partetrans1;
	}

	public void setPartetrans1(Integer partetrans1) {
		this.partetrans1 = partetrans1;
	}

	public Integer getPartetrans2() {
		return this.partetrans2;
	}

	public void setPartetrans2(Integer partetrans2) {
		this.partetrans2 = partetrans2;
	}

	public Double getSuperficietr() {
		return this.superficietr;
	}

	public void setSuperficietr(Double superficietr) {
		this.superficietr = superficietr;
	}

	public String getRefcatastral() {
		return this.refcatastral;
	}

	public void setRefcatastral(String refcatastral) {
		this.refcatastral = refcatastral;
	}

	public String getFijocatastral() {
		return this.fijocatastral;
	}

	public void setFijocatastral(String fijocatastral) {
		this.fijocatastral = fijocatastral;
	}

	public Double getValorunitario() {
		return this.valorunitario;
	}

	public void setValorunitario(Double valorunitario) {
		this.valorunitario = valorunitario;
	}

	public Double getValorterreno() {
		return this.valorterreno;
	}

	public void setValorterreno(Double valorterreno) {
		this.valorterreno = valorterreno;
	}

	public Double getValorterrenofin() {
		return this.valorterrenofin;
	}

	public void setValorterrenofin(Double valorterrenofin) {
		this.valorterrenofin = valorterrenofin;
	}

	public Integer getDerecho() {
		return this.derecho;
	}

	public void setDerecho(Integer derecho) {
		this.derecho = derecho;
	}

	public Double getDedderecho() {
		return this.dedderecho;
	}

	public void setDedderecho(Double dedderecho) {
		this.dedderecho = dedderecho;
	}

	public Date getFechatanterior() {
		return this.fechatanterior;
	}

	public void setFechatanterior(Date fechatanterior) {
		this.fechatanterior = fechatanterior;
	}

	public Date getFechatactual() {
		return this.fechatactual;
	}

	public void setFechatactual(Date fechatactual) {
		this.fechatactual = fechatactual;
	}

	public Integer getNumanyo() {
		return this.numanyo;
	}

	public void setNumanyo(Integer numanyo) {
		this.numanyo = numanyo;
	}

	public Double getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Double getBaseimponible() {
		return this.baseimponible;
	}

	public void setBaseimponible(Double baseimponible) {
		this.baseimponible = baseimponible;
	}

	public Double getTipoimpo() {
		return this.tipoimpo;
	}

	public void setTipoimpo(Double tipoimpo) {
		this.tipoimpo = tipoimpo;
	}

	public Integer getBoniexen() {
		return this.boniexen;
	}

	public void setBoniexen(Integer boniexen) {
		this.boniexen = boniexen;
	}

	public Double getCuotaliquida() {
		return this.cuotaliquida;
	}

	public void setCuotaliquida(Double cuotaliquida) {
		this.cuotaliquida = cuotaliquida;
	}

	public Double getCuotayaboniexen() {
		return this.cuotayaboniexen;
	}

	public void setCuotayaboniexen(Double cuotayaboniexen) {
		this.cuotayaboniexen = cuotayaboniexen;
	}

	public Double getRecargo() {
		return this.recargo;
	}

	public void setRecargo(Double recargo) {
		this.recargo = recargo;
	}

	public Double getIntedemora() {
		return this.intedemora;
	}

	public void setIntedemora(Double intedemora) {
		this.intedemora = intedemora;
	}

	public Double getTotalliquida() {
		return this.totalliquida;
	}

	public void setTotalliquida(Double totalliquida) {
		this.totalliquida = totalliquida;
	}

	public String getMonedaliquidacion() {
		return this.monedaliquidacion;
	}

	public void setMonedaliquidacion(String monedaliquidacion) {
		this.monedaliquidacion = monedaliquidacion;
	}

	public String getMonedapago() {
		return this.monedapago;
	}

	public void setMonedapago(String monedapago) {
		this.monedapago = monedapago;
	}

	public Date getFechmeca() {
		return this.fechmeca;
	}

	public void setFechmeca(Date fechmeca) {
		this.fechmeca = fechmeca;
	}

	public Integer getNumsituacion() {
		return this.numsituacion;
	}

	public void setNumsituacion(Integer numsituacion) {
		this.numsituacion = numsituacion;
	}

	public Date getFechnoti() {
		return this.fechnoti;
	}

	public void setFechnoti(Date fechnoti) {
		this.fechnoti = fechnoti;
	}

	public Date getFechpago() {
		return this.fechpago;
	}

	public void setFechpago(Date fechpago) {
		this.fechpago = fechpago;
	}

	public Integer getCartapago() {
		return this.cartapago;
	}

	public void setCartapago(Integer cartapago) {
		this.cartapago = cartapago;
	}

	public Integer getAnycargo() {
		return this.anycargo;
	}

	public void setAnycargo(Integer anycargo) {
		this.anycargo = anycargo;
	}

	public Integer getNumcargo() {
		return this.numcargo;
	}

	public void setNumcargo(Integer numcargo) {
		this.numcargo = numcargo;
	}

	public Integer getNumlotenoti() {
		return this.numlotenoti;
	}

	public void setNumlotenoti(Integer numlotenoti) {
		this.numlotenoti = numlotenoti;
	}

	public Integer getNumloteejec() {
		return this.numloteejec;
	}

	public void setNumloteejec(Integer numloteejec) {
		this.numloteejec = numloteejec;
	}

	public Integer getNumsituacionant() {
		return this.numsituacionant;
	}

	public void setNumsituacionant(Integer numsituacionant) {
		this.numsituacionant = numsituacionant;
	}

	public Double getSancion() {
		return this.sancion;
	}

	public void setSancion(Double sancion) {
		this.sancion = sancion;
	}

	public Integer getTiposancion() {
		return this.tiposancion;
	}

	public void setTiposancion(Integer tiposancion) {
		this.tiposancion = tiposancion;
	}

	public String getLocalizacion() {
		return this.localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Integer getAnyejercicioacta() {
		return this.anyejercicioacta;
	}

	public void setAnyejercicioacta(Integer anyejercicioacta) {
		this.anyejercicioacta = anyejercicioacta;
	}

	public Long getNumliquidacionacta() {
		return this.numliquidacionacta;
	}

	public void setNumliquidacionacta(Long numliquidacionacta) {
		this.numliquidacionacta = numliquidacionacta;
	}

	public Integer getDivliquidacionacta() {
		return this.divliquidacionacta;
	}

	public void setDivliquidacionacta(Integer divliquidacionacta) {
		this.divliquidacionacta = divliquidacionacta;
	}

	public Double getTotalliquidaacta() {
		return this.totalliquidaacta;
	}

	public void setTotalliquidaacta(Double totalliquidaacta) {
		this.totalliquidaacta = totalliquidaacta;
	}

	public Double getCuotayaboniexenact() {
		return this.cuotayaboniexenact;
	}

	public void setCuotayaboniexenact(Double cuotayaboniexenact) {
		this.cuotayaboniexenact = cuotayaboniexenact;
	}

	public Double getIntedemoraacta() {
		return this.intedemoraacta;
	}

	public void setIntedemoraacta(Double intedemoraacta) {
		this.intedemoraacta = intedemoraacta;
	}

	public Double getRecargoacta() {
		return this.recargoacta;
	}

	public void setRecargoacta(Double recargoacta) {
		this.recargoacta = recargoacta;
	}

	public Double getSancionacta() {
		return this.sancionacta;
	}

	public void setSancionacta(Double sancionacta) {
		this.sancionacta = sancionacta;
	}

	public Long getNumordinal() {
		return this.numordinal;
	}

	public void setNumordinal(Long numordinal) {
		this.numordinal = numordinal;
	}

	public Integer getAnylotenoti() {
		return this.anylotenoti;
	}

	public void setAnylotenoti(Integer anylotenoti) {
		this.anylotenoti = anylotenoti;
	}

	public Integer getAnyedicto() {
		return this.anyedicto;
	}

	public void setAnyedicto(Integer anyedicto) {
		this.anyedicto = anyedicto;
	}

	public Integer getNumedicto() {
		return this.numedicto;
	}

	public void setNumedicto(Integer numedicto) {
		this.numedicto = numedicto;
	}

	public Integer getNumcausa() {
		return this.numcausa;
	}

	public void setNumcausa(Integer numcausa) {
		this.numcausa = numcausa;
	}
}

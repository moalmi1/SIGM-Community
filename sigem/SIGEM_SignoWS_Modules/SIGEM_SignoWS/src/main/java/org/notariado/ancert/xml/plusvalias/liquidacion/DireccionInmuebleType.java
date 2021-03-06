//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.07.04 en 12:38:50 CEST 
//


package org.notariado.ancert.xml.plusvalias.liquidacion;


/**
 * Java content class for DireccionInmuebleType complex type.
 * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Sun/jwsdp-1.5/jaxb/bin/IIVTNU-WS-Ayto.xsd line 1187)
 * <p>
 * <pre>
 * &lt;complexType name="DireccionInmuebleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INE_PROVINCIA" type="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}CODIGO_PROVINCIAType"/>
 *         &lt;element name="INE_MUNICIPIO" type="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}CODIGO_MUNICIPIOType"/>
 *         &lt;element name="TIPO_VIA" type="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}TIPO_VIAType" minOccurs="0"/>
 *         &lt;element name="VIA" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}NOMBRE_VIAType">
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="NUMERO_VIA" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}NUMERO_VIAType">
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="ESCALERA" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}STRINGType">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="2"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="PLANTA" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}STRINGType">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="2"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="PUERTA" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}STRINGType">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="3"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="DUPLICADO" type="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}DUPLICADOType" minOccurs="0"/>
 *         &lt;element name="BLOQUE" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}STRINGType">
 *             &lt;maxLength value="4"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="APR_KM" type="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}KILOMETROType" minOccurs="0"/>
 *         &lt;element name="ENTIDAD_MENOR" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}STRINGType">
 *             &lt;maxLength value="15"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="RESTO" minOccurs="0">
 *           &lt;restriction base="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}STRINGType">
 *             &lt;maxLength value="25"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="CODIGO_POSTAL" type="{http://ancert.notariado.org/XML/Plusvalias/Liquidacion}CODIGO_POSTALType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface DireccionInmuebleType {


    /**
     * Gets the value of the planta property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPLANTA();

    /**
     * Sets the value of the planta property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPLANTA(java.lang.String value);

    /**
     * Gets the value of the numerovia property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getNUMEROVIA();

    /**
     * Sets the value of the numerovia property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setNUMEROVIA(java.lang.String value);

    /**
     * Gets the value of the escalera property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getESCALERA();

    /**
     * Sets the value of the escalera property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setESCALERA(java.lang.String value);

    /**
     * Resto de direcci�n no estructurada
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getRESTO();

    /**
     * Resto de direcci�n no estructurada
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setRESTO(java.lang.String value);

    /**
     * Bloque
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getBLOQUE();

    /**
     * Bloque
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setBLOQUE(java.lang.String value);

    /**
     * Gets the value of the codigopostal property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCODIGOPOSTAL();

    /**
     * Sets the value of the codigopostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCODIGOPOSTAL(java.lang.String value);

    /**
     * Codigo INE de Municipio. (incluye digito de control)
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getINEMUNICIPIO();

    /**
     * Codigo INE de Municipio. (incluye digito de control)
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setINEMUNICIPIO(java.lang.String value);

    /**
     * Gets the value of the tipovia property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTIPOVIA();

    /**
     * Sets the value of the tipovia property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTIPOVIA(java.lang.String value);

    /**
     * Entidad local menor o pedan�a
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getENTIDADMENOR();

    /**
     * Entidad local menor o pedan�a
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setENTIDADMENOR(java.lang.String value);

    /**
     * Aproximaci�n postal en kilometros
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getAPRKM();

    /**
     * Aproximaci�n postal en kilometros
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setAPRKM(java.lang.String value);

    /**
     * Gets the value of the via property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getVIA();

    /**
     * Sets the value of the via property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setVIA(java.lang.String value);

    /**
     * Codigo INE de Provincia
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getINEPROVINCIA();

    /**
     * Codigo INE de Provincia
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setINEPROVINCIA(java.lang.String value);

    /**
     * Gets the value of the puerta property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPUERTA();

    /**
     * Sets the value of the puerta property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPUERTA(java.lang.String value);

    /**
     * Duplicado ("D", "T", "C")
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getDUPLICADO();

    /**
     * Duplicado ("D", "T", "C")
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setDUPLICADO(java.lang.String value);

}

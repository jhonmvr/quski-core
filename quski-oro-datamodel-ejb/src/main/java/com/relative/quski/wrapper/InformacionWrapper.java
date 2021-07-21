//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.12.05 a las 12:32:40 PM COT 
//


package com.relative.quski.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="Identificacion" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="INFO_FINAN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TIPO_PAGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="INSTITUCION_FINANCIERA" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="TIPO_CUENTA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NUMERO_CUENTA" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="FIRMA_REGULARIZADA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CUENTA_NUEVA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codigoError",
    "mensaje",
    "identificacion",
    "infofinan"
})
@XmlRootElement(name = "Informacion")
public class InformacionWrapper {

    @XmlElement(name = "CodigoError")
    @XmlSchemaType(name = "unsignedByte")
    protected short codigoError;
    @XmlElement(name = "Mensaje", required = true)
    protected Object mensaje;
    @XmlElement(name = "Identificacion")
    @XmlSchemaType(name = "unsignedInt")
    protected long identificacion;
    @XmlElement(name = "INFO_FINAN", required = true)
    protected InformacionWrapper.INFOFINAN infofinan;

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     */
    public short getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     */
    public void setCodigoError(short value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMensaje(Object value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad identificacion.
     * 
     */
    public long getIdentificacion() {
        return identificacion;
    }

    /**
     * Define el valor de la propiedad identificacion.
     * 
     */
    public void setIdentificacion(long value) {
        this.identificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad infofinan.
     * 
     * @return
     *     possible object is
     *     {@link InformacionWrapper.INFOFINAN }
     *     
     */
    public InformacionWrapper.INFOFINAN getINFOFINAN() {
        return infofinan;
    }

    /**
     * Define el valor de la propiedad infofinan.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionWrapper.INFOFINAN }
     *     
     */
    public void setINFOFINAN(InformacionWrapper.INFOFINAN value) {
        this.infofinan = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TIPO_PAGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="INSTITUCION_FINANCIERA" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="TIPO_CUENTA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NUMERO_CUENTA" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="FIRMA_REGULARIZADA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CUENTA_NUEVA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tipopago",
        "institucionfinanciera",
        "tipocuenta",
        "numerocuenta",
        "firmaregularizada",
        "cuentanueva"
    })
    public static class INFOFINAN {

        @XmlElement(name = "TIPO_PAGO", required = true)
        protected String tipopago;
        @XmlElement(name = "INSTITUCION_FINANCIERA")
        @XmlSchemaType(name = "unsignedByte")
        protected short institucionfinanciera;
        @XmlElement(name = "TIPO_CUENTA", required = true)
        protected String tipocuenta;
        @XmlElement(name = "NUMERO_CUENTA")
        @XmlSchemaType(name = "unsignedInt")
        protected long numerocuenta;
        @XmlElement(name = "FIRMA_REGULARIZADA", required = true)
        protected String firmaregularizada;
        @XmlElement(name = "CUENTA_NUEVA", required = true)
        protected String cuentanueva;

        /**
         * Obtiene el valor de la propiedad tipopago.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTIPOPAGO() {
            return tipopago;
        }

        /**
         * Define el valor de la propiedad tipopago.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTIPOPAGO(String value) {
            this.tipopago = value;
        }

        /**
         * Obtiene el valor de la propiedad institucionfinanciera.
         * 
         */
        public short getINSTITUCIONFINANCIERA() {
            return institucionfinanciera;
        }

        /**
         * Define el valor de la propiedad institucionfinanciera.
         * 
         */
        public void setINSTITUCIONFINANCIERA(short value) {
            this.institucionfinanciera = value;
        }

        /**
         * Obtiene el valor de la propiedad tipocuenta.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTIPOCUENTA() {
            return tipocuenta;
        }

        /**
         * Define el valor de la propiedad tipocuenta.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTIPOCUENTA(String value) {
            this.tipocuenta = value;
        }

        /**
         * Obtiene el valor de la propiedad numerocuenta.
         * 
         */
        public long getNUMEROCUENTA() {
            return numerocuenta;
        }

        /**
         * Define el valor de la propiedad numerocuenta.
         * 
         */
        public void setNUMEROCUENTA(long value) {
            this.numerocuenta = value;
        }

        /**
         * Obtiene el valor de la propiedad firmaregularizada.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFIRMAREGULARIZADA() {
            return firmaregularizada;
        }

        /**
         * Define el valor de la propiedad firmaregularizada.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFIRMAREGULARIZADA(String value) {
            this.firmaregularizada = value;
        }
        /**
         * Obtiene el valor de la propiedad cuentanueva.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCUENTANUEVA() {
            return cuentanueva;
        }

        /**
         * Define el valor de la propiedad cuentanueva.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCUENTANUEVA(String value) {
            this.cuentanueva = value;
        }

    }

}

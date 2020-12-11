//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.12.11 a las 10:26:19 AM COT 
//


package com.relative.quski.wrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="SimularResult">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="XmlOfertasSimuladas">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="OfertasSimuladas">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Opcion" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                                                 &lt;element name="MontoCredito" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                                                 &lt;element name="RiesgoAcumulado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                                                 &lt;element name="ValorDesembolso" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                                                 &lt;element name="Cuota" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="NumeroOperacionMadre" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
    "simularResult"
})
@XmlRootElement(name = "SimularResponse")
public class SimularResponseExcepcion {

    @XmlElement(name = "SimularResult", required = true)
    protected SimularResponseExcepcion.SimularResult simularResult;

    /**
     * Obtiene el valor de la propiedad simularResult.
     * 
     * @return
     *     possible object is
     *     {@link SimularResponseExcepcion.SimularResult }
     *     
     */
    public SimularResponseExcepcion.SimularResult getSimularResult() {
        return simularResult;
    }

    /**
     * Define el valor de la propiedad simularResult.
     * 
     * @param value
     *     allowed object is
     *     {@link SimularResponseExcepcion.SimularResult }
     *     
     */
    public void setSimularResult(SimularResponseExcepcion.SimularResult value) {
        this.simularResult = value;
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
     *         &lt;element name="XmlOfertasSimuladas">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="OfertasSimuladas">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Opcion" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *                                       &lt;element name="MontoCredito" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                                       &lt;element name="RiesgoAcumulado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                                       &lt;element name="ValorDesembolso" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                                       &lt;element name="Cuota" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="NumeroOperacionMadre" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
        "xmlOfertasSimuladas",
        "codigoError",
        "mensaje",
        "numeroOperacionMadre"
    })
    public static class SimularResult {

        @XmlElement(name = "XmlOfertasSimuladas", required = true)
        protected SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas xmlOfertasSimuladas;
        @XmlElement(name = "CodigoError")
        @XmlSchemaType(name = "unsignedByte")
        protected short codigoError;
        @XmlElement(name = "Mensaje", required = true)
        protected Object mensaje;
        @XmlElement(name = "NumeroOperacionMadre", required = true)
        protected Object numeroOperacionMadre;

        /**
         * Obtiene el valor de la propiedad xmlOfertasSimuladas.
         * 
         * @return
         *     possible object is
         *     {@link SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas }
         *     
         */
        public SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas getXmlOfertasSimuladas() {
            return xmlOfertasSimuladas;
        }

        /**
         * Define el valor de la propiedad xmlOfertasSimuladas.
         * 
         * @param value
         *     allowed object is
         *     {@link SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas }
         *     
         */
        public void setXmlOfertasSimuladas(SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas value) {
            this.xmlOfertasSimuladas = value;
        }

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
         * Obtiene el valor de la propiedad numeroOperacionMadre.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getNumeroOperacionMadre() {
            return numeroOperacionMadre;
        }

        /**
         * Define el valor de la propiedad numeroOperacionMadre.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setNumeroOperacionMadre(Object value) {
            this.numeroOperacionMadre = value;
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
         *         &lt;element name="OfertasSimuladas">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Opcion" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
         *                             &lt;element name="MontoCredito" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *                             &lt;element name="RiesgoAcumulado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *                             &lt;element name="ValorDesembolso" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *                             &lt;element name="Cuota" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
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
            "ofertasSimuladas"
        })
        public static class XmlOfertasSimuladas {

            @XmlElement(name = "OfertasSimuladas", required = true)
            protected SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas ofertasSimuladas;

            /**
             * Obtiene el valor de la propiedad ofertasSimuladas.
             * 
             * @return
             *     possible object is
             *     {@link SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas }
             *     
             */
            public SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas getOfertasSimuladas() {
                return ofertasSimuladas;
            }

            /**
             * Define el valor de la propiedad ofertasSimuladas.
             * 
             * @param value
             *     allowed object is
             *     {@link SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas }
             *     
             */
            public void setOfertasSimuladas(SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas value) {
                this.ofertasSimuladas = value;
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
             *         &lt;element name="Opcion" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
             *                   &lt;element name="MontoCredito" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
             *                   &lt;element name="RiesgoAcumulado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
             *                   &lt;element name="ValorDesembolso" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
             *                   &lt;element name="Cuota" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
                "opcion"
            })
            public static class OfertasSimuladas {

                @XmlElement(name = "Opcion", required = true)
                protected List<SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas.Opcion> opcion;

                /**
                 * Gets the value of the opcion property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the opcion property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getOpcion().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas.Opcion }
                 * 
                 * 
                 */
                public List<SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas.Opcion> getOpcion() {
                    if (opcion == null) {
                        opcion = new ArrayList<SimularResponseExcepcion.SimularResult.XmlOfertasSimuladas.OfertasSimuladas.Opcion>();
                    }
                    return this.opcion;
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
                 *         &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
                 *         &lt;element name="MontoCredito" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
                 *         &lt;element name="RiesgoAcumulado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
                 *         &lt;element name="ValorDesembolso" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
                 *         &lt;element name="Cuota" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
                    "plazo",
                    "montoCredito",
                    "riesgoAcumulado",
                    "valorDesembolso",
                    "cuota"
                })
                public static class Opcion {

                    @XmlElement(name = "Plazo")
                    @XmlSchemaType(name = "unsignedByte")
                    protected short plazo;
                    @XmlElement(name = "MontoCredito", required = true)
                    protected BigDecimal montoCredito;
                    @XmlElement(name = "RiesgoAcumulado", required = true)
                    protected BigDecimal riesgoAcumulado;
                    @XmlElement(name = "ValorDesembolso", required = true)
                    protected BigDecimal valorDesembolso;
                    @XmlElement(name = "Cuota", required = true)
                    protected BigDecimal cuota;

                    /**
                     * Obtiene el valor de la propiedad plazo.
                     * 
                     */
                    public short getPlazo() {
                        return plazo;
                    }

                    /**
                     * Define el valor de la propiedad plazo.
                     * 
                     */
                    public void setPlazo(short value) {
                        this.plazo = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad montoCredito.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getMontoCredito() {
                        return montoCredito;
                    }

                    /**
                     * Define el valor de la propiedad montoCredito.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setMontoCredito(BigDecimal value) {
                        this.montoCredito = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad riesgoAcumulado.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getRiesgoAcumulado() {
                        return riesgoAcumulado;
                    }

                    /**
                     * Define el valor de la propiedad riesgoAcumulado.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setRiesgoAcumulado(BigDecimal value) {
                        this.riesgoAcumulado = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad valorDesembolso.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getValorDesembolso() {
                        return valorDesembolso;
                    }

                    /**
                     * Define el valor de la propiedad valorDesembolso.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setValorDesembolso(BigDecimal value) {
                        this.valorDesembolso = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad cuota.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getCuota() {
                        return cuota;
                    }

                    /**
                     * Define el valor de la propiedad cuota.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setCuota(BigDecimal value) {
                        this.cuota = value;
                    }

                }

            }

        }

    }

}

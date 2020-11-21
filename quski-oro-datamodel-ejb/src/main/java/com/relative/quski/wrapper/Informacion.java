//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.05.25 a las 08:33:55 PM ECT 
//


package com.relative.quski.wrapper;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="Identificacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DATOS_CLIENTE">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ES_RENOVACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CODIGO_CAMPANIA" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="IDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="TIPO_IDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PERFIL_RIESGO" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="PERFIL_PREFERENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NOMBRES_COMPLETOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ESTADO_CIVIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NIVEL_EDUCACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FECHA_NACIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LUGAR_NACIMIENTO" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="NACIONALIDAD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="INDICE_DACTILAR_CEDULA" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="GENERO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ORIGEN_INGRESOS" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="TIPO_VIVIENDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UBICACION" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="CORREO_ELECTRONICO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ACTIVIDAD_ECONOMICA_VENTA" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="ACTIVIDAD_ECONOMICA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ACTIVIDAD_ECONOMICA_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="OCUPACION" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="RELACION_DEPENDENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PROFESION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CARGAS_FAMILIARES" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="TELEFONO_FIJO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TELEFONO_MOVIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SCRIPT_AGENDAMIENTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="USUARIO_AGENDAMIENTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GESTION_EN_AGENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="INGRESOS_EGRESOS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RUBRO" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TIPO_RUBRO_ECONOMICO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="VALOR" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="tagGrupo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="XmlVariablesInternas">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="VariablesInternas">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Variable" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Orden" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                                       &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ejecucion" type="{http://www.w3.org/2001/XMLSchema}byte"/>
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
    "identificacion",
    "datoscliente",
    "ingresosegresos",
    "xmlVariablesInternas",
    "codigoError",
    "mensaje",
    "ejecucion"
})
@XmlRootElement(name = "Informacion")
public class Informacion implements Serializable{

    @XmlElement(name = "Identificacion")
    protected int identificacion;
    @XmlElement(name = "DATOS_CLIENTE", required = true)
    protected Informacion.DATOSCLIENTE datoscliente;
    @XmlElement(name = "INGRESOS_EGRESOS", required = true)
    protected Informacion.INGRESOSEGRESOS ingresosegresos;
    @XmlElement(name = "XmlVariablesInternas", required = true)
    protected Informacion.XmlVariablesInternas xmlVariablesInternas;
    @XmlElement(name = "CodigoError")
    protected byte codigoError;
    @XmlElement(name = "Mensaje", required = true)
    protected String mensaje;
    @XmlElement(name = "Ejecucion")
    protected byte ejecucion;

    /**
     * Obtiene el valor de la propiedad identificacion.
     * 
     */
    public int getIdentificacion() {
        return identificacion;
    }

    /**
     * Define el valor de la propiedad identificacion.
     * 
     */
    public void setIdentificacion(int value) {
        this.identificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad datoscliente.
     * 
     * @return
     *     possible object is
     *     {@link Informacion.DATOSCLIENTE }
     *     
     */
    public Informacion.DATOSCLIENTE getDATOSCLIENTE() {
        return datoscliente;
    }

    /**
     * Define el valor de la propiedad datoscliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Informacion.DATOSCLIENTE }
     *     
     */
    public void setDATOSCLIENTE(Informacion.DATOSCLIENTE value) {
        this.datoscliente = value;
    }

    /**
     * Obtiene el valor de la propiedad ingresosegresos.
     * 
     * @return
     *     possible object is
     *     {@link Informacion.INGRESOSEGRESOS }
     *     
     */
    public Informacion.INGRESOSEGRESOS getINGRESOSEGRESOS() {
        return ingresosegresos;
    }

    /**
     * Define el valor de la propiedad ingresosegresos.
     * 
     * @param value
     *     allowed object is
     *     {@link Informacion.INGRESOSEGRESOS }
     *     
     */
    public void setINGRESOSEGRESOS(Informacion.INGRESOSEGRESOS value) {
        this.ingresosegresos = value;
    }

    /**
     * Obtiene el valor de la propiedad xmlVariablesInternas.
     * 
     * @return
     *     possible object is
     *     {@link Informacion.XmlVariablesInternas }
     *     
     */
    public Informacion.XmlVariablesInternas getXmlVariablesInternas() {
        return xmlVariablesInternas;
    }

    /**
     * Define el valor de la propiedad xmlVariablesInternas.
     * 
     * @param value
     *     allowed object is
     *     {@link Informacion.XmlVariablesInternas }
     *     
     */
    public void setXmlVariablesInternas(Informacion.XmlVariablesInternas value) {
        this.xmlVariablesInternas = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     */
    public byte getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     */
    public void setCodigoError(byte value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad ejecucion.
     * 
     */
    public byte getEjecucion() {
        return ejecucion;
    }

    /**
     * Define el valor de la propiedad ejecucion.
     * 
     */
    public void setEjecucion(byte value) {
        this.ejecucion = value;
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
     *         &lt;element name="ES_RENOVACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CODIGO_CAMPANIA" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *         &lt;element name="IDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="TIPO_IDENTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PERFIL_RIESGO" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="PERFIL_PREFERENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NOMBRES_COMPLETOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ESTADO_CIVIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NIVEL_EDUCACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FECHA_NACIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LUGAR_NACIMIENTO" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *         &lt;element name="NACIONALIDAD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="INDICE_DACTILAR_CEDULA" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="GENERO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ORIGEN_INGRESOS" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="TIPO_VIVIENDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="UBICACION" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="CORREO_ELECTRONICO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ACTIVIDAD_ECONOMICA_VENTA" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *         &lt;element name="ACTIVIDAD_ECONOMICA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ACTIVIDAD_ECONOMICA_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *         &lt;element name="OCUPACION" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="RELACION_DEPENDENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PROFESION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CARGAS_FAMILIARES" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="TELEFONO_FIJO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TELEFONO_MOVIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SCRIPT_AGENDAMIENTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="USUARIO_AGENDAMIENTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GESTION_EN_AGENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "esrenovacion",
        "codigocampania",
        "identificacion",
        "tipoidentificacion",
        "perfilriesgo",
        "perfilpreferencia",
        "nombrescompletos",
        "estadocivil",
        "niveleducacion",
        "fechanacimiento",
        "lugarnacimiento",
        "nacionalidad",
        "indicedactilarcedula",
        "genero",
        "origeningresos",
        "tipovivienda",
        "ubicacion",
        "correoelectronico",
        "actividadeconomicaventa",
        "actividadeconomica",
        "actividadeconomicaempresa",
        "ocupacion",
        "relaciondependencia",
        "cargo",
        "profesion",
        "cargasfamiliares",
        "telefonofijo",
        "telefonomovil",
        "scriptagendamiento",
        "usuarioagendamiento",
        "gestionenagencia"
    })
    public static class DATOSCLIENTE {

        @XmlElement(name = "ES_RENOVACION", required = true)
        protected String esrenovacion;
        @XmlElement(name = "CODIGO_CAMPANIA")
        protected short codigocampania;
        @XmlElement(name = "IDENTIFICACION")
        protected int identificacion;
        @XmlElement(name = "TIPO_IDENTIFICACION", required = true)
        protected String tipoidentificacion;
        @XmlElement(name = "PERFIL_RIESGO")
        protected byte perfilriesgo;
        @XmlElement(name = "PERFIL_PREFERENCIA", required = true)
        protected String perfilpreferencia;
        @XmlElement(name = "NOMBRES_COMPLETOS", required = true)
        protected String nombrescompletos;
        @XmlElement(name = "ESTADO_CIVIL", required = true)
        protected String estadocivil;
        @XmlElement(name = "NIVEL_EDUCACION", required = true)
        protected String niveleducacion;
        @XmlElement(name = "FECHA_NACIMIENTO", required = true)
        protected String fechanacimiento;
        @XmlElement(name = "LUGAR_NACIMIENTO")
        protected short lugarnacimiento;
        @XmlElement(name = "NACIONALIDAD", required = true)
        protected String nacionalidad;
        @XmlElement(name = "INDICE_DACTILAR_CEDULA")
        protected byte indicedactilarcedula;
        @XmlElement(name = "GENERO", required = true)
        protected String genero;
        @XmlElement(name = "ORIGEN_INGRESOS")
        protected byte origeningresos;
        @XmlElement(name = "TIPO_VIVIENDA", required = true)
        protected String tipovivienda;
        @XmlElement(name = "UBICACION")
        protected int ubicacion;
        @XmlElement(name = "CORREO_ELECTRONICO", required = true)
        protected String correoelectronico;
        @XmlElement(name = "ACTIVIDAD_ECONOMICA_VENTA")
        protected short actividadeconomicaventa;
        @XmlElement(name = "ACTIVIDAD_ECONOMICA", required = true)
        protected String actividadeconomica;
        @XmlElement(name = "ACTIVIDAD_ECONOMICA_EMPRESA")
        protected short actividadeconomicaempresa;
        @XmlElement(name = "OCUPACION")
        protected byte ocupacion;
        @XmlElement(name = "RELACION_DEPENDENCIA", required = true)
        protected String relaciondependencia;
        @XmlElement(name = "CARGO", required = true)
        protected String cargo;
        @XmlElement(name = "PROFESION", required = true)
        protected String profesion;
        @XmlElement(name = "CARGAS_FAMILIARES")
        protected byte cargasfamiliares;
        @XmlElement(name = "TELEFONO_FIJO", required = true)
        protected String telefonofijo;
        @XmlElement(name = "TELEFONO_MOVIL", required = true)
        protected String telefonomovil;
        @XmlElement(name = "SCRIPT_AGENDAMIENTO", required = true)
        protected String scriptagendamiento;
        @XmlElement(name = "USUARIO_AGENDAMIENTO", required = true)
        protected String usuarioagendamiento;
        @XmlElement(name = "GESTION_EN_AGENCIA", required = true)
        protected String gestionenagencia;

        /**
         * Obtiene el valor de la propiedad esrenovacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getESRENOVACION() {
            return esrenovacion;
        }

        /**
         * Define el valor de la propiedad esrenovacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setESRENOVACION(String value) {
            this.esrenovacion = value;
        }

        /**
         * Obtiene el valor de la propiedad codigocampania.
         * 
         */
        public short getCODIGOCAMPANIA() {
            return codigocampania;
        }

        /**
         * Define el valor de la propiedad codigocampania.
         * 
         */
        public void setCODIGOCAMPANIA(short value) {
            this.codigocampania = value;
        }

        /**
         * Obtiene el valor de la propiedad identificacion.
         * 
         */
        public int getIDENTIFICACION() {
            return identificacion;
        }

        /**
         * Define el valor de la propiedad identificacion.
         * 
         */
        public void setIDENTIFICACION(int value) {
            this.identificacion = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoidentificacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTIPOIDENTIFICACION() {
            return tipoidentificacion;
        }

        /**
         * Define el valor de la propiedad tipoidentificacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTIPOIDENTIFICACION(String value) {
            this.tipoidentificacion = value;
        }

        /**
         * Obtiene el valor de la propiedad perfilriesgo.
         * 
         */
        public byte getPERFILRIESGO() {
            return perfilriesgo;
        }

        /**
         * Define el valor de la propiedad perfilriesgo.
         * 
         */
        public void setPERFILRIESGO(byte value) {
            this.perfilriesgo = value;
        }

        /**
         * Obtiene el valor de la propiedad perfilpreferencia.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPERFILPREFERENCIA() {
            return perfilpreferencia;
        }

        /**
         * Define el valor de la propiedad perfilpreferencia.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPERFILPREFERENCIA(String value) {
            this.perfilpreferencia = value;
        }

        /**
         * Obtiene el valor de la propiedad nombrescompletos.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNOMBRESCOMPLETOS() {
            return nombrescompletos;
        }

        /**
         * Define el valor de la propiedad nombrescompletos.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNOMBRESCOMPLETOS(String value) {
            this.nombrescompletos = value;
        }

        /**
         * Obtiene el valor de la propiedad estadocivil.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getESTADOCIVIL() {
            return estadocivil;
        }

        /**
         * Define el valor de la propiedad estadocivil.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setESTADOCIVIL(String value) {
            this.estadocivil = value;
        }

        /**
         * Obtiene el valor de la propiedad niveleducacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNIVELEDUCACION() {
            return niveleducacion;
        }

        /**
         * Define el valor de la propiedad niveleducacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNIVELEDUCACION(String value) {
            this.niveleducacion = value;
        }

        /**
         * Obtiene el valor de la propiedad fechanacimiento.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFECHANACIMIENTO() {
            return fechanacimiento;
        }

        /**
         * Define el valor de la propiedad fechanacimiento.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFECHANACIMIENTO(String value) {
            this.fechanacimiento = value;
        }

        /**
         * Obtiene el valor de la propiedad lugarnacimiento.
         * 
         */
        public short getLUGARNACIMIENTO() {
            return lugarnacimiento;
        }

        /**
         * Define el valor de la propiedad lugarnacimiento.
         * 
         */
        public void setLUGARNACIMIENTO(short value) {
            this.lugarnacimiento = value;
        }

        /**
         * Obtiene el valor de la propiedad nacionalidad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNACIONALIDAD() {
            return nacionalidad;
        }

        /**
         * Define el valor de la propiedad nacionalidad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNACIONALIDAD(String value) {
            this.nacionalidad = value;
        }

        /**
         * Obtiene el valor de la propiedad indicedactilarcedula.
         * 
         */
        public byte getINDICEDACTILARCEDULA() {
            return indicedactilarcedula;
        }

        /**
         * Define el valor de la propiedad indicedactilarcedula.
         * 
         */
        public void setINDICEDACTILARCEDULA(byte value) {
            this.indicedactilarcedula = value;
        }

        /**
         * Obtiene el valor de la propiedad genero.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGENERO() {
            return genero;
        }

        /**
         * Define el valor de la propiedad genero.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGENERO(String value) {
            this.genero = value;
        }

        /**
         * Obtiene el valor de la propiedad origeningresos.
         * 
         */
        public byte getORIGENINGRESOS() {
            return origeningresos;
        }

        /**
         * Define el valor de la propiedad origeningresos.
         * 
         */
        public void setORIGENINGRESOS(byte value) {
            this.origeningresos = value;
        }

        /**
         * Obtiene el valor de la propiedad tipovivienda.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTIPOVIVIENDA() {
            return tipovivienda;
        }

        /**
         * Define el valor de la propiedad tipovivienda.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTIPOVIVIENDA(String value) {
            this.tipovivienda = value;
        }

        /**
         * Obtiene el valor de la propiedad ubicacion.
         * 
         */
        public int getUBICACION() {
            return ubicacion;
        }

        /**
         * Define el valor de la propiedad ubicacion.
         * 
         */
        public void setUBICACION(int value) {
            this.ubicacion = value;
        }

        /**
         * Obtiene el valor de la propiedad correoelectronico.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCORREOELECTRONICO() {
            return correoelectronico;
        }

        /**
         * Define el valor de la propiedad correoelectronico.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCORREOELECTRONICO(String value) {
            this.correoelectronico = value;
        }

        /**
         * Obtiene el valor de la propiedad actividadeconomicaventa.
         * 
         */
        public short getACTIVIDADECONOMICAVENTA() {
            return actividadeconomicaventa;
        }

        /**
         * Define el valor de la propiedad actividadeconomicaventa.
         * 
         */
        public void setACTIVIDADECONOMICAVENTA(short value) {
            this.actividadeconomicaventa = value;
        }

        /**
         * Obtiene el valor de la propiedad actividadeconomica.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getACTIVIDADECONOMICA() {
            return actividadeconomica;
        }

        /**
         * Define el valor de la propiedad actividadeconomica.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setACTIVIDADECONOMICA(String value) {
            this.actividadeconomica = value;
        }

        /**
         * Obtiene el valor de la propiedad actividadeconomicaempresa.
         * 
         */
        public short getACTIVIDADECONOMICAEMPRESA() {
            return actividadeconomicaempresa;
        }

        /**
         * Define el valor de la propiedad actividadeconomicaempresa.
         * 
         */
        public void setACTIVIDADECONOMICAEMPRESA(short value) {
            this.actividadeconomicaempresa = value;
        }

        /**
         * Obtiene el valor de la propiedad ocupacion.
         * 
         */
        public byte getOCUPACION() {
            return ocupacion;
        }

        /**
         * Define el valor de la propiedad ocupacion.
         * 
         */
        public void setOCUPACION(byte value) {
            this.ocupacion = value;
        }

        /**
         * Obtiene el valor de la propiedad relaciondependencia.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRELACIONDEPENDENCIA() {
            return relaciondependencia;
        }

        /**
         * Define el valor de la propiedad relaciondependencia.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRELACIONDEPENDENCIA(String value) {
            this.relaciondependencia = value;
        }

        /**
         * Obtiene el valor de la propiedad cargo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCARGO() {
            return cargo;
        }

        /**
         * Define el valor de la propiedad cargo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCARGO(String value) {
            this.cargo = value;
        }

        /**
         * Obtiene el valor de la propiedad profesion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPROFESION() {
            return profesion;
        }

        /**
         * Define el valor de la propiedad profesion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPROFESION(String value) {
            this.profesion = value;
        }

        /**
         * Obtiene el valor de la propiedad cargasfamiliares.
         * 
         */
        public byte getCARGASFAMILIARES() {
            return cargasfamiliares;
        }

        /**
         * Define el valor de la propiedad cargasfamiliares.
         * 
         */
        public void setCARGASFAMILIARES(byte value) {
            this.cargasfamiliares = value;
        }

        /**
         * Obtiene el valor de la propiedad telefonofijo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTELEFONOFIJO() {
            return telefonofijo;
        }

        /**
         * Define el valor de la propiedad telefonofijo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTELEFONOFIJO(String value) {
            this.telefonofijo = value;
        }

        /**
         * Obtiene el valor de la propiedad telefonomovil.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTELEFONOMOVIL() {
            return telefonomovil;
        }

        /**
         * Define el valor de la propiedad telefonomovil.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTELEFONOMOVIL(String value) {
            this.telefonomovil = value;
        }

        /**
         * Obtiene el valor de la propiedad scriptagendamiento.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSCRIPTAGENDAMIENTO() {
            return scriptagendamiento;
        }

        /**
         * Define el valor de la propiedad scriptagendamiento.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSCRIPTAGENDAMIENTO(String value) {
            this.scriptagendamiento = value;
        }

        /**
         * Obtiene el valor de la propiedad usuarioagendamiento.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUSUARIOAGENDAMIENTO() {
            return usuarioagendamiento;
        }

        /**
         * Define el valor de la propiedad usuarioagendamiento.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUSUARIOAGENDAMIENTO(String value) {
            this.usuarioagendamiento = value;
        }

        /**
         * Obtiene el valor de la propiedad gestionenagencia.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGESTIONENAGENCIA() {
            return gestionenagencia;
        }

        /**
         * Define el valor de la propiedad gestionenagencia.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGESTIONENAGENCIA(String value) {
            this.gestionenagencia = value;
        }

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
     *         &lt;element name="RUBRO" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TIPO_RUBRO_ECONOMICO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="VALOR" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="tagGrupo" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "rubro"
    })
    public static class INGRESOSEGRESOS {

        @XmlElement(name = "RUBRO")
        protected List<Informacion.INGRESOSEGRESOS.RUBRO> rubro;

        /**
         * Gets the value of the rubro property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rubro property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRUBRO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Informacion.INGRESOSEGRESOS.RUBRO }
         * 
         * 
         */
        public List<Informacion.INGRESOSEGRESOS.RUBRO> getRUBRO() {
            if (rubro == null) {
                rubro = new ArrayList<Informacion.INGRESOSEGRESOS.RUBRO>();
            }
            return this.rubro;
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
         *         &lt;element name="TIPO_RUBRO_ECONOMICO" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="VALOR" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *       &lt;/sequence>
         *       &lt;attribute name="tagGrupo" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "tiporubroeconomico",
            "valor"
        })
        public static class RUBRO {

            @XmlElement(name = "TIPO_RUBRO_ECONOMICO", required = true)
            protected String tiporubroeconomico;
            @XmlElement(name = "VALOR")
            protected float valor;
            @XmlAttribute(name = "tagGrupo")
            protected String tagGrupo;

            /**
             * Obtiene el valor de la propiedad tiporubroeconomico.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTIPORUBROECONOMICO() {
                return tiporubroeconomico;
            }

            /**
             * Define el valor de la propiedad tiporubroeconomico.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTIPORUBROECONOMICO(String value) {
                this.tiporubroeconomico = value;
            }

            /**
             * Obtiene el valor de la propiedad valor.
             * 
             */
            public float getVALOR() {
                return valor;
            }

            /**
             * Define el valor de la propiedad valor.
             * 
             */
            public void setVALOR(float value) {
                this.valor = value;
            }

            /**
             * Obtiene el valor de la propiedad tagGrupo.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTagGrupo() {
                return tagGrupo;
            }

            /**
             * Define el valor de la propiedad tagGrupo.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTagGrupo(String value) {
                this.tagGrupo = value;
            }

        }

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
     *         &lt;element name="VariablesInternas">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Variable" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Orden" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *                             &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="Valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "variablesInternas"
    })
    public static class XmlVariablesInternas {

        @XmlElement(name = "VariablesInternas", required = true)
        protected Informacion.XmlVariablesInternas.VariablesInternas variablesInternas;

        /**
         * Obtiene el valor de la propiedad variablesInternas.
         * 
         * @return
         *     possible object is
         *     {@link Informacion.XmlVariablesInternas.VariablesInternas }
         *     
         */
        public Informacion.XmlVariablesInternas.VariablesInternas getVariablesInternas() {
            return variablesInternas;
        }

        /**
         * Define el valor de la propiedad variablesInternas.
         * 
         * @param value
         *     allowed object is
         *     {@link Informacion.XmlVariablesInternas.VariablesInternas }
         *     
         */
        public void setVariablesInternas(Informacion.XmlVariablesInternas.VariablesInternas value) {
            this.variablesInternas = value;
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
         *         &lt;element name="Variable" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Orden" type="{http://www.w3.org/2001/XMLSchema}byte"/>
         *                   &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="Valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "variable"
        })
        public static class VariablesInternas {

            @XmlElement(name = "Variable")
            protected List<Informacion.XmlVariablesInternas.VariablesInternas.Variable> variable;

            /**
             * Gets the value of the variable property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the variable property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getVariable().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Informacion.XmlVariablesInternas.VariablesInternas.Variable }
             * 
             * 
             */
            public List<Informacion.XmlVariablesInternas.VariablesInternas.Variable> getVariable() {
                if (variable == null) {
                    variable = new ArrayList<Informacion.XmlVariablesInternas.VariablesInternas.Variable>();
                }
                return this.variable;
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
             *         &lt;element name="Orden" type="{http://www.w3.org/2001/XMLSchema}byte"/>
             *         &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="Valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "orden",
                "codigo",
                "nombre",
                "valor"
            })
            public static class Variable {

                @XmlElement(name = "Orden")
                protected byte orden;
                @XmlElement(name = "Codigo", required = true)
                protected String codigo;
                @XmlElement(name = "Nombre", required = true)
                protected String nombre;
                @XmlElement(name = "Valor", required = true)
                protected String valor;

                /**
                 * Obtiene el valor de la propiedad orden.
                 * 
                 */
                public byte getOrden() {
                    return orden;
                }

                /**
                 * Define el valor de la propiedad orden.
                 * 
                 */
                public void setOrden(byte value) {
                    this.orden = value;
                }

                /**
                 * Obtiene el valor de la propiedad codigo.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCodigo() {
                    return codigo;
                }

                /**
                 * Define el valor de la propiedad codigo.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCodigo(String value) {
                    this.codigo = value;
                }

                /**
                 * Obtiene el valor de la propiedad nombre.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getNombre() {
                    return nombre;
                }

                /**
                 * Define el valor de la propiedad nombre.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setNombre(String value) {
                    this.nombre = value;
                }

                /**
                 * Obtiene el valor de la propiedad valor.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValor() {
                    return valor;
                }

                /**
                 * Define el valor de la propiedad valor.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValor(String value) {
                    this.valor = value;
                }

            }

        }

    }

}

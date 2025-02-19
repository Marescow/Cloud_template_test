//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "headerOrMessageOrRequestOrResponse",
    "dsSignature"
})
@XmlRootElement(name = "cXML")
public class CXML {

    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String version;
    @XmlAttribute(name = "payloadID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String payloadID;
    @XmlAttribute(name = "timestamp", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String timestamp;
    @XmlAttribute(name = "signatureVersion")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String signatureVersion;
    @XmlAttribute(name = "xml:lang")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlLang;
    @XmlElements({
        @XmlElement(name = "Header", required = true, type = Header.class),
        @XmlElement(name = "Message", required = true, type = Message.class),
        @XmlElement(name = "Request", required = true, type = Request.class),
        @XmlElement(name = "Response", required = true, type = Response.class)
    })
    protected List<Object> headerOrMessageOrRequestOrResponse;
    @XmlElement(name = "ds:Signature")
    protected List<DsSignature> dsSignature;

    /**
     * Obtient la valeur de la propriété version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "1.2.051";
        } else {
            return version;
        }
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propriété payloadID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayloadID() {
        return payloadID;
    }

    /**
     * Définit la valeur de la propriété payloadID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayloadID(String value) {
        this.payloadID = value;
    }

    /**
     * Obtient la valeur de la propriété timestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Définit la valeur de la propriété timestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Obtient la valeur de la propriété signatureVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureVersion() {
        return signatureVersion;
    }

    /**
     * Définit la valeur de la propriété signatureVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureVersion(String value) {
        this.signatureVersion = value;
    }

    /**
     * Obtient la valeur de la propriété xmlLang.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlLang() {
        return xmlLang;
    }

    /**
     * Définit la valeur de la propriété xmlLang.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlLang(String value) {
        this.xmlLang = value;
    }

    /**
     * Gets the value of the headerOrMessageOrRequestOrResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the headerOrMessageOrRequestOrResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeaderOrMessageOrRequestOrResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Header }
     * {@link Message }
     * {@link Request }
     * {@link Response }
     * 
     * 
     */
    public List<Object> getHeaderOrMessageOrRequestOrResponse() {
        if (headerOrMessageOrRequestOrResponse == null) {
            headerOrMessageOrRequestOrResponse = new ArrayList<Object>();
        }
        return this.headerOrMessageOrRequestOrResponse;
    }

    /**
     * Gets the value of the dsSignature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dsSignature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDsSignature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DsSignature }
     * 
     * 
     */
    public List<DsSignature> getDsSignature() {
        if (dsSignature == null) {
            dsSignature = new ArrayList<DsSignature>();
        }
        return this.dsSignature;
    }

}

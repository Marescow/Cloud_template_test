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
import javax.xml.bind.annotation.XmlID;
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
    "dsSignedInfo",
    "dsSignatureValue",
    "dsKeyInfo",
    "dsObject"
})
@XmlRootElement(name = "ds:Signature")
public class DsSignature {

    @XmlAttribute(name = "xmlns:ds")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlnsDs;
    @XmlAttribute(name = "xmlns:xades")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlnsXades;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "ds:SignedInfo", required = true)
    protected DsSignedInfo dsSignedInfo;
    @XmlElement(name = "ds:SignatureValue", required = true)
    protected DsSignatureValue dsSignatureValue;
    @XmlElement(name = "ds:KeyInfo")
    protected DsKeyInfo dsKeyInfo;
    @XmlElement(name = "ds:Object")
    protected List<DsObject> dsObject;

    /**
     * Obtient la valeur de la propriété xmlnsDs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlnsDs() {
        if (xmlnsDs == null) {
            return "http://www.w3.org/2000/09/xmldsig#";
        } else {
            return xmlnsDs;
        }
    }

    /**
     * Définit la valeur de la propriété xmlnsDs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlnsDs(String value) {
        this.xmlnsDs = value;
    }

    /**
     * Obtient la valeur de la propriété xmlnsXades.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlnsXades() {
        if (xmlnsXades == null) {
            return "http://uri.etsi.org/01903/v1.3.2#";
        } else {
            return xmlnsXades;
        }
    }

    /**
     * Définit la valeur de la propriété xmlnsXades.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlnsXades(String value) {
        this.xmlnsXades = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété dsSignedInfo.
     * 
     * @return
     *     possible object is
     *     {@link DsSignedInfo }
     *     
     */
    public DsSignedInfo getDsSignedInfo() {
        return dsSignedInfo;
    }

    /**
     * Définit la valeur de la propriété dsSignedInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link DsSignedInfo }
     *     
     */
    public void setDsSignedInfo(DsSignedInfo value) {
        this.dsSignedInfo = value;
    }

    /**
     * Obtient la valeur de la propriété dsSignatureValue.
     * 
     * @return
     *     possible object is
     *     {@link DsSignatureValue }
     *     
     */
    public DsSignatureValue getDsSignatureValue() {
        return dsSignatureValue;
    }

    /**
     * Définit la valeur de la propriété dsSignatureValue.
     * 
     * @param value
     *     allowed object is
     *     {@link DsSignatureValue }
     *     
     */
    public void setDsSignatureValue(DsSignatureValue value) {
        this.dsSignatureValue = value;
    }

    /**
     * Obtient la valeur de la propriété dsKeyInfo.
     * 
     * @return
     *     possible object is
     *     {@link DsKeyInfo }
     *     
     */
    public DsKeyInfo getDsKeyInfo() {
        return dsKeyInfo;
    }

    /**
     * Définit la valeur de la propriété dsKeyInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link DsKeyInfo }
     *     
     */
    public void setDsKeyInfo(DsKeyInfo value) {
        this.dsKeyInfo = value;
    }

    /**
     * Gets the value of the dsObject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dsObject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDsObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DsObject }
     * 
     * 
     */
    public List<DsObject> getDsObject() {
        if (dsObject == null) {
            dsObject = new ArrayList<DsObject>();
        }
        return this.dsObject;
    }

}

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xadesSignedSignatureProperties",
    "xadesSignedDataObjectProperties"
})
@XmlRootElement(name = "xades:SignedProperties")
public class XadesSignedProperties {

    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "xades:SignedSignatureProperties", required = true)
    protected XadesSignedSignatureProperties xadesSignedSignatureProperties;
    @XmlElement(name = "xades:SignedDataObjectProperties")
    protected XadesSignedDataObjectProperties xadesSignedDataObjectProperties;

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
     * Obtient la valeur de la propriété xadesSignedSignatureProperties.
     * 
     * @return
     *     possible object is
     *     {@link XadesSignedSignatureProperties }
     *     
     */
    public XadesSignedSignatureProperties getXadesSignedSignatureProperties() {
        return xadesSignedSignatureProperties;
    }

    /**
     * Définit la valeur de la propriété xadesSignedSignatureProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSignedSignatureProperties }
     *     
     */
    public void setXadesSignedSignatureProperties(XadesSignedSignatureProperties value) {
        this.xadesSignedSignatureProperties = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSignedDataObjectProperties.
     * 
     * @return
     *     possible object is
     *     {@link XadesSignedDataObjectProperties }
     *     
     */
    public XadesSignedDataObjectProperties getXadesSignedDataObjectProperties() {
        return xadesSignedDataObjectProperties;
    }

    /**
     * Définit la valeur de la propriété xadesSignedDataObjectProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSignedDataObjectProperties }
     *     
     */
    public void setXadesSignedDataObjectProperties(XadesSignedDataObjectProperties value) {
        this.xadesSignedDataObjectProperties = value;
    }

}

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xadesDescription",
    "xadesObjectIdentifier",
    "xadesMimeType",
    "xadesEncoding"
})
@XmlRootElement(name = "xades:DataObjectFormat")
public class XadesDataObjectFormat {

    @XmlAttribute(name = "ObjectReference", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String objectReference;
    @XmlElement(name = "xades:Description")
    protected String xadesDescription;
    @XmlElement(name = "xades:ObjectIdentifier")
    protected XadesObjectIdentifier xadesObjectIdentifier;
    @XmlElement(name = "xades:MimeType")
    protected String xadesMimeType;
    @XmlElement(name = "xades:Encoding")
    protected String xadesEncoding;

    /**
     * Obtient la valeur de la propriété objectReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectReference() {
        return objectReference;
    }

    /**
     * Définit la valeur de la propriété objectReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectReference(String value) {
        this.objectReference = value;
    }

    /**
     * Obtient la valeur de la propriété xadesDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesDescription() {
        return xadesDescription;
    }

    /**
     * Définit la valeur de la propriété xadesDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesDescription(String value) {
        this.xadesDescription = value;
    }

    /**
     * Obtient la valeur de la propriété xadesObjectIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link XadesObjectIdentifier }
     *     
     */
    public XadesObjectIdentifier getXadesObjectIdentifier() {
        return xadesObjectIdentifier;
    }

    /**
     * Définit la valeur de la propriété xadesObjectIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesObjectIdentifier }
     *     
     */
    public void setXadesObjectIdentifier(XadesObjectIdentifier value) {
        this.xadesObjectIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété xadesMimeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesMimeType() {
        return xadesMimeType;
    }

    /**
     * Définit la valeur de la propriété xadesMimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesMimeType(String value) {
        this.xadesMimeType = value;
    }

    /**
     * Obtient la valeur de la propriété xadesEncoding.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesEncoding() {
        return xadesEncoding;
    }

    /**
     * Définit la valeur de la propriété xadesEncoding.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesEncoding(String value) {
        this.xadesEncoding = value;
    }

}

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xadesDigestAlgAndValue",
    "xadesCRLIdentifier"
})
@XmlRootElement(name = "xades:CRLRef")
public class XadesCRLRef {

    @XmlElement(name = "xades:DigestAlgAndValue", required = true)
    protected XadesDigestAlgAndValue xadesDigestAlgAndValue;
    @XmlElement(name = "xades:CRLIdentifier")
    protected XadesCRLIdentifier xadesCRLIdentifier;

    /**
     * Obtient la valeur de la propriété xadesDigestAlgAndValue.
     * 
     * @return
     *     possible object is
     *     {@link XadesDigestAlgAndValue }
     *     
     */
    public XadesDigestAlgAndValue getXadesDigestAlgAndValue() {
        return xadesDigestAlgAndValue;
    }

    /**
     * Définit la valeur de la propriété xadesDigestAlgAndValue.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesDigestAlgAndValue }
     *     
     */
    public void setXadesDigestAlgAndValue(XadesDigestAlgAndValue value) {
        this.xadesDigestAlgAndValue = value;
    }

    /**
     * Obtient la valeur de la propriété xadesCRLIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link XadesCRLIdentifier }
     *     
     */
    public XadesCRLIdentifier getXadesCRLIdentifier() {
        return xadesCRLIdentifier;
    }

    /**
     * Définit la valeur de la propriété xadesCRLIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesCRLIdentifier }
     *     
     */
    public void setXadesCRLIdentifier(XadesCRLIdentifier value) {
        this.xadesCRLIdentifier = value;
    }

}

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
    "dsDigestMethod",
    "dsDigestValue"
})
@XmlRootElement(name = "xades:CertDigest")
public class XadesCertDigest {

    @XmlElement(name = "ds:DigestMethod", required = true)
    protected DsDigestMethod dsDigestMethod;
    @XmlElement(name = "ds:DigestValue", required = true)
    protected String dsDigestValue;

    /**
     * Obtient la valeur de la propriété dsDigestMethod.
     * 
     * @return
     *     possible object is
     *     {@link DsDigestMethod }
     *     
     */
    public DsDigestMethod getDsDigestMethod() {
        return dsDigestMethod;
    }

    /**
     * Définit la valeur de la propriété dsDigestMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DsDigestMethod }
     *     
     */
    public void setDsDigestMethod(DsDigestMethod value) {
        this.dsDigestMethod = value;
    }

    /**
     * Obtient la valeur de la propriété dsDigestValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsDigestValue() {
        return dsDigestValue;
    }

    /**
     * Définit la valeur de la propriété dsDigestValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsDigestValue(String value) {
        this.dsDigestValue = value;
    }

}

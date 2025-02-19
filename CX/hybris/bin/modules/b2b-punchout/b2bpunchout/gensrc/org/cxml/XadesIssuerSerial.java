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
    "dsX509IssuerName",
    "dsX509SerialNumber"
})
@XmlRootElement(name = "xades:IssuerSerial")
public class XadesIssuerSerial {

    @XmlElement(name = "ds:X509IssuerName", required = true)
    protected String dsX509IssuerName;
    @XmlElement(name = "ds:X509SerialNumber", required = true)
    protected String dsX509SerialNumber;

    /**
     * Obtient la valeur de la propriété dsX509IssuerName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsX509IssuerName() {
        return dsX509IssuerName;
    }

    /**
     * Définit la valeur de la propriété dsX509IssuerName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsX509IssuerName(String value) {
        this.dsX509IssuerName = value;
    }

    /**
     * Obtient la valeur de la propriété dsX509SerialNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsX509SerialNumber() {
        return dsX509SerialNumber;
    }

    /**
     * Définit la valeur de la propriété dsX509SerialNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsX509SerialNumber(String value) {
        this.dsX509SerialNumber = value;
    }

}

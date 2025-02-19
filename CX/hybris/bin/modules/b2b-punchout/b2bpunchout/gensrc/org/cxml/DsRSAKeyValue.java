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
    "dsModulus",
    "dsExponent"
})
@XmlRootElement(name = "ds:RSAKeyValue")
public class DsRSAKeyValue {

    @XmlElement(name = "ds:Modulus", required = true)
    protected String dsModulus;
    @XmlElement(name = "ds:Exponent", required = true)
    protected String dsExponent;

    /**
     * Obtient la valeur de la propriété dsModulus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsModulus() {
        return dsModulus;
    }

    /**
     * Définit la valeur de la propriété dsModulus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsModulus(String value) {
        this.dsModulus = value;
    }

    /**
     * Obtient la valeur de la propriété dsExponent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsExponent() {
        return dsExponent;
    }

    /**
     * Définit la valeur de la propriété dsExponent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsExponent(String value) {
        this.dsExponent = value;
    }

}

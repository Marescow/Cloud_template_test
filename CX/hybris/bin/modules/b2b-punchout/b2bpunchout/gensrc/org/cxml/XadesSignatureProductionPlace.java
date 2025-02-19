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
    "xadesCity",
    "xadesStateOrProvince",
    "xadesPostalCode",
    "xadesCountryName"
})
@XmlRootElement(name = "xades:SignatureProductionPlace")
public class XadesSignatureProductionPlace {

    @XmlElement(name = "xades:City")
    protected String xadesCity;
    @XmlElement(name = "xades:StateOrProvince")
    protected String xadesStateOrProvince;
    @XmlElement(name = "xades:PostalCode")
    protected String xadesPostalCode;
    @XmlElement(name = "xades:CountryName")
    protected String xadesCountryName;

    /**
     * Obtient la valeur de la propriété xadesCity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesCity() {
        return xadesCity;
    }

    /**
     * Définit la valeur de la propriété xadesCity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesCity(String value) {
        this.xadesCity = value;
    }

    /**
     * Obtient la valeur de la propriété xadesStateOrProvince.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesStateOrProvince() {
        return xadesStateOrProvince;
    }

    /**
     * Définit la valeur de la propriété xadesStateOrProvince.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesStateOrProvince(String value) {
        this.xadesStateOrProvince = value;
    }

    /**
     * Obtient la valeur de la propriété xadesPostalCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesPostalCode() {
        return xadesPostalCode;
    }

    /**
     * Définit la valeur de la propriété xadesPostalCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesPostalCode(String value) {
        this.xadesPostalCode = value;
    }

    /**
     * Obtient la valeur de la propriété xadesCountryName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesCountryName() {
        return xadesCountryName;
    }

    /**
     * Définit la valeur de la propriété xadesCountryName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesCountryName(String value) {
        this.xadesCountryName = value;
    }

}

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
    "dsP",
    "dsQ",
    "dsG",
    "dsY",
    "dsJ",
    "dsSeed",
    "dsPgenCounter"
})
@XmlRootElement(name = "ds:DSAKeyValue")
public class DsDSAKeyValue {

    @XmlElement(name = "ds:P")
    protected String dsP;
    @XmlElement(name = "ds:Q")
    protected String dsQ;
    @XmlElement(name = "ds:G")
    protected String dsG;
    @XmlElement(name = "ds:Y", required = true)
    protected String dsY;
    @XmlElement(name = "ds:J")
    protected String dsJ;
    @XmlElement(name = "ds:Seed")
    protected String dsSeed;
    @XmlElement(name = "ds:PgenCounter")
    protected String dsPgenCounter;

    /**
     * Obtient la valeur de la propriété dsP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsP() {
        return dsP;
    }

    /**
     * Définit la valeur de la propriété dsP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsP(String value) {
        this.dsP = value;
    }

    /**
     * Obtient la valeur de la propriété dsQ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsQ() {
        return dsQ;
    }

    /**
     * Définit la valeur de la propriété dsQ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsQ(String value) {
        this.dsQ = value;
    }

    /**
     * Obtient la valeur de la propriété dsG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsG() {
        return dsG;
    }

    /**
     * Définit la valeur de la propriété dsG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsG(String value) {
        this.dsG = value;
    }

    /**
     * Obtient la valeur de la propriété dsY.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsY() {
        return dsY;
    }

    /**
     * Définit la valeur de la propriété dsY.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsY(String value) {
        this.dsY = value;
    }

    /**
     * Obtient la valeur de la propriété dsJ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsJ() {
        return dsJ;
    }

    /**
     * Définit la valeur de la propriété dsJ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsJ(String value) {
        this.dsJ = value;
    }

    /**
     * Obtient la valeur de la propriété dsSeed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsSeed() {
        return dsSeed;
    }

    /**
     * Définit la valeur de la propriété dsSeed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsSeed(String value) {
        this.dsSeed = value;
    }

    /**
     * Obtient la valeur de la propriété dsPgenCounter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsPgenCounter() {
        return dsPgenCounter;
    }

    /**
     * Définit la valeur de la propriété dsPgenCounter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsPgenCounter(String value) {
        this.dsPgenCounter = value;
    }

}

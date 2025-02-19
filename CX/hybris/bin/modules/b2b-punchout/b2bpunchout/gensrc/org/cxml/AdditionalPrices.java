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
    "unitGrossPrice",
    "informationalPrice",
    "informationalPriceExclTax",
    "unitNetPriceCorrection"
})
@XmlRootElement(name = "AdditionalPrices")
public class AdditionalPrices {

    @XmlElement(name = "UnitGrossPrice")
    protected UnitGrossPrice unitGrossPrice;
    @XmlElement(name = "InformationalPrice")
    protected InformationalPrice informationalPrice;
    @XmlElement(name = "InformationalPriceExclTax")
    protected InformationalPriceExclTax informationalPriceExclTax;
    @XmlElement(name = "UnitNetPriceCorrection")
    protected UnitNetPriceCorrection unitNetPriceCorrection;

    /**
     * Obtient la valeur de la propriété unitGrossPrice.
     * 
     * @return
     *     possible object is
     *     {@link UnitGrossPrice }
     *     
     */
    public UnitGrossPrice getUnitGrossPrice() {
        return unitGrossPrice;
    }

    /**
     * Définit la valeur de la propriété unitGrossPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitGrossPrice }
     *     
     */
    public void setUnitGrossPrice(UnitGrossPrice value) {
        this.unitGrossPrice = value;
    }

    /**
     * Obtient la valeur de la propriété informationalPrice.
     * 
     * @return
     *     possible object is
     *     {@link InformationalPrice }
     *     
     */
    public InformationalPrice getInformationalPrice() {
        return informationalPrice;
    }

    /**
     * Définit la valeur de la propriété informationalPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationalPrice }
     *     
     */
    public void setInformationalPrice(InformationalPrice value) {
        this.informationalPrice = value;
    }

    /**
     * Obtient la valeur de la propriété informationalPriceExclTax.
     * 
     * @return
     *     possible object is
     *     {@link InformationalPriceExclTax }
     *     
     */
    public InformationalPriceExclTax getInformationalPriceExclTax() {
        return informationalPriceExclTax;
    }

    /**
     * Définit la valeur de la propriété informationalPriceExclTax.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationalPriceExclTax }
     *     
     */
    public void setInformationalPriceExclTax(InformationalPriceExclTax value) {
        this.informationalPriceExclTax = value;
    }

    /**
     * Obtient la valeur de la propriété unitNetPriceCorrection.
     * 
     * @return
     *     possible object is
     *     {@link UnitNetPriceCorrection }
     *     
     */
    public UnitNetPriceCorrection getUnitNetPriceCorrection() {
        return unitNetPriceCorrection;
    }

    /**
     * Définit la valeur de la propriété unitNetPriceCorrection.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitNetPriceCorrection }
     *     
     */
    public void setUnitNetPriceCorrection(UnitNetPriceCorrection value) {
        this.unitNetPriceCorrection = value;
    }

}

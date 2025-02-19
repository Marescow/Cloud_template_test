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
    "quantityTolerance",
    "priceTolerance",
    "timeTolerance"
})
@XmlRootElement(name = "Tolerances")
public class Tolerances {

    @XmlElement(name = "QuantityTolerance")
    protected QuantityTolerance quantityTolerance;
    @XmlElement(name = "PriceTolerance")
    protected PriceTolerance priceTolerance;
    @XmlElement(name = "TimeTolerance")
    protected TimeTolerance timeTolerance;

    /**
     * Obtient la valeur de la propriété quantityTolerance.
     * 
     * @return
     *     possible object is
     *     {@link QuantityTolerance }
     *     
     */
    public QuantityTolerance getQuantityTolerance() {
        return quantityTolerance;
    }

    /**
     * Définit la valeur de la propriété quantityTolerance.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityTolerance }
     *     
     */
    public void setQuantityTolerance(QuantityTolerance value) {
        this.quantityTolerance = value;
    }

    /**
     * Obtient la valeur de la propriété priceTolerance.
     * 
     * @return
     *     possible object is
     *     {@link PriceTolerance }
     *     
     */
    public PriceTolerance getPriceTolerance() {
        return priceTolerance;
    }

    /**
     * Définit la valeur de la propriété priceTolerance.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceTolerance }
     *     
     */
    public void setPriceTolerance(PriceTolerance value) {
        this.priceTolerance = value;
    }

    /**
     * Obtient la valeur de la propriété timeTolerance.
     * 
     * @return
     *     possible object is
     *     {@link TimeTolerance }
     *     
     */
    public TimeTolerance getTimeTolerance() {
        return timeTolerance;
    }

    /**
     * Définit la valeur de la propriété timeTolerance.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeTolerance }
     *     
     */
    public void setTimeTolerance(TimeTolerance value) {
        this.timeTolerance = value;
    }

}

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import java.util.ArrayList;
import java.util.List;
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
    "tripType",
    "railLeg",
    "availablePrice",
    "penalty"
})
@XmlRootElement(name = "RailDetail")
public class RailDetail {

    @XmlElement(name = "TripType", required = true)
    protected TripType tripType;
    @XmlElement(name = "RailLeg", required = true)
    protected List<RailLeg> railLeg;
    @XmlElement(name = "AvailablePrice")
    protected List<AvailablePrice> availablePrice;
    @XmlElement(name = "Penalty")
    protected Penalty penalty;

    /**
     * Obtient la valeur de la propriété tripType.
     * 
     * @return
     *     possible object is
     *     {@link TripType }
     *     
     */
    public TripType getTripType() {
        return tripType;
    }

    /**
     * Définit la valeur de la propriété tripType.
     * 
     * @param value
     *     allowed object is
     *     {@link TripType }
     *     
     */
    public void setTripType(TripType value) {
        this.tripType = value;
    }

    /**
     * Gets the value of the railLeg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the railLeg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRailLeg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RailLeg }
     * 
     * 
     */
    public List<RailLeg> getRailLeg() {
        if (railLeg == null) {
            railLeg = new ArrayList<RailLeg>();
        }
        return this.railLeg;
    }

    /**
     * Gets the value of the availablePrice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the availablePrice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvailablePrice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AvailablePrice }
     * 
     * 
     */
    public List<AvailablePrice> getAvailablePrice() {
        if (availablePrice == null) {
            availablePrice = new ArrayList<AvailablePrice>();
        }
        return this.availablePrice;
    }

    /**
     * Obtient la valeur de la propriété penalty.
     * 
     * @return
     *     possible object is
     *     {@link Penalty }
     *     
     */
    public Penalty getPenalty() {
        return penalty;
    }

    /**
     * Définit la valeur de la propriété penalty.
     * 
     * @param value
     *     allowed object is
     *     {@link Penalty }
     *     
     */
    public void setPenalty(Penalty value) {
        this.penalty = value;
    }

}

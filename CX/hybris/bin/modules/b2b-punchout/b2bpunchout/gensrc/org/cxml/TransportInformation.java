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
    "route",
    "shippingContractNumber",
    "shippingInstructions"
})
@XmlRootElement(name = "TransportInformation")
public class TransportInformation {

    @XmlElement(name = "Route")
    protected Route route;
    @XmlElement(name = "ShippingContractNumber")
    protected String shippingContractNumber;
    @XmlElement(name = "ShippingInstructions")
    protected ShippingInstructions shippingInstructions;

    /**
     * Obtient la valeur de la propriété route.
     * 
     * @return
     *     possible object is
     *     {@link Route }
     *     
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Définit la valeur de la propriété route.
     * 
     * @param value
     *     allowed object is
     *     {@link Route }
     *     
     */
    public void setRoute(Route value) {
        this.route = value;
    }

    /**
     * Obtient la valeur de la propriété shippingContractNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingContractNumber() {
        return shippingContractNumber;
    }

    /**
     * Définit la valeur de la propriété shippingContractNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingContractNumber(String value) {
        this.shippingContractNumber = value;
    }

    /**
     * Obtient la valeur de la propriété shippingInstructions.
     * 
     * @return
     *     possible object is
     *     {@link ShippingInstructions }
     *     
     */
    public ShippingInstructions getShippingInstructions() {
        return shippingInstructions;
    }

    /**
     * Définit la valeur de la propriété shippingInstructions.
     * 
     * @param value
     *     allowed object is
     *     {@link ShippingInstructions }
     *     
     */
    public void setShippingInstructions(ShippingInstructions value) {
        this.shippingInstructions = value;
    }

}

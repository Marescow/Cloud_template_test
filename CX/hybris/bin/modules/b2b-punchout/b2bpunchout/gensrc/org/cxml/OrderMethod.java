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
    "orderTarget",
    "orderProtocol"
})
@XmlRootElement(name = "OrderMethod")
public class OrderMethod {

    @XmlElement(name = "OrderTarget", required = true)
    protected OrderTarget orderTarget;
    @XmlElement(name = "OrderProtocol")
    protected String orderProtocol;

    /**
     * Obtient la valeur de la propriété orderTarget.
     * 
     * @return
     *     possible object is
     *     {@link OrderTarget }
     *     
     */
    public OrderTarget getOrderTarget() {
        return orderTarget;
    }

    /**
     * Définit la valeur de la propriété orderTarget.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderTarget }
     *     
     */
    public void setOrderTarget(OrderTarget value) {
        this.orderTarget = value;
    }

    /**
     * Obtient la valeur de la propriété orderProtocol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderProtocol() {
        return orderProtocol;
    }

    /**
     * Définit la valeur de la propriété orderProtocol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderProtocol(String value) {
        this.orderProtocol = value;
    }

}

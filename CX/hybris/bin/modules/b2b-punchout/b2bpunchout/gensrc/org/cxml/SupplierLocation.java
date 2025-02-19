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
    "address",
    "orderMethods"
})
@XmlRootElement(name = "SupplierLocation")
public class SupplierLocation {

    @XmlElement(name = "Address", required = true)
    protected Address address;
    @XmlElement(name = "OrderMethods", required = true)
    protected OrderMethods orderMethods;

    /**
     * Obtient la valeur de la propriété address.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Définit la valeur de la propriété address.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Obtient la valeur de la propriété orderMethods.
     * 
     * @return
     *     possible object is
     *     {@link OrderMethods }
     *     
     */
    public OrderMethods getOrderMethods() {
        return orderMethods;
    }

    /**
     * Définit la valeur de la propriété orderMethods.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderMethods }
     *     
     */
    public void setOrderMethods(OrderMethods value) {
        this.orderMethods = value;
    }

}

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
    "paymentRelationshipInformation"
})
@XmlRootElement(name = "RelationshipInformation")
public class RelationshipInformation {

    @XmlElement(name = "PaymentRelationshipInformation")
    protected PaymentRelationshipInformation paymentRelationshipInformation;

    /**
     * Obtient la valeur de la propriété paymentRelationshipInformation.
     * 
     * @return
     *     possible object is
     *     {@link PaymentRelationshipInformation }
     *     
     */
    public PaymentRelationshipInformation getPaymentRelationshipInformation() {
        return paymentRelationshipInformation;
    }

    /**
     * Définit la valeur de la propriété paymentRelationshipInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentRelationshipInformation }
     *     
     */
    public void setPaymentRelationshipInformation(PaymentRelationshipInformation value) {
        this.paymentRelationshipInformation = value;
    }

}

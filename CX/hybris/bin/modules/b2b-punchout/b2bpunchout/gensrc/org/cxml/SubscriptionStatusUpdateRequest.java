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
    "internalID",
    "subscriptionVersion",
    "subscriptionStatus"
})
@XmlRootElement(name = "SubscriptionStatusUpdateRequest")
public class SubscriptionStatusUpdateRequest {

    @XmlElement(name = "InternalID", required = true)
    protected InternalID internalID;
    @XmlElement(name = "SubscriptionVersion")
    protected SubscriptionVersion subscriptionVersion;
    @XmlElement(name = "SubscriptionStatus", required = true)
    protected SubscriptionStatus subscriptionStatus;

    /**
     * Obtient la valeur de la propriété internalID.
     * 
     * @return
     *     possible object is
     *     {@link InternalID }
     *     
     */
    public InternalID getInternalID() {
        return internalID;
    }

    /**
     * Définit la valeur de la propriété internalID.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalID }
     *     
     */
    public void setInternalID(InternalID value) {
        this.internalID = value;
    }

    /**
     * Obtient la valeur de la propriété subscriptionVersion.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionVersion }
     *     
     */
    public SubscriptionVersion getSubscriptionVersion() {
        return subscriptionVersion;
    }

    /**
     * Définit la valeur de la propriété subscriptionVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionVersion }
     *     
     */
    public void setSubscriptionVersion(SubscriptionVersion value) {
        this.subscriptionVersion = value;
    }

    /**
     * Obtient la valeur de la propriété subscriptionStatus.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionStatus }
     *     
     */
    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    /**
     * Définit la valeur de la propriété subscriptionStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionStatus }
     *     
     */
    public void setSubscriptionStatus(SubscriptionStatus value) {
        this.subscriptionStatus = value;
    }

}

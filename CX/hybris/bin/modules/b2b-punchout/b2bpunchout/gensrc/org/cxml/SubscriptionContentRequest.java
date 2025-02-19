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
    "internalID",
    "subscriptionVersion",
    "supplierID"
})
@XmlRootElement(name = "SubscriptionContentRequest")
public class SubscriptionContentRequest {

    @XmlElement(name = "InternalID", required = true)
    protected InternalID internalID;
    @XmlElement(name = "SubscriptionVersion")
    protected SubscriptionVersion subscriptionVersion;
    @XmlElement(name = "SupplierID", required = true)
    protected List<SupplierID> supplierID;

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
     * Gets the value of the supplierID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplierID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplierID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplierID }
     * 
     * 
     */
    public List<SupplierID> getSupplierID() {
        if (supplierID == null) {
            supplierID = new ArrayList<SupplierID>();
        }
        return this.supplierID;
    }

}

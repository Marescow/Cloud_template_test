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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "punchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage"
})
@XmlRootElement(name = "Message")
public class Message {

    @XmlAttribute(name = "deploymentMode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String deploymentMode;
    @XmlAttribute(name = "inReplyTo")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String inReplyTo;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "Status")
    protected Status status;
    @XmlElements({
        @XmlElement(name = "PunchOutOrderMessage", required = true, type = PunchOutOrderMessage.class),
        @XmlElement(name = "ProviderDoneMessage", required = true, type = ProviderDoneMessage.class),
        @XmlElement(name = "SubscriptionChangeMessage", required = true, type = SubscriptionChangeMessage.class),
        @XmlElement(name = "DataAvailableMessage", required = true, type = DataAvailableMessage.class),
        @XmlElement(name = "SupplierChangeMessage", required = true, type = SupplierChangeMessage.class),
        @XmlElement(name = "OrganizationChangeMessage", required = true, type = OrganizationChangeMessage.class),
        @XmlElement(name = "ProductActivityMessage", required = true, type = ProductActivityMessage.class)
    })
    protected List<Object> punchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage;

    /**
     * Obtient la valeur de la propriété deploymentMode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeploymentMode() {
        if (deploymentMode == null) {
            return "production";
        } else {
            return deploymentMode;
        }
    }

    /**
     * Définit la valeur de la propriété deploymentMode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeploymentMode(String value) {
        this.deploymentMode = value;
    }

    /**
     * Obtient la valeur de la propriété inReplyTo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInReplyTo() {
        return inReplyTo;
    }

    /**
     * Définit la valeur de la propriété inReplyTo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInReplyTo(String value) {
        this.inReplyTo = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the punchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the punchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPunchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PunchOutOrderMessage }
     * {@link ProviderDoneMessage }
     * {@link SubscriptionChangeMessage }
     * {@link DataAvailableMessage }
     * {@link SupplierChangeMessage }
     * {@link OrganizationChangeMessage }
     * {@link ProductActivityMessage }
     * 
     * 
     */
    public List<Object> getPunchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage() {
        if (punchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage == null) {
            punchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage = new ArrayList<Object>();
        }
        return this.punchOutOrderMessageOrProviderDoneMessageOrSubscriptionChangeMessageOrDataAvailableMessageOrSupplierChangeMessageOrOrganizationChangeMessageOrProductActivityMessage;
    }

}

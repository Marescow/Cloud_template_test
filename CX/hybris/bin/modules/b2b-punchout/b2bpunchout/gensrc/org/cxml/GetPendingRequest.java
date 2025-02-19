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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "messageType"
})
@XmlRootElement(name = "GetPendingRequest")
public class GetPendingRequest {

    @XmlAttribute(name = "maxMessages")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String maxMessages;
    @XmlAttribute(name = "lastReceivedTimestamp")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lastReceivedTimestamp;
    @XmlElement(name = "MessageType", required = true)
    protected List<MessageType> messageType;

    /**
     * Obtient la valeur de la propriété maxMessages.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxMessages() {
        return maxMessages;
    }

    /**
     * Définit la valeur de la propriété maxMessages.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxMessages(String value) {
        this.maxMessages = value;
    }

    /**
     * Obtient la valeur de la propriété lastReceivedTimestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastReceivedTimestamp() {
        return lastReceivedTimestamp;
    }

    /**
     * Définit la valeur de la propriété lastReceivedTimestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastReceivedTimestamp(String value) {
        this.lastReceivedTimestamp = value;
    }

    /**
     * Gets the value of the messageType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageType }
     * 
     * 
     */
    public List<MessageType> getMessageType() {
        if (messageType == null) {
            messageType = new ArrayList<MessageType>();
        }
        return this.messageType;
    }

}

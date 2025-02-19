//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "integrationMessage"
})
@XmlRootElement(name = "IntegrationStatus")
public class IntegrationStatus {

    @XmlAttribute(name = "documentStatus", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String documentStatus;
    @XmlElement(name = "IntegrationMessage")
    protected IntegrationMessage integrationMessage;

    /**
     * Obtient la valeur de la propriété documentStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentStatus() {
        return documentStatus;
    }

    /**
     * Définit la valeur de la propriété documentStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentStatus(String value) {
        this.documentStatus = value;
    }

    /**
     * Obtient la valeur de la propriété integrationMessage.
     * 
     * @return
     *     possible object is
     *     {@link IntegrationMessage }
     *     
     */
    public IntegrationMessage getIntegrationMessage() {
        return integrationMessage;
    }

    /**
     * Définit la valeur de la propriété integrationMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegrationMessage }
     *     
     */
    public void setIntegrationMessage(IntegrationMessage value) {
        this.integrationMessage = value;
    }

}

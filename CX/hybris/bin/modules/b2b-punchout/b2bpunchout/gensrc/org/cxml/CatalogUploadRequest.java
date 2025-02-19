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
    "catalogName",
    "description",
    "attachment",
    "commodities",
    "autoPublish",
    "notification"
})
@XmlRootElement(name = "CatalogUploadRequest")
public class CatalogUploadRequest {

    @XmlAttribute(name = "operation", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String operation;
    @XmlElement(name = "CatalogName", required = true)
    protected CatalogName catalogName;
    @XmlElement(name = "Description", required = true)
    protected Description description;
    @XmlElement(name = "Attachment", required = true)
    protected Attachment attachment;
    @XmlElement(name = "Commodities")
    protected Commodities commodities;
    @XmlElement(name = "AutoPublish")
    protected AutoPublish autoPublish;
    @XmlElement(name = "Notification", required = true)
    protected Notification notification;

    /**
     * Obtient la valeur de la propriété operation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Définit la valeur de la propriété operation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Obtient la valeur de la propriété catalogName.
     * 
     * @return
     *     possible object is
     *     {@link CatalogName }
     *     
     */
    public CatalogName getCatalogName() {
        return catalogName;
    }

    /**
     * Définit la valeur de la propriété catalogName.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogName }
     *     
     */
    public void setCatalogName(CatalogName value) {
        this.catalogName = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété attachment.
     * 
     * @return
     *     possible object is
     *     {@link Attachment }
     *     
     */
    public Attachment getAttachment() {
        return attachment;
    }

    /**
     * Définit la valeur de la propriété attachment.
     * 
     * @param value
     *     allowed object is
     *     {@link Attachment }
     *     
     */
    public void setAttachment(Attachment value) {
        this.attachment = value;
    }

    /**
     * Obtient la valeur de la propriété commodities.
     * 
     * @return
     *     possible object is
     *     {@link Commodities }
     *     
     */
    public Commodities getCommodities() {
        return commodities;
    }

    /**
     * Définit la valeur de la propriété commodities.
     * 
     * @param value
     *     allowed object is
     *     {@link Commodities }
     *     
     */
    public void setCommodities(Commodities value) {
        this.commodities = value;
    }

    /**
     * Obtient la valeur de la propriété autoPublish.
     * 
     * @return
     *     possible object is
     *     {@link AutoPublish }
     *     
     */
    public AutoPublish getAutoPublish() {
        return autoPublish;
    }

    /**
     * Définit la valeur de la propriété autoPublish.
     * 
     * @param value
     *     allowed object is
     *     {@link AutoPublish }
     *     
     */
    public void setAutoPublish(AutoPublish value) {
        this.autoPublish = value;
    }

    /**
     * Obtient la valeur de la propriété notification.
     * 
     * @return
     *     possible object is
     *     {@link Notification }
     *     
     */
    public Notification getNotification() {
        return notification;
    }

    /**
     * Définit la valeur de la propriété notification.
     * 
     * @param value
     *     allowed object is
     *     {@link Notification }
     *     
     */
    public void setNotification(Notification value) {
        this.notification = value;
    }

}

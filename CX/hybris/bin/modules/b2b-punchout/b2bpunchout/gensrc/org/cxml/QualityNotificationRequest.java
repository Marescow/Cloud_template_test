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
    "qualityNotificationRequestHeader",
    "qualityNotificationRequestItem"
})
@XmlRootElement(name = "QualityNotificationRequest")
public class QualityNotificationRequest {

    @XmlElement(name = "QualityNotificationRequestHeader", required = true)
    protected QualityNotificationRequestHeader qualityNotificationRequestHeader;
    @XmlElement(name = "QualityNotificationRequestItem")
    protected List<QualityNotificationRequestItem> qualityNotificationRequestItem;

    /**
     * Obtient la valeur de la propriété qualityNotificationRequestHeader.
     * 
     * @return
     *     possible object is
     *     {@link QualityNotificationRequestHeader }
     *     
     */
    public QualityNotificationRequestHeader getQualityNotificationRequestHeader() {
        return qualityNotificationRequestHeader;
    }

    /**
     * Définit la valeur de la propriété qualityNotificationRequestHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityNotificationRequestHeader }
     *     
     */
    public void setQualityNotificationRequestHeader(QualityNotificationRequestHeader value) {
        this.qualityNotificationRequestHeader = value;
    }

    /**
     * Gets the value of the qualityNotificationRequestItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityNotificationRequestItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityNotificationRequestItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityNotificationRequestItem }
     * 
     * 
     */
    public List<QualityNotificationRequestItem> getQualityNotificationRequestItem() {
        if (qualityNotificationRequestItem == null) {
            qualityNotificationRequestItem = new ArrayList<QualityNotificationRequestItem>();
        }
        return this.qualityNotificationRequestItem;
    }

}

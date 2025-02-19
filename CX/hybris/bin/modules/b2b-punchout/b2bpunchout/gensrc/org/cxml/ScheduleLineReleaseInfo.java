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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "unitOfMeasure"
})
@XmlRootElement(name = "ScheduleLineReleaseInfo")
public class ScheduleLineReleaseInfo {

    @XmlAttribute(name = "commitmentCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String commitmentCode;
    @XmlAttribute(name = "cumulativeScheduledQuantity", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cumulativeScheduledQuantity;
    @XmlAttribute(name = "receivedQuantity")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String receivedQuantity;
    @XmlElement(name = "UnitOfMeasure", required = true)
    protected String unitOfMeasure;

    /**
     * Obtient la valeur de la propriété commitmentCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitmentCode() {
        return commitmentCode;
    }

    /**
     * Définit la valeur de la propriété commitmentCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitmentCode(String value) {
        this.commitmentCode = value;
    }

    /**
     * Obtient la valeur de la propriété cumulativeScheduledQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCumulativeScheduledQuantity() {
        return cumulativeScheduledQuantity;
    }

    /**
     * Définit la valeur de la propriété cumulativeScheduledQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCumulativeScheduledQuantity(String value) {
        this.cumulativeScheduledQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété receivedQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedQuantity() {
        return receivedQuantity;
    }

    /**
     * Définit la valeur de la propriété receivedQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedQuantity(String value) {
        this.receivedQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété unitOfMeasure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Définit la valeur de la propriété unitOfMeasure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
    }

}

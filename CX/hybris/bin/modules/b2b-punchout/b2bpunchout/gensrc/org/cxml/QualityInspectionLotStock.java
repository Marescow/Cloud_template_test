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
    "unrestrictedUseQuantity",
    "scrapQuantity",
    "sampleUsageQuantity",
    "blockedQuantity",
    "newMaterialQuantity",
    "reserveQuantity",
    "returnQuantity"
})
@XmlRootElement(name = "QualityInspectionLotStock")
public class QualityInspectionLotStock {

    @XmlElement(name = "UnrestrictedUseQuantity")
    protected UnrestrictedUseQuantity unrestrictedUseQuantity;
    @XmlElement(name = "ScrapQuantity")
    protected ScrapQuantity scrapQuantity;
    @XmlElement(name = "SampleUsageQuantity")
    protected SampleUsageQuantity sampleUsageQuantity;
    @XmlElement(name = "BlockedQuantity")
    protected BlockedQuantity blockedQuantity;
    @XmlElement(name = "NewMaterialQuantity")
    protected NewMaterialQuantity newMaterialQuantity;
    @XmlElement(name = "ReserveQuantity")
    protected ReserveQuantity reserveQuantity;
    @XmlElement(name = "ReturnQuantity")
    protected ReturnQuantity returnQuantity;

    /**
     * Obtient la valeur de la propriété unrestrictedUseQuantity.
     * 
     * @return
     *     possible object is
     *     {@link UnrestrictedUseQuantity }
     *     
     */
    public UnrestrictedUseQuantity getUnrestrictedUseQuantity() {
        return unrestrictedUseQuantity;
    }

    /**
     * Définit la valeur de la propriété unrestrictedUseQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link UnrestrictedUseQuantity }
     *     
     */
    public void setUnrestrictedUseQuantity(UnrestrictedUseQuantity value) {
        this.unrestrictedUseQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété scrapQuantity.
     * 
     * @return
     *     possible object is
     *     {@link ScrapQuantity }
     *     
     */
    public ScrapQuantity getScrapQuantity() {
        return scrapQuantity;
    }

    /**
     * Définit la valeur de la propriété scrapQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link ScrapQuantity }
     *     
     */
    public void setScrapQuantity(ScrapQuantity value) {
        this.scrapQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété sampleUsageQuantity.
     * 
     * @return
     *     possible object is
     *     {@link SampleUsageQuantity }
     *     
     */
    public SampleUsageQuantity getSampleUsageQuantity() {
        return sampleUsageQuantity;
    }

    /**
     * Définit la valeur de la propriété sampleUsageQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link SampleUsageQuantity }
     *     
     */
    public void setSampleUsageQuantity(SampleUsageQuantity value) {
        this.sampleUsageQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété blockedQuantity.
     * 
     * @return
     *     possible object is
     *     {@link BlockedQuantity }
     *     
     */
    public BlockedQuantity getBlockedQuantity() {
        return blockedQuantity;
    }

    /**
     * Définit la valeur de la propriété blockedQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockedQuantity }
     *     
     */
    public void setBlockedQuantity(BlockedQuantity value) {
        this.blockedQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété newMaterialQuantity.
     * 
     * @return
     *     possible object is
     *     {@link NewMaterialQuantity }
     *     
     */
    public NewMaterialQuantity getNewMaterialQuantity() {
        return newMaterialQuantity;
    }

    /**
     * Définit la valeur de la propriété newMaterialQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link NewMaterialQuantity }
     *     
     */
    public void setNewMaterialQuantity(NewMaterialQuantity value) {
        this.newMaterialQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété reserveQuantity.
     * 
     * @return
     *     possible object is
     *     {@link ReserveQuantity }
     *     
     */
    public ReserveQuantity getReserveQuantity() {
        return reserveQuantity;
    }

    /**
     * Définit la valeur de la propriété reserveQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link ReserveQuantity }
     *     
     */
    public void setReserveQuantity(ReserveQuantity value) {
        this.reserveQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété returnQuantity.
     * 
     * @return
     *     possible object is
     *     {@link ReturnQuantity }
     *     
     */
    public ReturnQuantity getReturnQuantity() {
        return returnQuantity;
    }

    /**
     * Définit la valeur de la propriété returnQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnQuantity }
     *     
     */
    public void setReturnQuantity(ReturnQuantity value) {
        this.returnQuantity = value;
    }

}

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
    "subcontractingStockInTransferQuantity",
    "unrestrictedUseQuantity",
    "blockedQuantity",
    "qualityInspectionQuantity",
    "promotionQuantity",
    "stockInTransferQuantity",
    "incrementQuantity",
    "requiredMinimumQuantity",
    "requiredMaximumQuantity",
    "stockOnHandQuantity",
    "workInProcessQuantity",
    "intransitQuantity",
    "scrapQuantity",
    "orderQuantity",
    "daysOfSupply"
})
@XmlRootElement(name = "Inventory")
public class Inventory {

    @XmlElement(name = "SubcontractingStockInTransferQuantity")
    protected SubcontractingStockInTransferQuantity subcontractingStockInTransferQuantity;
    @XmlElement(name = "UnrestrictedUseQuantity")
    protected UnrestrictedUseQuantity unrestrictedUseQuantity;
    @XmlElement(name = "BlockedQuantity")
    protected BlockedQuantity blockedQuantity;
    @XmlElement(name = "QualityInspectionQuantity")
    protected QualityInspectionQuantity qualityInspectionQuantity;
    @XmlElement(name = "PromotionQuantity")
    protected PromotionQuantity promotionQuantity;
    @XmlElement(name = "StockInTransferQuantity")
    protected StockInTransferQuantity stockInTransferQuantity;
    @XmlElement(name = "IncrementQuantity")
    protected IncrementQuantity incrementQuantity;
    @XmlElement(name = "RequiredMinimumQuantity")
    protected RequiredMinimumQuantity requiredMinimumQuantity;
    @XmlElement(name = "RequiredMaximumQuantity")
    protected RequiredMaximumQuantity requiredMaximumQuantity;
    @XmlElement(name = "StockOnHandQuantity")
    protected StockOnHandQuantity stockOnHandQuantity;
    @XmlElement(name = "WorkInProcessQuantity")
    protected WorkInProcessQuantity workInProcessQuantity;
    @XmlElement(name = "IntransitQuantity")
    protected IntransitQuantity intransitQuantity;
    @XmlElement(name = "ScrapQuantity")
    protected ScrapQuantity scrapQuantity;
    @XmlElement(name = "OrderQuantity")
    protected OrderQuantity orderQuantity;
    @XmlElement(name = "DaysOfSupply")
    protected DaysOfSupply daysOfSupply;

    /**
     * Obtient la valeur de la propriété subcontractingStockInTransferQuantity.
     * 
     * @return
     *     possible object is
     *     {@link SubcontractingStockInTransferQuantity }
     *     
     */
    public SubcontractingStockInTransferQuantity getSubcontractingStockInTransferQuantity() {
        return subcontractingStockInTransferQuantity;
    }

    /**
     * Définit la valeur de la propriété subcontractingStockInTransferQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link SubcontractingStockInTransferQuantity }
     *     
     */
    public void setSubcontractingStockInTransferQuantity(SubcontractingStockInTransferQuantity value) {
        this.subcontractingStockInTransferQuantity = value;
    }

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
     * Obtient la valeur de la propriété qualityInspectionQuantity.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionQuantity }
     *     
     */
    public QualityInspectionQuantity getQualityInspectionQuantity() {
        return qualityInspectionQuantity;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionQuantity }
     *     
     */
    public void setQualityInspectionQuantity(QualityInspectionQuantity value) {
        this.qualityInspectionQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété promotionQuantity.
     * 
     * @return
     *     possible object is
     *     {@link PromotionQuantity }
     *     
     */
    public PromotionQuantity getPromotionQuantity() {
        return promotionQuantity;
    }

    /**
     * Définit la valeur de la propriété promotionQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link PromotionQuantity }
     *     
     */
    public void setPromotionQuantity(PromotionQuantity value) {
        this.promotionQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété stockInTransferQuantity.
     * 
     * @return
     *     possible object is
     *     {@link StockInTransferQuantity }
     *     
     */
    public StockInTransferQuantity getStockInTransferQuantity() {
        return stockInTransferQuantity;
    }

    /**
     * Définit la valeur de la propriété stockInTransferQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link StockInTransferQuantity }
     *     
     */
    public void setStockInTransferQuantity(StockInTransferQuantity value) {
        this.stockInTransferQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété incrementQuantity.
     * 
     * @return
     *     possible object is
     *     {@link IncrementQuantity }
     *     
     */
    public IncrementQuantity getIncrementQuantity() {
        return incrementQuantity;
    }

    /**
     * Définit la valeur de la propriété incrementQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link IncrementQuantity }
     *     
     */
    public void setIncrementQuantity(IncrementQuantity value) {
        this.incrementQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété requiredMinimumQuantity.
     * 
     * @return
     *     possible object is
     *     {@link RequiredMinimumQuantity }
     *     
     */
    public RequiredMinimumQuantity getRequiredMinimumQuantity() {
        return requiredMinimumQuantity;
    }

    /**
     * Définit la valeur de la propriété requiredMinimumQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link RequiredMinimumQuantity }
     *     
     */
    public void setRequiredMinimumQuantity(RequiredMinimumQuantity value) {
        this.requiredMinimumQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété requiredMaximumQuantity.
     * 
     * @return
     *     possible object is
     *     {@link RequiredMaximumQuantity }
     *     
     */
    public RequiredMaximumQuantity getRequiredMaximumQuantity() {
        return requiredMaximumQuantity;
    }

    /**
     * Définit la valeur de la propriété requiredMaximumQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link RequiredMaximumQuantity }
     *     
     */
    public void setRequiredMaximumQuantity(RequiredMaximumQuantity value) {
        this.requiredMaximumQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété stockOnHandQuantity.
     * 
     * @return
     *     possible object is
     *     {@link StockOnHandQuantity }
     *     
     */
    public StockOnHandQuantity getStockOnHandQuantity() {
        return stockOnHandQuantity;
    }

    /**
     * Définit la valeur de la propriété stockOnHandQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link StockOnHandQuantity }
     *     
     */
    public void setStockOnHandQuantity(StockOnHandQuantity value) {
        this.stockOnHandQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété workInProcessQuantity.
     * 
     * @return
     *     possible object is
     *     {@link WorkInProcessQuantity }
     *     
     */
    public WorkInProcessQuantity getWorkInProcessQuantity() {
        return workInProcessQuantity;
    }

    /**
     * Définit la valeur de la propriété workInProcessQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkInProcessQuantity }
     *     
     */
    public void setWorkInProcessQuantity(WorkInProcessQuantity value) {
        this.workInProcessQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété intransitQuantity.
     * 
     * @return
     *     possible object is
     *     {@link IntransitQuantity }
     *     
     */
    public IntransitQuantity getIntransitQuantity() {
        return intransitQuantity;
    }

    /**
     * Définit la valeur de la propriété intransitQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link IntransitQuantity }
     *     
     */
    public void setIntransitQuantity(IntransitQuantity value) {
        this.intransitQuantity = value;
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
     * Obtient la valeur de la propriété orderQuantity.
     * 
     * @return
     *     possible object is
     *     {@link OrderQuantity }
     *     
     */
    public OrderQuantity getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * Définit la valeur de la propriété orderQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderQuantity }
     *     
     */
    public void setOrderQuantity(OrderQuantity value) {
        this.orderQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété daysOfSupply.
     * 
     * @return
     *     possible object is
     *     {@link DaysOfSupply }
     *     
     */
    public DaysOfSupply getDaysOfSupply() {
        return daysOfSupply;
    }

    /**
     * Définit la valeur de la propriété daysOfSupply.
     * 
     * @param value
     *     allowed object is
     *     {@link DaysOfSupply }
     *     
     */
    public void setDaysOfSupply(DaysOfSupply value) {
        this.daysOfSupply = value;
    }

}

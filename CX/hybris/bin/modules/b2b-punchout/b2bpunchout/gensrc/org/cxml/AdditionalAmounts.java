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
    "totalRetailAmount",
    "informationalAmount",
    "grossProgressPaymentAmount",
    "totalReturnableItemsDepositAmount",
    "goodsAndServiceAmount",
    "exactAmount"
})
@XmlRootElement(name = "AdditionalAmounts")
public class AdditionalAmounts {

    @XmlElement(name = "TotalRetailAmount")
    protected TotalRetailAmount totalRetailAmount;
    @XmlElement(name = "InformationalAmount")
    protected InformationalAmount informationalAmount;
    @XmlElement(name = "GrossProgressPaymentAmount")
    protected GrossProgressPaymentAmount grossProgressPaymentAmount;
    @XmlElement(name = "TotalReturnableItemsDepositAmount")
    protected TotalReturnableItemsDepositAmount totalReturnableItemsDepositAmount;
    @XmlElement(name = "GoodsAndServiceAmount")
    protected GoodsAndServiceAmount goodsAndServiceAmount;
    @XmlElement(name = "ExactAmount")
    protected ExactAmount exactAmount;

    /**
     * Obtient la valeur de la propriété totalRetailAmount.
     * 
     * @return
     *     possible object is
     *     {@link TotalRetailAmount }
     *     
     */
    public TotalRetailAmount getTotalRetailAmount() {
        return totalRetailAmount;
    }

    /**
     * Définit la valeur de la propriété totalRetailAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalRetailAmount }
     *     
     */
    public void setTotalRetailAmount(TotalRetailAmount value) {
        this.totalRetailAmount = value;
    }

    /**
     * Obtient la valeur de la propriété informationalAmount.
     * 
     * @return
     *     possible object is
     *     {@link InformationalAmount }
     *     
     */
    public InformationalAmount getInformationalAmount() {
        return informationalAmount;
    }

    /**
     * Définit la valeur de la propriété informationalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationalAmount }
     *     
     */
    public void setInformationalAmount(InformationalAmount value) {
        this.informationalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété grossProgressPaymentAmount.
     * 
     * @return
     *     possible object is
     *     {@link GrossProgressPaymentAmount }
     *     
     */
    public GrossProgressPaymentAmount getGrossProgressPaymentAmount() {
        return grossProgressPaymentAmount;
    }

    /**
     * Définit la valeur de la propriété grossProgressPaymentAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link GrossProgressPaymentAmount }
     *     
     */
    public void setGrossProgressPaymentAmount(GrossProgressPaymentAmount value) {
        this.grossProgressPaymentAmount = value;
    }

    /**
     * Obtient la valeur de la propriété totalReturnableItemsDepositAmount.
     * 
     * @return
     *     possible object is
     *     {@link TotalReturnableItemsDepositAmount }
     *     
     */
    public TotalReturnableItemsDepositAmount getTotalReturnableItemsDepositAmount() {
        return totalReturnableItemsDepositAmount;
    }

    /**
     * Définit la valeur de la propriété totalReturnableItemsDepositAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalReturnableItemsDepositAmount }
     *     
     */
    public void setTotalReturnableItemsDepositAmount(TotalReturnableItemsDepositAmount value) {
        this.totalReturnableItemsDepositAmount = value;
    }

    /**
     * Obtient la valeur de la propriété goodsAndServiceAmount.
     * 
     * @return
     *     possible object is
     *     {@link GoodsAndServiceAmount }
     *     
     */
    public GoodsAndServiceAmount getGoodsAndServiceAmount() {
        return goodsAndServiceAmount;
    }

    /**
     * Définit la valeur de la propriété goodsAndServiceAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link GoodsAndServiceAmount }
     *     
     */
    public void setGoodsAndServiceAmount(GoodsAndServiceAmount value) {
        this.goodsAndServiceAmount = value;
    }

    /**
     * Obtient la valeur de la propriété exactAmount.
     * 
     * @return
     *     possible object is
     *     {@link ExactAmount }
     *     
     */
    public ExactAmount getExactAmount() {
        return exactAmount;
    }

    /**
     * Définit la valeur de la propriété exactAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link ExactAmount }
     *     
     */
    public void setExactAmount(ExactAmount value) {
        this.exactAmount = value;
    }

}

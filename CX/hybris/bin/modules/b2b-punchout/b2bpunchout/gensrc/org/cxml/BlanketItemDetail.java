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
    "description",
    "overallLimit",
    "expectedLimit",
    "maxAmount",
    "minAmount",
    "maxContractAmount",
    "maxAdhocAmount",
    "maxQuantity",
    "minQuantity",
    "unitPrice",
    "unitOfMeasure",
    "priceBasisQuantity",
    "classification",
    "extrinsic"
})
@XmlRootElement(name = "BlanketItemDetail")
public class BlanketItemDetail {

    @XmlElement(name = "Description", required = true)
    protected List<Description> description;
    @XmlElement(name = "OverallLimit")
    protected OverallLimit overallLimit;
    @XmlElement(name = "ExpectedLimit")
    protected ExpectedLimit expectedLimit;
    @XmlElement(name = "MaxAmount")
    protected MaxAmount maxAmount;
    @XmlElement(name = "MinAmount")
    protected MinAmount minAmount;
    @XmlElement(name = "MaxContractAmount")
    protected MaxContractAmount maxContractAmount;
    @XmlElement(name = "MaxAdhocAmount")
    protected MaxAdhocAmount maxAdhocAmount;
    @XmlElement(name = "MaxQuantity")
    protected String maxQuantity;
    @XmlElement(name = "MinQuantity")
    protected String minQuantity;
    @XmlElement(name = "UnitPrice")
    protected UnitPrice unitPrice;
    @XmlElement(name = "UnitOfMeasure")
    protected String unitOfMeasure;
    @XmlElement(name = "PriceBasisQuantity")
    protected PriceBasisQuantity priceBasisQuantity;
    @XmlElement(name = "Classification")
    protected List<Classification> classification;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getDescription() {
        if (description == null) {
            description = new ArrayList<Description>();
        }
        return this.description;
    }

    /**
     * Obtient la valeur de la propriété overallLimit.
     * 
     * @return
     *     possible object is
     *     {@link OverallLimit }
     *     
     */
    public OverallLimit getOverallLimit() {
        return overallLimit;
    }

    /**
     * Définit la valeur de la propriété overallLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link OverallLimit }
     *     
     */
    public void setOverallLimit(OverallLimit value) {
        this.overallLimit = value;
    }

    /**
     * Obtient la valeur de la propriété expectedLimit.
     * 
     * @return
     *     possible object is
     *     {@link ExpectedLimit }
     *     
     */
    public ExpectedLimit getExpectedLimit() {
        return expectedLimit;
    }

    /**
     * Définit la valeur de la propriété expectedLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpectedLimit }
     *     
     */
    public void setExpectedLimit(ExpectedLimit value) {
        this.expectedLimit = value;
    }

    /**
     * Obtient la valeur de la propriété maxAmount.
     * 
     * @return
     *     possible object is
     *     {@link MaxAmount }
     *     
     */
    public MaxAmount getMaxAmount() {
        return maxAmount;
    }

    /**
     * Définit la valeur de la propriété maxAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxAmount }
     *     
     */
    public void setMaxAmount(MaxAmount value) {
        this.maxAmount = value;
    }

    /**
     * Obtient la valeur de la propriété minAmount.
     * 
     * @return
     *     possible object is
     *     {@link MinAmount }
     *     
     */
    public MinAmount getMinAmount() {
        return minAmount;
    }

    /**
     * Définit la valeur de la propriété minAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MinAmount }
     *     
     */
    public void setMinAmount(MinAmount value) {
        this.minAmount = value;
    }

    /**
     * Obtient la valeur de la propriété maxContractAmount.
     * 
     * @return
     *     possible object is
     *     {@link MaxContractAmount }
     *     
     */
    public MaxContractAmount getMaxContractAmount() {
        return maxContractAmount;
    }

    /**
     * Définit la valeur de la propriété maxContractAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxContractAmount }
     *     
     */
    public void setMaxContractAmount(MaxContractAmount value) {
        this.maxContractAmount = value;
    }

    /**
     * Obtient la valeur de la propriété maxAdhocAmount.
     * 
     * @return
     *     possible object is
     *     {@link MaxAdhocAmount }
     *     
     */
    public MaxAdhocAmount getMaxAdhocAmount() {
        return maxAdhocAmount;
    }

    /**
     * Définit la valeur de la propriété maxAdhocAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxAdhocAmount }
     *     
     */
    public void setMaxAdhocAmount(MaxAdhocAmount value) {
        this.maxAdhocAmount = value;
    }

    /**
     * Obtient la valeur de la propriété maxQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxQuantity() {
        return maxQuantity;
    }

    /**
     * Définit la valeur de la propriété maxQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxQuantity(String value) {
        this.maxQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété minQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinQuantity() {
        return minQuantity;
    }

    /**
     * Définit la valeur de la propriété minQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinQuantity(String value) {
        this.minQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété unitPrice.
     * 
     * @return
     *     possible object is
     *     {@link UnitPrice }
     *     
     */
    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    /**
     * Définit la valeur de la propriété unitPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitPrice }
     *     
     */
    public void setUnitPrice(UnitPrice value) {
        this.unitPrice = value;
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

    /**
     * Obtient la valeur de la propriété priceBasisQuantity.
     * 
     * @return
     *     possible object is
     *     {@link PriceBasisQuantity }
     *     
     */
    public PriceBasisQuantity getPriceBasisQuantity() {
        return priceBasisQuantity;
    }

    /**
     * Définit la valeur de la propriété priceBasisQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceBasisQuantity }
     *     
     */
    public void setPriceBasisQuantity(PriceBasisQuantity value) {
        this.priceBasisQuantity = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Classification }
     * 
     * 
     */
    public List<Classification> getClassification() {
        if (classification == null) {
            classification = new ArrayList<Classification>();
        }
        return this.classification;
    }

    /**
     * Gets the value of the extrinsic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extrinsic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtrinsic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Extrinsic }
     * 
     * 
     */
    public List<Extrinsic> getExtrinsic() {
        if (extrinsic == null) {
            extrinsic = new ArrayList<Extrinsic>();
        }
        return this.extrinsic;
    }

}

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
import javax.xml.bind.annotation.XmlIDREF;
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
    "taxableAmount",
    "taxAmount",
    "taxLocation",
    "taxAdjustmentAmount",
    "description",
    "triangularTransactionLawReference",
    "taxRegime",
    "taxExemption",
    "extrinsic"
})
@XmlRootElement(name = "TaxDetail")
public class TaxDetail {

    @XmlAttribute(name = "taxedElement")
    @XmlIDREF
    protected Object taxedElement;
    @XmlAttribute(name = "purpose")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String purpose;
    @XmlAttribute(name = "category", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String category;
    @XmlAttribute(name = "percentageRate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String percentageRate;
    @XmlAttribute(name = "isVatRecoverable")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isVatRecoverable;
    @XmlAttribute(name = "taxPointDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String taxPointDate;
    @XmlAttribute(name = "paymentDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String paymentDate;
    @XmlAttribute(name = "isTriangularTransaction")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isTriangularTransaction;
    @XmlAttribute(name = "exemptDetail")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String exemptDetail;
    @XmlAttribute(name = "isWithholdingTax")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isWithholdingTax;
    @XmlAttribute(name = "taxRateType")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String taxRateType;
    @XmlAttribute(name = "basePercentageRate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String basePercentageRate;
    @XmlAttribute(name = "isIncludedInPrice")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isIncludedInPrice;
    @XmlElement(name = "TaxableAmount")
    protected TaxableAmount taxableAmount;
    @XmlElement(name = "TaxAmount", required = true)
    protected TaxAmount taxAmount;
    @XmlElement(name = "TaxLocation")
    protected TaxLocation taxLocation;
    @XmlElement(name = "TaxAdjustmentAmount")
    protected TaxAdjustmentAmount taxAdjustmentAmount;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "TriangularTransactionLawReference")
    protected TriangularTransactionLawReference triangularTransactionLawReference;
    @XmlElement(name = "TaxRegime")
    protected String taxRegime;
    @XmlElement(name = "TaxExemption")
    protected TaxExemption taxExemption;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété taxedElement.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTaxedElement() {
        return taxedElement;
    }

    /**
     * Définit la valeur de la propriété taxedElement.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTaxedElement(Object value) {
        this.taxedElement = value;
    }

    /**
     * Obtient la valeur de la propriété purpose.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Définit la valeur de la propriété purpose.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurpose(String value) {
        this.purpose = value;
    }

    /**
     * Obtient la valeur de la propriété category.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Définit la valeur de la propriété category.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Obtient la valeur de la propriété percentageRate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercentageRate() {
        return percentageRate;
    }

    /**
     * Définit la valeur de la propriété percentageRate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercentageRate(String value) {
        this.percentageRate = value;
    }

    /**
     * Obtient la valeur de la propriété isVatRecoverable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsVatRecoverable() {
        return isVatRecoverable;
    }

    /**
     * Définit la valeur de la propriété isVatRecoverable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsVatRecoverable(String value) {
        this.isVatRecoverable = value;
    }

    /**
     * Obtient la valeur de la propriété taxPointDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxPointDate() {
        return taxPointDate;
    }

    /**
     * Définit la valeur de la propriété taxPointDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxPointDate(String value) {
        this.taxPointDate = value;
    }

    /**
     * Obtient la valeur de la propriété paymentDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * Définit la valeur de la propriété paymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentDate(String value) {
        this.paymentDate = value;
    }

    /**
     * Obtient la valeur de la propriété isTriangularTransaction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTriangularTransaction() {
        return isTriangularTransaction;
    }

    /**
     * Définit la valeur de la propriété isTriangularTransaction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTriangularTransaction(String value) {
        this.isTriangularTransaction = value;
    }

    /**
     * Obtient la valeur de la propriété exemptDetail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExemptDetail() {
        return exemptDetail;
    }

    /**
     * Définit la valeur de la propriété exemptDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExemptDetail(String value) {
        this.exemptDetail = value;
    }

    /**
     * Obtient la valeur de la propriété isWithholdingTax.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsWithholdingTax() {
        return isWithholdingTax;
    }

    /**
     * Définit la valeur de la propriété isWithholdingTax.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsWithholdingTax(String value) {
        this.isWithholdingTax = value;
    }

    /**
     * Obtient la valeur de la propriété taxRateType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxRateType() {
        return taxRateType;
    }

    /**
     * Définit la valeur de la propriété taxRateType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxRateType(String value) {
        this.taxRateType = value;
    }

    /**
     * Obtient la valeur de la propriété basePercentageRate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasePercentageRate() {
        return basePercentageRate;
    }

    /**
     * Définit la valeur de la propriété basePercentageRate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasePercentageRate(String value) {
        this.basePercentageRate = value;
    }

    /**
     * Obtient la valeur de la propriété isIncludedInPrice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsIncludedInPrice() {
        return isIncludedInPrice;
    }

    /**
     * Définit la valeur de la propriété isIncludedInPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsIncludedInPrice(String value) {
        this.isIncludedInPrice = value;
    }

    /**
     * Obtient la valeur de la propriété taxableAmount.
     * 
     * @return
     *     possible object is
     *     {@link TaxableAmount }
     *     
     */
    public TaxableAmount getTaxableAmount() {
        return taxableAmount;
    }

    /**
     * Définit la valeur de la propriété taxableAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxableAmount }
     *     
     */
    public void setTaxableAmount(TaxableAmount value) {
        this.taxableAmount = value;
    }

    /**
     * Obtient la valeur de la propriété taxAmount.
     * 
     * @return
     *     possible object is
     *     {@link TaxAmount }
     *     
     */
    public TaxAmount getTaxAmount() {
        return taxAmount;
    }

    /**
     * Définit la valeur de la propriété taxAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAmount }
     *     
     */
    public void setTaxAmount(TaxAmount value) {
        this.taxAmount = value;
    }

    /**
     * Obtient la valeur de la propriété taxLocation.
     * 
     * @return
     *     possible object is
     *     {@link TaxLocation }
     *     
     */
    public TaxLocation getTaxLocation() {
        return taxLocation;
    }

    /**
     * Définit la valeur de la propriété taxLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxLocation }
     *     
     */
    public void setTaxLocation(TaxLocation value) {
        this.taxLocation = value;
    }

    /**
     * Obtient la valeur de la propriété taxAdjustmentAmount.
     * 
     * @return
     *     possible object is
     *     {@link TaxAdjustmentAmount }
     *     
     */
    public TaxAdjustmentAmount getTaxAdjustmentAmount() {
        return taxAdjustmentAmount;
    }

    /**
     * Définit la valeur de la propriété taxAdjustmentAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAdjustmentAmount }
     *     
     */
    public void setTaxAdjustmentAmount(TaxAdjustmentAmount value) {
        this.taxAdjustmentAmount = value;
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
     * Obtient la valeur de la propriété triangularTransactionLawReference.
     * 
     * @return
     *     possible object is
     *     {@link TriangularTransactionLawReference }
     *     
     */
    public TriangularTransactionLawReference getTriangularTransactionLawReference() {
        return triangularTransactionLawReference;
    }

    /**
     * Définit la valeur de la propriété triangularTransactionLawReference.
     * 
     * @param value
     *     allowed object is
     *     {@link TriangularTransactionLawReference }
     *     
     */
    public void setTriangularTransactionLawReference(TriangularTransactionLawReference value) {
        this.triangularTransactionLawReference = value;
    }

    /**
     * Obtient la valeur de la propriété taxRegime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxRegime() {
        return taxRegime;
    }

    /**
     * Définit la valeur de la propriété taxRegime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxRegime(String value) {
        this.taxRegime = value;
    }

    /**
     * Obtient la valeur de la propriété taxExemption.
     * 
     * @return
     *     possible object is
     *     {@link TaxExemption }
     *     
     */
    public TaxExemption getTaxExemption() {
        return taxExemption;
    }

    /**
     * Définit la valeur de la propriété taxExemption.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxExemption }
     *     
     */
    public void setTaxExemption(TaxExemption value) {
        this.taxExemption = value;
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

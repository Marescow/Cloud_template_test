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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "maxAmount",
    "minAmount",
    "maxReleaseAmount",
    "minReleaseAmount",
    "maxQuantity",
    "minQuantity",
    "maxReleaseQuantity",
    "minReleaseQuantity",
    "termsOfDelivery",
    "itemIn",
    "referenceDocumentInfo",
    "alternative",
    "supplierProductionFacilityRelations",
    "extrinsic"
})
@XmlRootElement(name = "ContractItemIn")
public class ContractItemIn {

    @XmlAttribute(name = "operation")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String operation;
    @XmlAttribute(name = "itemType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemType;
    @XmlAttribute(name = "serviceLineType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceLineType;
    @XmlElement(name = "MaxAmount")
    protected MaxAmount maxAmount;
    @XmlElement(name = "MinAmount")
    protected MinAmount minAmount;
    @XmlElement(name = "MaxReleaseAmount")
    protected MaxReleaseAmount maxReleaseAmount;
    @XmlElement(name = "MinReleaseAmount")
    protected MinReleaseAmount minReleaseAmount;
    @XmlElement(name = "MaxQuantity")
    protected String maxQuantity;
    @XmlElement(name = "MinQuantity")
    protected String minQuantity;
    @XmlElement(name = "MaxReleaseQuantity")
    protected String maxReleaseQuantity;
    @XmlElement(name = "MinReleaseQuantity")
    protected String minReleaseQuantity;
    @XmlElement(name = "TermsOfDelivery")
    protected TermsOfDelivery termsOfDelivery;
    @XmlElement(name = "ItemIn", required = true)
    protected ItemIn itemIn;
    @XmlElement(name = "ReferenceDocumentInfo")
    protected List<ReferenceDocumentInfo> referenceDocumentInfo;
    @XmlElement(name = "Alternative")
    protected Alternative alternative;
    @XmlElement(name = "SupplierProductionFacilityRelations")
    protected SupplierProductionFacilityRelations supplierProductionFacilityRelations;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

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
     * Obtient la valeur de la propriété itemType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Définit la valeur de la propriété itemType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemType(String value) {
        this.itemType = value;
    }

    /**
     * Obtient la valeur de la propriété serviceLineType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceLineType() {
        return serviceLineType;
    }

    /**
     * Définit la valeur de la propriété serviceLineType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceLineType(String value) {
        this.serviceLineType = value;
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
     * Obtient la valeur de la propriété maxReleaseAmount.
     * 
     * @return
     *     possible object is
     *     {@link MaxReleaseAmount }
     *     
     */
    public MaxReleaseAmount getMaxReleaseAmount() {
        return maxReleaseAmount;
    }

    /**
     * Définit la valeur de la propriété maxReleaseAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxReleaseAmount }
     *     
     */
    public void setMaxReleaseAmount(MaxReleaseAmount value) {
        this.maxReleaseAmount = value;
    }

    /**
     * Obtient la valeur de la propriété minReleaseAmount.
     * 
     * @return
     *     possible object is
     *     {@link MinReleaseAmount }
     *     
     */
    public MinReleaseAmount getMinReleaseAmount() {
        return minReleaseAmount;
    }

    /**
     * Définit la valeur de la propriété minReleaseAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MinReleaseAmount }
     *     
     */
    public void setMinReleaseAmount(MinReleaseAmount value) {
        this.minReleaseAmount = value;
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
     * Obtient la valeur de la propriété maxReleaseQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxReleaseQuantity() {
        return maxReleaseQuantity;
    }

    /**
     * Définit la valeur de la propriété maxReleaseQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxReleaseQuantity(String value) {
        this.maxReleaseQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété minReleaseQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinReleaseQuantity() {
        return minReleaseQuantity;
    }

    /**
     * Définit la valeur de la propriété minReleaseQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinReleaseQuantity(String value) {
        this.minReleaseQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété termsOfDelivery.
     * 
     * @return
     *     possible object is
     *     {@link TermsOfDelivery }
     *     
     */
    public TermsOfDelivery getTermsOfDelivery() {
        return termsOfDelivery;
    }

    /**
     * Définit la valeur de la propriété termsOfDelivery.
     * 
     * @param value
     *     allowed object is
     *     {@link TermsOfDelivery }
     *     
     */
    public void setTermsOfDelivery(TermsOfDelivery value) {
        this.termsOfDelivery = value;
    }

    /**
     * Obtient la valeur de la propriété itemIn.
     * 
     * @return
     *     possible object is
     *     {@link ItemIn }
     *     
     */
    public ItemIn getItemIn() {
        return itemIn;
    }

    /**
     * Définit la valeur de la propriété itemIn.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemIn }
     *     
     */
    public void setItemIn(ItemIn value) {
        this.itemIn = value;
    }

    /**
     * Gets the value of the referenceDocumentInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceDocumentInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceDocumentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceDocumentInfo }
     * 
     * 
     */
    public List<ReferenceDocumentInfo> getReferenceDocumentInfo() {
        if (referenceDocumentInfo == null) {
            referenceDocumentInfo = new ArrayList<ReferenceDocumentInfo>();
        }
        return this.referenceDocumentInfo;
    }

    /**
     * Obtient la valeur de la propriété alternative.
     * 
     * @return
     *     possible object is
     *     {@link Alternative }
     *     
     */
    public Alternative getAlternative() {
        return alternative;
    }

    /**
     * Définit la valeur de la propriété alternative.
     * 
     * @param value
     *     allowed object is
     *     {@link Alternative }
     *     
     */
    public void setAlternative(Alternative value) {
        this.alternative = value;
    }

    /**
     * Obtient la valeur de la propriété supplierProductionFacilityRelations.
     * 
     * @return
     *     possible object is
     *     {@link SupplierProductionFacilityRelations }
     *     
     */
    public SupplierProductionFacilityRelations getSupplierProductionFacilityRelations() {
        return supplierProductionFacilityRelations;
    }

    /**
     * Définit la valeur de la propriété supplierProductionFacilityRelations.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierProductionFacilityRelations }
     *     
     */
    public void setSupplierProductionFacilityRelations(SupplierProductionFacilityRelations value) {
        this.supplierProductionFacilityRelations = value;
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

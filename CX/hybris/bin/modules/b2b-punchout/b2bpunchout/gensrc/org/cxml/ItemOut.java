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
import javax.xml.bind.annotation.XmlElements;
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
    "itemID",
    "path",
    "itemDetailOrBlanketItemDetail",
    "supplierIDOrSupplierList",
    "shipTo",
    "shipping",
    "tax",
    "spendDetail",
    "distribution",
    "contact",
    "termsOfDelivery",
    "comments",
    "tolerances",
    "controlKeys",
    "scheduleLine",
    "masterAgreementReferenceOrMasterAgreementIDInfo",
    "itemOutIndustry",
    "packaging",
    "releaseInfo",
    "batch"
})
@XmlRootElement(name = "ItemOut")
public class ItemOut {

    @XmlAttribute(name = "quantity", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String quantity;
    @XmlAttribute(name = "lineNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lineNumber;
    @XmlAttribute(name = "requisitionID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requisitionID;
    @XmlAttribute(name = "agreementItemNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String agreementItemNumber;
    @XmlAttribute(name = "requestedDeliveryDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestedDeliveryDate;
    @XmlAttribute(name = "isAdHoc")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isAdHoc;
    @XmlAttribute(name = "parentLineNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String parentLineNumber;
    @XmlAttribute(name = "itemType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemType;
    @XmlAttribute(name = "requiresServiceEntry")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String requiresServiceEntry;
    @XmlAttribute(name = "confirmationDueDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String confirmationDueDate;
    @XmlAttribute(name = "compositeItemType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String compositeItemType;
    @XmlAttribute(name = "itemClassification")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemClassification;
    @XmlAttribute(name = "itemCategory")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemCategory;
    @XmlAttribute(name = "subcontractingType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String subcontractingType;
    @XmlAttribute(name = "stockTransferType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String stockTransferType;
    @XmlAttribute(name = "requestedShipmentDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestedShipmentDate;
    @XmlAttribute(name = "isReturn")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isReturn;
    @XmlAttribute(name = "returnAuthorizationNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String returnAuthorizationNumber;
    @XmlAttribute(name = "isDeliveryCompleted")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isDeliveryCompleted;
    @XmlAttribute(name = "unlimitedDelivery")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String unlimitedDelivery;
    @XmlAttribute(name = "isItemChanged")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isItemChanged;
    @XmlAttribute(name = "isKanban")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isKanban;
    @XmlAttribute(name = "stoDelivery")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String stoDelivery;
    @XmlAttribute(name = "stoOrderCombination")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String stoOrderCombination;
    @XmlAttribute(name = "stoFinalDelivery")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String stoFinalDelivery;
    @XmlElement(name = "ItemID", required = true)
    protected ItemID itemID;
    @XmlElement(name = "Path")
    protected Path path;
    @XmlElements({
        @XmlElement(name = "ItemDetail", type = ItemDetail.class),
        @XmlElement(name = "BlanketItemDetail", type = BlanketItemDetail.class)
    })
    protected List<Object> itemDetailOrBlanketItemDetail;
    @XmlElements({
        @XmlElement(name = "SupplierID", type = SupplierID.class),
        @XmlElement(name = "SupplierList", type = SupplierList.class)
    })
    protected List<Object> supplierIDOrSupplierList;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "Shipping")
    protected Shipping shipping;
    @XmlElement(name = "Tax")
    protected Tax tax;
    @XmlElement(name = "SpendDetail")
    protected SpendDetail spendDetail;
    @XmlElement(name = "Distribution")
    protected List<Distribution> distribution;
    @XmlElement(name = "Contact")
    protected List<Contact> contact;
    @XmlElement(name = "TermsOfDelivery")
    protected TermsOfDelivery termsOfDelivery;
    @XmlElement(name = "Comments")
    protected List<Comments> comments;
    @XmlElement(name = "Tolerances")
    protected Tolerances tolerances;
    @XmlElement(name = "ControlKeys")
    protected ControlKeys controlKeys;
    @XmlElement(name = "ScheduleLine")
    protected List<ScheduleLine> scheduleLine;
    @XmlElements({
        @XmlElement(name = "MasterAgreementReference", type = MasterAgreementReference.class),
        @XmlElement(name = "MasterAgreementIDInfo", type = MasterAgreementIDInfo.class)
    })
    protected List<Object> masterAgreementReferenceOrMasterAgreementIDInfo;
    @XmlElement(name = "ItemOutIndustry")
    protected ItemOutIndustry itemOutIndustry;
    @XmlElement(name = "Packaging")
    protected List<Packaging> packaging;
    @XmlElement(name = "ReleaseInfo")
    protected ReleaseInfo releaseInfo;
    @XmlElement(name = "Batch")
    protected Batch batch;

    /**
     * Obtient la valeur de la propriété quantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Définit la valeur de la propriété quantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * Obtient la valeur de la propriété lineNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineNumber() {
        return lineNumber;
    }

    /**
     * Définit la valeur de la propriété lineNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineNumber(String value) {
        this.lineNumber = value;
    }

    /**
     * Obtient la valeur de la propriété requisitionID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequisitionID() {
        return requisitionID;
    }

    /**
     * Définit la valeur de la propriété requisitionID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequisitionID(String value) {
        this.requisitionID = value;
    }

    /**
     * Obtient la valeur de la propriété agreementItemNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementItemNumber() {
        return agreementItemNumber;
    }

    /**
     * Définit la valeur de la propriété agreementItemNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementItemNumber(String value) {
        this.agreementItemNumber = value;
    }

    /**
     * Obtient la valeur de la propriété requestedDeliveryDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    /**
     * Définit la valeur de la propriété requestedDeliveryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedDeliveryDate(String value) {
        this.requestedDeliveryDate = value;
    }

    /**
     * Obtient la valeur de la propriété isAdHoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAdHoc() {
        return isAdHoc;
    }

    /**
     * Définit la valeur de la propriété isAdHoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAdHoc(String value) {
        this.isAdHoc = value;
    }

    /**
     * Obtient la valeur de la propriété parentLineNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentLineNumber() {
        return parentLineNumber;
    }

    /**
     * Définit la valeur de la propriété parentLineNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentLineNumber(String value) {
        this.parentLineNumber = value;
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
     * Obtient la valeur de la propriété requiresServiceEntry.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiresServiceEntry() {
        return requiresServiceEntry;
    }

    /**
     * Définit la valeur de la propriété requiresServiceEntry.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiresServiceEntry(String value) {
        this.requiresServiceEntry = value;
    }

    /**
     * Obtient la valeur de la propriété confirmationDueDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmationDueDate() {
        return confirmationDueDate;
    }

    /**
     * Définit la valeur de la propriété confirmationDueDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmationDueDate(String value) {
        this.confirmationDueDate = value;
    }

    /**
     * Obtient la valeur de la propriété compositeItemType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompositeItemType() {
        return compositeItemType;
    }

    /**
     * Définit la valeur de la propriété compositeItemType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompositeItemType(String value) {
        this.compositeItemType = value;
    }

    /**
     * Obtient la valeur de la propriété itemClassification.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemClassification() {
        return itemClassification;
    }

    /**
     * Définit la valeur de la propriété itemClassification.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemClassification(String value) {
        this.itemClassification = value;
    }

    /**
     * Obtient la valeur de la propriété itemCategory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemCategory() {
        return itemCategory;
    }

    /**
     * Définit la valeur de la propriété itemCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemCategory(String value) {
        this.itemCategory = value;
    }

    /**
     * Obtient la valeur de la propriété subcontractingType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcontractingType() {
        return subcontractingType;
    }

    /**
     * Définit la valeur de la propriété subcontractingType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcontractingType(String value) {
        this.subcontractingType = value;
    }

    /**
     * Obtient la valeur de la propriété stockTransferType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStockTransferType() {
        return stockTransferType;
    }

    /**
     * Définit la valeur de la propriété stockTransferType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStockTransferType(String value) {
        this.stockTransferType = value;
    }

    /**
     * Obtient la valeur de la propriété requestedShipmentDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedShipmentDate() {
        return requestedShipmentDate;
    }

    /**
     * Définit la valeur de la propriété requestedShipmentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedShipmentDate(String value) {
        this.requestedShipmentDate = value;
    }

    /**
     * Obtient la valeur de la propriété isReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReturn() {
        return isReturn;
    }

    /**
     * Définit la valeur de la propriété isReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReturn(String value) {
        this.isReturn = value;
    }

    /**
     * Obtient la valeur de la propriété returnAuthorizationNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnAuthorizationNumber() {
        return returnAuthorizationNumber;
    }

    /**
     * Définit la valeur de la propriété returnAuthorizationNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnAuthorizationNumber(String value) {
        this.returnAuthorizationNumber = value;
    }

    /**
     * Obtient la valeur de la propriété isDeliveryCompleted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDeliveryCompleted() {
        return isDeliveryCompleted;
    }

    /**
     * Définit la valeur de la propriété isDeliveryCompleted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDeliveryCompleted(String value) {
        this.isDeliveryCompleted = value;
    }

    /**
     * Obtient la valeur de la propriété unlimitedDelivery.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnlimitedDelivery() {
        return unlimitedDelivery;
    }

    /**
     * Définit la valeur de la propriété unlimitedDelivery.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnlimitedDelivery(String value) {
        this.unlimitedDelivery = value;
    }

    /**
     * Obtient la valeur de la propriété isItemChanged.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsItemChanged() {
        return isItemChanged;
    }

    /**
     * Définit la valeur de la propriété isItemChanged.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsItemChanged(String value) {
        this.isItemChanged = value;
    }

    /**
     * Obtient la valeur de la propriété isKanban.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsKanban() {
        return isKanban;
    }

    /**
     * Définit la valeur de la propriété isKanban.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsKanban(String value) {
        this.isKanban = value;
    }

    /**
     * Obtient la valeur de la propriété stoDelivery.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoDelivery() {
        return stoDelivery;
    }

    /**
     * Définit la valeur de la propriété stoDelivery.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoDelivery(String value) {
        this.stoDelivery = value;
    }

    /**
     * Obtient la valeur de la propriété stoOrderCombination.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoOrderCombination() {
        return stoOrderCombination;
    }

    /**
     * Définit la valeur de la propriété stoOrderCombination.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoOrderCombination(String value) {
        this.stoOrderCombination = value;
    }

    /**
     * Obtient la valeur de la propriété stoFinalDelivery.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoFinalDelivery() {
        return stoFinalDelivery;
    }

    /**
     * Définit la valeur de la propriété stoFinalDelivery.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoFinalDelivery(String value) {
        this.stoFinalDelivery = value;
    }

    /**
     * Obtient la valeur de la propriété itemID.
     * 
     * @return
     *     possible object is
     *     {@link ItemID }
     *     
     */
    public ItemID getItemID() {
        return itemID;
    }

    /**
     * Définit la valeur de la propriété itemID.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemID }
     *     
     */
    public void setItemID(ItemID value) {
        this.itemID = value;
    }

    /**
     * Obtient la valeur de la propriété path.
     * 
     * @return
     *     possible object is
     *     {@link Path }
     *     
     */
    public Path getPath() {
        return path;
    }

    /**
     * Définit la valeur de la propriété path.
     * 
     * @param value
     *     allowed object is
     *     {@link Path }
     *     
     */
    public void setPath(Path value) {
        this.path = value;
    }

    /**
     * Gets the value of the itemDetailOrBlanketItemDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemDetailOrBlanketItemDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemDetailOrBlanketItemDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemDetail }
     * {@link BlanketItemDetail }
     * 
     * 
     */
    public List<Object> getItemDetailOrBlanketItemDetail() {
        if (itemDetailOrBlanketItemDetail == null) {
            itemDetailOrBlanketItemDetail = new ArrayList<Object>();
        }
        return this.itemDetailOrBlanketItemDetail;
    }

    /**
     * Gets the value of the supplierIDOrSupplierList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplierIDOrSupplierList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplierIDOrSupplierList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplierID }
     * {@link SupplierList }
     * 
     * 
     */
    public List<Object> getSupplierIDOrSupplierList() {
        if (supplierIDOrSupplierList == null) {
            supplierIDOrSupplierList = new ArrayList<Object>();
        }
        return this.supplierIDOrSupplierList;
    }

    /**
     * Obtient la valeur de la propriété shipTo.
     * 
     * @return
     *     possible object is
     *     {@link ShipTo }
     *     
     */
    public ShipTo getShipTo() {
        return shipTo;
    }

    /**
     * Définit la valeur de la propriété shipTo.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipTo }
     *     
     */
    public void setShipTo(ShipTo value) {
        this.shipTo = value;
    }

    /**
     * Obtient la valeur de la propriété shipping.
     * 
     * @return
     *     possible object is
     *     {@link Shipping }
     *     
     */
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * Définit la valeur de la propriété shipping.
     * 
     * @param value
     *     allowed object is
     *     {@link Shipping }
     *     
     */
    public void setShipping(Shipping value) {
        this.shipping = value;
    }

    /**
     * Obtient la valeur de la propriété tax.
     * 
     * @return
     *     possible object is
     *     {@link Tax }
     *     
     */
    public Tax getTax() {
        return tax;
    }

    /**
     * Définit la valeur de la propriété tax.
     * 
     * @param value
     *     allowed object is
     *     {@link Tax }
     *     
     */
    public void setTax(Tax value) {
        this.tax = value;
    }

    /**
     * Obtient la valeur de la propriété spendDetail.
     * 
     * @return
     *     possible object is
     *     {@link SpendDetail }
     *     
     */
    public SpendDetail getSpendDetail() {
        return spendDetail;
    }

    /**
     * Définit la valeur de la propriété spendDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link SpendDetail }
     *     
     */
    public void setSpendDetail(SpendDetail value) {
        this.spendDetail = value;
    }

    /**
     * Gets the value of the distribution property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the distribution property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDistribution().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Distribution }
     * 
     * 
     */
    public List<Distribution> getDistribution() {
        if (distribution == null) {
            distribution = new ArrayList<Distribution>();
        }
        return this.distribution;
    }

    /**
     * Gets the value of the contact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contact }
     * 
     * 
     */
    public List<Contact> getContact() {
        if (contact == null) {
            contact = new ArrayList<Contact>();
        }
        return this.contact;
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
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comments }
     * 
     * 
     */
    public List<Comments> getComments() {
        if (comments == null) {
            comments = new ArrayList<Comments>();
        }
        return this.comments;
    }

    /**
     * Obtient la valeur de la propriété tolerances.
     * 
     * @return
     *     possible object is
     *     {@link Tolerances }
     *     
     */
    public Tolerances getTolerances() {
        return tolerances;
    }

    /**
     * Définit la valeur de la propriété tolerances.
     * 
     * @param value
     *     allowed object is
     *     {@link Tolerances }
     *     
     */
    public void setTolerances(Tolerances value) {
        this.tolerances = value;
    }

    /**
     * Obtient la valeur de la propriété controlKeys.
     * 
     * @return
     *     possible object is
     *     {@link ControlKeys }
     *     
     */
    public ControlKeys getControlKeys() {
        return controlKeys;
    }

    /**
     * Définit la valeur de la propriété controlKeys.
     * 
     * @param value
     *     allowed object is
     *     {@link ControlKeys }
     *     
     */
    public void setControlKeys(ControlKeys value) {
        this.controlKeys = value;
    }

    /**
     * Gets the value of the scheduleLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduleLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduleLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScheduleLine }
     * 
     * 
     */
    public List<ScheduleLine> getScheduleLine() {
        if (scheduleLine == null) {
            scheduleLine = new ArrayList<ScheduleLine>();
        }
        return this.scheduleLine;
    }

    /**
     * Gets the value of the masterAgreementReferenceOrMasterAgreementIDInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the masterAgreementReferenceOrMasterAgreementIDInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMasterAgreementReferenceOrMasterAgreementIDInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MasterAgreementReference }
     * {@link MasterAgreementIDInfo }
     * 
     * 
     */
    public List<Object> getMasterAgreementReferenceOrMasterAgreementIDInfo() {
        if (masterAgreementReferenceOrMasterAgreementIDInfo == null) {
            masterAgreementReferenceOrMasterAgreementIDInfo = new ArrayList<Object>();
        }
        return this.masterAgreementReferenceOrMasterAgreementIDInfo;
    }

    /**
     * Obtient la valeur de la propriété itemOutIndustry.
     * 
     * @return
     *     possible object is
     *     {@link ItemOutIndustry }
     *     
     */
    public ItemOutIndustry getItemOutIndustry() {
        return itemOutIndustry;
    }

    /**
     * Définit la valeur de la propriété itemOutIndustry.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemOutIndustry }
     *     
     */
    public void setItemOutIndustry(ItemOutIndustry value) {
        this.itemOutIndustry = value;
    }

    /**
     * Gets the value of the packaging property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packaging property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackaging().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Packaging }
     * 
     * 
     */
    public List<Packaging> getPackaging() {
        if (packaging == null) {
            packaging = new ArrayList<Packaging>();
        }
        return this.packaging;
    }

    /**
     * Obtient la valeur de la propriété releaseInfo.
     * 
     * @return
     *     possible object is
     *     {@link ReleaseInfo }
     *     
     */
    public ReleaseInfo getReleaseInfo() {
        return releaseInfo;
    }

    /**
     * Définit la valeur de la propriété releaseInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ReleaseInfo }
     *     
     */
    public void setReleaseInfo(ReleaseInfo value) {
        this.releaseInfo = value;
    }

    /**
     * Obtient la valeur de la propriété batch.
     * 
     * @return
     *     possible object is
     *     {@link Batch }
     *     
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * Définit la valeur de la propriété batch.
     * 
     * @param value
     *     allowed object is
     *     {@link Batch }
     *     
     */
    public void setBatch(Batch value) {
        this.batch = value;
    }

}

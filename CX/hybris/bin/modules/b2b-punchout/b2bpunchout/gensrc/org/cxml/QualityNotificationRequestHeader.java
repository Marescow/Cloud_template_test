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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "documentReference",
    "qualityInspectionRequestReference",
    "qnCode",
    "shipTo",
    "billTo",
    "shipping",
    "contact",
    "qnNotes",
    "priority",
    "requestedProcessingPeriod",
    "malfunctionPeriod",
    "referenceDocumentInfo",
    "itemInfo",
    "batch",
    "complainQuantity",
    "returnQuantity",
    "qualityNotificationTask",
    "qualityNotificationActivity",
    "assetInfo",
    "extrinsic"
})
@XmlRootElement(name = "QualityNotificationRequestHeader")
public class QualityNotificationRequestHeader {

    @XmlAttribute(name = "requestID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestID;
    @XmlAttribute(name = "externalRequestID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String externalRequestID;
    @XmlAttribute(name = "requestDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestDate;
    @XmlAttribute(name = "requestVersion")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestVersion;
    @XmlAttribute(name = "operation")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String operation;
    @XmlAttribute(name = "status")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String status;
    @XmlAttribute(name = "discoveryDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String discoveryDate;
    @XmlAttribute(name = "serialNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String serialNumber;
    @XmlAttribute(name = "returnDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String returnDate;
    @XmlAttribute(name = "returnAuthorizationNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String returnAuthorizationNumber;
    @XmlAttribute(name = "itemCategory")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemCategory;
    @XmlAttribute(name = "minimumRequiredTasks")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minimumRequiredTasks;
    @XmlAttribute(name = "minimumRequiredActivities")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minimumRequiredActivities;
    @XmlAttribute(name = "minimumRequiredCauses")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minimumRequiredCauses;
    @XmlElement(name = "DocumentReference")
    protected DocumentReference documentReference;
    @XmlElement(name = "QualityInspectionRequestReference")
    protected QualityInspectionRequestReference qualityInspectionRequestReference;
    @XmlElement(name = "QNCode")
    protected List<QNCode> qnCode;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "BillTo")
    protected BillTo billTo;
    @XmlElement(name = "Shipping")
    protected Shipping shipping;
    @XmlElement(name = "Contact")
    protected List<Contact> contact;
    @XmlElement(name = "QNNotes", required = true)
    protected List<QNNotes> qnNotes;
    @XmlElement(name = "Priority", required = true)
    protected Priority priority;
    @XmlElement(name = "RequestedProcessingPeriod")
    protected RequestedProcessingPeriod requestedProcessingPeriod;
    @XmlElement(name = "MalfunctionPeriod")
    protected MalfunctionPeriod malfunctionPeriod;
    @XmlElement(name = "ReferenceDocumentInfo")
    protected ReferenceDocumentInfo referenceDocumentInfo;
    @XmlElement(name = "ItemInfo", required = true)
    protected ItemInfo itemInfo;
    @XmlElement(name = "Batch")
    protected Batch batch;
    @XmlElement(name = "ComplainQuantity")
    protected ComplainQuantity complainQuantity;
    @XmlElement(name = "ReturnQuantity")
    protected ReturnQuantity returnQuantity;
    @XmlElement(name = "QualityNotificationTask")
    protected List<QualityNotificationTask> qualityNotificationTask;
    @XmlElement(name = "QualityNotificationActivity")
    protected List<QualityNotificationActivity> qualityNotificationActivity;
    @XmlElement(name = "AssetInfo")
    protected List<AssetInfo> assetInfo;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété requestID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Définit la valeur de la propriété requestID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestID(String value) {
        this.requestID = value;
    }

    /**
     * Obtient la valeur de la propriété externalRequestID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalRequestID() {
        return externalRequestID;
    }

    /**
     * Définit la valeur de la propriété externalRequestID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalRequestID(String value) {
        this.externalRequestID = value;
    }

    /**
     * Obtient la valeur de la propriété requestDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * Définit la valeur de la propriété requestDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestDate(String value) {
        this.requestDate = value;
    }

    /**
     * Obtient la valeur de la propriété requestVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestVersion() {
        return requestVersion;
    }

    /**
     * Définit la valeur de la propriété requestVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestVersion(String value) {
        this.requestVersion = value;
    }

    /**
     * Obtient la valeur de la propriété operation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        if (operation == null) {
            return "new";
        } else {
            return operation;
        }
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
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        if (status == null) {
            return "new";
        } else {
            return status;
        }
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtient la valeur de la propriété discoveryDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscoveryDate() {
        return discoveryDate;
    }

    /**
     * Définit la valeur de la propriété discoveryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscoveryDate(String value) {
        this.discoveryDate = value;
    }

    /**
     * Obtient la valeur de la propriété serialNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Définit la valeur de la propriété serialNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Obtient la valeur de la propriété returnDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Définit la valeur de la propriété returnDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnDate(String value) {
        this.returnDate = value;
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
     * Obtient la valeur de la propriété minimumRequiredTasks.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumRequiredTasks() {
        return minimumRequiredTasks;
    }

    /**
     * Définit la valeur de la propriété minimumRequiredTasks.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumRequiredTasks(String value) {
        this.minimumRequiredTasks = value;
    }

    /**
     * Obtient la valeur de la propriété minimumRequiredActivities.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumRequiredActivities() {
        return minimumRequiredActivities;
    }

    /**
     * Définit la valeur de la propriété minimumRequiredActivities.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumRequiredActivities(String value) {
        this.minimumRequiredActivities = value;
    }

    /**
     * Obtient la valeur de la propriété minimumRequiredCauses.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumRequiredCauses() {
        return minimumRequiredCauses;
    }

    /**
     * Définit la valeur de la propriété minimumRequiredCauses.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumRequiredCauses(String value) {
        this.minimumRequiredCauses = value;
    }

    /**
     * Obtient la valeur de la propriété documentReference.
     * 
     * @return
     *     possible object is
     *     {@link DocumentReference }
     *     
     */
    public DocumentReference getDocumentReference() {
        return documentReference;
    }

    /**
     * Définit la valeur de la propriété documentReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentReference }
     *     
     */
    public void setDocumentReference(DocumentReference value) {
        this.documentReference = value;
    }

    /**
     * Obtient la valeur de la propriété qualityInspectionRequestReference.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionRequestReference }
     *     
     */
    public QualityInspectionRequestReference getQualityInspectionRequestReference() {
        return qualityInspectionRequestReference;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionRequestReference.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionRequestReference }
     *     
     */
    public void setQualityInspectionRequestReference(QualityInspectionRequestReference value) {
        this.qualityInspectionRequestReference = value;
    }

    /**
     * Gets the value of the qnCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qnCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQNCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QNCode }
     * 
     * 
     */
    public List<QNCode> getQNCode() {
        if (qnCode == null) {
            qnCode = new ArrayList<QNCode>();
        }
        return this.qnCode;
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
     * Obtient la valeur de la propriété billTo.
     * 
     * @return
     *     possible object is
     *     {@link BillTo }
     *     
     */
    public BillTo getBillTo() {
        return billTo;
    }

    /**
     * Définit la valeur de la propriété billTo.
     * 
     * @param value
     *     allowed object is
     *     {@link BillTo }
     *     
     */
    public void setBillTo(BillTo value) {
        this.billTo = value;
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
     * Gets the value of the qnNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qnNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQNNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QNNotes }
     * 
     * 
     */
    public List<QNNotes> getQNNotes() {
        if (qnNotes == null) {
            qnNotes = new ArrayList<QNNotes>();
        }
        return this.qnNotes;
    }

    /**
     * Obtient la valeur de la propriété priority.
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Définit la valeur de la propriété priority.
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPriority(Priority value) {
        this.priority = value;
    }

    /**
     * Obtient la valeur de la propriété requestedProcessingPeriod.
     * 
     * @return
     *     possible object is
     *     {@link RequestedProcessingPeriod }
     *     
     */
    public RequestedProcessingPeriod getRequestedProcessingPeriod() {
        return requestedProcessingPeriod;
    }

    /**
     * Définit la valeur de la propriété requestedProcessingPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestedProcessingPeriod }
     *     
     */
    public void setRequestedProcessingPeriod(RequestedProcessingPeriod value) {
        this.requestedProcessingPeriod = value;
    }

    /**
     * Obtient la valeur de la propriété malfunctionPeriod.
     * 
     * @return
     *     possible object is
     *     {@link MalfunctionPeriod }
     *     
     */
    public MalfunctionPeriod getMalfunctionPeriod() {
        return malfunctionPeriod;
    }

    /**
     * Définit la valeur de la propriété malfunctionPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link MalfunctionPeriod }
     *     
     */
    public void setMalfunctionPeriod(MalfunctionPeriod value) {
        this.malfunctionPeriod = value;
    }

    /**
     * Obtient la valeur de la propriété referenceDocumentInfo.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDocumentInfo }
     *     
     */
    public ReferenceDocumentInfo getReferenceDocumentInfo() {
        return referenceDocumentInfo;
    }

    /**
     * Définit la valeur de la propriété referenceDocumentInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDocumentInfo }
     *     
     */
    public void setReferenceDocumentInfo(ReferenceDocumentInfo value) {
        this.referenceDocumentInfo = value;
    }

    /**
     * Obtient la valeur de la propriété itemInfo.
     * 
     * @return
     *     possible object is
     *     {@link ItemInfo }
     *     
     */
    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    /**
     * Définit la valeur de la propriété itemInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemInfo }
     *     
     */
    public void setItemInfo(ItemInfo value) {
        this.itemInfo = value;
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

    /**
     * Obtient la valeur de la propriété complainQuantity.
     * 
     * @return
     *     possible object is
     *     {@link ComplainQuantity }
     *     
     */
    public ComplainQuantity getComplainQuantity() {
        return complainQuantity;
    }

    /**
     * Définit la valeur de la propriété complainQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplainQuantity }
     *     
     */
    public void setComplainQuantity(ComplainQuantity value) {
        this.complainQuantity = value;
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

    /**
     * Gets the value of the qualityNotificationTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityNotificationTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityNotificationTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityNotificationTask }
     * 
     * 
     */
    public List<QualityNotificationTask> getQualityNotificationTask() {
        if (qualityNotificationTask == null) {
            qualityNotificationTask = new ArrayList<QualityNotificationTask>();
        }
        return this.qualityNotificationTask;
    }

    /**
     * Gets the value of the qualityNotificationActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityNotificationActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityNotificationActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityNotificationActivity }
     * 
     * 
     */
    public List<QualityNotificationActivity> getQualityNotificationActivity() {
        if (qualityNotificationActivity == null) {
            qualityNotificationActivity = new ArrayList<QualityNotificationActivity>();
        }
        return this.qualityNotificationActivity;
    }

    /**
     * Gets the value of the assetInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetInfo }
     * 
     * 
     */
    public List<AssetInfo> getAssetInfo() {
        if (assetInfo == null) {
            assetInfo = new ArrayList<AssetInfo>();
        }
        return this.assetInfo;
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

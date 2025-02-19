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
    "total",
    "shipTo",
    "billTo",
    "businessPartner",
    "legalEntity",
    "organizationalUnit",
    "shipping",
    "tax",
    "payment",
    "paymentTerm",
    "contact",
    "comments",
    "followup",
    "controlKeys",
    "documentReference",
    "supplierOrderInfo",
    "termsOfDelivery",
    "deliveryPeriod",
    "idReference",
    "orderRequestHeaderIndustry",
    "extrinsic"
})
@XmlRootElement(name = "OrderRequestHeader")
public class OrderRequestHeader {

    @XmlAttribute(name = "orderID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String orderID;
    @XmlAttribute(name = "orderDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String orderDate;
    @XmlAttribute(name = "orderType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String orderType;
    @XmlAttribute(name = "releaseRequired")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String releaseRequired;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "orderVersion")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String orderVersion;
    @XmlAttribute(name = "isInternalVersion")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isInternalVersion;
    @XmlAttribute(name = "parentAgreementID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String parentAgreementID;
    @XmlAttribute(name = "parentAgreementPayloadID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String parentAgreementPayloadID;
    @XmlAttribute(name = "effectiveDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String expirationDate;
    @XmlAttribute(name = "agreementID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String agreementID;
    @XmlAttribute(name = "agreementPayloadID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String agreementPayloadID;
    @XmlAttribute(name = "requisitionID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requisitionID;
    @XmlAttribute(name = "shipComplete")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String shipComplete;
    @XmlAttribute(name = "pickUpDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pickUpDate;
    @XmlAttribute(name = "requestedDeliveryDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestedDeliveryDate;
    @XmlAttribute(name = "isSTOOutbound")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isSTOOutbound;
    @XmlElement(name = "Total", required = true)
    protected Total total;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "BillTo", required = true)
    protected BillTo billTo;
    @XmlElement(name = "BusinessPartner")
    protected List<BusinessPartner> businessPartner;
    @XmlElement(name = "LegalEntity")
    protected LegalEntity legalEntity;
    @XmlElement(name = "OrganizationalUnit")
    protected List<OrganizationalUnit> organizationalUnit;
    @XmlElement(name = "Shipping")
    protected Shipping shipping;
    @XmlElement(name = "Tax")
    protected Tax tax;
    @XmlElement(name = "Payment")
    protected Payment payment;
    @XmlElement(name = "PaymentTerm")
    protected List<PaymentTerm> paymentTerm;
    @XmlElement(name = "Contact")
    protected List<Contact> contact;
    @XmlElement(name = "Comments")
    protected List<Comments> comments;
    @XmlElement(name = "Followup")
    protected Followup followup;
    @XmlElement(name = "ControlKeys")
    protected ControlKeys controlKeys;
    @XmlElement(name = "DocumentReference")
    protected DocumentReference documentReference;
    @XmlElement(name = "SupplierOrderInfo")
    protected SupplierOrderInfo supplierOrderInfo;
    @XmlElement(name = "TermsOfDelivery")
    protected TermsOfDelivery termsOfDelivery;
    @XmlElement(name = "DeliveryPeriod")
    protected DeliveryPeriod deliveryPeriod;
    @XmlElement(name = "IdReference")
    protected List<IdReference> idReference;
    @XmlElement(name = "OrderRequestHeaderIndustry")
    protected OrderRequestHeaderIndustry orderRequestHeaderIndustry;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété orderID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Définit la valeur de la propriété orderID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderID(String value) {
        this.orderID = value;
    }

    /**
     * Obtient la valeur de la propriété orderDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Définit la valeur de la propriété orderDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDate(String value) {
        this.orderDate = value;
    }

    /**
     * Obtient la valeur de la propriété orderType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        if (orderType == null) {
            return "regular";
        } else {
            return orderType;
        }
    }

    /**
     * Définit la valeur de la propriété orderType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Obtient la valeur de la propriété releaseRequired.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseRequired() {
        return releaseRequired;
    }

    /**
     * Définit la valeur de la propriété releaseRequired.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseRequired(String value) {
        this.releaseRequired = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "new";
        } else {
            return type;
        }
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété orderVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderVersion() {
        return orderVersion;
    }

    /**
     * Définit la valeur de la propriété orderVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderVersion(String value) {
        this.orderVersion = value;
    }

    /**
     * Obtient la valeur de la propriété isInternalVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsInternalVersion() {
        return isInternalVersion;
    }

    /**
     * Définit la valeur de la propriété isInternalVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsInternalVersion(String value) {
        this.isInternalVersion = value;
    }

    /**
     * Obtient la valeur de la propriété parentAgreementID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentAgreementID() {
        return parentAgreementID;
    }

    /**
     * Définit la valeur de la propriété parentAgreementID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentAgreementID(String value) {
        this.parentAgreementID = value;
    }

    /**
     * Obtient la valeur de la propriété parentAgreementPayloadID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentAgreementPayloadID() {
        return parentAgreementPayloadID;
    }

    /**
     * Définit la valeur de la propriété parentAgreementPayloadID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentAgreementPayloadID(String value) {
        this.parentAgreementPayloadID = value;
    }

    /**
     * Obtient la valeur de la propriété effectiveDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Définit la valeur de la propriété effectiveDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveDate(String value) {
        this.effectiveDate = value;
    }

    /**
     * Obtient la valeur de la propriété expirationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Définit la valeur de la propriété expirationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

    /**
     * Obtient la valeur de la propriété agreementID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementID() {
        return agreementID;
    }

    /**
     * Définit la valeur de la propriété agreementID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementID(String value) {
        this.agreementID = value;
    }

    /**
     * Obtient la valeur de la propriété agreementPayloadID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementPayloadID() {
        return agreementPayloadID;
    }

    /**
     * Définit la valeur de la propriété agreementPayloadID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementPayloadID(String value) {
        this.agreementPayloadID = value;
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
     * Obtient la valeur de la propriété shipComplete.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipComplete() {
        return shipComplete;
    }

    /**
     * Définit la valeur de la propriété shipComplete.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipComplete(String value) {
        this.shipComplete = value;
    }

    /**
     * Obtient la valeur de la propriété pickUpDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickUpDate() {
        return pickUpDate;
    }

    /**
     * Définit la valeur de la propriété pickUpDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickUpDate(String value) {
        this.pickUpDate = value;
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
     * Obtient la valeur de la propriété isSTOOutbound.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSTOOutbound() {
        return isSTOOutbound;
    }

    /**
     * Définit la valeur de la propriété isSTOOutbound.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSTOOutbound(String value) {
        this.isSTOOutbound = value;
    }

    /**
     * Obtient la valeur de la propriété total.
     * 
     * @return
     *     possible object is
     *     {@link Total }
     *     
     */
    public Total getTotal() {
        return total;
    }

    /**
     * Définit la valeur de la propriété total.
     * 
     * @param value
     *     allowed object is
     *     {@link Total }
     *     
     */
    public void setTotal(Total value) {
        this.total = value;
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
     * Gets the value of the businessPartner property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessPartner property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessPartner().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessPartner }
     * 
     * 
     */
    public List<BusinessPartner> getBusinessPartner() {
        if (businessPartner == null) {
            businessPartner = new ArrayList<BusinessPartner>();
        }
        return this.businessPartner;
    }

    /**
     * Obtient la valeur de la propriété legalEntity.
     * 
     * @return
     *     possible object is
     *     {@link LegalEntity }
     *     
     */
    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    /**
     * Définit la valeur de la propriété legalEntity.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalEntity }
     *     
     */
    public void setLegalEntity(LegalEntity value) {
        this.legalEntity = value;
    }

    /**
     * Gets the value of the organizationalUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the organizationalUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrganizationalUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganizationalUnit }
     * 
     * 
     */
    public List<OrganizationalUnit> getOrganizationalUnit() {
        if (organizationalUnit == null) {
            organizationalUnit = new ArrayList<OrganizationalUnit>();
        }
        return this.organizationalUnit;
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
     * Obtient la valeur de la propriété payment.
     * 
     * @return
     *     possible object is
     *     {@link Payment }
     *     
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Définit la valeur de la propriété payment.
     * 
     * @param value
     *     allowed object is
     *     {@link Payment }
     *     
     */
    public void setPayment(Payment value) {
        this.payment = value;
    }

    /**
     * Gets the value of the paymentTerm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentTerm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentTerm }
     * 
     * 
     */
    public List<PaymentTerm> getPaymentTerm() {
        if (paymentTerm == null) {
            paymentTerm = new ArrayList<PaymentTerm>();
        }
        return this.paymentTerm;
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
     * Obtient la valeur de la propriété followup.
     * 
     * @return
     *     possible object is
     *     {@link Followup }
     *     
     */
    public Followup getFollowup() {
        return followup;
    }

    /**
     * Définit la valeur de la propriété followup.
     * 
     * @param value
     *     allowed object is
     *     {@link Followup }
     *     
     */
    public void setFollowup(Followup value) {
        this.followup = value;
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
     * Obtient la valeur de la propriété supplierOrderInfo.
     * 
     * @return
     *     possible object is
     *     {@link SupplierOrderInfo }
     *     
     */
    public SupplierOrderInfo getSupplierOrderInfo() {
        return supplierOrderInfo;
    }

    /**
     * Définit la valeur de la propriété supplierOrderInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierOrderInfo }
     *     
     */
    public void setSupplierOrderInfo(SupplierOrderInfo value) {
        this.supplierOrderInfo = value;
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
     * Obtient la valeur de la propriété deliveryPeriod.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryPeriod }
     *     
     */
    public DeliveryPeriod getDeliveryPeriod() {
        return deliveryPeriod;
    }

    /**
     * Définit la valeur de la propriété deliveryPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryPeriod }
     *     
     */
    public void setDeliveryPeriod(DeliveryPeriod value) {
        this.deliveryPeriod = value;
    }

    /**
     * Gets the value of the idReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdReference }
     * 
     * 
     */
    public List<IdReference> getIdReference() {
        if (idReference == null) {
            idReference = new ArrayList<IdReference>();
        }
        return this.idReference;
    }

    /**
     * Obtient la valeur de la propriété orderRequestHeaderIndustry.
     * 
     * @return
     *     possible object is
     *     {@link OrderRequestHeaderIndustry }
     *     
     */
    public OrderRequestHeaderIndustry getOrderRequestHeaderIndustry() {
        return orderRequestHeaderIndustry;
    }

    /**
     * Définit la valeur de la propriété orderRequestHeaderIndustry.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderRequestHeaderIndustry }
     *     
     */
    public void setOrderRequestHeaderIndustry(OrderRequestHeaderIndustry value) {
        this.orderRequestHeaderIndustry = value;
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

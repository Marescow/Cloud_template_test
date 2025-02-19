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
    "legalEntity",
    "organizationID",
    "organizationalUnit",
    "paymentTerm",
    "quoteRequestReference",
    "maxAmount",
    "minAmount",
    "maxReleaseAmount",
    "minReleaseAmount",
    "contact",
    "comments",
    "documentInfo",
    "parentContractInfo",
    "followUpDocument",
    "termsOfDelivery",
    "supplierProductionFacilityRelations",
    "extrinsic"
})
@XmlRootElement(name = "ContractRequestHeader")
public class ContractRequestHeader {

    @XmlAttribute(name = "contractID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String contractID;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "createDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String createDate;
    @XmlAttribute(name = "agreementDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String agreementDate;
    @XmlAttribute(name = "effectiveDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String expirationDate;
    @XmlAttribute(name = "xml:lang", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlLang;
    @XmlAttribute(name = "operation")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String operation;
    @XmlElement(name = "LegalEntity", required = true)
    protected LegalEntity legalEntity;
    @XmlElement(name = "OrganizationID", required = true)
    protected OrganizationID organizationID;
    @XmlElement(name = "OrganizationalUnit")
    protected List<OrganizationalUnit> organizationalUnit;
    @XmlElement(name = "PaymentTerm")
    protected List<PaymentTerm> paymentTerm;
    @XmlElement(name = "QuoteRequestReference")
    protected QuoteRequestReference quoteRequestReference;
    @XmlElement(name = "MaxAmount")
    protected MaxAmount maxAmount;
    @XmlElement(name = "MinAmount")
    protected MinAmount minAmount;
    @XmlElement(name = "MaxReleaseAmount")
    protected MaxReleaseAmount maxReleaseAmount;
    @XmlElement(name = "MinReleaseAmount")
    protected MinReleaseAmount minReleaseAmount;
    @XmlElement(name = "Contact")
    protected List<Contact> contact;
    @XmlElement(name = "Comments")
    protected Comments comments;
    @XmlElement(name = "DocumentInfo")
    protected DocumentInfo documentInfo;
    @XmlElement(name = "ParentContractInfo")
    protected ParentContractInfo parentContractInfo;
    @XmlElement(name = "FollowUpDocument")
    protected FollowUpDocument followUpDocument;
    @XmlElement(name = "TermsOfDelivery")
    protected TermsOfDelivery termsOfDelivery;
    @XmlElement(name = "SupplierProductionFacilityRelations")
    protected SupplierProductionFacilityRelations supplierProductionFacilityRelations;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété contractID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractID() {
        return contractID;
    }

    /**
     * Définit la valeur de la propriété contractID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractID(String value) {
        this.contractID = value;
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
            return "value";
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
     * Obtient la valeur de la propriété createDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Définit la valeur de la propriété createDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateDate(String value) {
        this.createDate = value;
    }

    /**
     * Obtient la valeur de la propriété agreementDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementDate() {
        return agreementDate;
    }

    /**
     * Définit la valeur de la propriété agreementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementDate(String value) {
        this.agreementDate = value;
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
     * Obtient la valeur de la propriété xmlLang.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlLang() {
        return xmlLang;
    }

    /**
     * Définit la valeur de la propriété xmlLang.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlLang(String value) {
        this.xmlLang = value;
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
     * Obtient la valeur de la propriété organizationID.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationID }
     *     
     */
    public OrganizationID getOrganizationID() {
        return organizationID;
    }

    /**
     * Définit la valeur de la propriété organizationID.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationID }
     *     
     */
    public void setOrganizationID(OrganizationID value) {
        this.organizationID = value;
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
     * Obtient la valeur de la propriété quoteRequestReference.
     * 
     * @return
     *     possible object is
     *     {@link QuoteRequestReference }
     *     
     */
    public QuoteRequestReference getQuoteRequestReference() {
        return quoteRequestReference;
    }

    /**
     * Définit la valeur de la propriété quoteRequestReference.
     * 
     * @param value
     *     allowed object is
     *     {@link QuoteRequestReference }
     *     
     */
    public void setQuoteRequestReference(QuoteRequestReference value) {
        this.quoteRequestReference = value;
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
     * Obtient la valeur de la propriété comments.
     * 
     * @return
     *     possible object is
     *     {@link Comments }
     *     
     */
    public Comments getComments() {
        return comments;
    }

    /**
     * Définit la valeur de la propriété comments.
     * 
     * @param value
     *     allowed object is
     *     {@link Comments }
     *     
     */
    public void setComments(Comments value) {
        this.comments = value;
    }

    /**
     * Obtient la valeur de la propriété documentInfo.
     * 
     * @return
     *     possible object is
     *     {@link DocumentInfo }
     *     
     */
    public DocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    /**
     * Définit la valeur de la propriété documentInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentInfo }
     *     
     */
    public void setDocumentInfo(DocumentInfo value) {
        this.documentInfo = value;
    }

    /**
     * Obtient la valeur de la propriété parentContractInfo.
     * 
     * @return
     *     possible object is
     *     {@link ParentContractInfo }
     *     
     */
    public ParentContractInfo getParentContractInfo() {
        return parentContractInfo;
    }

    /**
     * Définit la valeur de la propriété parentContractInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentContractInfo }
     *     
     */
    public void setParentContractInfo(ParentContractInfo value) {
        this.parentContractInfo = value;
    }

    /**
     * Obtient la valeur de la propriété followUpDocument.
     * 
     * @return
     *     possible object is
     *     {@link FollowUpDocument }
     *     
     */
    public FollowUpDocument getFollowUpDocument() {
        return followUpDocument;
    }

    /**
     * Définit la valeur de la propriété followUpDocument.
     * 
     * @param value
     *     allowed object is
     *     {@link FollowUpDocument }
     *     
     */
    public void setFollowUpDocument(FollowUpDocument value) {
        this.followUpDocument = value;
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

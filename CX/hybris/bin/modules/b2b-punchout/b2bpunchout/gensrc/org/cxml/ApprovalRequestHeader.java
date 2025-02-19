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
    "cxmlAttachment",
    "contact",
    "comments",
    "extrinsic"
})
@XmlRootElement(name = "ApprovalRequestHeader")
public class ApprovalRequestHeader {

    @XmlAttribute(name = "approvalStatus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String approvalStatus;
    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "creationDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationDate;
    @XmlAttribute(name = "approvalStartDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String approvalStartDate;
    @XmlAttribute(name = "approvalEndDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String approvalEndDate;
    @XmlElement(name = "DocumentReference", required = true)
    protected DocumentReference documentReference;
    @XmlElement(name = "cXMLAttachment", required = true)
    protected CXMLAttachment cxmlAttachment;
    @XmlElement(name = "Contact")
    protected Contact contact;
    @XmlElement(name = "Comments")
    protected Comments comments;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété approvalStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * Définit la valeur de la propriété approvalStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalStatus(String value) {
        this.approvalStatus = value;
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
        return type;
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
     * Obtient la valeur de la propriété creationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Définit la valeur de la propriété creationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDate(String value) {
        this.creationDate = value;
    }

    /**
     * Obtient la valeur de la propriété approvalStartDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalStartDate() {
        return approvalStartDate;
    }

    /**
     * Définit la valeur de la propriété approvalStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalStartDate(String value) {
        this.approvalStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété approvalEndDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalEndDate() {
        return approvalEndDate;
    }

    /**
     * Définit la valeur de la propriété approvalEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalEndDate(String value) {
        this.approvalEndDate = value;
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
     * Obtient la valeur de la propriété cxmlAttachment.
     * 
     * @return
     *     possible object is
     *     {@link CXMLAttachment }
     *     
     */
    public CXMLAttachment getCXMLAttachment() {
        return cxmlAttachment;
    }

    /**
     * Définit la valeur de la propriété cxmlAttachment.
     * 
     * @param value
     *     allowed object is
     *     {@link CXMLAttachment }
     *     
     */
    public void setCXMLAttachment(CXMLAttachment value) {
        this.cxmlAttachment = value;
    }

    /**
     * Obtient la valeur de la propriété contact.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Définit la valeur de la propriété contact.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
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

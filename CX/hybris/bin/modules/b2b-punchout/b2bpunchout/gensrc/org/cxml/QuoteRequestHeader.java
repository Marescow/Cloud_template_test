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
    "name",
    "supplierSelector",
    "total",
    "description",
    "shipTo",
    "contact",
    "comments",
    "quoteHeaderInfo",
    "extrinsic"
})
@XmlRootElement(name = "QuoteRequestHeader")
public class QuoteRequestHeader {

    @XmlAttribute(name = "requestID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestID;
    @XmlAttribute(name = "requestDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestDate;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "openDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String openDate;
    @XmlAttribute(name = "closeDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String closeDate;
    @XmlAttribute(name = "previewDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String previewDate;
    @XmlAttribute(name = "templateName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String templateName;
    @XmlAttribute(name = "currency", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String currency;
    @XmlAttribute(name = "xml:lang", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlLang;
    @XmlAttribute(name = "quoteReceivingPreference")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String quoteReceivingPreference;
    @XmlElement(name = "Name")
    protected Name name;
    @XmlElement(name = "SupplierSelector")
    protected SupplierSelector supplierSelector;
    @XmlElement(name = "Total")
    protected Total total;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "Contact")
    protected List<Contact> contact;
    @XmlElement(name = "Comments")
    protected Comments comments;
    @XmlElement(name = "QuoteHeaderInfo")
    protected QuoteHeaderInfo quoteHeaderInfo;
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
     * Obtient la valeur de la propriété openDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenDate() {
        return openDate;
    }

    /**
     * Définit la valeur de la propriété openDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenDate(String value) {
        this.openDate = value;
    }

    /**
     * Obtient la valeur de la propriété closeDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * Définit la valeur de la propriété closeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCloseDate(String value) {
        this.closeDate = value;
    }

    /**
     * Obtient la valeur de la propriété previewDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviewDate() {
        return previewDate;
    }

    /**
     * Définit la valeur de la propriété previewDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviewDate(String value) {
        this.previewDate = value;
    }

    /**
     * Obtient la valeur de la propriété templateName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Définit la valeur de la propriété templateName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateName(String value) {
        this.templateName = value;
    }

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
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
     * Obtient la valeur de la propriété quoteReceivingPreference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteReceivingPreference() {
        return quoteReceivingPreference;
    }

    /**
     * Définit la valeur de la propriété quoteReceivingPreference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteReceivingPreference(String value) {
        this.quoteReceivingPreference = value;
    }

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setName(Name value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété supplierSelector.
     * 
     * @return
     *     possible object is
     *     {@link SupplierSelector }
     *     
     */
    public SupplierSelector getSupplierSelector() {
        return supplierSelector;
    }

    /**
     * Définit la valeur de la propriété supplierSelector.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierSelector }
     *     
     */
    public void setSupplierSelector(SupplierSelector value) {
        this.supplierSelector = value;
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
     * Obtient la valeur de la propriété quoteHeaderInfo.
     * 
     * @return
     *     possible object is
     *     {@link QuoteHeaderInfo }
     *     
     */
    public QuoteHeaderInfo getQuoteHeaderInfo() {
        return quoteHeaderInfo;
    }

    /**
     * Définit la valeur de la propriété quoteHeaderInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link QuoteHeaderInfo }
     *     
     */
    public void setQuoteHeaderInfo(QuoteHeaderInfo value) {
        this.quoteHeaderInfo = value;
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

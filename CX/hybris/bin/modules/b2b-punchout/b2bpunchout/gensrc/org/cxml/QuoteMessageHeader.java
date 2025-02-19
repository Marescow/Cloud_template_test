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
    "organizationID",
    "total",
    "shipTo",
    "contact",
    "quoteRequestReference",
    "comments",
    "quoteHeaderInfo",
    "supplierProductionFacilityRelations",
    "extrinsic"
})
@XmlRootElement(name = "QuoteMessageHeader")
public class QuoteMessageHeader {

    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "quoteID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String quoteID;
    @XmlAttribute(name = "quoteDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String quoteDate;
    @XmlAttribute(name = "currency", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String currency;
    @XmlAttribute(name = "xml:lang", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlLang;
    @XmlElement(name = "OrganizationID", required = true)
    protected OrganizationID organizationID;
    @XmlElement(name = "Total", required = true)
    protected Total total;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "Contact")
    protected List<Contact> contact;
    @XmlElement(name = "QuoteRequestReference")
    protected QuoteRequestReference quoteRequestReference;
    @XmlElement(name = "Comments")
    protected Comments comments;
    @XmlElement(name = "QuoteHeaderInfo")
    protected QuoteHeaderInfo quoteHeaderInfo;
    @XmlElement(name = "SupplierProductionFacilityRelations")
    protected SupplierProductionFacilityRelations supplierProductionFacilityRelations;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

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
     * Obtient la valeur de la propriété quoteID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteID() {
        return quoteID;
    }

    /**
     * Définit la valeur de la propriété quoteID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteID(String value) {
        this.quoteID = value;
    }

    /**
     * Obtient la valeur de la propriété quoteDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteDate() {
        return quoteDate;
    }

    /**
     * Définit la valeur de la propriété quoteDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteDate(String value) {
        this.quoteDate = value;
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

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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "period",
    "salesQuantity",
    "returnQuantity",
    "total",
    "promotionVariantID",
    "comments"
})
@XmlRootElement(name = "SalesReport")
public class SalesReport {

    @XmlAttribute(name = "salesDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String salesDate;
    @XmlAttribute(name = "lineNumber", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lineNumber;
    @XmlElement(name = "Period", required = true)
    protected Period period;
    @XmlElement(name = "SalesQuantity", required = true)
    protected SalesQuantity salesQuantity;
    @XmlElement(name = "ReturnQuantity")
    protected ReturnQuantity returnQuantity;
    @XmlElement(name = "Total")
    protected Total total;
    @XmlElement(name = "PromotionVariantID")
    protected String promotionVariantID;
    @XmlElement(name = "Comments")
    protected List<Comments> comments;

    /**
     * Obtient la valeur de la propriété salesDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesDate() {
        return salesDate;
    }

    /**
     * Définit la valeur de la propriété salesDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesDate(String value) {
        this.salesDate = value;
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
     * Obtient la valeur de la propriété period.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * Définit la valeur de la propriété period.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setPeriod(Period value) {
        this.period = value;
    }

    /**
     * Obtient la valeur de la propriété salesQuantity.
     * 
     * @return
     *     possible object is
     *     {@link SalesQuantity }
     *     
     */
    public SalesQuantity getSalesQuantity() {
        return salesQuantity;
    }

    /**
     * Définit la valeur de la propriété salesQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesQuantity }
     *     
     */
    public void setSalesQuantity(SalesQuantity value) {
        this.salesQuantity = value;
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
     * Obtient la valeur de la propriété promotionVariantID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionVariantID() {
        return promotionVariantID;
    }

    /**
     * Définit la valeur de la propriété promotionVariantID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionVariantID(String value) {
        this.promotionVariantID = value;
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

}

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

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
    "sourcingStatus",
    "total",
    "shipTo",
    "shipping",
    "tax",
    "supplierOrderInfo"
})
@XmlRootElement(name = "PunchOutOrderMessageHeader")
public class PunchOutOrderMessageHeader {

    @XmlAttribute(name = "operationAllowed", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String operationAllowed;
    @XmlAttribute(name = "quoteStatus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String quoteStatus;
    @XmlElement(name = "SourcingStatus")
    protected SourcingStatus sourcingStatus;
    @XmlElement(name = "Total", required = true)
    protected Total total;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "Shipping")
    protected Shipping shipping;
    @XmlElement(name = "Tax")
    protected Tax tax;
    @XmlElement(name = "SupplierOrderInfo")
    protected SupplierOrderInfo supplierOrderInfo;

    /**
     * Obtient la valeur de la propriété operationAllowed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationAllowed() {
        return operationAllowed;
    }

    /**
     * Définit la valeur de la propriété operationAllowed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationAllowed(String value) {
        this.operationAllowed = value;
    }

    /**
     * Obtient la valeur de la propriété quoteStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteStatus() {
        if (quoteStatus == null) {
            return "final";
        } else {
            return quoteStatus;
        }
    }

    /**
     * Définit la valeur de la propriété quoteStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteStatus(String value) {
        this.quoteStatus = value;
    }

    /**
     * Obtient la valeur de la propriété sourcingStatus.
     * 
     * @return
     *     possible object is
     *     {@link SourcingStatus }
     *     
     */
    public SourcingStatus getSourcingStatus() {
        return sourcingStatus;
    }

    /**
     * Définit la valeur de la propriété sourcingStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link SourcingStatus }
     *     
     */
    public void setSourcingStatus(SourcingStatus value) {
        this.sourcingStatus = value;
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

}

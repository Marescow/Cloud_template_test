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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "productMovementItemIDInfo",
    "invoiceItemIDInfo",
    "referenceDocumentInfo",
    "movementQuantity",
    "subtotalAmount",
    "unitPrice",
    "extrinsic"
})
@XmlRootElement(name = "ConsignmentMovement")
public class ConsignmentMovement {

    @XmlElement(name = "ProductMovementItemIDInfo", required = true)
    protected ProductMovementItemIDInfo productMovementItemIDInfo;
    @XmlElement(name = "InvoiceItemIDInfo")
    protected InvoiceItemIDInfo invoiceItemIDInfo;
    @XmlElement(name = "ReferenceDocumentInfo")
    protected ReferenceDocumentInfo referenceDocumentInfo;
    @XmlElement(name = "MovementQuantity", required = true)
    protected MovementQuantity movementQuantity;
    @XmlElement(name = "SubtotalAmount", required = true)
    protected SubtotalAmount subtotalAmount;
    @XmlElement(name = "UnitPrice")
    protected UnitPrice unitPrice;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété productMovementItemIDInfo.
     * 
     * @return
     *     possible object is
     *     {@link ProductMovementItemIDInfo }
     *     
     */
    public ProductMovementItemIDInfo getProductMovementItemIDInfo() {
        return productMovementItemIDInfo;
    }

    /**
     * Définit la valeur de la propriété productMovementItemIDInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductMovementItemIDInfo }
     *     
     */
    public void setProductMovementItemIDInfo(ProductMovementItemIDInfo value) {
        this.productMovementItemIDInfo = value;
    }

    /**
     * Obtient la valeur de la propriété invoiceItemIDInfo.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceItemIDInfo }
     *     
     */
    public InvoiceItemIDInfo getInvoiceItemIDInfo() {
        return invoiceItemIDInfo;
    }

    /**
     * Définit la valeur de la propriété invoiceItemIDInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceItemIDInfo }
     *     
     */
    public void setInvoiceItemIDInfo(InvoiceItemIDInfo value) {
        this.invoiceItemIDInfo = value;
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
     * Obtient la valeur de la propriété movementQuantity.
     * 
     * @return
     *     possible object is
     *     {@link MovementQuantity }
     *     
     */
    public MovementQuantity getMovementQuantity() {
        return movementQuantity;
    }

    /**
     * Définit la valeur de la propriété movementQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link MovementQuantity }
     *     
     */
    public void setMovementQuantity(MovementQuantity value) {
        this.movementQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété subtotalAmount.
     * 
     * @return
     *     possible object is
     *     {@link SubtotalAmount }
     *     
     */
    public SubtotalAmount getSubtotalAmount() {
        return subtotalAmount;
    }

    /**
     * Définit la valeur de la propriété subtotalAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link SubtotalAmount }
     *     
     */
    public void setSubtotalAmount(SubtotalAmount value) {
        this.subtotalAmount = value;
    }

    /**
     * Obtient la valeur de la propriété unitPrice.
     * 
     * @return
     *     possible object is
     *     {@link UnitPrice }
     *     
     */
    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    /**
     * Définit la valeur de la propriété unitPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitPrice }
     *     
     */
    public void setUnitPrice(UnitPrice value) {
        this.unitPrice = value;
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

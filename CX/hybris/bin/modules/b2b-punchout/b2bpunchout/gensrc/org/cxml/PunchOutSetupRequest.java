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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "buyerCookie",
    "extrinsic",
    "browserFormPost",
    "contact",
    "supplierSetup",
    "shipTo",
    "selectedItem",
    "itemOut"
})
@XmlRootElement(name = "PunchOutSetupRequest")
public class PunchOutSetupRequest {

    @XmlAttribute(name = "operation", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String operation;
    @XmlElement(name = "BuyerCookie", required = true)
    protected BuyerCookie buyerCookie;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;
    @XmlElement(name = "BrowserFormPost")
    protected BrowserFormPost browserFormPost;
    @XmlElement(name = "Contact")
    protected List<Contact> contact;
    @XmlElement(name = "SupplierSetup")
    protected SupplierSetup supplierSetup;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "SelectedItem")
    protected SelectedItem selectedItem;
    @XmlElement(name = "ItemOut")
    protected List<ItemOut> itemOut;

    /**
     * Obtient la valeur de la propriété operation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
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
     * Obtient la valeur de la propriété buyerCookie.
     * 
     * @return
     *     possible object is
     *     {@link BuyerCookie }
     *     
     */
    public BuyerCookie getBuyerCookie() {
        return buyerCookie;
    }

    /**
     * Définit la valeur de la propriété buyerCookie.
     * 
     * @param value
     *     allowed object is
     *     {@link BuyerCookie }
     *     
     */
    public void setBuyerCookie(BuyerCookie value) {
        this.buyerCookie = value;
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

    /**
     * Obtient la valeur de la propriété browserFormPost.
     * 
     * @return
     *     possible object is
     *     {@link BrowserFormPost }
     *     
     */
    public BrowserFormPost getBrowserFormPost() {
        return browserFormPost;
    }

    /**
     * Définit la valeur de la propriété browserFormPost.
     * 
     * @param value
     *     allowed object is
     *     {@link BrowserFormPost }
     *     
     */
    public void setBrowserFormPost(BrowserFormPost value) {
        this.browserFormPost = value;
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
     * Obtient la valeur de la propriété supplierSetup.
     * 
     * @return
     *     possible object is
     *     {@link SupplierSetup }
     *     
     */
    public SupplierSetup getSupplierSetup() {
        return supplierSetup;
    }

    /**
     * Définit la valeur de la propriété supplierSetup.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierSetup }
     *     
     */
    public void setSupplierSetup(SupplierSetup value) {
        this.supplierSetup = value;
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
     * Obtient la valeur de la propriété selectedItem.
     * 
     * @return
     *     possible object is
     *     {@link SelectedItem }
     *     
     */
    public SelectedItem getSelectedItem() {
        return selectedItem;
    }

    /**
     * Définit la valeur de la propriété selectedItem.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedItem }
     *     
     */
    public void setSelectedItem(SelectedItem value) {
        this.selectedItem = value;
    }

    /**
     * Gets the value of the itemOut property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemOut property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemOut().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemOut }
     * 
     * 
     */
    public List<ItemOut> getItemOut() {
        if (itemOut == null) {
            itemOut = new ArrayList<ItemOut>();
        }
        return this.itemOut;
    }

}

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
    "buyerCookie",
    "punchOutOrderMessageHeader",
    "itemIn"
})
@XmlRootElement(name = "PunchOutOrderMessage")
public class PunchOutOrderMessage {

    @XmlElement(name = "BuyerCookie", required = true)
    protected BuyerCookie buyerCookie;
    @XmlElement(name = "PunchOutOrderMessageHeader", required = true)
    protected PunchOutOrderMessageHeader punchOutOrderMessageHeader;
    @XmlElement(name = "ItemIn")
    protected List<ItemIn> itemIn;

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
     * Obtient la valeur de la propriété punchOutOrderMessageHeader.
     * 
     * @return
     *     possible object is
     *     {@link PunchOutOrderMessageHeader }
     *     
     */
    public PunchOutOrderMessageHeader getPunchOutOrderMessageHeader() {
        return punchOutOrderMessageHeader;
    }

    /**
     * Définit la valeur de la propriété punchOutOrderMessageHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link PunchOutOrderMessageHeader }
     *     
     */
    public void setPunchOutOrderMessageHeader(PunchOutOrderMessageHeader value) {
        this.punchOutOrderMessageHeader = value;
    }

    /**
     * Gets the value of the itemIn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemIn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemIn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemIn }
     * 
     * 
     */
    public List<ItemIn> getItemIn() {
        if (itemIn == null) {
            itemIn = new ArrayList<ItemIn>();
        }
        return this.itemIn;
    }

}

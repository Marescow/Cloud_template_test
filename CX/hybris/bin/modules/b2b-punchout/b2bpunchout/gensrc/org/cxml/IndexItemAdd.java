//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

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
    "itemID",
    "itemDetail",
    "indexItemDetail"
})
@XmlRootElement(name = "IndexItemAdd")
public class IndexItemAdd {

    @XmlElement(name = "ItemID", required = true)
    protected ItemID itemID;
    @XmlElement(name = "ItemDetail", required = true)
    protected ItemDetail itemDetail;
    @XmlElement(name = "IndexItemDetail", required = true)
    protected IndexItemDetail indexItemDetail;

    /**
     * Obtient la valeur de la propriété itemID.
     * 
     * @return
     *     possible object is
     *     {@link ItemID }
     *     
     */
    public ItemID getItemID() {
        return itemID;
    }

    /**
     * Définit la valeur de la propriété itemID.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemID }
     *     
     */
    public void setItemID(ItemID value) {
        this.itemID = value;
    }

    /**
     * Obtient la valeur de la propriété itemDetail.
     * 
     * @return
     *     possible object is
     *     {@link ItemDetail }
     *     
     */
    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    /**
     * Définit la valeur de la propriété itemDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemDetail }
     *     
     */
    public void setItemDetail(ItemDetail value) {
        this.itemDetail = value;
    }

    /**
     * Obtient la valeur de la propriété indexItemDetail.
     * 
     * @return
     *     possible object is
     *     {@link IndexItemDetail }
     *     
     */
    public IndexItemDetail getIndexItemDetail() {
        return indexItemDetail;
    }

    /**
     * Définit la valeur de la propriété indexItemDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link IndexItemDetail }
     *     
     */
    public void setIndexItemDetail(IndexItemDetail value) {
        this.indexItemDetail = value;
    }

}

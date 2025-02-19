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
    "promotionVariantID",
    "promotionDealID"
})
@XmlRootElement(name = "ItemOutRetail")
public class ItemOutRetail {

    @XmlElement(name = "PromotionVariantID")
    protected String promotionVariantID;
    @XmlElement(name = "PromotionDealID")
    protected String promotionDealID;

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
     * Obtient la valeur de la propriété promotionDealID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionDealID() {
        return promotionDealID;
    }

    /**
     * Définit la valeur de la propriété promotionDealID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionDealID(String value) {
        this.promotionDealID = value;
    }

}

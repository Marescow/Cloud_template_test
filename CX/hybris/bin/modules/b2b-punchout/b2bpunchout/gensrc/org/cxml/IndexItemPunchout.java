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
    "punchoutDetail"
})
@XmlRootElement(name = "IndexItemPunchout")
public class IndexItemPunchout {

    @XmlElement(name = "ItemID", required = true)
    protected ItemID itemID;
    @XmlElement(name = "PunchoutDetail", required = true)
    protected PunchoutDetail punchoutDetail;

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
     * Obtient la valeur de la propriété punchoutDetail.
     * 
     * @return
     *     possible object is
     *     {@link PunchoutDetail }
     *     
     */
    public PunchoutDetail getPunchoutDetail() {
        return punchoutDetail;
    }

    /**
     * Définit la valeur de la propriété punchoutDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link PunchoutDetail }
     *     
     */
    public void setPunchoutDetail(PunchoutDetail value) {
        this.punchoutDetail = value;
    }

}

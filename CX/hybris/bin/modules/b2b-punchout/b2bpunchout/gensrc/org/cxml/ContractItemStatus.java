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
    "itemStatus",
    "idReference",
    "comments"
})
@XmlRootElement(name = "ContractItemStatus")
public class ContractItemStatus {

    @XmlElement(name = "ItemStatus", required = true)
    protected ItemStatus itemStatus;
    @XmlElement(name = "IdReference")
    protected IdReference idReference;
    @XmlElement(name = "Comments")
    protected List<Comments> comments;

    /**
     * Obtient la valeur de la propriété itemStatus.
     * 
     * @return
     *     possible object is
     *     {@link ItemStatus }
     *     
     */
    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    /**
     * Définit la valeur de la propriété itemStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemStatus }
     *     
     */
    public void setItemStatus(ItemStatus value) {
        this.itemStatus = value;
    }

    /**
     * Obtient la valeur de la propriété idReference.
     * 
     * @return
     *     possible object is
     *     {@link IdReference }
     *     
     */
    public IdReference getIdReference() {
        return idReference;
    }

    /**
     * Définit la valeur de la propriété idReference.
     * 
     * @param value
     *     allowed object is
     *     {@link IdReference }
     *     
     */
    public void setIdReference(IdReference value) {
        this.idReference = value;
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

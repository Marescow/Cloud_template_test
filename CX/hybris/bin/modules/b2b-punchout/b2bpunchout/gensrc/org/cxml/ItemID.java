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
    "supplierPartID",
    "supplierPartAuxiliaryID",
    "buyerPartID",
    "idReference"
})
@XmlRootElement(name = "ItemID")
public class ItemID {

    @XmlElement(name = "SupplierPartID", required = true)
    protected SupplierPartID supplierPartID;
    @XmlElement(name = "SupplierPartAuxiliaryID")
    protected SupplierPartAuxiliaryID supplierPartAuxiliaryID;
    @XmlElement(name = "BuyerPartID")
    protected String buyerPartID;
    @XmlElement(name = "IdReference")
    protected List<IdReference> idReference;

    /**
     * Obtient la valeur de la propriété supplierPartID.
     * 
     * @return
     *     possible object is
     *     {@link SupplierPartID }
     *     
     */
    public SupplierPartID getSupplierPartID() {
        return supplierPartID;
    }

    /**
     * Définit la valeur de la propriété supplierPartID.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierPartID }
     *     
     */
    public void setSupplierPartID(SupplierPartID value) {
        this.supplierPartID = value;
    }

    /**
     * Obtient la valeur de la propriété supplierPartAuxiliaryID.
     * 
     * @return
     *     possible object is
     *     {@link SupplierPartAuxiliaryID }
     *     
     */
    public SupplierPartAuxiliaryID getSupplierPartAuxiliaryID() {
        return supplierPartAuxiliaryID;
    }

    /**
     * Définit la valeur de la propriété supplierPartAuxiliaryID.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierPartAuxiliaryID }
     *     
     */
    public void setSupplierPartAuxiliaryID(SupplierPartAuxiliaryID value) {
        this.supplierPartAuxiliaryID = value;
    }

    /**
     * Obtient la valeur de la propriété buyerPartID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerPartID() {
        return buyerPartID;
    }

    /**
     * Définit la valeur de la propriété buyerPartID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerPartID(String value) {
        this.buyerPartID = value;
    }

    /**
     * Gets the value of the idReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdReference }
     * 
     * 
     */
    public List<IdReference> getIdReference() {
        if (idReference == null) {
            idReference = new ArrayList<IdReference>();
        }
        return this.idReference;
    }

}

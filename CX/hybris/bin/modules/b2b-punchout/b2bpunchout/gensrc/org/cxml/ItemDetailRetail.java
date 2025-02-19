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
    "eanid",
    "europeanWasteCatalogID",
    "characteristic"
})
@XmlRootElement(name = "ItemDetailRetail")
public class ItemDetailRetail {

    @XmlElement(name = "EANID")
    protected String eanid;
    @XmlElement(name = "EuropeanWasteCatalogID")
    protected String europeanWasteCatalogID;
    @XmlElement(name = "Characteristic")
    protected List<Characteristic> characteristic;

    /**
     * Obtient la valeur de la propriété eanid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEANID() {
        return eanid;
    }

    /**
     * Définit la valeur de la propriété eanid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEANID(String value) {
        this.eanid = value;
    }

    /**
     * Obtient la valeur de la propriété europeanWasteCatalogID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEuropeanWasteCatalogID() {
        return europeanWasteCatalogID;
    }

    /**
     * Définit la valeur de la propriété europeanWasteCatalogID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEuropeanWasteCatalogID(String value) {
        this.europeanWasteCatalogID = value;
    }

    /**
     * Gets the value of the characteristic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the characteristic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharacteristic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Characteristic }
     * 
     * 
     */
    public List<Characteristic> getCharacteristic() {
        if (characteristic == null) {
            characteristic = new ArrayList<Characteristic>();
        }
        return this.characteristic;
    }

}

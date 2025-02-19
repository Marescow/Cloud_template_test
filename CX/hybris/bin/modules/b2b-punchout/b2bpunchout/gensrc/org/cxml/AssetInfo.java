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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "extrinsic"
})
@XmlRootElement(name = "AssetInfo")
public class AssetInfo {

    @XmlAttribute(name = "isReferencedAsset")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isReferencedAsset;
    @XmlAttribute(name = "tagNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tagNumber;
    @XmlAttribute(name = "serialNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String serialNumber;
    @XmlAttribute(name = "location")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String location;
    @XmlAttribute(name = "equipmentId")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String equipmentId;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété isReferencedAsset.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReferencedAsset() {
        return isReferencedAsset;
    }

    /**
     * Définit la valeur de la propriété isReferencedAsset.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReferencedAsset(String value) {
        this.isReferencedAsset = value;
    }

    /**
     * Obtient la valeur de la propriété tagNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagNumber() {
        return tagNumber;
    }

    /**
     * Définit la valeur de la propriété tagNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagNumber(String value) {
        this.tagNumber = value;
    }

    /**
     * Obtient la valeur de la propriété serialNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Définit la valeur de la propriété serialNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Obtient la valeur de la propriété location.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Définit la valeur de la propriété location.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Obtient la valeur de la propriété equipmentId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * Définit la valeur de la propriété equipmentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentId(String value) {
        this.equipmentId = value;
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

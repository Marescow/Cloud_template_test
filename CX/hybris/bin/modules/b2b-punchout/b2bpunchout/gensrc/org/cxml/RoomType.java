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
    "description",
    "amenities"
})
@XmlRootElement(name = "RoomType")
public class RoomType {

    @XmlAttribute(name = "smoking", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String smoking;
    @XmlAttribute(name = "numberOfBed")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String numberOfBed;
    @XmlAttribute(name = "bedType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String bedType;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "Amenities")
    protected List<Amenities> amenities;

    /**
     * Obtient la valeur de la propriété smoking.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmoking() {
        return smoking;
    }

    /**
     * Définit la valeur de la propriété smoking.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmoking(String value) {
        this.smoking = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfBed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfBed() {
        return numberOfBed;
    }

    /**
     * Définit la valeur de la propriété numberOfBed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfBed(String value) {
        this.numberOfBed = value;
    }

    /**
     * Obtient la valeur de la propriété bedType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBedType() {
        return bedType;
    }

    /**
     * Définit la valeur de la propriété bedType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBedType(String value) {
        this.bedType = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the amenities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amenities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmenities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Amenities }
     * 
     * 
     */
    public List<Amenities> getAmenities() {
        if (amenities == null) {
            amenities = new ArrayList<Amenities>();
        }
        return this.amenities;
    }

}

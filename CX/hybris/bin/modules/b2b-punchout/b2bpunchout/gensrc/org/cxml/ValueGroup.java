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
    "idReference",
    "parentID",
    "propertyValue"
})
@XmlRootElement(name = "ValueGroup")
public class ValueGroup {

    @XmlElement(name = "IdReference")
    protected IdReference idReference;
    @XmlElement(name = "ParentID")
    protected String parentID;
    @XmlElement(name = "PropertyValue")
    protected List<PropertyValue> propertyValue;

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
     * Obtient la valeur de la propriété parentID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentID() {
        return parentID;
    }

    /**
     * Définit la valeur de la propriété parentID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentID(String value) {
        this.parentID = value;
    }

    /**
     * Gets the value of the propertyValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyValue }
     * 
     * 
     */
    public List<PropertyValue> getPropertyValue() {
        if (propertyValue == null) {
            propertyValue = new ArrayList<PropertyValue>();
        }
        return this.propertyValue;
    }

}

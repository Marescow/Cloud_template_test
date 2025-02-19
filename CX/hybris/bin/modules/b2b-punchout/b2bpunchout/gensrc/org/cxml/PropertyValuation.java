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
    "propertyReference",
    "valueGroup"
})
@XmlRootElement(name = "PropertyValuation")
public class PropertyValuation {

    @XmlElement(name = "PropertyReference")
    protected PropertyReference propertyReference;
    @XmlElement(name = "ValueGroup")
    protected List<ValueGroup> valueGroup;

    /**
     * Obtient la valeur de la propriété propertyReference.
     * 
     * @return
     *     possible object is
     *     {@link PropertyReference }
     *     
     */
    public PropertyReference getPropertyReference() {
        return propertyReference;
    }

    /**
     * Définit la valeur de la propriété propertyReference.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyReference }
     *     
     */
    public void setPropertyReference(PropertyReference value) {
        this.propertyReference = value;
    }

    /**
     * Gets the value of the valueGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueGroup }
     * 
     * 
     */
    public List<ValueGroup> getValueGroup() {
        if (valueGroup == null) {
            valueGroup = new ArrayList<ValueGroup>();
        }
        return this.valueGroup;
    }

}

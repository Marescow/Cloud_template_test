//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "propertyValue"
})
@XmlRootElement(name = "QualitySampleResult")
public class QualitySampleResult {

    @XmlAttribute(name = "sampleID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String sampleID;
    @XmlAttribute(name = "unitValue")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String unitValue;
    @XmlAttribute(name = "physicalSampleNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String physicalSampleNumber;
    @XmlElement(name = "PropertyValue")
    protected PropertyValue propertyValue;

    /**
     * Obtient la valeur de la propriété sampleID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampleID() {
        return sampleID;
    }

    /**
     * Définit la valeur de la propriété sampleID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampleID(String value) {
        this.sampleID = value;
    }

    /**
     * Obtient la valeur de la propriété unitValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitValue() {
        return unitValue;
    }

    /**
     * Définit la valeur de la propriété unitValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitValue(String value) {
        this.unitValue = value;
    }

    /**
     * Obtient la valeur de la propriété physicalSampleNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalSampleNumber() {
        return physicalSampleNumber;
    }

    /**
     * Définit la valeur de la propriété physicalSampleNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalSampleNumber(String value) {
        this.physicalSampleNumber = value;
    }

    /**
     * Obtient la valeur de la propriété propertyValue.
     * 
     * @return
     *     possible object is
     *     {@link PropertyValue }
     *     
     */
    public PropertyValue getPropertyValue() {
        return propertyValue;
    }

    /**
     * Définit la valeur de la propriété propertyValue.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyValue }
     *     
     */
    public void setPropertyValue(PropertyValue value) {
        this.propertyValue = value;
    }

}

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
    "minimumLimit",
    "maximumLimit",
    "propertyValue"
})
@XmlRootElement(name = "ExpectedResult")
public class ExpectedResult {

    @XmlAttribute(name = "targetValue")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String targetValue;
    @XmlAttribute(name = "valuePrecision")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String valuePrecision;
    @XmlAttribute(name = "qualitativeValue")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String qualitativeValue;
    @XmlElement(name = "MinimumLimit")
    protected MinimumLimit minimumLimit;
    @XmlElement(name = "MaximumLimit")
    protected MaximumLimit maximumLimit;
    @XmlElement(name = "PropertyValue")
    protected PropertyValue propertyValue;

    /**
     * Obtient la valeur de la propriété targetValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetValue() {
        return targetValue;
    }

    /**
     * Définit la valeur de la propriété targetValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetValue(String value) {
        this.targetValue = value;
    }

    /**
     * Obtient la valeur de la propriété valuePrecision.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValuePrecision() {
        return valuePrecision;
    }

    /**
     * Définit la valeur de la propriété valuePrecision.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValuePrecision(String value) {
        this.valuePrecision = value;
    }

    /**
     * Obtient la valeur de la propriété qualitativeValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualitativeValue() {
        return qualitativeValue;
    }

    /**
     * Définit la valeur de la propriété qualitativeValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualitativeValue(String value) {
        this.qualitativeValue = value;
    }

    /**
     * Obtient la valeur de la propriété minimumLimit.
     * 
     * @return
     *     possible object is
     *     {@link MinimumLimit }
     *     
     */
    public MinimumLimit getMinimumLimit() {
        return minimumLimit;
    }

    /**
     * Définit la valeur de la propriété minimumLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link MinimumLimit }
     *     
     */
    public void setMinimumLimit(MinimumLimit value) {
        this.minimumLimit = value;
    }

    /**
     * Obtient la valeur de la propriété maximumLimit.
     * 
     * @return
     *     possible object is
     *     {@link MaximumLimit }
     *     
     */
    public MaximumLimit getMaximumLimit() {
        return maximumLimit;
    }

    /**
     * Définit la valeur de la propriété maximumLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link MaximumLimit }
     *     
     */
    public void setMaximumLimit(MaximumLimit value) {
        this.maximumLimit = value;
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

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
    "costTermValue",
    "scales"
})
@XmlRootElement(name = "ConditionType")
public class ConditionType {

    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlElement(name = "CostTermValue")
    protected CostTermValue costTermValue;
    @XmlElement(name = "Scales")
    protected Scales scales;

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété costTermValue.
     * 
     * @return
     *     possible object is
     *     {@link CostTermValue }
     *     
     */
    public CostTermValue getCostTermValue() {
        return costTermValue;
    }

    /**
     * Définit la valeur de la propriété costTermValue.
     * 
     * @param value
     *     allowed object is
     *     {@link CostTermValue }
     *     
     */
    public void setCostTermValue(CostTermValue value) {
        this.costTermValue = value;
    }

    /**
     * Obtient la valeur de la propriété scales.
     * 
     * @return
     *     possible object is
     *     {@link Scales }
     *     
     */
    public Scales getScales() {
        return scales;
    }

    /**
     * Définit la valeur de la propriété scales.
     * 
     * @param value
     *     allowed object is
     *     {@link Scales }
     *     
     */
    public void setScales(Scales value) {
        this.scales = value;
    }

}

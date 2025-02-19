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
    "conditionTypes"
})
@XmlRootElement(name = "ValidityPeriod")
public class ValidityPeriod {

    @XmlAttribute(name = "from", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String from;
    @XmlAttribute(name = "to", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String to;
    @XmlElement(name = "ConditionTypes", required = true)
    protected ConditionTypes conditionTypes;

    /**
     * Obtient la valeur de la propriété from.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Définit la valeur de la propriété from.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Obtient la valeur de la propriété to.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Définit la valeur de la propriété to.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Obtient la valeur de la propriété conditionTypes.
     * 
     * @return
     *     possible object is
     *     {@link ConditionTypes }
     *     
     */
    public ConditionTypes getConditionTypes() {
        return conditionTypes;
    }

    /**
     * Définit la valeur de la propriété conditionTypes.
     * 
     * @param value
     *     allowed object is
     *     {@link ConditionTypes }
     *     
     */
    public void setConditionTypes(ConditionTypes value) {
        this.conditionTypes = value;
    }

}

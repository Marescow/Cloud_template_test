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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ComparatorInfo")
public class ComparatorInfo {

    @XmlAttribute(name = "comparatorType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String comparatorType;
    @XmlAttribute(name = "comparatorValue", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String comparatorValue;

    /**
     * Obtient la valeur de la propriété comparatorType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComparatorType() {
        return comparatorType;
    }

    /**
     * Définit la valeur de la propriété comparatorType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComparatorType(String value) {
        this.comparatorType = value;
    }

    /**
     * Obtient la valeur de la propriété comparatorValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComparatorValue() {
        return comparatorValue;
    }

    /**
     * Définit la valeur de la propriété comparatorValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComparatorValue(String value) {
        this.comparatorValue = value;
    }

}

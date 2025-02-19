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
@XmlRootElement(name = "SampleDefinition")
public class SampleDefinition {

    @XmlAttribute(name = "sampleSize")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String sampleSize;
    @XmlAttribute(name = "sampleType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sampleType;

    /**
     * Obtient la valeur de la propriété sampleSize.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampleSize() {
        return sampleSize;
    }

    /**
     * Définit la valeur de la propriété sampleSize.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampleSize(String value) {
        this.sampleSize = value;
    }

    /**
     * Obtient la valeur de la propriété sampleType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampleType() {
        return sampleType;
    }

    /**
     * Définit la valeur de la propriété sampleType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampleType(String value) {
        this.sampleType = value;
    }

}

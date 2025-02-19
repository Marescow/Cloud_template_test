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
@XmlRootElement(name = "Alternative")
public class Alternative {

    @XmlAttribute(name = "alternativeType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String alternativeType;
    @XmlAttribute(name = "basicLineNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String basicLineNumber;

    /**
     * Obtient la valeur de la propriété alternativeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternativeType() {
        return alternativeType;
    }

    /**
     * Définit la valeur de la propriété alternativeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternativeType(String value) {
        this.alternativeType = value;
    }

    /**
     * Obtient la valeur de la propriété basicLineNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasicLineNumber() {
        return basicLineNumber;
    }

    /**
     * Définit la valeur de la propriété basicLineNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasicLineNumber(String value) {
        this.basicLineNumber = value;
    }

}

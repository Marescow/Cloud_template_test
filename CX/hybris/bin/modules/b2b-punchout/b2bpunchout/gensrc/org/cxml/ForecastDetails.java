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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ForecastDetails")
public class ForecastDetails {

    @XmlAttribute(name = "cumulativeStartDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cumulativeStartDate;
    @XmlAttribute(name = "cumulativeEndDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cumulativeEndDate;

    /**
     * Obtient la valeur de la propriété cumulativeStartDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCumulativeStartDate() {
        return cumulativeStartDate;
    }

    /**
     * Définit la valeur de la propriété cumulativeStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCumulativeStartDate(String value) {
        this.cumulativeStartDate = value;
    }

    /**
     * Obtient la valeur de la propriété cumulativeEndDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCumulativeEndDate() {
        return cumulativeEndDate;
    }

    /**
     * Définit la valeur de la propriété cumulativeEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCumulativeEndDate(String value) {
        this.cumulativeEndDate = value;
    }

}

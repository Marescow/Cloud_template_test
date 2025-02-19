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
    "exemptReason"
})
@XmlRootElement(name = "TaxExemption")
public class TaxExemption {

    @XmlAttribute(name = "exemptCode", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String exemptCode;
    @XmlElement(name = "ExemptReason")
    protected ExemptReason exemptReason;

    /**
     * Obtient la valeur de la propriété exemptCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExemptCode() {
        return exemptCode;
    }

    /**
     * Définit la valeur de la propriété exemptCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExemptCode(String value) {
        this.exemptCode = value;
    }

    /**
     * Obtient la valeur de la propriété exemptReason.
     * 
     * @return
     *     possible object is
     *     {@link ExemptReason }
     *     
     */
    public ExemptReason getExemptReason() {
        return exemptReason;
    }

    /**
     * Définit la valeur de la propriété exemptReason.
     * 
     * @param value
     *     allowed object is
     *     {@link ExemptReason }
     *     
     */
    public void setExemptReason(ExemptReason value) {
        this.exemptReason = value;
    }

}

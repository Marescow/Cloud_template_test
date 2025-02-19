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
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "State")
public class State {

    @XmlAttribute(name = "isoStateCode")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String isoStateCode;
    @XmlValue
    protected String value;

    /**
     * Obtient la valeur de la propriété isoStateCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsoStateCode() {
        return isoStateCode;
    }

    /**
     * Définit la valeur de la propriété isoStateCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsoStateCode(String value) {
        this.isoStateCode = value;
    }

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getvalue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setvalue(String value) {
        this.value = value;
    }

}

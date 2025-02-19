//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dsSPKISexp"
})
@XmlRootElement(name = "ds:SPKIData")
public class DsSPKIData {

    @XmlElement(name = "ds:SPKISexp", required = true)
    protected String dsSPKISexp;

    /**
     * Obtient la valeur de la propriété dsSPKISexp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsSPKISexp() {
        return dsSPKISexp;
    }

    /**
     * Définit la valeur de la propriété dsSPKISexp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsSPKISexp(String value) {
        this.dsSPKISexp = value;
    }

}

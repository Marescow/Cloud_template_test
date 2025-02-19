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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "BatchInfo")
public class BatchInfo {

    @XmlAttribute(name = "requiresBatch")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String requiresBatch;

    /**
     * Obtient la valeur de la propriété requiresBatch.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiresBatch() {
        return requiresBatch;
    }

    /**
     * Définit la valeur de la propriété requiresBatch.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiresBatch(String value) {
        this.requiresBatch = value;
    }

}

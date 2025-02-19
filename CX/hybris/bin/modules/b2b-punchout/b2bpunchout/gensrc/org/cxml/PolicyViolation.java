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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "policyViolationJustification",
    "comments"
})
@XmlRootElement(name = "PolicyViolation")
public class PolicyViolation {

    @XmlAttribute(name = "level", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String level;
    @XmlElement(name = "Description", required = true)
    protected Description description;
    @XmlElement(name = "PolicyViolationJustification", required = true)
    protected PolicyViolationJustification policyViolationJustification;
    @XmlElement(name = "Comments")
    protected Comments comments;

    /**
     * Obtient la valeur de la propriété level.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel() {
        return level;
    }

    /**
     * Définit la valeur de la propriété level.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété policyViolationJustification.
     * 
     * @return
     *     possible object is
     *     {@link PolicyViolationJustification }
     *     
     */
    public PolicyViolationJustification getPolicyViolationJustification() {
        return policyViolationJustification;
    }

    /**
     * Définit la valeur de la propriété policyViolationJustification.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyViolationJustification }
     *     
     */
    public void setPolicyViolationJustification(PolicyViolationJustification value) {
        this.policyViolationJustification = value;
    }

    /**
     * Obtient la valeur de la propriété comments.
     * 
     * @return
     *     possible object is
     *     {@link Comments }
     *     
     */
    public Comments getComments() {
        return comments;
    }

    /**
     * Définit la valeur de la propriété comments.
     * 
     * @param value
     *     allowed object is
     *     {@link Comments }
     *     
     */
    public void setComments(Comments value) {
        this.comments = value;
    }

}

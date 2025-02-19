//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "scale"
})
@XmlRootElement(name = "Scales")
public class Scales {

    @XmlAttribute(name = "scaleType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String scaleType;
    @XmlAttribute(name = "scaleBasis")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String scaleBasis;
    @XmlElement(name = "Scale", required = true)
    protected List<Scale> scale;

    /**
     * Obtient la valeur de la propriété scaleType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScaleType() {
        return scaleType;
    }

    /**
     * Définit la valeur de la propriété scaleType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScaleType(String value) {
        this.scaleType = value;
    }

    /**
     * Obtient la valeur de la propriété scaleBasis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScaleBasis() {
        if (scaleBasis == null) {
            return "quantity";
        } else {
            return scaleBasis;
        }
    }

    /**
     * Définit la valeur de la propriété scaleBasis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScaleBasis(String value) {
        this.scaleBasis = value;
    }

    /**
     * Gets the value of the scale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Scale }
     * 
     * 
     */
    public List<Scale> getScale() {
        if (scale == null) {
            scale = new ArrayList<Scale>();
        }
        return this.scale;
    }

}

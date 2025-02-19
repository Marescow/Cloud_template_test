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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "percentageOrValue"
})
@XmlRootElement(name = "QuantityTolerance")
public class QuantityTolerance {

    @XmlElements({
        @XmlElement(name = "Percentage", required = true, type = Percentage.class),
        @XmlElement(name = "Value", required = true, type = Value.class)
    })
    protected List<Object> percentageOrValue;

    /**
     * Gets the value of the percentageOrValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the percentageOrValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPercentageOrValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Percentage }
     * {@link Value }
     * 
     * 
     */
    public List<Object> getPercentageOrValue() {
        if (percentageOrValue == null) {
            percentageOrValue = new ArrayList<Object>();
        }
        return this.percentageOrValue;
    }

}

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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "deductionAmountOrDeductionPercentOrDeductedPrice"
})
@XmlRootElement(name = "AdditionalDeduction")
public class AdditionalDeduction {

    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String type;
    @XmlElements({
        @XmlElement(name = "DeductionAmount", required = true, type = DeductionAmount.class),
        @XmlElement(name = "DeductionPercent", required = true, type = DeductionPercent.class),
        @XmlElement(name = "DeductedPrice", required = true, type = DeductedPrice.class)
    })
    protected List<Object> deductionAmountOrDeductionPercentOrDeductedPrice;

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the deductionAmountOrDeductionPercentOrDeductedPrice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deductionAmountOrDeductionPercentOrDeductedPrice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeductionAmountOrDeductionPercentOrDeductedPrice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeductionAmount }
     * {@link DeductionPercent }
     * {@link DeductedPrice }
     * 
     * 
     */
    public List<Object> getDeductionAmountOrDeductionPercentOrDeductedPrice() {
        if (deductionAmountOrDeductionPercentOrDeductedPrice == null) {
            deductionAmountOrDeductionPercentOrDeductedPrice = new ArrayList<Object>();
        }
        return this.deductionAmountOrDeductionPercentOrDeductedPrice;
    }

}

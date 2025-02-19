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
    "originalPrice",
    "additionalDeductionOrAdditionalCost",
    "tax",
    "modificationDetail"
})
@XmlRootElement(name = "Modification")
public class Modification {

    @XmlAttribute(name = "level")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String level;
    @XmlElement(name = "OriginalPrice")
    protected OriginalPrice originalPrice;
    @XmlElements({
        @XmlElement(name = "AdditionalDeduction", required = true, type = AdditionalDeduction.class),
        @XmlElement(name = "AdditionalCost", required = true, type = AdditionalCost.class)
    })
    protected List<Object> additionalDeductionOrAdditionalCost;
    @XmlElement(name = "Tax")
    protected Tax tax;
    @XmlElement(name = "ModificationDetail")
    protected ModificationDetail modificationDetail;

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
     * Obtient la valeur de la propriété originalPrice.
     * 
     * @return
     *     possible object is
     *     {@link OriginalPrice }
     *     
     */
    public OriginalPrice getOriginalPrice() {
        return originalPrice;
    }

    /**
     * Définit la valeur de la propriété originalPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalPrice }
     *     
     */
    public void setOriginalPrice(OriginalPrice value) {
        this.originalPrice = value;
    }

    /**
     * Gets the value of the additionalDeductionOrAdditionalCost property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalDeductionOrAdditionalCost property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalDeductionOrAdditionalCost().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalDeduction }
     * {@link AdditionalCost }
     * 
     * 
     */
    public List<Object> getAdditionalDeductionOrAdditionalCost() {
        if (additionalDeductionOrAdditionalCost == null) {
            additionalDeductionOrAdditionalCost = new ArrayList<Object>();
        }
        return this.additionalDeductionOrAdditionalCost;
    }

    /**
     * Obtient la valeur de la propriété tax.
     * 
     * @return
     *     possible object is
     *     {@link Tax }
     *     
     */
    public Tax getTax() {
        return tax;
    }

    /**
     * Définit la valeur de la propriété tax.
     * 
     * @param value
     *     allowed object is
     *     {@link Tax }
     *     
     */
    public void setTax(Tax value) {
        this.tax = value;
    }

    /**
     * Obtient la valeur de la propriété modificationDetail.
     * 
     * @return
     *     possible object is
     *     {@link ModificationDetail }
     *     
     */
    public ModificationDetail getModificationDetail() {
        return modificationDetail;
    }

    /**
     * Définit la valeur de la propriété modificationDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link ModificationDetail }
     *     
     */
    public void setModificationDetail(ModificationDetail value) {
        this.modificationDetail = value;
    }

}

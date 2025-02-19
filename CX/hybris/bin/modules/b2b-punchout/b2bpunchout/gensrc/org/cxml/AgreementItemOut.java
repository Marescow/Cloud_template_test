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
    "maxAmount",
    "minAmount",
    "maxReleaseAmount",
    "minReleaseAmount",
    "itemOut"
})
@XmlRootElement(name = "AgreementItemOut")
public class AgreementItemOut {

    @XmlAttribute(name = "maxQuantity")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String maxQuantity;
    @XmlAttribute(name = "minQuantity")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minQuantity;
    @XmlAttribute(name = "maxReleaseQuantity")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String maxReleaseQuantity;
    @XmlAttribute(name = "minReleaseQuantity")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minReleaseQuantity;
    @XmlElement(name = "MaxAmount")
    protected MaxAmount maxAmount;
    @XmlElement(name = "MinAmount")
    protected MinAmount minAmount;
    @XmlElement(name = "MaxReleaseAmount")
    protected MaxReleaseAmount maxReleaseAmount;
    @XmlElement(name = "MinReleaseAmount")
    protected MinReleaseAmount minReleaseAmount;
    @XmlElement(name = "ItemOut", required = true)
    protected ItemOut itemOut;

    /**
     * Obtient la valeur de la propriété maxQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxQuantity() {
        return maxQuantity;
    }

    /**
     * Définit la valeur de la propriété maxQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxQuantity(String value) {
        this.maxQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété minQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinQuantity() {
        return minQuantity;
    }

    /**
     * Définit la valeur de la propriété minQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinQuantity(String value) {
        this.minQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété maxReleaseQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxReleaseQuantity() {
        return maxReleaseQuantity;
    }

    /**
     * Définit la valeur de la propriété maxReleaseQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxReleaseQuantity(String value) {
        this.maxReleaseQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété minReleaseQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinReleaseQuantity() {
        return minReleaseQuantity;
    }

    /**
     * Définit la valeur de la propriété minReleaseQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinReleaseQuantity(String value) {
        this.minReleaseQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété maxAmount.
     * 
     * @return
     *     possible object is
     *     {@link MaxAmount }
     *     
     */
    public MaxAmount getMaxAmount() {
        return maxAmount;
    }

    /**
     * Définit la valeur de la propriété maxAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxAmount }
     *     
     */
    public void setMaxAmount(MaxAmount value) {
        this.maxAmount = value;
    }

    /**
     * Obtient la valeur de la propriété minAmount.
     * 
     * @return
     *     possible object is
     *     {@link MinAmount }
     *     
     */
    public MinAmount getMinAmount() {
        return minAmount;
    }

    /**
     * Définit la valeur de la propriété minAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MinAmount }
     *     
     */
    public void setMinAmount(MinAmount value) {
        this.minAmount = value;
    }

    /**
     * Obtient la valeur de la propriété maxReleaseAmount.
     * 
     * @return
     *     possible object is
     *     {@link MaxReleaseAmount }
     *     
     */
    public MaxReleaseAmount getMaxReleaseAmount() {
        return maxReleaseAmount;
    }

    /**
     * Définit la valeur de la propriété maxReleaseAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxReleaseAmount }
     *     
     */
    public void setMaxReleaseAmount(MaxReleaseAmount value) {
        this.maxReleaseAmount = value;
    }

    /**
     * Obtient la valeur de la propriété minReleaseAmount.
     * 
     * @return
     *     possible object is
     *     {@link MinReleaseAmount }
     *     
     */
    public MinReleaseAmount getMinReleaseAmount() {
        return minReleaseAmount;
    }

    /**
     * Définit la valeur de la propriété minReleaseAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link MinReleaseAmount }
     *     
     */
    public void setMinReleaseAmount(MinReleaseAmount value) {
        this.minReleaseAmount = value;
    }

    /**
     * Obtient la valeur de la propriété itemOut.
     * 
     * @return
     *     possible object is
     *     {@link ItemOut }
     *     
     */
    public ItemOut getItemOut() {
        return itemOut;
    }

    /**
     * Définit la valeur de la propriété itemOut.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemOut }
     *     
     */
    public void setItemOut(ItemOut value) {
        this.itemOut = value;
    }

}

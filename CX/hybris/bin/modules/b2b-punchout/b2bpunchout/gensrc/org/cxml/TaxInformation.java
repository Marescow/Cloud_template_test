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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "legalName",
    "taxID"
})
@XmlRootElement(name = "TaxInformation")
public class TaxInformation {

    @XmlAttribute(name = "isExemptFromBackupWithholding")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isExemptFromBackupWithholding;
    @XmlElement(name = "LegalName")
    protected LegalName legalName;
    @XmlElement(name = "TaxID")
    protected List<TaxID> taxID;

    /**
     * Obtient la valeur de la propriété isExemptFromBackupWithholding.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsExemptFromBackupWithholding() {
        return isExemptFromBackupWithholding;
    }

    /**
     * Définit la valeur de la propriété isExemptFromBackupWithholding.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsExemptFromBackupWithholding(String value) {
        this.isExemptFromBackupWithholding = value;
    }

    /**
     * Obtient la valeur de la propriété legalName.
     * 
     * @return
     *     possible object is
     *     {@link LegalName }
     *     
     */
    public LegalName getLegalName() {
        return legalName;
    }

    /**
     * Définit la valeur de la propriété legalName.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalName }
     *     
     */
    public void setLegalName(LegalName value) {
        this.legalName = value;
    }

    /**
     * Gets the value of the taxID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxID }
     * 
     * 
     */
    public List<TaxID> getTaxID() {
        if (taxID == null) {
            taxID = new ArrayList<TaxID>();
        }
        return this.taxID;
    }

}

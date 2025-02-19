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
    "annualRevenue",
    "stateOfIncorporation",
    "ownershipType"
})
@XmlRootElement(name = "CorporateInformation")
public class CorporateInformation {

    @XmlAttribute(name = "yearFounded")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String yearFounded;
    @XmlAttribute(name = "numberOfEmployees")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String numberOfEmployees;
    @XmlAttribute(name = "organizationType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String organizationType;
    @XmlElement(name = "AnnualRevenue")
    protected AnnualRevenue annualRevenue;
    @XmlElement(name = "StateOfIncorporation")
    protected StateOfIncorporation stateOfIncorporation;
    @XmlElement(name = "OwnershipType")
    protected List<OwnershipType> ownershipType;

    /**
     * Obtient la valeur de la propriété yearFounded.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYearFounded() {
        return yearFounded;
    }

    /**
     * Définit la valeur de la propriété yearFounded.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYearFounded(String value) {
        this.yearFounded = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfEmployees.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    /**
     * Définit la valeur de la propriété numberOfEmployees.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfEmployees(String value) {
        this.numberOfEmployees = value;
    }

    /**
     * Obtient la valeur de la propriété organizationType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationType() {
        return organizationType;
    }

    /**
     * Définit la valeur de la propriété organizationType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationType(String value) {
        this.organizationType = value;
    }

    /**
     * Obtient la valeur de la propriété annualRevenue.
     * 
     * @return
     *     possible object is
     *     {@link AnnualRevenue }
     *     
     */
    public AnnualRevenue getAnnualRevenue() {
        return annualRevenue;
    }

    /**
     * Définit la valeur de la propriété annualRevenue.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnualRevenue }
     *     
     */
    public void setAnnualRevenue(AnnualRevenue value) {
        this.annualRevenue = value;
    }

    /**
     * Obtient la valeur de la propriété stateOfIncorporation.
     * 
     * @return
     *     possible object is
     *     {@link StateOfIncorporation }
     *     
     */
    public StateOfIncorporation getStateOfIncorporation() {
        return stateOfIncorporation;
    }

    /**
     * Définit la valeur de la propriété stateOfIncorporation.
     * 
     * @param value
     *     allowed object is
     *     {@link StateOfIncorporation }
     *     
     */
    public void setStateOfIncorporation(StateOfIncorporation value) {
        this.stateOfIncorporation = value;
    }

    /**
     * Gets the value of the ownershipType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ownershipType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOwnershipType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OwnershipType }
     * 
     * 
     */
    public List<OwnershipType> getOwnershipType() {
        if (ownershipType == null) {
            ownershipType = new ArrayList<OwnershipType>();
        }
        return this.ownershipType;
    }

}

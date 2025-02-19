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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "comments",
    "supplierID",
    "supplierLocation"
})
@XmlRootElement(name = "Supplier")
public class Supplier {

    @XmlAttribute(name = "corporateURL")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String corporateURL;
    @XmlAttribute(name = "storeFrontURL")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String storeFrontURL;
    @XmlElement(name = "Name", required = true)
    protected Name name;
    @XmlElement(name = "Comments")
    protected Comments comments;
    @XmlElement(name = "SupplierID", required = true)
    protected List<SupplierID> supplierID;
    @XmlElement(name = "SupplierLocation")
    protected List<SupplierLocation> supplierLocation;

    /**
     * Obtient la valeur de la propriété corporateURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorporateURL() {
        return corporateURL;
    }

    /**
     * Définit la valeur de la propriété corporateURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorporateURL(String value) {
        this.corporateURL = value;
    }

    /**
     * Obtient la valeur de la propriété storeFrontURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreFrontURL() {
        return storeFrontURL;
    }

    /**
     * Définit la valeur de la propriété storeFrontURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreFrontURL(String value) {
        this.storeFrontURL = value;
    }

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setName(Name value) {
        this.name = value;
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

    /**
     * Gets the value of the supplierID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplierID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplierID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplierID }
     * 
     * 
     */
    public List<SupplierID> getSupplierID() {
        if (supplierID == null) {
            supplierID = new ArrayList<SupplierID>();
        }
        return this.supplierID;
    }

    /**
     * Gets the value of the supplierLocation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplierLocation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplierLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplierLocation }
     * 
     * 
     */
    public List<SupplierLocation> getSupplierLocation() {
        if (supplierLocation == null) {
            supplierLocation = new ArrayList<SupplierLocation>();
        }
        return this.supplierLocation;
    }

}

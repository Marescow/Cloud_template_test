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
    "buyerBatchID",
    "supplierBatchID",
    "propertyValuation"
})
@XmlRootElement(name = "Batch")
public class Batch {

    @XmlAttribute(name = "productionDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String productionDate;
    @XmlAttribute(name = "expirationDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String expirationDate;
    @XmlAttribute(name = "inspectionDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String inspectionDate;
    @XmlAttribute(name = "shelfLife")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String shelfLife;
    @XmlAttribute(name = "originCountryCode")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String originCountryCode;
    @XmlAttribute(name = "batchQuantity")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String batchQuantity;
    @XmlElement(name = "BuyerBatchID")
    protected String buyerBatchID;
    @XmlElement(name = "SupplierBatchID")
    protected String supplierBatchID;
    @XmlElement(name = "PropertyValuation")
    protected List<PropertyValuation> propertyValuation;

    /**
     * Obtient la valeur de la propriété productionDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * Définit la valeur de la propriété productionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductionDate(String value) {
        this.productionDate = value;
    }

    /**
     * Obtient la valeur de la propriété expirationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Définit la valeur de la propriété expirationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

    /**
     * Obtient la valeur de la propriété inspectionDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectionDate() {
        return inspectionDate;
    }

    /**
     * Définit la valeur de la propriété inspectionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectionDate(String value) {
        this.inspectionDate = value;
    }

    /**
     * Obtient la valeur de la propriété shelfLife.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShelfLife() {
        return shelfLife;
    }

    /**
     * Définit la valeur de la propriété shelfLife.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShelfLife(String value) {
        this.shelfLife = value;
    }

    /**
     * Obtient la valeur de la propriété originCountryCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCountryCode() {
        return originCountryCode;
    }

    /**
     * Définit la valeur de la propriété originCountryCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCountryCode(String value) {
        this.originCountryCode = value;
    }

    /**
     * Obtient la valeur de la propriété batchQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchQuantity() {
        return batchQuantity;
    }

    /**
     * Définit la valeur de la propriété batchQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchQuantity(String value) {
        this.batchQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété buyerBatchID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerBatchID() {
        return buyerBatchID;
    }

    /**
     * Définit la valeur de la propriété buyerBatchID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerBatchID(String value) {
        this.buyerBatchID = value;
    }

    /**
     * Obtient la valeur de la propriété supplierBatchID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierBatchID() {
        return supplierBatchID;
    }

    /**
     * Définit la valeur de la propriété supplierBatchID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierBatchID(String value) {
        this.supplierBatchID = value;
    }

    /**
     * Gets the value of the propertyValuation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyValuation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyValuation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyValuation }
     * 
     * 
     */
    public List<PropertyValuation> getPropertyValuation() {
        if (propertyValuation == null) {
            propertyValuation = new ArrayList<PropertyValuation>();
        }
        return this.propertyValuation;
    }

}

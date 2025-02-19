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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "unitPrice",
    "description",
    "overallLimit",
    "expectedLimit",
    "unitOfMeasure",
    "priceBasisQuantity",
    "classification",
    "manufacturerPartID",
    "manufacturerName",
    "url",
    "leadTime",
    "dimension",
    "itemDetailIndustry",
    "attachmentReference",
    "plannedAcceptanceDays",
    "extrinsic"
})
@XmlRootElement(name = "ItemDetail")
public class ItemDetail {

    @XmlElement(name = "UnitPrice", required = true)
    protected UnitPrice unitPrice;
    @XmlElement(name = "Description", required = true)
    protected List<Description> description;
    @XmlElement(name = "OverallLimit")
    protected OverallLimit overallLimit;
    @XmlElement(name = "ExpectedLimit")
    protected ExpectedLimit expectedLimit;
    @XmlElement(name = "UnitOfMeasure", required = true)
    protected String unitOfMeasure;
    @XmlElement(name = "PriceBasisQuantity")
    protected PriceBasisQuantity priceBasisQuantity;
    @XmlElement(name = "Classification", required = true)
    protected List<Classification> classification;
    @XmlElement(name = "ManufacturerPartID")
    protected String manufacturerPartID;
    @XmlElement(name = "ManufacturerName")
    protected ManufacturerName manufacturerName;
    @XmlElement(name = "URL")
    protected URL url;
    @XmlElement(name = "LeadTime")
    protected String leadTime;
    @XmlElement(name = "Dimension")
    protected List<Dimension> dimension;
    @XmlElement(name = "ItemDetailIndustry")
    protected ItemDetailIndustry itemDetailIndustry;
    @XmlElement(name = "AttachmentReference")
    protected List<AttachmentReference> attachmentReference;
    @XmlElement(name = "PlannedAcceptanceDays")
    protected String plannedAcceptanceDays;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété unitPrice.
     * 
     * @return
     *     possible object is
     *     {@link UnitPrice }
     *     
     */
    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    /**
     * Définit la valeur de la propriété unitPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitPrice }
     *     
     */
    public void setUnitPrice(UnitPrice value) {
        this.unitPrice = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getDescription() {
        if (description == null) {
            description = new ArrayList<Description>();
        }
        return this.description;
    }

    /**
     * Obtient la valeur de la propriété overallLimit.
     * 
     * @return
     *     possible object is
     *     {@link OverallLimit }
     *     
     */
    public OverallLimit getOverallLimit() {
        return overallLimit;
    }

    /**
     * Définit la valeur de la propriété overallLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link OverallLimit }
     *     
     */
    public void setOverallLimit(OverallLimit value) {
        this.overallLimit = value;
    }

    /**
     * Obtient la valeur de la propriété expectedLimit.
     * 
     * @return
     *     possible object is
     *     {@link ExpectedLimit }
     *     
     */
    public ExpectedLimit getExpectedLimit() {
        return expectedLimit;
    }

    /**
     * Définit la valeur de la propriété expectedLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpectedLimit }
     *     
     */
    public void setExpectedLimit(ExpectedLimit value) {
        this.expectedLimit = value;
    }

    /**
     * Obtient la valeur de la propriété unitOfMeasure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Définit la valeur de la propriété unitOfMeasure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
    }

    /**
     * Obtient la valeur de la propriété priceBasisQuantity.
     * 
     * @return
     *     possible object is
     *     {@link PriceBasisQuantity }
     *     
     */
    public PriceBasisQuantity getPriceBasisQuantity() {
        return priceBasisQuantity;
    }

    /**
     * Définit la valeur de la propriété priceBasisQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceBasisQuantity }
     *     
     */
    public void setPriceBasisQuantity(PriceBasisQuantity value) {
        this.priceBasisQuantity = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Classification }
     * 
     * 
     */
    public List<Classification> getClassification() {
        if (classification == null) {
            classification = new ArrayList<Classification>();
        }
        return this.classification;
    }

    /**
     * Obtient la valeur de la propriété manufacturerPartID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturerPartID() {
        return manufacturerPartID;
    }

    /**
     * Définit la valeur de la propriété manufacturerPartID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturerPartID(String value) {
        this.manufacturerPartID = value;
    }

    /**
     * Obtient la valeur de la propriété manufacturerName.
     * 
     * @return
     *     possible object is
     *     {@link ManufacturerName }
     *     
     */
    public ManufacturerName getManufacturerName() {
        return manufacturerName;
    }

    /**
     * Définit la valeur de la propriété manufacturerName.
     * 
     * @param value
     *     allowed object is
     *     {@link ManufacturerName }
     *     
     */
    public void setManufacturerName(ManufacturerName value) {
        this.manufacturerName = value;
    }

    /**
     * Obtient la valeur de la propriété url.
     * 
     * @return
     *     possible object is
     *     {@link URL }
     *     
     */
    public URL getURL() {
        return url;
    }

    /**
     * Définit la valeur de la propriété url.
     * 
     * @param value
     *     allowed object is
     *     {@link URL }
     *     
     */
    public void setURL(URL value) {
        this.url = value;
    }

    /**
     * Obtient la valeur de la propriété leadTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadTime() {
        return leadTime;
    }

    /**
     * Définit la valeur de la propriété leadTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadTime(String value) {
        this.leadTime = value;
    }

    /**
     * Gets the value of the dimension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dimension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDimension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dimension }
     * 
     * 
     */
    public List<Dimension> getDimension() {
        if (dimension == null) {
            dimension = new ArrayList<Dimension>();
        }
        return this.dimension;
    }

    /**
     * Obtient la valeur de la propriété itemDetailIndustry.
     * 
     * @return
     *     possible object is
     *     {@link ItemDetailIndustry }
     *     
     */
    public ItemDetailIndustry getItemDetailIndustry() {
        return itemDetailIndustry;
    }

    /**
     * Définit la valeur de la propriété itemDetailIndustry.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemDetailIndustry }
     *     
     */
    public void setItemDetailIndustry(ItemDetailIndustry value) {
        this.itemDetailIndustry = value;
    }

    /**
     * Gets the value of the attachmentReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachmentReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachmentReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttachmentReference }
     * 
     * 
     */
    public List<AttachmentReference> getAttachmentReference() {
        if (attachmentReference == null) {
            attachmentReference = new ArrayList<AttachmentReference>();
        }
        return this.attachmentReference;
    }

    /**
     * Obtient la valeur de la propriété plannedAcceptanceDays.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlannedAcceptanceDays() {
        return plannedAcceptanceDays;
    }

    /**
     * Définit la valeur de la propriété plannedAcceptanceDays.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlannedAcceptanceDays(String value) {
        this.plannedAcceptanceDays = value;
    }

    /**
     * Gets the value of the extrinsic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extrinsic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtrinsic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Extrinsic }
     * 
     * 
     */
    public List<Extrinsic> getExtrinsic() {
        if (extrinsic == null) {
            extrinsic = new ArrayList<Extrinsic>();
        }
        return this.extrinsic;
    }

}

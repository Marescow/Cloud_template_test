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
    "itemOutRetail",
    "referenceDocumentInfo",
    "priority",
    "qualityInfo",
    "serialNumberInfo",
    "batchInfo",
    "assetInfo",
    "packagingDistribution"
})
@XmlRootElement(name = "ItemOutIndustry")
public class ItemOutIndustry {

    @XmlAttribute(name = "planningType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String planningType;
    @XmlAttribute(name = "requiresRealTimeConsumption")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String requiresRealTimeConsumption;
    @XmlAttribute(name = "isHUMandatory")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isHUMandatory;
    @XmlElement(name = "ItemOutRetail")
    protected ItemOutRetail itemOutRetail;
    @XmlElement(name = "ReferenceDocumentInfo")
    protected List<ReferenceDocumentInfo> referenceDocumentInfo;
    @XmlElement(name = "Priority")
    protected Priority priority;
    @XmlElement(name = "QualityInfo")
    protected QualityInfo qualityInfo;
    @XmlElement(name = "SerialNumberInfo")
    protected SerialNumberInfo serialNumberInfo;
    @XmlElement(name = "BatchInfo")
    protected BatchInfo batchInfo;
    @XmlElement(name = "AssetInfo")
    protected List<AssetInfo> assetInfo;
    @XmlElement(name = "PackagingDistribution")
    protected List<PackagingDistribution> packagingDistribution;

    /**
     * Obtient la valeur de la propriété planningType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanningType() {
        return planningType;
    }

    /**
     * Définit la valeur de la propriété planningType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanningType(String value) {
        this.planningType = value;
    }

    /**
     * Obtient la valeur de la propriété requiresRealTimeConsumption.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiresRealTimeConsumption() {
        return requiresRealTimeConsumption;
    }

    /**
     * Définit la valeur de la propriété requiresRealTimeConsumption.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiresRealTimeConsumption(String value) {
        this.requiresRealTimeConsumption = value;
    }

    /**
     * Obtient la valeur de la propriété isHUMandatory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsHUMandatory() {
        return isHUMandatory;
    }

    /**
     * Définit la valeur de la propriété isHUMandatory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsHUMandatory(String value) {
        this.isHUMandatory = value;
    }

    /**
     * Obtient la valeur de la propriété itemOutRetail.
     * 
     * @return
     *     possible object is
     *     {@link ItemOutRetail }
     *     
     */
    public ItemOutRetail getItemOutRetail() {
        return itemOutRetail;
    }

    /**
     * Définit la valeur de la propriété itemOutRetail.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemOutRetail }
     *     
     */
    public void setItemOutRetail(ItemOutRetail value) {
        this.itemOutRetail = value;
    }

    /**
     * Gets the value of the referenceDocumentInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceDocumentInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceDocumentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceDocumentInfo }
     * 
     * 
     */
    public List<ReferenceDocumentInfo> getReferenceDocumentInfo() {
        if (referenceDocumentInfo == null) {
            referenceDocumentInfo = new ArrayList<ReferenceDocumentInfo>();
        }
        return this.referenceDocumentInfo;
    }

    /**
     * Obtient la valeur de la propriété priority.
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Définit la valeur de la propriété priority.
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPriority(Priority value) {
        this.priority = value;
    }

    /**
     * Obtient la valeur de la propriété qualityInfo.
     * 
     * @return
     *     possible object is
     *     {@link QualityInfo }
     *     
     */
    public QualityInfo getQualityInfo() {
        return qualityInfo;
    }

    /**
     * Définit la valeur de la propriété qualityInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInfo }
     *     
     */
    public void setQualityInfo(QualityInfo value) {
        this.qualityInfo = value;
    }

    /**
     * Obtient la valeur de la propriété serialNumberInfo.
     * 
     * @return
     *     possible object is
     *     {@link SerialNumberInfo }
     *     
     */
    public SerialNumberInfo getSerialNumberInfo() {
        return serialNumberInfo;
    }

    /**
     * Définit la valeur de la propriété serialNumberInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link SerialNumberInfo }
     *     
     */
    public void setSerialNumberInfo(SerialNumberInfo value) {
        this.serialNumberInfo = value;
    }

    /**
     * Obtient la valeur de la propriété batchInfo.
     * 
     * @return
     *     possible object is
     *     {@link BatchInfo }
     *     
     */
    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    /**
     * Définit la valeur de la propriété batchInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchInfo }
     *     
     */
    public void setBatchInfo(BatchInfo value) {
        this.batchInfo = value;
    }

    /**
     * Gets the value of the assetInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetInfo }
     * 
     * 
     */
    public List<AssetInfo> getAssetInfo() {
        if (assetInfo == null) {
            assetInfo = new ArrayList<AssetInfo>();
        }
        return this.assetInfo;
    }

    /**
     * Gets the value of the packagingDistribution property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packagingDistribution property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackagingDistribution().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackagingDistribution }
     * 
     * 
     */
    public List<PackagingDistribution> getPackagingDistribution() {
        if (packagingDistribution == null) {
            packagingDistribution = new ArrayList<PackagingDistribution>();
        }
        return this.packagingDistribution;
    }

}

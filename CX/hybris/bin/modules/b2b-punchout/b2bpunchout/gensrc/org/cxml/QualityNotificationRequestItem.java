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
    "qnCode",
    "ownerInfo",
    "description",
    "period",
    "additionalQNInfo",
    "qualityNotificationTask",
    "qualityNotificationActivity",
    "qualityNotificationCause",
    "assetInfo",
    "extrinsic"
})
@XmlRootElement(name = "QualityNotificationRequestItem")
public class QualityNotificationRequestItem {

    @XmlAttribute(name = "defectId", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String defectId;
    @XmlAttribute(name = "defectCount")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String defectCount;
    @XmlAttribute(name = "isCompleted")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isCompleted;
    @XmlAttribute(name = "completedDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String completedDate;
    @XmlAttribute(name = "minimumRequiredTasks")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minimumRequiredTasks;
    @XmlAttribute(name = "minimumRequiredActivities")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minimumRequiredActivities;
    @XmlAttribute(name = "minimumRequiredCauses")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String minimumRequiredCauses;
    @XmlElement(name = "QNCode")
    protected List<QNCode> qnCode;
    @XmlElement(name = "OwnerInfo")
    protected OwnerInfo ownerInfo;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "Period")
    protected Period period;
    @XmlElement(name = "AdditionalQNInfo")
    protected List<AdditionalQNInfo> additionalQNInfo;
    @XmlElement(name = "QualityNotificationTask")
    protected List<QualityNotificationTask> qualityNotificationTask;
    @XmlElement(name = "QualityNotificationActivity")
    protected List<QualityNotificationActivity> qualityNotificationActivity;
    @XmlElement(name = "QualityNotificationCause")
    protected List<QualityNotificationCause> qualityNotificationCause;
    @XmlElement(name = "AssetInfo")
    protected List<AssetInfo> assetInfo;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété defectId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefectId() {
        return defectId;
    }

    /**
     * Définit la valeur de la propriété defectId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefectId(String value) {
        this.defectId = value;
    }

    /**
     * Obtient la valeur de la propriété defectCount.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefectCount() {
        return defectCount;
    }

    /**
     * Définit la valeur de la propriété defectCount.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefectCount(String value) {
        this.defectCount = value;
    }

    /**
     * Obtient la valeur de la propriété isCompleted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsCompleted() {
        return isCompleted;
    }

    /**
     * Définit la valeur de la propriété isCompleted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsCompleted(String value) {
        this.isCompleted = value;
    }

    /**
     * Obtient la valeur de la propriété completedDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompletedDate() {
        return completedDate;
    }

    /**
     * Définit la valeur de la propriété completedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompletedDate(String value) {
        this.completedDate = value;
    }

    /**
     * Obtient la valeur de la propriété minimumRequiredTasks.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumRequiredTasks() {
        return minimumRequiredTasks;
    }

    /**
     * Définit la valeur de la propriété minimumRequiredTasks.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumRequiredTasks(String value) {
        this.minimumRequiredTasks = value;
    }

    /**
     * Obtient la valeur de la propriété minimumRequiredActivities.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumRequiredActivities() {
        return minimumRequiredActivities;
    }

    /**
     * Définit la valeur de la propriété minimumRequiredActivities.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumRequiredActivities(String value) {
        this.minimumRequiredActivities = value;
    }

    /**
     * Obtient la valeur de la propriété minimumRequiredCauses.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumRequiredCauses() {
        return minimumRequiredCauses;
    }

    /**
     * Définit la valeur de la propriété minimumRequiredCauses.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumRequiredCauses(String value) {
        this.minimumRequiredCauses = value;
    }

    /**
     * Gets the value of the qnCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qnCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQNCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QNCode }
     * 
     * 
     */
    public List<QNCode> getQNCode() {
        if (qnCode == null) {
            qnCode = new ArrayList<QNCode>();
        }
        return this.qnCode;
    }

    /**
     * Obtient la valeur de la propriété ownerInfo.
     * 
     * @return
     *     possible object is
     *     {@link OwnerInfo }
     *     
     */
    public OwnerInfo getOwnerInfo() {
        return ownerInfo;
    }

    /**
     * Définit la valeur de la propriété ownerInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnerInfo }
     *     
     */
    public void setOwnerInfo(OwnerInfo value) {
        this.ownerInfo = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété period.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * Définit la valeur de la propriété period.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setPeriod(Period value) {
        this.period = value;
    }

    /**
     * Gets the value of the additionalQNInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalQNInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalQNInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalQNInfo }
     * 
     * 
     */
    public List<AdditionalQNInfo> getAdditionalQNInfo() {
        if (additionalQNInfo == null) {
            additionalQNInfo = new ArrayList<AdditionalQNInfo>();
        }
        return this.additionalQNInfo;
    }

    /**
     * Gets the value of the qualityNotificationTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityNotificationTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityNotificationTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityNotificationTask }
     * 
     * 
     */
    public List<QualityNotificationTask> getQualityNotificationTask() {
        if (qualityNotificationTask == null) {
            qualityNotificationTask = new ArrayList<QualityNotificationTask>();
        }
        return this.qualityNotificationTask;
    }

    /**
     * Gets the value of the qualityNotificationActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityNotificationActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityNotificationActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityNotificationActivity }
     * 
     * 
     */
    public List<QualityNotificationActivity> getQualityNotificationActivity() {
        if (qualityNotificationActivity == null) {
            qualityNotificationActivity = new ArrayList<QualityNotificationActivity>();
        }
        return this.qualityNotificationActivity;
    }

    /**
     * Gets the value of the qualityNotificationCause property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityNotificationCause property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityNotificationCause().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityNotificationCause }
     * 
     * 
     */
    public List<QualityNotificationCause> getQualityNotificationCause() {
        if (qualityNotificationCause == null) {
            qualityNotificationCause = new ArrayList<QualityNotificationCause>();
        }
        return this.qualityNotificationCause;
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

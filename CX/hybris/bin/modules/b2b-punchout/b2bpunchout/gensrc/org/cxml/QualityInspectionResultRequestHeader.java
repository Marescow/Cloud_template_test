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
    "qualityInspectionRequestReference",
    "batch",
    "comments",
    "qualityInspectionQuantity",
    "assetInfo",
    "extrinsic"
})
@XmlRootElement(name = "QualityInspectionResultRequestHeader")
public class QualityInspectionResultRequestHeader {

    @XmlAttribute(name = "resultID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String resultID;
    @XmlAttribute(name = "resultDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String resultDate;
    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String version;
    @XmlAttribute(name = "createdBy")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String createdBy;
    @XmlElement(name = "QualityInspectionRequestReference", required = true)
    protected QualityInspectionRequestReference qualityInspectionRequestReference;
    @XmlElement(name = "Batch")
    protected List<Batch> batch;
    @XmlElement(name = "Comments")
    protected List<Comments> comments;
    @XmlElement(name = "QualityInspectionQuantity")
    protected QualityInspectionQuantity qualityInspectionQuantity;
    @XmlElement(name = "AssetInfo")
    protected List<AssetInfo> assetInfo;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété resultID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultID() {
        return resultID;
    }

    /**
     * Définit la valeur de la propriété resultID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultID(String value) {
        this.resultID = value;
    }

    /**
     * Obtient la valeur de la propriété resultDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultDate() {
        return resultDate;
    }

    /**
     * Définit la valeur de la propriété resultDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultDate(String value) {
        this.resultDate = value;
    }

    /**
     * Obtient la valeur de la propriété version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propriété createdBy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Définit la valeur de la propriété createdBy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Obtient la valeur de la propriété qualityInspectionRequestReference.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionRequestReference }
     *     
     */
    public QualityInspectionRequestReference getQualityInspectionRequestReference() {
        return qualityInspectionRequestReference;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionRequestReference.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionRequestReference }
     *     
     */
    public void setQualityInspectionRequestReference(QualityInspectionRequestReference value) {
        this.qualityInspectionRequestReference = value;
    }

    /**
     * Gets the value of the batch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Batch }
     * 
     * 
     */
    public List<Batch> getBatch() {
        if (batch == null) {
            batch = new ArrayList<Batch>();
        }
        return this.batch;
    }

    /**
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comments }
     * 
     * 
     */
    public List<Comments> getComments() {
        if (comments == null) {
            comments = new ArrayList<Comments>();
        }
        return this.comments;
    }

    /**
     * Obtient la valeur de la propriété qualityInspectionQuantity.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionQuantity }
     *     
     */
    public QualityInspectionQuantity getQualityInspectionQuantity() {
        return qualityInspectionQuantity;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionQuantity }
     *     
     */
    public void setQualityInspectionQuantity(QualityInspectionQuantity value) {
        this.qualityInspectionQuantity = value;
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

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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "qualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference",
    "qualityInspectionLotStock",
    "valueGroup",
    "description",
    "extrinsic"
})
@XmlRootElement(name = "QualityInspectionDecisionDetail")
public class QualityInspectionDecisionDetail {

    @XmlAttribute(name = "decisionID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String decisionID;
    @XmlAttribute(name = "decisionDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String decisionDate;
    @XmlAttribute(name = "status")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String status;
    @XmlAttribute(name = "qualityScore")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String qualityScore;
    @XmlAttribute(name = "createdBy")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String createdBy;
    @XmlElements({
        @XmlElement(name = "QualityInspectionResultReference", required = true, type = QualityInspectionResultReference.class),
        @XmlElement(name = "QualityInspectionRequestReference", required = true, type = QualityInspectionRequestReference.class),
        @XmlElement(name = "ShipNoticeReference", required = true, type = ShipNoticeReference.class),
        @XmlElement(name = "ReceiptReference", required = true, type = ReceiptReference.class)
    })
    protected List<Object> qualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference;
    @XmlElement(name = "QualityInspectionLotStock", required = true)
    protected QualityInspectionLotStock qualityInspectionLotStock;
    @XmlElement(name = "ValueGroup")
    protected List<ValueGroup> valueGroup;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété decisionID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionID() {
        return decisionID;
    }

    /**
     * Définit la valeur de la propriété decisionID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionID(String value) {
        this.decisionID = value;
    }

    /**
     * Obtient la valeur de la propriété decisionDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionDate() {
        return decisionDate;
    }

    /**
     * Définit la valeur de la propriété decisionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionDate(String value) {
        this.decisionDate = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtient la valeur de la propriété qualityScore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualityScore() {
        return qualityScore;
    }

    /**
     * Définit la valeur de la propriété qualityScore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualityScore(String value) {
        this.qualityScore = value;
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
     * Gets the value of the qualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityInspectionResultReference }
     * {@link QualityInspectionRequestReference }
     * {@link ShipNoticeReference }
     * {@link ReceiptReference }
     * 
     * 
     */
    public List<Object> getQualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference() {
        if (qualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference == null) {
            qualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference = new ArrayList<Object>();
        }
        return this.qualityInspectionResultReferenceOrQualityInspectionRequestReferenceOrShipNoticeReferenceOrReceiptReference;
    }

    /**
     * Obtient la valeur de la propriété qualityInspectionLotStock.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionLotStock }
     *     
     */
    public QualityInspectionLotStock getQualityInspectionLotStock() {
        return qualityInspectionLotStock;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionLotStock.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionLotStock }
     *     
     */
    public void setQualityInspectionLotStock(QualityInspectionLotStock value) {
        this.qualityInspectionLotStock = value;
    }

    /**
     * Gets the value of the valueGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueGroup }
     * 
     * 
     */
    public List<ValueGroup> getValueGroup() {
        if (valueGroup == null) {
            valueGroup = new ArrayList<ValueGroup>();
        }
        return this.valueGroup;
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

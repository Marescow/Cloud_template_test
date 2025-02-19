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
    "qualitySampleResult",
    "valueGroup",
    "description",
    "extrinsic"
})
@XmlRootElement(name = "QualityInspectionValuation")
public class QualityInspectionValuation {

    @XmlAttribute(name = "valuationID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String valuationID;
    @XmlAttribute(name = "characteristicID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String characteristicID;
    @XmlAttribute(name = "operationNumber", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String operationNumber;
    @XmlAttribute(name = "workCenter")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String workCenter;
    @XmlAttribute(name = "meanValue")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String meanValue;
    @XmlAttribute(name = "aboveTolerance")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String aboveTolerance;
    @XmlAttribute(name = "belowTolerance")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String belowTolerance;
    @XmlAttribute(name = "inspectedQuantity")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String inspectedQuantity;
    @XmlAttribute(name = "nonConformance")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String nonConformance;
    @XmlAttribute(name = "deviation")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String deviation;
    @XmlAttribute(name = "variance")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String variance;
    @XmlAttribute(name = "numberOfDefects")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String numberOfDefects;
    @XmlAttribute(name = "serialNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String serialNumber;
    @XmlAttribute(name = "inspectionDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String inspectionDate;
    @XmlAttribute(name = "isAdHoc")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isAdHoc;
    @XmlElement(name = "QualitySampleResult")
    protected List<QualitySampleResult> qualitySampleResult;
    @XmlElement(name = "ValueGroup")
    protected List<ValueGroup> valueGroup;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété valuationID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValuationID() {
        return valuationID;
    }

    /**
     * Définit la valeur de la propriété valuationID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValuationID(String value) {
        this.valuationID = value;
    }

    /**
     * Obtient la valeur de la propriété characteristicID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharacteristicID() {
        return characteristicID;
    }

    /**
     * Définit la valeur de la propriété characteristicID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharacteristicID(String value) {
        this.characteristicID = value;
    }

    /**
     * Obtient la valeur de la propriété operationNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationNumber() {
        return operationNumber;
    }

    /**
     * Définit la valeur de la propriété operationNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationNumber(String value) {
        this.operationNumber = value;
    }

    /**
     * Obtient la valeur de la propriété workCenter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkCenter() {
        return workCenter;
    }

    /**
     * Définit la valeur de la propriété workCenter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkCenter(String value) {
        this.workCenter = value;
    }

    /**
     * Obtient la valeur de la propriété meanValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeanValue() {
        return meanValue;
    }

    /**
     * Définit la valeur de la propriété meanValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeanValue(String value) {
        this.meanValue = value;
    }

    /**
     * Obtient la valeur de la propriété aboveTolerance.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAboveTolerance() {
        return aboveTolerance;
    }

    /**
     * Définit la valeur de la propriété aboveTolerance.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAboveTolerance(String value) {
        this.aboveTolerance = value;
    }

    /**
     * Obtient la valeur de la propriété belowTolerance.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBelowTolerance() {
        return belowTolerance;
    }

    /**
     * Définit la valeur de la propriété belowTolerance.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBelowTolerance(String value) {
        this.belowTolerance = value;
    }

    /**
     * Obtient la valeur de la propriété inspectedQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectedQuantity() {
        return inspectedQuantity;
    }

    /**
     * Définit la valeur de la propriété inspectedQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectedQuantity(String value) {
        this.inspectedQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété nonConformance.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonConformance() {
        return nonConformance;
    }

    /**
     * Définit la valeur de la propriété nonConformance.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonConformance(String value) {
        this.nonConformance = value;
    }

    /**
     * Obtient la valeur de la propriété deviation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviation() {
        return deviation;
    }

    /**
     * Définit la valeur de la propriété deviation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviation(String value) {
        this.deviation = value;
    }

    /**
     * Obtient la valeur de la propriété variance.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariance() {
        return variance;
    }

    /**
     * Définit la valeur de la propriété variance.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariance(String value) {
        this.variance = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfDefects.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfDefects() {
        return numberOfDefects;
    }

    /**
     * Définit la valeur de la propriété numberOfDefects.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfDefects(String value) {
        this.numberOfDefects = value;
    }

    /**
     * Obtient la valeur de la propriété serialNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Définit la valeur de la propriété serialNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
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
     * Obtient la valeur de la propriété isAdHoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAdHoc() {
        return isAdHoc;
    }

    /**
     * Définit la valeur de la propriété isAdHoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAdHoc(String value) {
        this.isAdHoc = value;
    }

    /**
     * Gets the value of the qualitySampleResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualitySampleResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualitySampleResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualitySampleResult }
     * 
     * 
     */
    public List<QualitySampleResult> getQualitySampleResult() {
        if (qualitySampleResult == null) {
            qualitySampleResult = new ArrayList<QualitySampleResult>();
        }
        return this.qualitySampleResult;
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

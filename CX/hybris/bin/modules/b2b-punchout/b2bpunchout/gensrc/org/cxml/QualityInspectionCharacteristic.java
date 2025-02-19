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
    "description",
    "idReference",
    "allowedValues",
    "expectedResult",
    "sampleDefinition",
    "comments",
    "extrinsic"
})
@XmlRootElement(name = "QualityInspectionCharacteristic")
public class QualityInspectionCharacteristic {

    @XmlAttribute(name = "characteristicID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String characteristicID;
    @XmlAttribute(name = "operationNumber", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String operationNumber;
    @XmlAttribute(name = "workCenter")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String workCenter;
    @XmlAttribute(name = "procedure")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String procedure;
    @XmlAttribute(name = "isLocked")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isLocked;
    @XmlAttribute(name = "allowDefectRecording")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String allowDefectRecording;
    @XmlAttribute(name = "characteristicType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String characteristicType;
    @XmlAttribute(name = "isQuantitative")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isQuantitative;
    @XmlAttribute(name = "recordingType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String recordingType;
    @XmlAttribute(name = "expirationDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String expirationDate;
    @XmlAttribute(name = "inspectionPoint")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String inspectionPoint;
    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String version;
    @XmlAttribute(name = "isAdHoc")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String isAdHoc;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "IdReference")
    protected List<IdReference> idReference;
    @XmlElement(name = "AllowedValues", required = true)
    protected AllowedValues allowedValues;
    @XmlElement(name = "ExpectedResult")
    protected ExpectedResult expectedResult;
    @XmlElement(name = "SampleDefinition")
    protected SampleDefinition sampleDefinition;
    @XmlElement(name = "Comments")
    protected Comments comments;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

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
     * Obtient la valeur de la propriété procedure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcedure() {
        return procedure;
    }

    /**
     * Définit la valeur de la propriété procedure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcedure(String value) {
        this.procedure = value;
    }

    /**
     * Obtient la valeur de la propriété isLocked.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsLocked() {
        return isLocked;
    }

    /**
     * Définit la valeur de la propriété isLocked.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLocked(String value) {
        this.isLocked = value;
    }

    /**
     * Obtient la valeur de la propriété allowDefectRecording.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowDefectRecording() {
        return allowDefectRecording;
    }

    /**
     * Définit la valeur de la propriété allowDefectRecording.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowDefectRecording(String value) {
        this.allowDefectRecording = value;
    }

    /**
     * Obtient la valeur de la propriété characteristicType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharacteristicType() {
        return characteristicType;
    }

    /**
     * Définit la valeur de la propriété characteristicType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharacteristicType(String value) {
        this.characteristicType = value;
    }

    /**
     * Obtient la valeur de la propriété isQuantitative.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsQuantitative() {
        return isQuantitative;
    }

    /**
     * Définit la valeur de la propriété isQuantitative.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsQuantitative(String value) {
        this.isQuantitative = value;
    }

    /**
     * Obtient la valeur de la propriété recordingType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordingType() {
        return recordingType;
    }

    /**
     * Définit la valeur de la propriété recordingType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordingType(String value) {
        this.recordingType = value;
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
     * Obtient la valeur de la propriété inspectionPoint.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectionPoint() {
        return inspectionPoint;
    }

    /**
     * Définit la valeur de la propriété inspectionPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectionPoint(String value) {
        this.inspectionPoint = value;
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
     * Gets the value of the idReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdReference }
     * 
     * 
     */
    public List<IdReference> getIdReference() {
        if (idReference == null) {
            idReference = new ArrayList<IdReference>();
        }
        return this.idReference;
    }

    /**
     * Obtient la valeur de la propriété allowedValues.
     * 
     * @return
     *     possible object is
     *     {@link AllowedValues }
     *     
     */
    public AllowedValues getAllowedValues() {
        return allowedValues;
    }

    /**
     * Définit la valeur de la propriété allowedValues.
     * 
     * @param value
     *     allowed object is
     *     {@link AllowedValues }
     *     
     */
    public void setAllowedValues(AllowedValues value) {
        this.allowedValues = value;
    }

    /**
     * Obtient la valeur de la propriété expectedResult.
     * 
     * @return
     *     possible object is
     *     {@link ExpectedResult }
     *     
     */
    public ExpectedResult getExpectedResult() {
        return expectedResult;
    }

    /**
     * Définit la valeur de la propriété expectedResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpectedResult }
     *     
     */
    public void setExpectedResult(ExpectedResult value) {
        this.expectedResult = value;
    }

    /**
     * Obtient la valeur de la propriété sampleDefinition.
     * 
     * @return
     *     possible object is
     *     {@link SampleDefinition }
     *     
     */
    public SampleDefinition getSampleDefinition() {
        return sampleDefinition;
    }

    /**
     * Définit la valeur de la propriété sampleDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link SampleDefinition }
     *     
     */
    public void setSampleDefinition(SampleDefinition value) {
        this.sampleDefinition = value;
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

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

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
    "documentReference"
})
@XmlRootElement(name = "QualityInspectionRequestReference")
public class QualityInspectionRequestReference {

    @XmlAttribute(name = "inspectionID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String inspectionID;
    @XmlAttribute(name = "inspectionDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String inspectionDate;
    @XmlElement(name = "DocumentReference")
    protected DocumentReference documentReference;

    /**
     * Obtient la valeur de la propriété inspectionID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectionID() {
        return inspectionID;
    }

    /**
     * Définit la valeur de la propriété inspectionID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectionID(String value) {
        this.inspectionID = value;
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
     * Obtient la valeur de la propriété documentReference.
     * 
     * @return
     *     possible object is
     *     {@link DocumentReference }
     *     
     */
    public DocumentReference getDocumentReference() {
        return documentReference;
    }

    /**
     * Définit la valeur de la propriété documentReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentReference }
     *     
     */
    public void setDocumentReference(DocumentReference value) {
        this.documentReference = value;
    }

}

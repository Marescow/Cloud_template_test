//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

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
    "qualityInspectionResultRequestHeader",
    "qualityInspectionResultRequestDetail"
})
@XmlRootElement(name = "QualityInspectionResultRequest")
public class QualityInspectionResultRequest {

    @XmlElement(name = "QualityInspectionResultRequestHeader", required = true)
    protected QualityInspectionResultRequestHeader qualityInspectionResultRequestHeader;
    @XmlElement(name = "QualityInspectionResultRequestDetail", required = true)
    protected QualityInspectionResultRequestDetail qualityInspectionResultRequestDetail;

    /**
     * Obtient la valeur de la propriété qualityInspectionResultRequestHeader.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionResultRequestHeader }
     *     
     */
    public QualityInspectionResultRequestHeader getQualityInspectionResultRequestHeader() {
        return qualityInspectionResultRequestHeader;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionResultRequestHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionResultRequestHeader }
     *     
     */
    public void setQualityInspectionResultRequestHeader(QualityInspectionResultRequestHeader value) {
        this.qualityInspectionResultRequestHeader = value;
    }

    /**
     * Obtient la valeur de la propriété qualityInspectionResultRequestDetail.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionResultRequestDetail }
     *     
     */
    public QualityInspectionResultRequestDetail getQualityInspectionResultRequestDetail() {
        return qualityInspectionResultRequestDetail;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionResultRequestDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionResultRequestDetail }
     *     
     */
    public void setQualityInspectionResultRequestDetail(QualityInspectionResultRequestDetail value) {
        this.qualityInspectionResultRequestDetail = value;
    }

}

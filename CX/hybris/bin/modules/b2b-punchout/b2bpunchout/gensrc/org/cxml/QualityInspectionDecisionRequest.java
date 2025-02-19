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
    "qualityInspectionDecisionDetail"
})
@XmlRootElement(name = "QualityInspectionDecisionRequest")
public class QualityInspectionDecisionRequest {

    @XmlElement(name = "QualityInspectionDecisionDetail", required = true)
    protected QualityInspectionDecisionDetail qualityInspectionDecisionDetail;

    /**
     * Obtient la valeur de la propriété qualityInspectionDecisionDetail.
     * 
     * @return
     *     possible object is
     *     {@link QualityInspectionDecisionDetail }
     *     
     */
    public QualityInspectionDecisionDetail getQualityInspectionDecisionDetail() {
        return qualityInspectionDecisionDetail;
    }

    /**
     * Définit la valeur de la propriété qualityInspectionDecisionDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInspectionDecisionDetail }
     *     
     */
    public void setQualityInspectionDecisionDetail(QualityInspectionDecisionDetail value) {
        this.qualityInspectionDecisionDetail = value;
    }

}

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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idReferenceOrCertificateInfo"
})
@XmlRootElement(name = "QualityInfo")
public class QualityInfo {

    @XmlAttribute(name = "requiresQualityProcess")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String requiresQualityProcess;
    @XmlElements({
        @XmlElement(name = "IdReference", type = IdReference.class),
        @XmlElement(name = "CertificateInfo", type = CertificateInfo.class)
    })
    protected List<Object> idReferenceOrCertificateInfo;

    /**
     * Obtient la valeur de la propriété requiresQualityProcess.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiresQualityProcess() {
        return requiresQualityProcess;
    }

    /**
     * Définit la valeur de la propriété requiresQualityProcess.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiresQualityProcess(String value) {
        this.requiresQualityProcess = value;
    }

    /**
     * Gets the value of the idReferenceOrCertificateInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idReferenceOrCertificateInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdReferenceOrCertificateInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdReference }
     * {@link CertificateInfo }
     * 
     * 
     */
    public List<Object> getIdReferenceOrCertificateInfo() {
        if (idReferenceOrCertificateInfo == null) {
            idReferenceOrCertificateInfo = new ArrayList<Object>();
        }
        return this.idReferenceOrCertificateInfo;
    }

}

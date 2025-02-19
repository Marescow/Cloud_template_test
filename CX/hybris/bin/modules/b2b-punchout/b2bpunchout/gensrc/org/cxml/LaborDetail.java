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
    "unitRate",
    "period",
    "contractor",
    "jobDescription",
    "supervisor",
    "workLocation",
    "extrinsic"
})
@XmlRootElement(name = "LaborDetail")
public class LaborDetail {

    @XmlAttribute(name = "supplierReferenceCode")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String supplierReferenceCode;
    @XmlElement(name = "UnitRate", required = true)
    protected List<UnitRate> unitRate;
    @XmlElement(name = "Period", required = true)
    protected Period period;
    @XmlElement(name = "Contractor")
    protected Contractor contractor;
    @XmlElement(name = "JobDescription")
    protected JobDescription jobDescription;
    @XmlElement(name = "Supervisor")
    protected Supervisor supervisor;
    @XmlElement(name = "WorkLocation")
    protected WorkLocation workLocation;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété supplierReferenceCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierReferenceCode() {
        return supplierReferenceCode;
    }

    /**
     * Définit la valeur de la propriété supplierReferenceCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierReferenceCode(String value) {
        this.supplierReferenceCode = value;
    }

    /**
     * Gets the value of the unitRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnitRate }
     * 
     * 
     */
    public List<UnitRate> getUnitRate() {
        if (unitRate == null) {
            unitRate = new ArrayList<UnitRate>();
        }
        return this.unitRate;
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
     * Obtient la valeur de la propriété contractor.
     * 
     * @return
     *     possible object is
     *     {@link Contractor }
     *     
     */
    public Contractor getContractor() {
        return contractor;
    }

    /**
     * Définit la valeur de la propriété contractor.
     * 
     * @param value
     *     allowed object is
     *     {@link Contractor }
     *     
     */
    public void setContractor(Contractor value) {
        this.contractor = value;
    }

    /**
     * Obtient la valeur de la propriété jobDescription.
     * 
     * @return
     *     possible object is
     *     {@link JobDescription }
     *     
     */
    public JobDescription getJobDescription() {
        return jobDescription;
    }

    /**
     * Définit la valeur de la propriété jobDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link JobDescription }
     *     
     */
    public void setJobDescription(JobDescription value) {
        this.jobDescription = value;
    }

    /**
     * Obtient la valeur de la propriété supervisor.
     * 
     * @return
     *     possible object is
     *     {@link Supervisor }
     *     
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    /**
     * Définit la valeur de la propriété supervisor.
     * 
     * @param value
     *     allowed object is
     *     {@link Supervisor }
     *     
     */
    public void setSupervisor(Supervisor value) {
        this.supervisor = value;
    }

    /**
     * Obtient la valeur de la propriété workLocation.
     * 
     * @return
     *     possible object is
     *     {@link WorkLocation }
     *     
     */
    public WorkLocation getWorkLocation() {
        return workLocation;
    }

    /**
     * Définit la valeur de la propriété workLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkLocation }
     *     
     */
    public void setWorkLocation(WorkLocation value) {
        this.workLocation = value;
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

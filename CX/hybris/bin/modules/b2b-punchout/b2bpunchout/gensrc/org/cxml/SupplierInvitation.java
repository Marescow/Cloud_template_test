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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "organizationID",
    "correspondent",
    "masterAgreementIDInfo",
    "extrinsic"
})
@XmlRootElement(name = "SupplierInvitation")
public class SupplierInvitation {

    @XmlAttribute(name = "supplierStatus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String supplierStatus;
    @XmlElement(name = "OrganizationID", required = true)
    protected OrganizationID organizationID;
    @XmlElement(name = "Correspondent")
    protected Correspondent correspondent;
    @XmlElement(name = "MasterAgreementIDInfo")
    protected MasterAgreementIDInfo masterAgreementIDInfo;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété supplierStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierStatus() {
        if (supplierStatus == null) {
            return "approved";
        } else {
            return supplierStatus;
        }
    }

    /**
     * Définit la valeur de la propriété supplierStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierStatus(String value) {
        this.supplierStatus = value;
    }

    /**
     * Obtient la valeur de la propriété organizationID.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationID }
     *     
     */
    public OrganizationID getOrganizationID() {
        return organizationID;
    }

    /**
     * Définit la valeur de la propriété organizationID.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationID }
     *     
     */
    public void setOrganizationID(OrganizationID value) {
        this.organizationID = value;
    }

    /**
     * Obtient la valeur de la propriété correspondent.
     * 
     * @return
     *     possible object is
     *     {@link Correspondent }
     *     
     */
    public Correspondent getCorrespondent() {
        return correspondent;
    }

    /**
     * Définit la valeur de la propriété correspondent.
     * 
     * @param value
     *     allowed object is
     *     {@link Correspondent }
     *     
     */
    public void setCorrespondent(Correspondent value) {
        this.correspondent = value;
    }

    /**
     * Obtient la valeur de la propriété masterAgreementIDInfo.
     * 
     * @return
     *     possible object is
     *     {@link MasterAgreementIDInfo }
     *     
     */
    public MasterAgreementIDInfo getMasterAgreementIDInfo() {
        return masterAgreementIDInfo;
    }

    /**
     * Définit la valeur de la propriété masterAgreementIDInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterAgreementIDInfo }
     *     
     */
    public void setMasterAgreementIDInfo(MasterAgreementIDInfo value) {
        this.masterAgreementIDInfo = value;
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

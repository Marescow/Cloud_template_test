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
    "globalIndividualAssetID",
    "returnablePackageID",
    "packageTrackingID"
})
@XmlRootElement(name = "PackageID")
public class PackageID {

    @XmlElement(name = "GlobalIndividualAssetID")
    protected String globalIndividualAssetID;
    @XmlElement(name = "ReturnablePackageID")
    protected String returnablePackageID;
    @XmlElement(name = "PackageTrackingID")
    protected String packageTrackingID;

    /**
     * Obtient la valeur de la propriété globalIndividualAssetID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalIndividualAssetID() {
        return globalIndividualAssetID;
    }

    /**
     * Définit la valeur de la propriété globalIndividualAssetID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalIndividualAssetID(String value) {
        this.globalIndividualAssetID = value;
    }

    /**
     * Obtient la valeur de la propriété returnablePackageID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnablePackageID() {
        return returnablePackageID;
    }

    /**
     * Définit la valeur de la propriété returnablePackageID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnablePackageID(String value) {
        this.returnablePackageID = value;
    }

    /**
     * Obtient la valeur de la propriété packageTrackingID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageTrackingID() {
        return packageTrackingID;
    }

    /**
     * Définit la valeur de la propriété packageTrackingID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageTrackingID(String value) {
        this.packageTrackingID = value;
    }

}

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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "supplierResourceID",
    "resourceType",
    "inputItemID",
    "outputItemID",
    "idReference"
})
@XmlRootElement(name = "ResourceID")
public class ResourceID {

    @XmlElement(name = "SupplierResourceID", required = true)
    protected String supplierResourceID;
    @XmlElement(name = "ResourceType")
    protected String resourceType;
    @XmlElement(name = "InputItemID")
    protected InputItemID inputItemID;
    @XmlElement(name = "OutputItemID")
    protected OutputItemID outputItemID;
    @XmlElement(name = "IdReference")
    protected List<IdReference> idReference;

    /**
     * Obtient la valeur de la propriété supplierResourceID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierResourceID() {
        return supplierResourceID;
    }

    /**
     * Définit la valeur de la propriété supplierResourceID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierResourceID(String value) {
        this.supplierResourceID = value;
    }

    /**
     * Obtient la valeur de la propriété resourceType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Définit la valeur de la propriété resourceType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceType(String value) {
        this.resourceType = value;
    }

    /**
     * Obtient la valeur de la propriété inputItemID.
     * 
     * @return
     *     possible object is
     *     {@link InputItemID }
     *     
     */
    public InputItemID getInputItemID() {
        return inputItemID;
    }

    /**
     * Définit la valeur de la propriété inputItemID.
     * 
     * @param value
     *     allowed object is
     *     {@link InputItemID }
     *     
     */
    public void setInputItemID(InputItemID value) {
        this.inputItemID = value;
    }

    /**
     * Obtient la valeur de la propriété outputItemID.
     * 
     * @return
     *     possible object is
     *     {@link OutputItemID }
     *     
     */
    public OutputItemID getOutputItemID() {
        return outputItemID;
    }

    /**
     * Définit la valeur de la propriété outputItemID.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputItemID }
     *     
     */
    public void setOutputItemID(OutputItemID value) {
        this.outputItemID = value;
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

}

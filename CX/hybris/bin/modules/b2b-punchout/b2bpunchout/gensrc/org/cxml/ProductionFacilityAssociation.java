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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "productionFacility",
    "organizationalUnit",
    "shipTo"
})
@XmlRootElement(name = "ProductionFacilityAssociation")
public class ProductionFacilityAssociation {

    @XmlAttribute(name = "operation")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String operation;
    @XmlElement(name = "ProductionFacility", required = true)
    protected ProductionFacility productionFacility;
    @XmlElement(name = "OrganizationalUnit", required = true)
    protected OrganizationalUnit organizationalUnit;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;

    /**
     * Obtient la valeur de la propriété operation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        if (operation == null) {
            return "new";
        } else {
            return operation;
        }
    }

    /**
     * Définit la valeur de la propriété operation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Obtient la valeur de la propriété productionFacility.
     * 
     * @return
     *     possible object is
     *     {@link ProductionFacility }
     *     
     */
    public ProductionFacility getProductionFacility() {
        return productionFacility;
    }

    /**
     * Définit la valeur de la propriété productionFacility.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductionFacility }
     *     
     */
    public void setProductionFacility(ProductionFacility value) {
        this.productionFacility = value;
    }

    /**
     * Obtient la valeur de la propriété organizationalUnit.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationalUnit }
     *     
     */
    public OrganizationalUnit getOrganizationalUnit() {
        return organizationalUnit;
    }

    /**
     * Définit la valeur de la propriété organizationalUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationalUnit }
     *     
     */
    public void setOrganizationalUnit(OrganizationalUnit value) {
        this.organizationalUnit = value;
    }

    /**
     * Obtient la valeur de la propriété shipTo.
     * 
     * @return
     *     possible object is
     *     {@link ShipTo }
     *     
     */
    public ShipTo getShipTo() {
        return shipTo;
    }

    /**
     * Définit la valeur de la propriété shipTo.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipTo }
     *     
     */
    public void setShipTo(ShipTo value) {
        this.shipTo = value;
    }

}

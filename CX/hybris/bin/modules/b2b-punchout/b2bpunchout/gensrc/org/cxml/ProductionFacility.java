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
    "idReference",
    "productionFacilityRole"
})
@XmlRootElement(name = "ProductionFacility")
public class ProductionFacility {

    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlElement(name = "IdReference", required = true)
    protected IdReference idReference;
    @XmlElement(name = "ProductionFacilityRole", required = true)
    protected ProductionFacilityRole productionFacilityRole;

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété idReference.
     * 
     * @return
     *     possible object is
     *     {@link IdReference }
     *     
     */
    public IdReference getIdReference() {
        return idReference;
    }

    /**
     * Définit la valeur de la propriété idReference.
     * 
     * @param value
     *     allowed object is
     *     {@link IdReference }
     *     
     */
    public void setIdReference(IdReference value) {
        this.idReference = value;
    }

    /**
     * Obtient la valeur de la propriété productionFacilityRole.
     * 
     * @return
     *     possible object is
     *     {@link ProductionFacilityRole }
     *     
     */
    public ProductionFacilityRole getProductionFacilityRole() {
        return productionFacilityRole;
    }

    /**
     * Définit la valeur de la propriété productionFacilityRole.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductionFacilityRole }
     *     
     */
    public void setProductionFacilityRole(ProductionFacilityRole value) {
        this.productionFacilityRole = value;
    }

}

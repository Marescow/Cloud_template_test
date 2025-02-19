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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "componentID",
    "unitOfMeasure",
    "description",
    "product",
    "productRevisionID",
    "batch"
})
@XmlRootElement(name = "SubcontractingComponent")
public class SubcontractingComponent {

    @XmlAttribute(name = "quantity", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String quantity;
    @XmlAttribute(name = "requirementDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requirementDate;
    @XmlAttribute(name = "materialProvisionIndicator")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String materialProvisionIndicator;
    @XmlElement(name = "ComponentID", required = true)
    protected String componentID;
    @XmlElement(name = "UnitOfMeasure", required = true)
    protected String unitOfMeasure;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "Product")
    protected Product product;
    @XmlElement(name = "ProductRevisionID")
    protected String productRevisionID;
    @XmlElement(name = "Batch")
    protected Batch batch;

    /**
     * Obtient la valeur de la propriété quantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Définit la valeur de la propriété quantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * Obtient la valeur de la propriété requirementDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequirementDate() {
        return requirementDate;
    }

    /**
     * Définit la valeur de la propriété requirementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequirementDate(String value) {
        this.requirementDate = value;
    }

    /**
     * Obtient la valeur de la propriété materialProvisionIndicator.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialProvisionIndicator() {
        return materialProvisionIndicator;
    }

    /**
     * Définit la valeur de la propriété materialProvisionIndicator.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialProvisionIndicator(String value) {
        this.materialProvisionIndicator = value;
    }

    /**
     * Obtient la valeur de la propriété componentID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentID() {
        return componentID;
    }

    /**
     * Définit la valeur de la propriété componentID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentID(String value) {
        this.componentID = value;
    }

    /**
     * Obtient la valeur de la propriété unitOfMeasure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Définit la valeur de la propriété unitOfMeasure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété product.
     * 
     * @return
     *     possible object is
     *     {@link Product }
     *     
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Définit la valeur de la propriété product.
     * 
     * @param value
     *     allowed object is
     *     {@link Product }
     *     
     */
    public void setProduct(Product value) {
        this.product = value;
    }

    /**
     * Obtient la valeur de la propriété productRevisionID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductRevisionID() {
        return productRevisionID;
    }

    /**
     * Définit la valeur de la propriété productRevisionID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductRevisionID(String value) {
        this.productRevisionID = value;
    }

    /**
     * Obtient la valeur de la propriété batch.
     * 
     * @return
     *     possible object is
     *     {@link Batch }
     *     
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * Définit la valeur de la propriété batch.
     * 
     * @param value
     *     allowed object is
     *     {@link Batch }
     *     
     */
    public void setBatch(Batch value) {
        this.batch = value;
    }

}

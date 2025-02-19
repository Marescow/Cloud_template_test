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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "unitOfMeasure",
    "shipNoticeReleaseInfo",
    "extrinsic"
})
@XmlRootElement(name = "ReleaseInfo")
public class ReleaseInfo {

    @XmlAttribute(name = "releaseType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String releaseType;
    @XmlAttribute(name = "cumulativeReceivedQuantity", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cumulativeReceivedQuantity;
    @XmlAttribute(name = "releaseNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String releaseNumber;
    @XmlAttribute(name = "productionGoAheadEndDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String productionGoAheadEndDate;
    @XmlAttribute(name = "materialGoAheadEndDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String materialGoAheadEndDate;
    @XmlElement(name = "UnitOfMeasure", required = true)
    protected String unitOfMeasure;
    @XmlElement(name = "ShipNoticeReleaseInfo")
    protected ShipNoticeReleaseInfo shipNoticeReleaseInfo;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété releaseType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseType() {
        return releaseType;
    }

    /**
     * Définit la valeur de la propriété releaseType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseType(String value) {
        this.releaseType = value;
    }

    /**
     * Obtient la valeur de la propriété cumulativeReceivedQuantity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCumulativeReceivedQuantity() {
        return cumulativeReceivedQuantity;
    }

    /**
     * Définit la valeur de la propriété cumulativeReceivedQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCumulativeReceivedQuantity(String value) {
        this.cumulativeReceivedQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété releaseNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseNumber() {
        return releaseNumber;
    }

    /**
     * Définit la valeur de la propriété releaseNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseNumber(String value) {
        this.releaseNumber = value;
    }

    /**
     * Obtient la valeur de la propriété productionGoAheadEndDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductionGoAheadEndDate() {
        return productionGoAheadEndDate;
    }

    /**
     * Définit la valeur de la propriété productionGoAheadEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductionGoAheadEndDate(String value) {
        this.productionGoAheadEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété materialGoAheadEndDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialGoAheadEndDate() {
        return materialGoAheadEndDate;
    }

    /**
     * Définit la valeur de la propriété materialGoAheadEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialGoAheadEndDate(String value) {
        this.materialGoAheadEndDate = value;
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
     * Obtient la valeur de la propriété shipNoticeReleaseInfo.
     * 
     * @return
     *     possible object is
     *     {@link ShipNoticeReleaseInfo }
     *     
     */
    public ShipNoticeReleaseInfo getShipNoticeReleaseInfo() {
        return shipNoticeReleaseInfo;
    }

    /**
     * Définit la valeur de la propriété shipNoticeReleaseInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipNoticeReleaseInfo }
     *     
     */
    public void setShipNoticeReleaseInfo(ShipNoticeReleaseInfo value) {
        this.shipNoticeReleaseInfo = value;
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

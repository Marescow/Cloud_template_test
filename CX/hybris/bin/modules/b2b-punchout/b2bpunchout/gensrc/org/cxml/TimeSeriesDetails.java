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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "period",
    "timeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount",
    "upsideQuantity",
    "idReference",
    "extrinsic"
})
@XmlRootElement(name = "TimeSeriesDetails")
public class TimeSeriesDetails {

    @XmlElement(name = "Period", required = true)
    protected Period period;
    @XmlElements({
        @XmlElement(name = "TimeSeriesQuantity", type = TimeSeriesQuantity.class),
        @XmlElement(name = "TimeSeriesValue", type = TimeSeriesValue.class),
        @XmlElement(name = "TimeSeriesAmount", type = TimeSeriesAmount.class)
    })
    protected List<Object> timeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount;
    @XmlElement(name = "UpsideQuantity")
    protected UpsideQuantity upsideQuantity;
    @XmlElement(name = "IdReference")
    protected List<IdReference> idReference;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

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
     * Gets the value of the timeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeSeriesQuantity }
     * {@link TimeSeriesValue }
     * {@link TimeSeriesAmount }
     * 
     * 
     */
    public List<Object> getTimeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount() {
        if (timeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount == null) {
            timeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount = new ArrayList<Object>();
        }
        return this.timeSeriesQuantityOrTimeSeriesValueOrTimeSeriesAmount;
    }

    /**
     * Obtient la valeur de la propriété upsideQuantity.
     * 
     * @return
     *     possible object is
     *     {@link UpsideQuantity }
     *     
     */
    public UpsideQuantity getUpsideQuantity() {
        return upsideQuantity;
    }

    /**
     * Définit la valeur de la propriété upsideQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link UpsideQuantity }
     *     
     */
    public void setUpsideQuantity(UpsideQuantity value) {
        this.upsideQuantity = value;
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

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
    "unitOfMeasure",
    "scheduleLineReleaseInfo",
    "subcontractingComponent",
    "shipTo",
    "extrinsic"
})
@XmlRootElement(name = "ScheduleLine")
public class ScheduleLine {

    @XmlAttribute(name = "quantity", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String quantity;
    @XmlAttribute(name = "requestedDeliveryDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestedDeliveryDate;
    @XmlAttribute(name = "lineNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lineNumber;
    @XmlAttribute(name = "deliveryWindow")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String deliveryWindow;
    @XmlAttribute(name = "requestedShipmentDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String requestedShipmentDate;
    @XmlAttribute(name = "originalRequestedDeliveryDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String originalRequestedDeliveryDate;
    @XmlElement(name = "UnitOfMeasure", required = true)
    protected String unitOfMeasure;
    @XmlElement(name = "ScheduleLineReleaseInfo")
    protected ScheduleLineReleaseInfo scheduleLineReleaseInfo;
    @XmlElement(name = "SubcontractingComponent")
    protected List<SubcontractingComponent> subcontractingComponent;
    @XmlElement(name = "ShipTo")
    protected ShipTo shipTo;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

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
     * Obtient la valeur de la propriété requestedDeliveryDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    /**
     * Définit la valeur de la propriété requestedDeliveryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedDeliveryDate(String value) {
        this.requestedDeliveryDate = value;
    }

    /**
     * Obtient la valeur de la propriété lineNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineNumber() {
        return lineNumber;
    }

    /**
     * Définit la valeur de la propriété lineNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineNumber(String value) {
        this.lineNumber = value;
    }

    /**
     * Obtient la valeur de la propriété deliveryWindow.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryWindow() {
        return deliveryWindow;
    }

    /**
     * Définit la valeur de la propriété deliveryWindow.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryWindow(String value) {
        this.deliveryWindow = value;
    }

    /**
     * Obtient la valeur de la propriété requestedShipmentDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedShipmentDate() {
        return requestedShipmentDate;
    }

    /**
     * Définit la valeur de la propriété requestedShipmentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedShipmentDate(String value) {
        this.requestedShipmentDate = value;
    }

    /**
     * Obtient la valeur de la propriété originalRequestedDeliveryDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalRequestedDeliveryDate() {
        return originalRequestedDeliveryDate;
    }

    /**
     * Définit la valeur de la propriété originalRequestedDeliveryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalRequestedDeliveryDate(String value) {
        this.originalRequestedDeliveryDate = value;
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
     * Obtient la valeur de la propriété scheduleLineReleaseInfo.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleLineReleaseInfo }
     *     
     */
    public ScheduleLineReleaseInfo getScheduleLineReleaseInfo() {
        return scheduleLineReleaseInfo;
    }

    /**
     * Définit la valeur de la propriété scheduleLineReleaseInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleLineReleaseInfo }
     *     
     */
    public void setScheduleLineReleaseInfo(ScheduleLineReleaseInfo value) {
        this.scheduleLineReleaseInfo = value;
    }

    /**
     * Gets the value of the subcontractingComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subcontractingComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubcontractingComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubcontractingComponent }
     * 
     * 
     */
    public List<SubcontractingComponent> getSubcontractingComponent() {
        if (subcontractingComponent == null) {
            subcontractingComponent = new ArrayList<SubcontractingComponent>();
        }
        return this.subcontractingComponent;
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

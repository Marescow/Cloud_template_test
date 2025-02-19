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
    "vendor",
    "carRentalPickup",
    "carRentalDropoff",
    "bookingClassCode",
    "carRentalFee",
    "limitedMileage",
    "availablePrice"
})
@XmlRootElement(name = "CarRentalDetail")
public class CarRentalDetail {

    @XmlAttribute(name = "travelSegment", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String travelSegment;
    @XmlAttribute(name = "pickupTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pickupTime;
    @XmlAttribute(name = "dropoffTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dropoffTime;
    @XmlElement(name = "Vendor", required = true)
    protected Vendor vendor;
    @XmlElement(name = "CarRentalPickup", required = true)
    protected CarRentalPickup carRentalPickup;
    @XmlElement(name = "CarRentalDropoff", required = true)
    protected CarRentalDropoff carRentalDropoff;
    @XmlElement(name = "BookingClassCode")
    protected BookingClassCode bookingClassCode;
    @XmlElement(name = "CarRentalFee", required = true)
    protected List<CarRentalFee> carRentalFee;
    @XmlElement(name = "LimitedMileage")
    protected LimitedMileage limitedMileage;
    @XmlElement(name = "AvailablePrice")
    protected List<AvailablePrice> availablePrice;

    /**
     * Obtient la valeur de la propriété travelSegment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelSegment() {
        return travelSegment;
    }

    /**
     * Définit la valeur de la propriété travelSegment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelSegment(String value) {
        this.travelSegment = value;
    }

    /**
     * Obtient la valeur de la propriété pickupTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupTime() {
        return pickupTime;
    }

    /**
     * Définit la valeur de la propriété pickupTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupTime(String value) {
        this.pickupTime = value;
    }

    /**
     * Obtient la valeur de la propriété dropoffTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropoffTime() {
        return dropoffTime;
    }

    /**
     * Définit la valeur de la propriété dropoffTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropoffTime(String value) {
        this.dropoffTime = value;
    }

    /**
     * Obtient la valeur de la propriété vendor.
     * 
     * @return
     *     possible object is
     *     {@link Vendor }
     *     
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * Définit la valeur de la propriété vendor.
     * 
     * @param value
     *     allowed object is
     *     {@link Vendor }
     *     
     */
    public void setVendor(Vendor value) {
        this.vendor = value;
    }

    /**
     * Obtient la valeur de la propriété carRentalPickup.
     * 
     * @return
     *     possible object is
     *     {@link CarRentalPickup }
     *     
     */
    public CarRentalPickup getCarRentalPickup() {
        return carRentalPickup;
    }

    /**
     * Définit la valeur de la propriété carRentalPickup.
     * 
     * @param value
     *     allowed object is
     *     {@link CarRentalPickup }
     *     
     */
    public void setCarRentalPickup(CarRentalPickup value) {
        this.carRentalPickup = value;
    }

    /**
     * Obtient la valeur de la propriété carRentalDropoff.
     * 
     * @return
     *     possible object is
     *     {@link CarRentalDropoff }
     *     
     */
    public CarRentalDropoff getCarRentalDropoff() {
        return carRentalDropoff;
    }

    /**
     * Définit la valeur de la propriété carRentalDropoff.
     * 
     * @param value
     *     allowed object is
     *     {@link CarRentalDropoff }
     *     
     */
    public void setCarRentalDropoff(CarRentalDropoff value) {
        this.carRentalDropoff = value;
    }

    /**
     * Obtient la valeur de la propriété bookingClassCode.
     * 
     * @return
     *     possible object is
     *     {@link BookingClassCode }
     *     
     */
    public BookingClassCode getBookingClassCode() {
        return bookingClassCode;
    }

    /**
     * Définit la valeur de la propriété bookingClassCode.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingClassCode }
     *     
     */
    public void setBookingClassCode(BookingClassCode value) {
        this.bookingClassCode = value;
    }

    /**
     * Gets the value of the carRentalFee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carRentalFee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarRentalFee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CarRentalFee }
     * 
     * 
     */
    public List<CarRentalFee> getCarRentalFee() {
        if (carRentalFee == null) {
            carRentalFee = new ArrayList<CarRentalFee>();
        }
        return this.carRentalFee;
    }

    /**
     * Obtient la valeur de la propriété limitedMileage.
     * 
     * @return
     *     possible object is
     *     {@link LimitedMileage }
     *     
     */
    public LimitedMileage getLimitedMileage() {
        return limitedMileage;
    }

    /**
     * Définit la valeur de la propriété limitedMileage.
     * 
     * @param value
     *     allowed object is
     *     {@link LimitedMileage }
     *     
     */
    public void setLimitedMileage(LimitedMileage value) {
        this.limitedMileage = value;
    }

    /**
     * Gets the value of the availablePrice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the availablePrice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvailablePrice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AvailablePrice }
     * 
     * 
     */
    public List<AvailablePrice> getAvailablePrice() {
        if (availablePrice == null) {
            availablePrice = new ArrayList<AvailablePrice>();
        }
        return this.availablePrice;
    }

}

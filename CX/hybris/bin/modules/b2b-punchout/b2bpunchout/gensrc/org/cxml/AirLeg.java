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
    "vendor",
    "airLegOrigin",
    "airLegDestination",
    "bookingClassCode",
    "rate",
    "meal"
})
@XmlRootElement(name = "AirLeg")
public class AirLeg {

    @XmlAttribute(name = "travelSegment", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String travelSegment;
    @XmlAttribute(name = "departureTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String departureTime;
    @XmlAttribute(name = "arrivalTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String arrivalTime;
    @XmlAttribute(name = "flightNumber", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String flightNumber;
    @XmlAttribute(name = "seatNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String seatNumber;
    @XmlAttribute(name = "seatType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String seatType;
    @XmlAttribute(name = "upgrade")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String upgrade;
    @XmlAttribute(name = "stops")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String stops;
    @XmlAttribute(name = "equipment")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String equipment;
    @XmlElement(name = "Vendor", required = true)
    protected Vendor vendor;
    @XmlElement(name = "AirLegOrigin", required = true)
    protected AirLegOrigin airLegOrigin;
    @XmlElement(name = "AirLegDestination", required = true)
    protected AirLegDestination airLegDestination;
    @XmlElement(name = "BookingClassCode")
    protected BookingClassCode bookingClassCode;
    @XmlElement(name = "Rate")
    protected Rate rate;
    @XmlElement(name = "Meal")
    protected List<Meal> meal;

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
     * Obtient la valeur de la propriété departureTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Définit la valeur de la propriété departureTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureTime(String value) {
        this.departureTime = value;
    }

    /**
     * Obtient la valeur de la propriété arrivalTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Définit la valeur de la propriété arrivalTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalTime(String value) {
        this.arrivalTime = value;
    }

    /**
     * Obtient la valeur de la propriété flightNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Définit la valeur de la propriété flightNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightNumber(String value) {
        this.flightNumber = value;
    }

    /**
     * Obtient la valeur de la propriété seatNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * Définit la valeur de la propriété seatNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatNumber(String value) {
        this.seatNumber = value;
    }

    /**
     * Obtient la valeur de la propriété seatType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatType() {
        return seatType;
    }

    /**
     * Définit la valeur de la propriété seatType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatType(String value) {
        this.seatType = value;
    }

    /**
     * Obtient la valeur de la propriété upgrade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpgrade() {
        return upgrade;
    }

    /**
     * Définit la valeur de la propriété upgrade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpgrade(String value) {
        this.upgrade = value;
    }

    /**
     * Obtient la valeur de la propriété stops.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStops() {
        return stops;
    }

    /**
     * Définit la valeur de la propriété stops.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStops(String value) {
        this.stops = value;
    }

    /**
     * Obtient la valeur de la propriété equipment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * Définit la valeur de la propriété equipment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipment(String value) {
        this.equipment = value;
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
     * Obtient la valeur de la propriété airLegOrigin.
     * 
     * @return
     *     possible object is
     *     {@link AirLegOrigin }
     *     
     */
    public AirLegOrigin getAirLegOrigin() {
        return airLegOrigin;
    }

    /**
     * Définit la valeur de la propriété airLegOrigin.
     * 
     * @param value
     *     allowed object is
     *     {@link AirLegOrigin }
     *     
     */
    public void setAirLegOrigin(AirLegOrigin value) {
        this.airLegOrigin = value;
    }

    /**
     * Obtient la valeur de la propriété airLegDestination.
     * 
     * @return
     *     possible object is
     *     {@link AirLegDestination }
     *     
     */
    public AirLegDestination getAirLegDestination() {
        return airLegDestination;
    }

    /**
     * Définit la valeur de la propriété airLegDestination.
     * 
     * @param value
     *     allowed object is
     *     {@link AirLegDestination }
     *     
     */
    public void setAirLegDestination(AirLegDestination value) {
        this.airLegDestination = value;
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
     * Obtient la valeur de la propriété rate.
     * 
     * @return
     *     possible object is
     *     {@link Rate }
     *     
     */
    public Rate getRate() {
        return rate;
    }

    /**
     * Définit la valeur de la propriété rate.
     * 
     * @param value
     *     allowed object is
     *     {@link Rate }
     *     
     */
    public void setRate(Rate value) {
        this.rate = value;
    }

    /**
     * Gets the value of the meal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Meal }
     * 
     * 
     */
    public List<Meal> getMeal() {
        if (meal == null) {
            meal = new ArrayList<Meal>();
        }
        return this.meal;
    }

}

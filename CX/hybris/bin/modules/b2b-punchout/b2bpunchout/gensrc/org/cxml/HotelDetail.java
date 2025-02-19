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
    "address",
    "roomType",
    "bookingClassCode",
    "meal",
    "rate",
    "availablePrice"
})
@XmlRootElement(name = "HotelDetail")
public class HotelDetail {

    @XmlAttribute(name = "travelSegment", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String travelSegment;
    @XmlAttribute(name = "arrivalTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String arrivalTime;
    @XmlAttribute(name = "departureTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String departureTime;
    @XmlAttribute(name = "checkinTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String checkinTime;
    @XmlAttribute(name = "checkoutTime", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String checkoutTime;
    @XmlAttribute(name = "earlyCheckinAllowed")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String earlyCheckinAllowed;
    @XmlAttribute(name = "lateCheckoutAllowed")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String lateCheckoutAllowed;
    @XmlElement(name = "Vendor", required = true)
    protected Vendor vendor;
    @XmlElement(name = "Address", required = true)
    protected Address address;
    @XmlElement(name = "RoomType", required = true)
    protected RoomType roomType;
    @XmlElement(name = "BookingClassCode")
    protected BookingClassCode bookingClassCode;
    @XmlElement(name = "Meal")
    protected List<Meal> meal;
    @XmlElement(name = "Rate")
    protected List<Rate> rate;
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
     * Obtient la valeur de la propriété checkinTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckinTime() {
        return checkinTime;
    }

    /**
     * Définit la valeur de la propriété checkinTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckinTime(String value) {
        this.checkinTime = value;
    }

    /**
     * Obtient la valeur de la propriété checkoutTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckoutTime() {
        return checkoutTime;
    }

    /**
     * Définit la valeur de la propriété checkoutTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckoutTime(String value) {
        this.checkoutTime = value;
    }

    /**
     * Obtient la valeur de la propriété earlyCheckinAllowed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarlyCheckinAllowed() {
        return earlyCheckinAllowed;
    }

    /**
     * Définit la valeur de la propriété earlyCheckinAllowed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarlyCheckinAllowed(String value) {
        this.earlyCheckinAllowed = value;
    }

    /**
     * Obtient la valeur de la propriété lateCheckoutAllowed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLateCheckoutAllowed() {
        return lateCheckoutAllowed;
    }

    /**
     * Définit la valeur de la propriété lateCheckoutAllowed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLateCheckoutAllowed(String value) {
        this.lateCheckoutAllowed = value;
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
     * Obtient la valeur de la propriété address.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Définit la valeur de la propriété address.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Obtient la valeur de la propriété roomType.
     * 
     * @return
     *     possible object is
     *     {@link RoomType }
     *     
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Définit la valeur de la propriété roomType.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomType }
     *     
     */
    public void setRoomType(RoomType value) {
        this.roomType = value;
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

    /**
     * Gets the value of the rate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rate }
     * 
     * 
     */
    public List<Rate> getRate() {
        if (rate == null) {
            rate = new ArrayList<Rate>();
        }
        return this.rate;
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

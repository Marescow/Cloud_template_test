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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "airDetailOrCarRentalDetailOrHotelDetailOrRailDetail",
    "policyViolation",
    "comments",
    "termsAndConditions"
})
@XmlRootElement(name = "TravelDetail")
public class TravelDetail {

    @XmlAttribute(name = "confirmationNumber", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String confirmationNumber;
    @XmlAttribute(name = "pnrLocator")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pnrLocator;
    @XmlAttribute(name = "quoteExpirationTime")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String quoteExpirationTime;
    @XmlElements({
        @XmlElement(name = "AirDetail", required = true, type = AirDetail.class),
        @XmlElement(name = "CarRentalDetail", required = true, type = CarRentalDetail.class),
        @XmlElement(name = "HotelDetail", required = true, type = HotelDetail.class),
        @XmlElement(name = "RailDetail", required = true, type = RailDetail.class)
    })
    protected List<Object> airDetailOrCarRentalDetailOrHotelDetailOrRailDetail;
    @XmlElement(name = "PolicyViolation")
    protected List<PolicyViolation> policyViolation;
    @XmlElement(name = "Comments")
    protected Comments comments;
    @XmlElement(name = "TermsAndConditions")
    protected TermsAndConditions termsAndConditions;

    /**
     * Obtient la valeur de la propriété confirmationNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Définit la valeur de la propriété confirmationNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmationNumber(String value) {
        this.confirmationNumber = value;
    }

    /**
     * Obtient la valeur de la propriété pnrLocator.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnrLocator() {
        return pnrLocator;
    }

    /**
     * Définit la valeur de la propriété pnrLocator.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnrLocator(String value) {
        this.pnrLocator = value;
    }

    /**
     * Obtient la valeur de la propriété quoteExpirationTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteExpirationTime() {
        return quoteExpirationTime;
    }

    /**
     * Définit la valeur de la propriété quoteExpirationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteExpirationTime(String value) {
        this.quoteExpirationTime = value;
    }

    /**
     * Gets the value of the airDetailOrCarRentalDetailOrHotelDetailOrRailDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the airDetailOrCarRentalDetailOrHotelDetailOrRailDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAirDetailOrCarRentalDetailOrHotelDetailOrRailDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AirDetail }
     * {@link CarRentalDetail }
     * {@link HotelDetail }
     * {@link RailDetail }
     * 
     * 
     */
    public List<Object> getAirDetailOrCarRentalDetailOrHotelDetailOrRailDetail() {
        if (airDetailOrCarRentalDetailOrHotelDetailOrRailDetail == null) {
            airDetailOrCarRentalDetailOrHotelDetailOrRailDetail = new ArrayList<Object>();
        }
        return this.airDetailOrCarRentalDetailOrHotelDetailOrRailDetail;
    }

    /**
     * Gets the value of the policyViolation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policyViolation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicyViolation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolicyViolation }
     * 
     * 
     */
    public List<PolicyViolation> getPolicyViolation() {
        if (policyViolation == null) {
            policyViolation = new ArrayList<PolicyViolation>();
        }
        return this.policyViolation;
    }

    /**
     * Obtient la valeur de la propriété comments.
     * 
     * @return
     *     possible object is
     *     {@link Comments }
     *     
     */
    public Comments getComments() {
        return comments;
    }

    /**
     * Définit la valeur de la propriété comments.
     * 
     * @param value
     *     allowed object is
     *     {@link Comments }
     *     
     */
    public void setComments(Comments value) {
        this.comments = value;
    }

    /**
     * Obtient la valeur de la propriété termsAndConditions.
     * 
     * @return
     *     possible object is
     *     {@link TermsAndConditions }
     *     
     */
    public TermsAndConditions getTermsAndConditions() {
        return termsAndConditions;
    }

    /**
     * Définit la valeur de la propriété termsAndConditions.
     * 
     * @param value
     *     allowed object is
     *     {@link TermsAndConditions }
     *     
     */
    public void setTermsAndConditions(TermsAndConditions value) {
        this.termsAndConditions = value;
    }

}

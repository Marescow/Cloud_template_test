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
    "money",
    "description",
    "modifications"
})
@XmlRootElement(name = "Shipping")
public class Shipping {

    @XmlAttribute(name = "trackingDomain")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String trackingDomain;
    @XmlAttribute(name = "trackingId")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String trackingId;
    @XmlAttribute(name = "tracking")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tracking;
    @XmlElement(name = "Money", required = true)
    protected Money money;
    @XmlElement(name = "Description", required = true)
    protected Description description;
    @XmlElement(name = "Modifications")
    protected Modifications modifications;

    /**
     * Obtient la valeur de la propriété trackingDomain.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingDomain() {
        return trackingDomain;
    }

    /**
     * Définit la valeur de la propriété trackingDomain.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingDomain(String value) {
        this.trackingDomain = value;
    }

    /**
     * Obtient la valeur de la propriété trackingId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingId() {
        return trackingId;
    }

    /**
     * Définit la valeur de la propriété trackingId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingId(String value) {
        this.trackingId = value;
    }

    /**
     * Obtient la valeur de la propriété tracking.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTracking() {
        return tracking;
    }

    /**
     * Définit la valeur de la propriété tracking.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTracking(String value) {
        this.tracking = value;
    }

    /**
     * Obtient la valeur de la propriété money.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getMoney() {
        return money;
    }

    /**
     * Définit la valeur de la propriété money.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setMoney(Money value) {
        this.money = value;
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
     * Obtient la valeur de la propriété modifications.
     * 
     * @return
     *     possible object is
     *     {@link Modifications }
     *     
     */
    public Modifications getModifications() {
        return modifications;
    }

    /**
     * Définit la valeur de la propriété modifications.
     * 
     * @param value
     *     allowed object is
     *     {@link Modifications }
     *     
     */
    public void setModifications(Modifications value) {
        this.modifications = value;
    }

}

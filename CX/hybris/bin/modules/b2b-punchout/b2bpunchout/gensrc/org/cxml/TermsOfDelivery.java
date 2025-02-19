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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "termsOfDeliveryCode",
    "shippingPaymentMethod",
    "transportTerms",
    "address",
    "comments"
})
@XmlRootElement(name = "TermsOfDelivery")
public class TermsOfDelivery {

    @XmlElement(name = "TermsOfDeliveryCode", required = true)
    protected TermsOfDeliveryCode termsOfDeliveryCode;
    @XmlElement(name = "ShippingPaymentMethod", required = true)
    protected ShippingPaymentMethod shippingPaymentMethod;
    @XmlElement(name = "TransportTerms")
    protected TransportTerms transportTerms;
    @XmlElement(name = "Address")
    protected Address address;
    @XmlElement(name = "Comments")
    protected List<Comments> comments;

    /**
     * Obtient la valeur de la propriété termsOfDeliveryCode.
     * 
     * @return
     *     possible object is
     *     {@link TermsOfDeliveryCode }
     *     
     */
    public TermsOfDeliveryCode getTermsOfDeliveryCode() {
        return termsOfDeliveryCode;
    }

    /**
     * Définit la valeur de la propriété termsOfDeliveryCode.
     * 
     * @param value
     *     allowed object is
     *     {@link TermsOfDeliveryCode }
     *     
     */
    public void setTermsOfDeliveryCode(TermsOfDeliveryCode value) {
        this.termsOfDeliveryCode = value;
    }

    /**
     * Obtient la valeur de la propriété shippingPaymentMethod.
     * 
     * @return
     *     possible object is
     *     {@link ShippingPaymentMethod }
     *     
     */
    public ShippingPaymentMethod getShippingPaymentMethod() {
        return shippingPaymentMethod;
    }

    /**
     * Définit la valeur de la propriété shippingPaymentMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link ShippingPaymentMethod }
     *     
     */
    public void setShippingPaymentMethod(ShippingPaymentMethod value) {
        this.shippingPaymentMethod = value;
    }

    /**
     * Obtient la valeur de la propriété transportTerms.
     * 
     * @return
     *     possible object is
     *     {@link TransportTerms }
     *     
     */
    public TransportTerms getTransportTerms() {
        return transportTerms;
    }

    /**
     * Définit la valeur de la propriété transportTerms.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportTerms }
     *     
     */
    public void setTransportTerms(TransportTerms value) {
        this.transportTerms = value;
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
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comments }
     * 
     * 
     */
    public List<Comments> getComments() {
        if (comments == null) {
            comments = new ArrayList<Comments>();
        }
        return this.comments;
    }

}

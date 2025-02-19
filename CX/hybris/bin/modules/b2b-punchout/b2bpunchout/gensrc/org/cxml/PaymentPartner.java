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
    "contact",
    "idReference",
    "pCard",
    "natureOfBusiness",
    "incorporationType",
    "accountCurrency"
})
@XmlRootElement(name = "PaymentPartner")
public class PaymentPartner {

    @XmlElement(name = "Contact", required = true)
    protected Contact contact;
    @XmlElement(name = "IdReference")
    protected List<IdReference> idReference;
    @XmlElement(name = "PCard")
    protected PCard pCard;
    @XmlElement(name = "NatureOfBusiness")
    protected String natureOfBusiness;
    @XmlElement(name = "IncorporationType")
    protected String incorporationType;
    @XmlElement(name = "AccountCurrency")
    protected AccountCurrency accountCurrency;

    /**
     * Obtient la valeur de la propriété contact.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Définit la valeur de la propriété contact.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
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
     * Obtient la valeur de la propriété pCard.
     * 
     * @return
     *     possible object is
     *     {@link PCard }
     *     
     */
    public PCard getPCard() {
        return pCard;
    }

    /**
     * Définit la valeur de la propriété pCard.
     * 
     * @param value
     *     allowed object is
     *     {@link PCard }
     *     
     */
    public void setPCard(PCard value) {
        this.pCard = value;
    }

    /**
     * Obtient la valeur de la propriété natureOfBusiness.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    /**
     * Définit la valeur de la propriété natureOfBusiness.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNatureOfBusiness(String value) {
        this.natureOfBusiness = value;
    }

    /**
     * Obtient la valeur de la propriété incorporationType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncorporationType() {
        return incorporationType;
    }

    /**
     * Définit la valeur de la propriété incorporationType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncorporationType(String value) {
        this.incorporationType = value;
    }

    /**
     * Obtient la valeur de la propriété accountCurrency.
     * 
     * @return
     *     possible object is
     *     {@link AccountCurrency }
     *     
     */
    public AccountCurrency getAccountCurrency() {
        return accountCurrency;
    }

    /**
     * Définit la valeur de la propriété accountCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountCurrency }
     *     
     */
    public void setAccountCurrency(AccountCurrency value) {
        this.accountCurrency = value;
    }

}

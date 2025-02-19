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
    "name",
    "credential",
    "organizationRole",
    "address",
    "corporateInformation",
    "taxInformation",
    "relationshipInformation",
    "description",
    "person",
    "industry",
    "commodity",
    "territory",
    "paymentInformation",
    "extrinsic"
})
@XmlRootElement(name = "Organization")
public class Organization {

    @XmlElement(name = "Name", required = true)
    protected Name name;
    @XmlElement(name = "Credential", required = true)
    protected List<Credential> credential;
    @XmlElement(name = "OrganizationRole")
    protected List<OrganizationRole> organizationRole;
    @XmlElement(name = "Address", required = true)
    protected Address address;
    @XmlElement(name = "CorporateInformation")
    protected CorporateInformation corporateInformation;
    @XmlElement(name = "TaxInformation")
    protected TaxInformation taxInformation;
    @XmlElement(name = "RelationshipInformation")
    protected RelationshipInformation relationshipInformation;
    @XmlElement(name = "Description")
    protected List<Description> description;
    @XmlElement(name = "Person")
    protected List<Person> person;
    @XmlElement(name = "Industry")
    protected List<Industry> industry;
    @XmlElement(name = "Commodity")
    protected List<Commodity> commodity;
    @XmlElement(name = "Territory")
    protected List<Territory> territory;
    @XmlElement(name = "PaymentInformation")
    protected List<PaymentInformation> paymentInformation;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setName(Name value) {
        this.name = value;
    }

    /**
     * Gets the value of the credential property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the credential property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCredential().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Credential }
     * 
     * 
     */
    public List<Credential> getCredential() {
        if (credential == null) {
            credential = new ArrayList<Credential>();
        }
        return this.credential;
    }

    /**
     * Gets the value of the organizationRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the organizationRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrganizationRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganizationRole }
     * 
     * 
     */
    public List<OrganizationRole> getOrganizationRole() {
        if (organizationRole == null) {
            organizationRole = new ArrayList<OrganizationRole>();
        }
        return this.organizationRole;
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
     * Obtient la valeur de la propriété corporateInformation.
     * 
     * @return
     *     possible object is
     *     {@link CorporateInformation }
     *     
     */
    public CorporateInformation getCorporateInformation() {
        return corporateInformation;
    }

    /**
     * Définit la valeur de la propriété corporateInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link CorporateInformation }
     *     
     */
    public void setCorporateInformation(CorporateInformation value) {
        this.corporateInformation = value;
    }

    /**
     * Obtient la valeur de la propriété taxInformation.
     * 
     * @return
     *     possible object is
     *     {@link TaxInformation }
     *     
     */
    public TaxInformation getTaxInformation() {
        return taxInformation;
    }

    /**
     * Définit la valeur de la propriété taxInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxInformation }
     *     
     */
    public void setTaxInformation(TaxInformation value) {
        this.taxInformation = value;
    }

    /**
     * Obtient la valeur de la propriété relationshipInformation.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipInformation }
     *     
     */
    public RelationshipInformation getRelationshipInformation() {
        return relationshipInformation;
    }

    /**
     * Définit la valeur de la propriété relationshipInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipInformation }
     *     
     */
    public void setRelationshipInformation(RelationshipInformation value) {
        this.relationshipInformation = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getDescription() {
        if (description == null) {
            description = new ArrayList<Description>();
        }
        return this.description;
    }

    /**
     * Gets the value of the person property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the person property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Person }
     * 
     * 
     */
    public List<Person> getPerson() {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        return this.person;
    }

    /**
     * Gets the value of the industry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the industry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndustry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Industry }
     * 
     * 
     */
    public List<Industry> getIndustry() {
        if (industry == null) {
            industry = new ArrayList<Industry>();
        }
        return this.industry;
    }

    /**
     * Gets the value of the commodity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commodity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommodity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Commodity }
     * 
     * 
     */
    public List<Commodity> getCommodity() {
        if (commodity == null) {
            commodity = new ArrayList<Commodity>();
        }
        return this.commodity;
    }

    /**
     * Gets the value of the territory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the territory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerritory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Territory }
     * 
     * 
     */
    public List<Territory> getTerritory() {
        if (territory == null) {
            territory = new ArrayList<Territory>();
        }
        return this.territory;
    }

    /**
     * Gets the value of the paymentInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentInformation }
     * 
     * 
     */
    public List<PaymentInformation> getPaymentInformation() {
        if (paymentInformation == null) {
            paymentInformation = new ArrayList<PaymentInformation>();
        }
        return this.paymentInformation;
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

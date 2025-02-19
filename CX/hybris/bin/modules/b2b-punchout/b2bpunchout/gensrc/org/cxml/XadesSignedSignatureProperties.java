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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xadesSigningTime",
    "xadesSigningCertificate",
    "xadesSignaturePolicyIdentifier",
    "xadesSignatureProductionPlace",
    "xadesSignerRole"
})
@XmlRootElement(name = "xades:SignedSignatureProperties")
public class XadesSignedSignatureProperties {

    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "xades:SigningTime")
    protected String xadesSigningTime;
    @XmlElement(name = "xades:SigningCertificate")
    protected XadesSigningCertificate xadesSigningCertificate;
    @XmlElement(name = "xades:SignaturePolicyIdentifier")
    protected XadesSignaturePolicyIdentifier xadesSignaturePolicyIdentifier;
    @XmlElement(name = "xades:SignatureProductionPlace")
    protected XadesSignatureProductionPlace xadesSignatureProductionPlace;
    @XmlElement(name = "xades:SignerRole")
    protected XadesSignerRole xadesSignerRole;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSigningTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesSigningTime() {
        return xadesSigningTime;
    }

    /**
     * Définit la valeur de la propriété xadesSigningTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesSigningTime(String value) {
        this.xadesSigningTime = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSigningCertificate.
     * 
     * @return
     *     possible object is
     *     {@link XadesSigningCertificate }
     *     
     */
    public XadesSigningCertificate getXadesSigningCertificate() {
        return xadesSigningCertificate;
    }

    /**
     * Définit la valeur de la propriété xadesSigningCertificate.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSigningCertificate }
     *     
     */
    public void setXadesSigningCertificate(XadesSigningCertificate value) {
        this.xadesSigningCertificate = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSignaturePolicyIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link XadesSignaturePolicyIdentifier }
     *     
     */
    public XadesSignaturePolicyIdentifier getXadesSignaturePolicyIdentifier() {
        return xadesSignaturePolicyIdentifier;
    }

    /**
     * Définit la valeur de la propriété xadesSignaturePolicyIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSignaturePolicyIdentifier }
     *     
     */
    public void setXadesSignaturePolicyIdentifier(XadesSignaturePolicyIdentifier value) {
        this.xadesSignaturePolicyIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSignatureProductionPlace.
     * 
     * @return
     *     possible object is
     *     {@link XadesSignatureProductionPlace }
     *     
     */
    public XadesSignatureProductionPlace getXadesSignatureProductionPlace() {
        return xadesSignatureProductionPlace;
    }

    /**
     * Définit la valeur de la propriété xadesSignatureProductionPlace.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSignatureProductionPlace }
     *     
     */
    public void setXadesSignatureProductionPlace(XadesSignatureProductionPlace value) {
        this.xadesSignatureProductionPlace = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSignerRole.
     * 
     * @return
     *     possible object is
     *     {@link XadesSignerRole }
     *     
     */
    public XadesSignerRole getXadesSignerRole() {
        return xadesSignerRole;
    }

    /**
     * Définit la valeur de la propriété xadesSignerRole.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSignerRole }
     *     
     */
    public void setXadesSignerRole(XadesSignerRole value) {
        this.xadesSignerRole = value;
    }

}

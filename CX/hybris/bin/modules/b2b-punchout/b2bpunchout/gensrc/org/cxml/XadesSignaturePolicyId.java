//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

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
    "xadesSigPolicyId",
    "dsTransforms",
    "xadesSigPolicyHash",
    "xadesSigPolicyQualifiers"
})
@XmlRootElement(name = "xades:SignaturePolicyId")
public class XadesSignaturePolicyId {

    @XmlElement(name = "xades:SigPolicyId", required = true)
    protected XadesSigPolicyId xadesSigPolicyId;
    @XmlElement(name = "ds:Transforms")
    protected DsTransforms dsTransforms;
    @XmlElement(name = "xades:SigPolicyHash", required = true)
    protected XadesSigPolicyHash xadesSigPolicyHash;
    @XmlElement(name = "xades:SigPolicyQualifiers")
    protected XadesSigPolicyQualifiers xadesSigPolicyQualifiers;

    /**
     * Obtient la valeur de la propriété xadesSigPolicyId.
     * 
     * @return
     *     possible object is
     *     {@link XadesSigPolicyId }
     *     
     */
    public XadesSigPolicyId getXadesSigPolicyId() {
        return xadesSigPolicyId;
    }

    /**
     * Définit la valeur de la propriété xadesSigPolicyId.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSigPolicyId }
     *     
     */
    public void setXadesSigPolicyId(XadesSigPolicyId value) {
        this.xadesSigPolicyId = value;
    }

    /**
     * Obtient la valeur de la propriété dsTransforms.
     * 
     * @return
     *     possible object is
     *     {@link DsTransforms }
     *     
     */
    public DsTransforms getDsTransforms() {
        return dsTransforms;
    }

    /**
     * Définit la valeur de la propriété dsTransforms.
     * 
     * @param value
     *     allowed object is
     *     {@link DsTransforms }
     *     
     */
    public void setDsTransforms(DsTransforms value) {
        this.dsTransforms = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSigPolicyHash.
     * 
     * @return
     *     possible object is
     *     {@link XadesSigPolicyHash }
     *     
     */
    public XadesSigPolicyHash getXadesSigPolicyHash() {
        return xadesSigPolicyHash;
    }

    /**
     * Définit la valeur de la propriété xadesSigPolicyHash.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSigPolicyHash }
     *     
     */
    public void setXadesSigPolicyHash(XadesSigPolicyHash value) {
        this.xadesSigPolicyHash = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSigPolicyQualifiers.
     * 
     * @return
     *     possible object is
     *     {@link XadesSigPolicyQualifiers }
     *     
     */
    public XadesSigPolicyQualifiers getXadesSigPolicyQualifiers() {
        return xadesSigPolicyQualifiers;
    }

    /**
     * Définit la valeur de la propriété xadesSigPolicyQualifiers.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSigPolicyQualifiers }
     *     
     */
    public void setXadesSigPolicyQualifiers(XadesSigPolicyQualifiers value) {
        this.xadesSigPolicyQualifiers = value;
    }

}

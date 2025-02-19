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
    "xadesCertDigest",
    "xadesIssuerSerial"
})
@XmlRootElement(name = "xades:Cert")
public class XadesCert {

    @XmlAttribute(name = "URI")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String uri;
    @XmlElement(name = "xades:CertDigest", required = true)
    protected XadesCertDigest xadesCertDigest;
    @XmlElement(name = "xades:IssuerSerial", required = true)
    protected XadesIssuerSerial xadesIssuerSerial;

    /**
     * Obtient la valeur de la propriété uri.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURI() {
        return uri;
    }

    /**
     * Définit la valeur de la propriété uri.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURI(String value) {
        this.uri = value;
    }

    /**
     * Obtient la valeur de la propriété xadesCertDigest.
     * 
     * @return
     *     possible object is
     *     {@link XadesCertDigest }
     *     
     */
    public XadesCertDigest getXadesCertDigest() {
        return xadesCertDigest;
    }

    /**
     * Définit la valeur de la propriété xadesCertDigest.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesCertDigest }
     *     
     */
    public void setXadesCertDigest(XadesCertDigest value) {
        this.xadesCertDigest = value;
    }

    /**
     * Obtient la valeur de la propriété xadesIssuerSerial.
     * 
     * @return
     *     possible object is
     *     {@link XadesIssuerSerial }
     *     
     */
    public XadesIssuerSerial getXadesIssuerSerial() {
        return xadesIssuerSerial;
    }

    /**
     * Définit la valeur de la propriété xadesIssuerSerial.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesIssuerSerial }
     *     
     */
    public void setXadesIssuerSerial(XadesIssuerSerial value) {
        this.xadesIssuerSerial = value;
    }

}

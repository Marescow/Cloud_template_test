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
    "xadesIssuer",
    "xadesIssueTime",
    "xadesNumber"
})
@XmlRootElement(name = "xades:CRLIdentifier")
public class XadesCRLIdentifier {

    @XmlAttribute(name = "URI")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String uri;
    @XmlElement(name = "xades:Issuer", required = true)
    protected String xadesIssuer;
    @XmlElement(name = "xades:IssueTime", required = true)
    protected String xadesIssueTime;
    @XmlElement(name = "xades:Number")
    protected String xadesNumber;

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
     * Obtient la valeur de la propriété xadesIssuer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesIssuer() {
        return xadesIssuer;
    }

    /**
     * Définit la valeur de la propriété xadesIssuer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesIssuer(String value) {
        this.xadesIssuer = value;
    }

    /**
     * Obtient la valeur de la propriété xadesIssueTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesIssueTime() {
        return xadesIssueTime;
    }

    /**
     * Définit la valeur de la propriété xadesIssueTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesIssueTime(String value) {
        this.xadesIssueTime = value;
    }

    /**
     * Obtient la valeur de la propriété xadesNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesNumber() {
        return xadesNumber;
    }

    /**
     * Définit la valeur de la propriété xadesNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesNumber(String value) {
        this.xadesNumber = value;
    }

}

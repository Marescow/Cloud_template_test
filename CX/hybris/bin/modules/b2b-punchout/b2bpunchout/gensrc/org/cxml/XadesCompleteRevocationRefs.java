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
    "xadesCRLRefs",
    "xadesOCSPRefs",
    "xadesOtherRefs"
})
@XmlRootElement(name = "xades:CompleteRevocationRefs")
public class XadesCompleteRevocationRefs {

    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "xades:CRLRefs")
    protected XadesCRLRefs xadesCRLRefs;
    @XmlElement(name = "xades:OCSPRefs")
    protected XadesOCSPRefs xadesOCSPRefs;
    @XmlElement(name = "xades:OtherRefs")
    protected XadesOtherRefs xadesOtherRefs;

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
     * Obtient la valeur de la propriété xadesCRLRefs.
     * 
     * @return
     *     possible object is
     *     {@link XadesCRLRefs }
     *     
     */
    public XadesCRLRefs getXadesCRLRefs() {
        return xadesCRLRefs;
    }

    /**
     * Définit la valeur de la propriété xadesCRLRefs.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesCRLRefs }
     *     
     */
    public void setXadesCRLRefs(XadesCRLRefs value) {
        this.xadesCRLRefs = value;
    }

    /**
     * Obtient la valeur de la propriété xadesOCSPRefs.
     * 
     * @return
     *     possible object is
     *     {@link XadesOCSPRefs }
     *     
     */
    public XadesOCSPRefs getXadesOCSPRefs() {
        return xadesOCSPRefs;
    }

    /**
     * Définit la valeur de la propriété xadesOCSPRefs.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesOCSPRefs }
     *     
     */
    public void setXadesOCSPRefs(XadesOCSPRefs value) {
        this.xadesOCSPRefs = value;
    }

    /**
     * Obtient la valeur de la propriété xadesOtherRefs.
     * 
     * @return
     *     possible object is
     *     {@link XadesOtherRefs }
     *     
     */
    public XadesOtherRefs getXadesOtherRefs() {
        return xadesOtherRefs;
    }

    /**
     * Définit la valeur de la propriété xadesOtherRefs.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesOtherRefs }
     *     
     */
    public void setXadesOtherRefs(XadesOtherRefs value) {
        this.xadesOtherRefs = value;
    }

}

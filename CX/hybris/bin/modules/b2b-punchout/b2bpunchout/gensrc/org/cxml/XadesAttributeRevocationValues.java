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
    "xadesCRLValues",
    "xadesOCSPValues",
    "xadesOtherValues"
})
@XmlRootElement(name = "xades:AttributeRevocationValues")
public class XadesAttributeRevocationValues {

    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "xades:CRLValues")
    protected XadesCRLValues xadesCRLValues;
    @XmlElement(name = "xades:OCSPValues")
    protected XadesOCSPValues xadesOCSPValues;
    @XmlElement(name = "xades:OtherValues")
    protected XadesOtherValues xadesOtherValues;

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
     * Obtient la valeur de la propriété xadesCRLValues.
     * 
     * @return
     *     possible object is
     *     {@link XadesCRLValues }
     *     
     */
    public XadesCRLValues getXadesCRLValues() {
        return xadesCRLValues;
    }

    /**
     * Définit la valeur de la propriété xadesCRLValues.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesCRLValues }
     *     
     */
    public void setXadesCRLValues(XadesCRLValues value) {
        this.xadesCRLValues = value;
    }

    /**
     * Obtient la valeur de la propriété xadesOCSPValues.
     * 
     * @return
     *     possible object is
     *     {@link XadesOCSPValues }
     *     
     */
    public XadesOCSPValues getXadesOCSPValues() {
        return xadesOCSPValues;
    }

    /**
     * Définit la valeur de la propriété xadesOCSPValues.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesOCSPValues }
     *     
     */
    public void setXadesOCSPValues(XadesOCSPValues value) {
        this.xadesOCSPValues = value;
    }

    /**
     * Obtient la valeur de la propriété xadesOtherValues.
     * 
     * @return
     *     possible object is
     *     {@link XadesOtherValues }
     *     
     */
    public XadesOtherValues getXadesOtherValues() {
        return xadesOtherValues;
    }

    /**
     * Définit la valeur de la propriété xadesOtherValues.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesOtherValues }
     *     
     */
    public void setXadesOtherValues(XadesOtherValues value) {
        this.xadesOtherValues = value;
    }

}

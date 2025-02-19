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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xadesSignedProperties",
    "xadesUnsignedProperties"
})
@XmlRootElement(name = "xades:QualifyingProperties")
public class XadesQualifyingProperties {

    @XmlAttribute(name = "Target", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String target;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "xmlns:xades")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String xmlnsXades;
    @XmlElement(name = "xades:SignedProperties")
    protected XadesSignedProperties xadesSignedProperties;
    @XmlElement(name = "xades:UnsignedProperties")
    protected XadesUnsignedProperties xadesUnsignedProperties;

    /**
     * Obtient la valeur de la propriété target.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Définit la valeur de la propriété target.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

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
     * Obtient la valeur de la propriété xmlnsXades.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlnsXades() {
        if (xmlnsXades == null) {
            return "http://uri.etsi.org/01903/v1.3.2#";
        } else {
            return xmlnsXades;
        }
    }

    /**
     * Définit la valeur de la propriété xmlnsXades.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlnsXades(String value) {
        this.xmlnsXades = value;
    }

    /**
     * Obtient la valeur de la propriété xadesSignedProperties.
     * 
     * @return
     *     possible object is
     *     {@link XadesSignedProperties }
     *     
     */
    public XadesSignedProperties getXadesSignedProperties() {
        return xadesSignedProperties;
    }

    /**
     * Définit la valeur de la propriété xadesSignedProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesSignedProperties }
     *     
     */
    public void setXadesSignedProperties(XadesSignedProperties value) {
        this.xadesSignedProperties = value;
    }

    /**
     * Obtient la valeur de la propriété xadesUnsignedProperties.
     * 
     * @return
     *     possible object is
     *     {@link XadesUnsignedProperties }
     *     
     */
    public XadesUnsignedProperties getXadesUnsignedProperties() {
        return xadesUnsignedProperties;
    }

    /**
     * Définit la valeur de la propriété xadesUnsignedProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesUnsignedProperties }
     *     
     */
    public void setXadesUnsignedProperties(XadesUnsignedProperties value) {
        this.xadesUnsignedProperties = value;
    }

}

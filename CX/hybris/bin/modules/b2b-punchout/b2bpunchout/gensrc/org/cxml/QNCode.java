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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "QNCode")
public class QNCode {

    @XmlAttribute(name = "domain", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String domain;
    @XmlAttribute(name = "codeGroup")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String codeGroup;
    @XmlAttribute(name = "codeGroupDescription")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String codeGroupDescription;
    @XmlAttribute(name = "code", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String code;
    @XmlAttribute(name = "codeDescription", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String codeDescription;

    /**
     * Obtient la valeur de la propriété domain.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Définit la valeur de la propriété domain.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Obtient la valeur de la propriété codeGroup.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeGroup() {
        return codeGroup;
    }

    /**
     * Définit la valeur de la propriété codeGroup.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeGroup(String value) {
        this.codeGroup = value;
    }

    /**
     * Obtient la valeur de la propriété codeGroupDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeGroupDescription() {
        return codeGroupDescription;
    }

    /**
     * Définit la valeur de la propriété codeGroupDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeGroupDescription(String value) {
        this.codeGroupDescription = value;
    }

    /**
     * Obtient la valeur de la propriété code.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Définit la valeur de la propriété code.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Obtient la valeur de la propriété codeDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeDescription() {
        return codeDescription;
    }

    /**
     * Définit la valeur de la propriété codeDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeDescription(String value) {
        this.codeDescription = value;
    }

}

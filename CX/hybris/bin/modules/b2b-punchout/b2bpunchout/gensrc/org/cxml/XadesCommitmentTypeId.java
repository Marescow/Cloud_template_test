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
    "xadesIdentifier",
    "xadesDescription",
    "xadesDocumentationReferences"
})
@XmlRootElement(name = "xades:CommitmentTypeId")
public class XadesCommitmentTypeId {

    @XmlElement(name = "xades:Identifier", required = true)
    protected XadesIdentifier xadesIdentifier;
    @XmlElement(name = "xades:Description")
    protected String xadesDescription;
    @XmlElement(name = "xades:DocumentationReferences")
    protected XadesDocumentationReferences xadesDocumentationReferences;

    /**
     * Obtient la valeur de la propriété xadesIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link XadesIdentifier }
     *     
     */
    public XadesIdentifier getXadesIdentifier() {
        return xadesIdentifier;
    }

    /**
     * Définit la valeur de la propriété xadesIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesIdentifier }
     *     
     */
    public void setXadesIdentifier(XadesIdentifier value) {
        this.xadesIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété xadesDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesDescription() {
        return xadesDescription;
    }

    /**
     * Définit la valeur de la propriété xadesDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesDescription(String value) {
        this.xadesDescription = value;
    }

    /**
     * Obtient la valeur de la propriété xadesDocumentationReferences.
     * 
     * @return
     *     possible object is
     *     {@link XadesDocumentationReferences }
     *     
     */
    public XadesDocumentationReferences getXadesDocumentationReferences() {
        return xadesDocumentationReferences;
    }

    /**
     * Définit la valeur de la propriété xadesDocumentationReferences.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesDocumentationReferences }
     *     
     */
    public void setXadesDocumentationReferences(XadesDocumentationReferences value) {
        this.xadesDocumentationReferences = value;
    }

}

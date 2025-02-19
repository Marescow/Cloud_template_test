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
    "xadesClaimedRoles",
    "xadesCertifiedRoles"
})
@XmlRootElement(name = "xades:SignerRole")
public class XadesSignerRole {

    @XmlElement(name = "xades:ClaimedRoles")
    protected XadesClaimedRoles xadesClaimedRoles;
    @XmlElement(name = "xades:CertifiedRoles")
    protected XadesCertifiedRoles xadesCertifiedRoles;

    /**
     * Obtient la valeur de la propriété xadesClaimedRoles.
     * 
     * @return
     *     possible object is
     *     {@link XadesClaimedRoles }
     *     
     */
    public XadesClaimedRoles getXadesClaimedRoles() {
        return xadesClaimedRoles;
    }

    /**
     * Définit la valeur de la propriété xadesClaimedRoles.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesClaimedRoles }
     *     
     */
    public void setXadesClaimedRoles(XadesClaimedRoles value) {
        this.xadesClaimedRoles = value;
    }

    /**
     * Obtient la valeur de la propriété xadesCertifiedRoles.
     * 
     * @return
     *     possible object is
     *     {@link XadesCertifiedRoles }
     *     
     */
    public XadesCertifiedRoles getXadesCertifiedRoles() {
        return xadesCertifiedRoles;
    }

    /**
     * Définit la valeur de la propriété xadesCertifiedRoles.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesCertifiedRoles }
     *     
     */
    public void setXadesCertifiedRoles(XadesCertifiedRoles value) {
        this.xadesCertifiedRoles = value;
    }

}

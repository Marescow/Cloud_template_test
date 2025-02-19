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
    "startPage"
})
@XmlRootElement(name = "ProviderSetupResponse")
public class ProviderSetupResponse {

    @XmlElement(name = "StartPage", required = true)
    protected StartPage startPage;

    /**
     * Obtient la valeur de la propriété startPage.
     * 
     * @return
     *     possible object is
     *     {@link StartPage }
     *     
     */
    public StartPage getStartPage() {
        return startPage;
    }

    /**
     * Définit la valeur de la propriété startPage.
     * 
     * @param value
     *     allowed object is
     *     {@link StartPage }
     *     
     */
    public void setStartPage(StartPage value) {
        this.startPage = value;
    }

}

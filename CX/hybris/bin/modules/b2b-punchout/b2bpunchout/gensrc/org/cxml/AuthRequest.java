//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:32:52 PM CET 
//


package org.cxml;

import java.util.ArrayList;
import java.util.List;
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
    "credential",
    "x509Data"
})
@XmlRootElement(name = "AuthRequest")
public class AuthRequest {

    @XmlElement(name = "Credential", required = true)
    protected List<Credential> credential;
    @XmlElement(name = "X509Data")
    protected X509Data x509Data;

    /**
     * Gets the value of the credential property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the credential property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCredential().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Credential }
     * 
     * 
     */
    public List<Credential> getCredential() {
        if (credential == null) {
            credential = new ArrayList<Credential>();
        }
        return this.credential;
    }

    /**
     * Obtient la valeur de la propriété x509Data.
     * 
     * @return
     *     possible object is
     *     {@link X509Data }
     *     
     */
    public X509Data getX509Data() {
        return x509Data;
    }

    /**
     * Définit la valeur de la propriété x509Data.
     * 
     * @param value
     *     allowed object is
     *     {@link X509Data }
     *     
     */
    public void setX509Data(X509Data value) {
        this.x509Data = value;
    }

}

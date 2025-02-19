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
    "email",
    "urlPost"
})
@XmlRootElement(name = "Notification")
public class Notification {

    @XmlElement(name = "Email")
    protected Email email;
    @XmlElement(name = "URLPost")
    protected URLPost urlPost;

    /**
     * Obtient la valeur de la propriété email.
     * 
     * @return
     *     possible object is
     *     {@link Email }
     *     
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Définit la valeur de la propriété email.
     * 
     * @param value
     *     allowed object is
     *     {@link Email }
     *     
     */
    public void setEmail(Email value) {
        this.email = value;
    }

    /**
     * Obtient la valeur de la propriété urlPost.
     * 
     * @return
     *     possible object is
     *     {@link URLPost }
     *     
     */
    public URLPost getURLPost() {
        return urlPost;
    }

    /**
     * Définit la valeur de la propriété urlPost.
     * 
     * @param value
     *     allowed object is
     *     {@link URLPost }
     *     
     */
    public void setURLPost(URLPost value) {
        this.urlPost = value;
    }

}

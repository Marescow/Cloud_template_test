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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
    "identity",
    "sharedSecretOrDigitalSignatureOrCredentialMac"
})
@XmlRootElement(name = "Credential")
public class Credential {

    @XmlAttribute(name = "domain", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String domain;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlElement(name = "Identity", required = true)
    protected Identity identity;
    @XmlElements({
        @XmlElement(name = "SharedSecret", type = SharedSecret.class),
        @XmlElement(name = "DigitalSignature", type = DigitalSignature.class),
        @XmlElement(name = "CredentialMac", type = CredentialMac.class)
    })
    protected List<Object> sharedSecretOrDigitalSignatureOrCredentialMac;

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
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété identity.
     * 
     * @return
     *     possible object is
     *     {@link Identity }
     *     
     */
    public Identity getIdentity() {
        return identity;
    }

    /**
     * Définit la valeur de la propriété identity.
     * 
     * @param value
     *     allowed object is
     *     {@link Identity }
     *     
     */
    public void setIdentity(Identity value) {
        this.identity = value;
    }

    /**
     * Gets the value of the sharedSecretOrDigitalSignatureOrCredentialMac property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sharedSecretOrDigitalSignatureOrCredentialMac property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSharedSecretOrDigitalSignatureOrCredentialMac().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SharedSecret }
     * {@link DigitalSignature }
     * {@link CredentialMac }
     * 
     * 
     */
    public List<Object> getSharedSecretOrDigitalSignatureOrCredentialMac() {
        if (sharedSecretOrDigitalSignatureOrCredentialMac == null) {
            sharedSecretOrDigitalSignatureOrCredentialMac = new ArrayList<Object>();
        }
        return this.sharedSecretOrDigitalSignatureOrCredentialMac;
    }

}

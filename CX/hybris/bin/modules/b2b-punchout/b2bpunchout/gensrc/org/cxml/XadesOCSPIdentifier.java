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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xadesResponderID",
    "xadesProducedAt"
})
@XmlRootElement(name = "xades:OCSPIdentifier")
public class XadesOCSPIdentifier {

    @XmlAttribute(name = "URI")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String uri;
    @XmlElement(name = "xades:ResponderID", required = true)
    protected XadesResponderID xadesResponderID;
    @XmlElement(name = "xades:ProducedAt", required = true)
    protected String xadesProducedAt;

    /**
     * Obtient la valeur de la propriété uri.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURI() {
        return uri;
    }

    /**
     * Définit la valeur de la propriété uri.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURI(String value) {
        this.uri = value;
    }

    /**
     * Obtient la valeur de la propriété xadesResponderID.
     * 
     * @return
     *     possible object is
     *     {@link XadesResponderID }
     *     
     */
    public XadesResponderID getXadesResponderID() {
        return xadesResponderID;
    }

    /**
     * Définit la valeur de la propriété xadesResponderID.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesResponderID }
     *     
     */
    public void setXadesResponderID(XadesResponderID value) {
        this.xadesResponderID = value;
    }

    /**
     * Obtient la valeur de la propriété xadesProducedAt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesProducedAt() {
        return xadesProducedAt;
    }

    /**
     * Définit la valeur de la propriété xadesProducedAt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesProducedAt(String value) {
        this.xadesProducedAt = value;
    }

}

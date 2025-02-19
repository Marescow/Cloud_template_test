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
    "xadesEncapsulatedX509CertificateOrXadesOtherCertificate"
})
@XmlRootElement(name = "xades:CertificateValues")
public class XadesCertificateValues {

    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElements({
        @XmlElement(name = "xades:EncapsulatedX509Certificate", type = XadesEncapsulatedX509Certificate.class),
        @XmlElement(name = "xades:OtherCertificate", type = XadesOtherCertificate.class)
    })
    protected List<Object> xadesEncapsulatedX509CertificateOrXadesOtherCertificate;

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
     * Gets the value of the xadesEncapsulatedX509CertificateOrXadesOtherCertificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xadesEncapsulatedX509CertificateOrXadesOtherCertificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXadesEncapsulatedX509CertificateOrXadesOtherCertificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XadesEncapsulatedX509Certificate }
     * {@link XadesOtherCertificate }
     * 
     * 
     */
    public List<Object> getXadesEncapsulatedX509CertificateOrXadesOtherCertificate() {
        if (xadesEncapsulatedX509CertificateOrXadesOtherCertificate == null) {
            xadesEncapsulatedX509CertificateOrXadesOtherCertificate = new ArrayList<Object>();
        }
        return this.xadesEncapsulatedX509CertificateOrXadesOtherCertificate;
    }

}

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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "x509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL"
})
@XmlRootElement(name = "X509Data")
public class X509Data {

    @XmlElements({
        @XmlElement(name = "X509IssuerSerial", required = true, type = X509IssuerSerial.class),
        @XmlElement(name = "X509SKI", required = true, type = X509SKI.class),
        @XmlElement(name = "X509SubjectName", required = true, type = X509SubjectName.class),
        @XmlElement(name = "X509Certificate", required = true, type = X509Certificate.class),
        @XmlElement(name = "X509CRL", required = true, type = X509CRL.class)
    })
    protected List<Object> x509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL;

    /**
     * Gets the value of the x509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the x509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getX509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link X509IssuerSerial }
     * {@link X509SKI }
     * {@link X509SubjectName }
     * {@link X509Certificate }
     * {@link X509CRL }
     * 
     * 
     */
    public List<Object> getX509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL() {
        if (x509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL == null) {
            x509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL = new ArrayList<Object>();
        }
        return this.x509IssuerSerialOrX509SKIOrX509SubjectNameOrX509CertificateOrX509CRL;
    }

}

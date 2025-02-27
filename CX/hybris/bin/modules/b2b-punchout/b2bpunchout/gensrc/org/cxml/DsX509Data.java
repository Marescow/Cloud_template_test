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
    "dsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL"
})
@XmlRootElement(name = "ds:X509Data")
public class DsX509Data {

    @XmlElements({
        @XmlElement(name = "ds:X509IssuerSerial", required = true, type = DsX509IssuerSerial.class),
        @XmlElement(name = "ds:X509SKI", required = true, type = DsX509SKI.class),
        @XmlElement(name = "ds:X509SubjectName", required = true, type = DsX509SubjectName.class),
        @XmlElement(name = "ds:X509Certificate", required = true, type = DsX509Certificate.class),
        @XmlElement(name = "ds:X509CRL", required = true, type = DsX509CRL.class)
    })
    protected List<Object> dsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL;

    /**
     * Gets the value of the dsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DsX509IssuerSerial }
     * {@link DsX509SKI }
     * {@link DsX509SubjectName }
     * {@link DsX509Certificate }
     * {@link DsX509CRL }
     * 
     * 
     */
    public List<Object> getDsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL() {
        if (dsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL == null) {
            dsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL = new ArrayList<Object>();
        }
        return this.dsX509IssuerSerialOrDsX509SKIOrDsX509SubjectNameOrDsX509CertificateOrDsX509CRL;
    }

}

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
    "xadesOrganization",
    "xadesNoticeNumbers"
})
@XmlRootElement(name = "xades:NoticeRef")
public class XadesNoticeRef {

    @XmlElement(name = "xades:Organization", required = true)
    protected String xadesOrganization;
    @XmlElement(name = "xades:NoticeNumbers", required = true)
    protected XadesNoticeNumbers xadesNoticeNumbers;

    /**
     * Obtient la valeur de la propriété xadesOrganization.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesOrganization() {
        return xadesOrganization;
    }

    /**
     * Définit la valeur de la propriété xadesOrganization.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesOrganization(String value) {
        this.xadesOrganization = value;
    }

    /**
     * Obtient la valeur de la propriété xadesNoticeNumbers.
     * 
     * @return
     *     possible object is
     *     {@link XadesNoticeNumbers }
     *     
     */
    public XadesNoticeNumbers getXadesNoticeNumbers() {
        return xadesNoticeNumbers;
    }

    /**
     * Définit la valeur de la propriété xadesNoticeNumbers.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesNoticeNumbers }
     *     
     */
    public void setXadesNoticeNumbers(XadesNoticeNumbers value) {
        this.xadesNoticeNumbers = value;
    }

}

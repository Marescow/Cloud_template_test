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
    "xadesNoticeRef",
    "xadesExplicitText"
})
@XmlRootElement(name = "xades:SPUserNotice")
public class XadesSPUserNotice {

    @XmlElement(name = "xades:NoticeRef")
    protected XadesNoticeRef xadesNoticeRef;
    @XmlElement(name = "xades:ExplicitText")
    protected String xadesExplicitText;

    /**
     * Obtient la valeur de la propriété xadesNoticeRef.
     * 
     * @return
     *     possible object is
     *     {@link XadesNoticeRef }
     *     
     */
    public XadesNoticeRef getXadesNoticeRef() {
        return xadesNoticeRef;
    }

    /**
     * Définit la valeur de la propriété xadesNoticeRef.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesNoticeRef }
     *     
     */
    public void setXadesNoticeRef(XadesNoticeRef value) {
        this.xadesNoticeRef = value;
    }

    /**
     * Obtient la valeur de la propriété xadesExplicitText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXadesExplicitText() {
        return xadesExplicitText;
    }

    /**
     * Définit la valeur de la propriété xadesExplicitText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXadesExplicitText(String value) {
        this.xadesExplicitText = value;
    }

}

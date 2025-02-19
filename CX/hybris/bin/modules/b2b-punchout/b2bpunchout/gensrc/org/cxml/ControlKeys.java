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
    "ocInstruction",
    "asnInstruction",
    "invoiceInstruction",
    "sesInstruction"
})
@XmlRootElement(name = "ControlKeys")
public class ControlKeys {

    @XmlElement(name = "OCInstruction")
    protected OCInstruction ocInstruction;
    @XmlElement(name = "ASNInstruction")
    protected ASNInstruction asnInstruction;
    @XmlElement(name = "InvoiceInstruction")
    protected InvoiceInstruction invoiceInstruction;
    @XmlElement(name = "SESInstruction")
    protected SESInstruction sesInstruction;

    /**
     * Obtient la valeur de la propriété ocInstruction.
     * 
     * @return
     *     possible object is
     *     {@link OCInstruction }
     *     
     */
    public OCInstruction getOCInstruction() {
        return ocInstruction;
    }

    /**
     * Définit la valeur de la propriété ocInstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link OCInstruction }
     *     
     */
    public void setOCInstruction(OCInstruction value) {
        this.ocInstruction = value;
    }

    /**
     * Obtient la valeur de la propriété asnInstruction.
     * 
     * @return
     *     possible object is
     *     {@link ASNInstruction }
     *     
     */
    public ASNInstruction getASNInstruction() {
        return asnInstruction;
    }

    /**
     * Définit la valeur de la propriété asnInstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link ASNInstruction }
     *     
     */
    public void setASNInstruction(ASNInstruction value) {
        this.asnInstruction = value;
    }

    /**
     * Obtient la valeur de la propriété invoiceInstruction.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceInstruction }
     *     
     */
    public InvoiceInstruction getInvoiceInstruction() {
        return invoiceInstruction;
    }

    /**
     * Définit la valeur de la propriété invoiceInstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceInstruction }
     *     
     */
    public void setInvoiceInstruction(InvoiceInstruction value) {
        this.invoiceInstruction = value;
    }

    /**
     * Obtient la valeur de la propriété sesInstruction.
     * 
     * @return
     *     possible object is
     *     {@link SESInstruction }
     *     
     */
    public SESInstruction getSESInstruction() {
        return sesInstruction;
    }

    /**
     * Définit la valeur de la propriété sesInstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SESInstruction }
     *     
     */
    public void setSESInstruction(SESInstruction value) {
        this.sesInstruction = value;
    }

}

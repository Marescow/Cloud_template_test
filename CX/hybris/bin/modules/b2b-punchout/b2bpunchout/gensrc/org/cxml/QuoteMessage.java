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
    "quoteMessageHeader",
    "quoteItemIn"
})
@XmlRootElement(name = "QuoteMessage")
public class QuoteMessage {

    @XmlElement(name = "QuoteMessageHeader", required = true)
    protected QuoteMessageHeader quoteMessageHeader;
    @XmlElement(name = "QuoteItemIn")
    protected List<QuoteItemIn> quoteItemIn;

    /**
     * Obtient la valeur de la propriété quoteMessageHeader.
     * 
     * @return
     *     possible object is
     *     {@link QuoteMessageHeader }
     *     
     */
    public QuoteMessageHeader getQuoteMessageHeader() {
        return quoteMessageHeader;
    }

    /**
     * Définit la valeur de la propriété quoteMessageHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link QuoteMessageHeader }
     *     
     */
    public void setQuoteMessageHeader(QuoteMessageHeader value) {
        this.quoteMessageHeader = value;
    }

    /**
     * Gets the value of the quoteItemIn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quoteItemIn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuoteItemIn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QuoteItemIn }
     * 
     * 
     */
    public List<QuoteItemIn> getQuoteItemIn() {
        if (quoteItemIn == null) {
            quoteItemIn = new ArrayList<QuoteItemIn>();
        }
        return this.quoteItemIn;
    }

}

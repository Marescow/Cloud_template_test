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
    "originatorCookie",
    "returnData"
})
@XmlRootElement(name = "ProviderDoneMessage")
public class ProviderDoneMessage {

    @XmlElement(name = "OriginatorCookie", required = true)
    protected String originatorCookie;
    @XmlElement(name = "ReturnData")
    protected List<ReturnData> returnData;

    /**
     * Obtient la valeur de la propriété originatorCookie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginatorCookie() {
        return originatorCookie;
    }

    /**
     * Définit la valeur de la propriété originatorCookie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginatorCookie(String value) {
        this.originatorCookie = value;
    }

    /**
     * Gets the value of the returnData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReturnData }
     * 
     * 
     */
    public List<ReturnData> getReturnData() {
        if (returnData == null) {
            returnData = new ArrayList<ReturnData>();
        }
        return this.returnData;
    }

}

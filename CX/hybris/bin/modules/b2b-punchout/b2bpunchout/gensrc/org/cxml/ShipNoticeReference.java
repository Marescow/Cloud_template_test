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
    "documentReference"
})
@XmlRootElement(name = "ShipNoticeReference")
public class ShipNoticeReference {

    @XmlAttribute(name = "shipNoticeID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String shipNoticeID;
    @XmlAttribute(name = "shipNoticeDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String shipNoticeDate;
    @XmlElement(name = "DocumentReference", required = true)
    protected DocumentReference documentReference;

    /**
     * Obtient la valeur de la propriété shipNoticeID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipNoticeID() {
        return shipNoticeID;
    }

    /**
     * Définit la valeur de la propriété shipNoticeID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipNoticeID(String value) {
        this.shipNoticeID = value;
    }

    /**
     * Obtient la valeur de la propriété shipNoticeDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipNoticeDate() {
        return shipNoticeDate;
    }

    /**
     * Définit la valeur de la propriété shipNoticeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipNoticeDate(String value) {
        this.shipNoticeDate = value;
    }

    /**
     * Obtient la valeur de la propriété documentReference.
     * 
     * @return
     *     possible object is
     *     {@link DocumentReference }
     *     
     */
    public DocumentReference getDocumentReference() {
        return documentReference;
    }

    /**
     * Définit la valeur de la propriété documentReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentReference }
     *     
     */
    public void setDocumentReference(DocumentReference value) {
        this.documentReference = value;
    }

}

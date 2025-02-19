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
    "sealID",
    "sealingPartyCode",
    "equipmentIdentificationCode",
    "transportTerms",
    "dimension",
    "extrinsic"
})
@XmlRootElement(name = "TermsOfTransport")
public class TermsOfTransport {

    @XmlElement(name = "SealID")
    protected String sealID;
    @XmlElement(name = "SealingPartyCode")
    protected String sealingPartyCode;
    @XmlElement(name = "EquipmentIdentificationCode")
    protected String equipmentIdentificationCode;
    @XmlElement(name = "TransportTerms")
    protected TransportTerms transportTerms;
    @XmlElement(name = "Dimension")
    protected List<Dimension> dimension;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété sealID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSealID() {
        return sealID;
    }

    /**
     * Définit la valeur de la propriété sealID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSealID(String value) {
        this.sealID = value;
    }

    /**
     * Obtient la valeur de la propriété sealingPartyCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSealingPartyCode() {
        return sealingPartyCode;
    }

    /**
     * Définit la valeur de la propriété sealingPartyCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSealingPartyCode(String value) {
        this.sealingPartyCode = value;
    }

    /**
     * Obtient la valeur de la propriété equipmentIdentificationCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentIdentificationCode() {
        return equipmentIdentificationCode;
    }

    /**
     * Définit la valeur de la propriété equipmentIdentificationCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentIdentificationCode(String value) {
        this.equipmentIdentificationCode = value;
    }

    /**
     * Obtient la valeur de la propriété transportTerms.
     * 
     * @return
     *     possible object is
     *     {@link TransportTerms }
     *     
     */
    public TransportTerms getTransportTerms() {
        return transportTerms;
    }

    /**
     * Définit la valeur de la propriété transportTerms.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportTerms }
     *     
     */
    public void setTransportTerms(TransportTerms value) {
        this.transportTerms = value;
    }

    /**
     * Gets the value of the dimension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dimension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDimension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dimension }
     * 
     * 
     */
    public List<Dimension> getDimension() {
        if (dimension == null) {
            dimension = new ArrayList<Dimension>();
        }
        return this.dimension;
    }

    /**
     * Gets the value of the extrinsic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extrinsic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtrinsic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Extrinsic }
     * 
     * 
     */
    public List<Extrinsic> getExtrinsic() {
        if (extrinsic == null) {
            extrinsic = new ArrayList<Extrinsic>();
        }
        return this.extrinsic;
    }

}

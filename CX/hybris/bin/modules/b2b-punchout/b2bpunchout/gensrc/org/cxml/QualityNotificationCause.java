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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "qnCode",
    "ownerInfo",
    "description",
    "extrinsic"
})
@XmlRootElement(name = "QualityNotificationCause")
public class QualityNotificationCause {

    @XmlAttribute(name = "causeId", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String causeId;
    @XmlElement(name = "QNCode")
    protected QNCode qnCode;
    @XmlElement(name = "OwnerInfo")
    protected OwnerInfo ownerInfo;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété causeId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCauseId() {
        return causeId;
    }

    /**
     * Définit la valeur de la propriété causeId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCauseId(String value) {
        this.causeId = value;
    }

    /**
     * Obtient la valeur de la propriété qnCode.
     * 
     * @return
     *     possible object is
     *     {@link QNCode }
     *     
     */
    public QNCode getQNCode() {
        return qnCode;
    }

    /**
     * Définit la valeur de la propriété qnCode.
     * 
     * @param value
     *     allowed object is
     *     {@link QNCode }
     *     
     */
    public void setQNCode(QNCode value) {
        this.qnCode = value;
    }

    /**
     * Obtient la valeur de la propriété ownerInfo.
     * 
     * @return
     *     possible object is
     *     {@link OwnerInfo }
     *     
     */
    public OwnerInfo getOwnerInfo() {
        return ownerInfo;
    }

    /**
     * Définit la valeur de la propriété ownerInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnerInfo }
     *     
     */
    public void setOwnerInfo(OwnerInfo value) {
        this.ownerInfo = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
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

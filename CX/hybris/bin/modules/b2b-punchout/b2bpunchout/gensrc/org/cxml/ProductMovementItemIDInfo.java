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
    "idReference"
})
@XmlRootElement(name = "ProductMovementItemIDInfo")
public class ProductMovementItemIDInfo {

    @XmlAttribute(name = "movementLineNumber", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String movementLineNumber;
    @XmlAttribute(name = "movementID", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String movementID;
    @XmlAttribute(name = "movementDate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String movementDate;
    @XmlElement(name = "IdReference")
    protected List<IdReference> idReference;

    /**
     * Obtient la valeur de la propriété movementLineNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementLineNumber() {
        return movementLineNumber;
    }

    /**
     * Définit la valeur de la propriété movementLineNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementLineNumber(String value) {
        this.movementLineNumber = value;
    }

    /**
     * Obtient la valeur de la propriété movementID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementID() {
        return movementID;
    }

    /**
     * Définit la valeur de la propriété movementID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementID(String value) {
        this.movementID = value;
    }

    /**
     * Obtient la valeur de la propriété movementDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementDate() {
        return movementDate;
    }

    /**
     * Définit la valeur de la propriété movementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementDate(String value) {
        this.movementDate = value;
    }

    /**
     * Gets the value of the idReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdReference }
     * 
     * 
     */
    public List<IdReference> getIdReference() {
        if (idReference == null) {
            idReference = new ArrayList<IdReference>();
        }
        return this.idReference;
    }

}

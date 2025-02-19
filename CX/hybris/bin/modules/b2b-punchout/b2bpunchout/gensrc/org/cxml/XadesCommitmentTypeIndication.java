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
    "xadesCommitmentTypeId",
    "xadesObjectReferenceOrXadesAllSignedDataObjects",
    "xadesCommitmentTypeQualifiers"
})
@XmlRootElement(name = "xades:CommitmentTypeIndication")
public class XadesCommitmentTypeIndication {

    @XmlElement(name = "xades:CommitmentTypeId", required = true)
    protected XadesCommitmentTypeId xadesCommitmentTypeId;
    @XmlElements({
        @XmlElement(name = "xades:ObjectReference", required = true, type = XadesObjectReference.class),
        @XmlElement(name = "xades:AllSignedDataObjects", required = true, type = XadesAllSignedDataObjects.class)
    })
    protected List<Object> xadesObjectReferenceOrXadesAllSignedDataObjects;
    @XmlElement(name = "xades:CommitmentTypeQualifiers")
    protected XadesCommitmentTypeQualifiers xadesCommitmentTypeQualifiers;

    /**
     * Obtient la valeur de la propriété xadesCommitmentTypeId.
     * 
     * @return
     *     possible object is
     *     {@link XadesCommitmentTypeId }
     *     
     */
    public XadesCommitmentTypeId getXadesCommitmentTypeId() {
        return xadesCommitmentTypeId;
    }

    /**
     * Définit la valeur de la propriété xadesCommitmentTypeId.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesCommitmentTypeId }
     *     
     */
    public void setXadesCommitmentTypeId(XadesCommitmentTypeId value) {
        this.xadesCommitmentTypeId = value;
    }

    /**
     * Gets the value of the xadesObjectReferenceOrXadesAllSignedDataObjects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xadesObjectReferenceOrXadesAllSignedDataObjects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXadesObjectReferenceOrXadesAllSignedDataObjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XadesObjectReference }
     * {@link XadesAllSignedDataObjects }
     * 
     * 
     */
    public List<Object> getXadesObjectReferenceOrXadesAllSignedDataObjects() {
        if (xadesObjectReferenceOrXadesAllSignedDataObjects == null) {
            xadesObjectReferenceOrXadesAllSignedDataObjects = new ArrayList<Object>();
        }
        return this.xadesObjectReferenceOrXadesAllSignedDataObjects;
    }

    /**
     * Obtient la valeur de la propriété xadesCommitmentTypeQualifiers.
     * 
     * @return
     *     possible object is
     *     {@link XadesCommitmentTypeQualifiers }
     *     
     */
    public XadesCommitmentTypeQualifiers getXadesCommitmentTypeQualifiers() {
        return xadesCommitmentTypeQualifiers;
    }

    /**
     * Définit la valeur de la propriété xadesCommitmentTypeQualifiers.
     * 
     * @param value
     *     allowed object is
     *     {@link XadesCommitmentTypeQualifiers }
     *     
     */
    public void setXadesCommitmentTypeQualifiers(XadesCommitmentTypeQualifiers value) {
        this.xadesCommitmentTypeQualifiers = value;
    }

}

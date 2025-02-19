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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cxmlAttachmentOrCXML"
})
@XmlRootElement(name = "CopyRequest")
public class CopyRequest {

    @XmlAttribute(name = "processingMode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String processingMode;
    @XmlElements({
        @XmlElement(name = "cXMLAttachment", required = true, type = CXMLAttachment.class),
        @XmlElement(name = "cXML", required = true, type = CXML.class)
    })
    protected List<Object> cxmlAttachmentOrCXML;

    /**
     * Obtient la valeur de la propriété processingMode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingMode() {
        return processingMode;
    }

    /**
     * Définit la valeur de la propriété processingMode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingMode(String value) {
        this.processingMode = value;
    }

    /**
     * Gets the value of the cxmlAttachmentOrCXML property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cxmlAttachmentOrCXML property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCXMLAttachmentOrCXML().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CXMLAttachment }
     * {@link CXML }
     * 
     * 
     */
    public List<Object> getCXMLAttachmentOrCXML() {
        if (cxmlAttachmentOrCXML == null) {
            cxmlAttachmentOrCXML = new ArrayList<Object>();
        }
        return this.cxmlAttachmentOrCXML;
    }

}

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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cifContentOrIndexOrContract"
})
@XmlRootElement(name = "SubscriptionContent")
public class SubscriptionContent {

    @XmlAttribute(name = "filename")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String filename;
    @XmlElements({
        @XmlElement(name = "CIFContent", required = true, type = CIFContent.class),
        @XmlElement(name = "Index", required = true, type = Index.class),
        @XmlElement(name = "Contract", required = true, type = Contract.class)
    })
    protected List<Object> cifContentOrIndexOrContract;

    /**
     * Obtient la valeur de la propriété filename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Définit la valeur de la propriété filename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilename(String value) {
        this.filename = value;
    }

    /**
     * Gets the value of the cifContentOrIndexOrContract property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cifContentOrIndexOrContract property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCIFContentOrIndexOrContract().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIFContent }
     * {@link Index }
     * {@link Contract }
     * 
     * 
     */
    public List<Object> getCIFContentOrIndexOrContract() {
        if (cifContentOrIndexOrContract == null) {
            cifContentOrIndexOrContract = new ArrayList<Object>();
        }
        return this.cifContentOrIndexOrContract;
    }

}

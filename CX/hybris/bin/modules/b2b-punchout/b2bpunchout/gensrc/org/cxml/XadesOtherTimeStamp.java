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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xadesReferenceInfo",
    "dsCanonicalizationMethod",
    "xadesEncapsulatedTimeStampOrXadesXMLTimeStamp"
})
@XmlRootElement(name = "xades:OtherTimeStamp")
public class XadesOtherTimeStamp {

    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "xades:ReferenceInfo", required = true)
    protected List<XadesReferenceInfo> xadesReferenceInfo;
    @XmlElement(name = "ds:CanonicalizationMethod")
    protected DsCanonicalizationMethod dsCanonicalizationMethod;
    @XmlElements({
        @XmlElement(name = "xades:EncapsulatedTimeStamp", required = true, type = XadesEncapsulatedTimeStamp.class),
        @XmlElement(name = "xades:XMLTimeStamp", required = true, type = XadesXMLTimeStamp.class)
    })
    protected List<Object> xadesEncapsulatedTimeStampOrXadesXMLTimeStamp;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the xadesReferenceInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xadesReferenceInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXadesReferenceInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XadesReferenceInfo }
     * 
     * 
     */
    public List<XadesReferenceInfo> getXadesReferenceInfo() {
        if (xadesReferenceInfo == null) {
            xadesReferenceInfo = new ArrayList<XadesReferenceInfo>();
        }
        return this.xadesReferenceInfo;
    }

    /**
     * Obtient la valeur de la propriété dsCanonicalizationMethod.
     * 
     * @return
     *     possible object is
     *     {@link DsCanonicalizationMethod }
     *     
     */
    public DsCanonicalizationMethod getDsCanonicalizationMethod() {
        return dsCanonicalizationMethod;
    }

    /**
     * Définit la valeur de la propriété dsCanonicalizationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DsCanonicalizationMethod }
     *     
     */
    public void setDsCanonicalizationMethod(DsCanonicalizationMethod value) {
        this.dsCanonicalizationMethod = value;
    }

    /**
     * Gets the value of the xadesEncapsulatedTimeStampOrXadesXMLTimeStamp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xadesEncapsulatedTimeStampOrXadesXMLTimeStamp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXadesEncapsulatedTimeStampOrXadesXMLTimeStamp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XadesEncapsulatedTimeStamp }
     * {@link XadesXMLTimeStamp }
     * 
     * 
     */
    public List<Object> getXadesEncapsulatedTimeStampOrXadesXMLTimeStamp() {
        if (xadesEncapsulatedTimeStampOrXadesXMLTimeStamp == null) {
            xadesEncapsulatedTimeStampOrXadesXMLTimeStamp = new ArrayList<Object>();
        }
        return this.xadesEncapsulatedTimeStampOrXadesXMLTimeStamp;
    }

}

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
    "xadesEncapsulatedOCSPValue"
})
@XmlRootElement(name = "xades:OCSPValues")
public class XadesOCSPValues {

    @XmlElement(name = "xades:EncapsulatedOCSPValue", required = true)
    protected List<XadesEncapsulatedOCSPValue> xadesEncapsulatedOCSPValue;

    /**
     * Gets the value of the xadesEncapsulatedOCSPValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xadesEncapsulatedOCSPValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXadesEncapsulatedOCSPValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XadesEncapsulatedOCSPValue }
     * 
     * 
     */
    public List<XadesEncapsulatedOCSPValue> getXadesEncapsulatedOCSPValue() {
        if (xadesEncapsulatedOCSPValue == null) {
            xadesEncapsulatedOCSPValue = new ArrayList<XadesEncapsulatedOCSPValue>();
        }
        return this.xadesEncapsulatedOCSPValue;
    }

}

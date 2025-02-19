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
    "xadesByNameOrXadesByKey"
})
@XmlRootElement(name = "xades:ResponderID")
public class XadesResponderID {

    @XmlElements({
        @XmlElement(name = "xades:ByName", required = true, type = XadesByName.class),
        @XmlElement(name = "xades:ByKey", required = true, type = XadesByKey.class)
    })
    protected List<Object> xadesByNameOrXadesByKey;

    /**
     * Gets the value of the xadesByNameOrXadesByKey property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xadesByNameOrXadesByKey property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXadesByNameOrXadesByKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XadesByName }
     * {@link XadesByKey }
     * 
     * 
     */
    public List<Object> getXadesByNameOrXadesByKey() {
        if (xadesByNameOrXadesByKey == null) {
            xadesByNameOrXadesByKey = new ArrayList<Object>();
        }
        return this.xadesByNameOrXadesByKey;
    }

}

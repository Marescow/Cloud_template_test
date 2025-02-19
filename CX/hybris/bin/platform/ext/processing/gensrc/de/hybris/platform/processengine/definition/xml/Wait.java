//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:28:05 PM CET 
//


package de.hybris.platform.processengine.definition.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour wait complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="wait"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="event" type="{http://www.hybris.de/xsd/processdefinition}name"/&gt;
 *           &lt;element name="case" type="{http://www.hybris.de/xsd/processdefinition}case"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="timeout" type="{http://www.hybris.de/xsd/processdefinition}timeout" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attGroup ref="{http://www.hybris.de/xsd/processdefinition}nodeAttributes"/&gt;
 *       &lt;attribute name="then" type="{http://www.hybris.de/xsd/processdefinition}name" /&gt;
 *       &lt;attribute name="prependProcessCode" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wait", propOrder = {
    "event",
    "_case",
    "timeout"
})
public class Wait {

    protected String event;
    @XmlElement(name = "case")
    protected Case _case;
    protected Timeout timeout;
    @XmlAttribute(name = "then")
    protected String then;
    @XmlAttribute(name = "prependProcessCode")
    protected Boolean prependProcessCode;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Obtient la valeur de la propriété event.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvent() {
        return event;
    }

    /**
     * Définit la valeur de la propriété event.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvent(String value) {
        this.event = value;
    }

    /**
     * Obtient la valeur de la propriété case.
     * 
     * @return
     *     possible object is
     *     {@link Case }
     *     
     */
    public Case getCase() {
        return _case;
    }

    /**
     * Définit la valeur de la propriété case.
     * 
     * @param value
     *     allowed object is
     *     {@link Case }
     *     
     */
    public void setCase(Case value) {
        this._case = value;
    }

    /**
     * Obtient la valeur de la propriété timeout.
     * 
     * @return
     *     possible object is
     *     {@link Timeout }
     *     
     */
    public Timeout getTimeout() {
        return timeout;
    }

    /**
     * Définit la valeur de la propriété timeout.
     * 
     * @param value
     *     allowed object is
     *     {@link Timeout }
     *     
     */
    public void setTimeout(Timeout value) {
        this.timeout = value;
    }

    /**
     * Obtient la valeur de la propriété then.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThen() {
        return then;
    }

    /**
     * Définit la valeur de la propriété then.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThen(String value) {
        this.then = value;
    }

    /**
     * Obtient la valeur de la propriété prependProcessCode.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPrependProcessCode() {
        if (prependProcessCode == null) {
            return true;
        } else {
            return prependProcessCode.booleanValue();
        }
    }

    /**
     * Définit la valeur de la propriété prependProcessCode.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrependProcessCode(Boolean value) {
        this.prependProcessCode = value;
    }

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

}

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.02.13 à 04:28:05 PM CET 
//


package de.hybris.platform.processengine.definition.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour action complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="action"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parameter" type="{http://www.hybris.de/xsd/processdefinition}parameter" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="transition" type="{http://www.hybris.de/xsd/processdefinition}transition" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attGroup ref="{http://www.hybris.de/xsd/processdefinition}nodeAttributes"/&gt;
 *       &lt;attribute name="bean" use="required" type="{http://www.hybris.de/xsd/processdefinition}name" /&gt;
 *       &lt;attribute name="node" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="nodeGroup" type="{http://www.hybris.de/xsd/processdefinition}name" /&gt;
 *       &lt;attribute name="canJoinPreviousNode" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "action", propOrder = {
    "parameter",
    "transition"
})
public class Action {

    protected List<Parameter> parameter;
    @XmlElement(required = true)
    protected List<Transition> transition;
    @XmlAttribute(name = "bean", required = true)
    protected String bean;
    @XmlAttribute(name = "node")
    protected Integer node;
    @XmlAttribute(name = "nodeGroup")
    protected String nodeGroup;
    @XmlAttribute(name = "canJoinPreviousNode")
    protected Boolean canJoinPreviousNode;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parameter }
     * 
     * 
     */
    public List<Parameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<Parameter>();
        }
        return this.parameter;
    }

    /**
     * Gets the value of the transition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transition }
     * 
     * 
     */
    public List<Transition> getTransition() {
        if (transition == null) {
            transition = new ArrayList<Transition>();
        }
        return this.transition;
    }

    /**
     * Obtient la valeur de la propriété bean.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBean() {
        return bean;
    }

    /**
     * Définit la valeur de la propriété bean.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBean(String value) {
        this.bean = value;
    }

    /**
     * Obtient la valeur de la propriété node.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNode() {
        return node;
    }

    /**
     * Définit la valeur de la propriété node.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNode(Integer value) {
        this.node = value;
    }

    /**
     * Obtient la valeur de la propriété nodeGroup.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeGroup() {
        return nodeGroup;
    }

    /**
     * Définit la valeur de la propriété nodeGroup.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeGroup(String value) {
        this.nodeGroup = value;
    }

    /**
     * Obtient la valeur de la propriété canJoinPreviousNode.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCanJoinPreviousNode() {
        return canJoinPreviousNode;
    }

    /**
     * Définit la valeur de la propriété canJoinPreviousNode.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCanJoinPreviousNode(Boolean value) {
        this.canJoinPreviousNode = value;
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

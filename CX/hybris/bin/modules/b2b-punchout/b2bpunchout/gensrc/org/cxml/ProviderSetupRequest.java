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
    "originatorCookie",
    "browserFormPost",
    "followup",
    "selectedService",
    "extrinsic"
})
@XmlRootElement(name = "ProviderSetupRequest")
public class ProviderSetupRequest {

    @XmlElement(name = "OriginatorCookie", required = true)
    protected String originatorCookie;
    @XmlElement(name = "BrowserFormPost")
    protected BrowserFormPost browserFormPost;
    @XmlElement(name = "Followup")
    protected Followup followup;
    @XmlElement(name = "SelectedService", required = true)
    protected String selectedService;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Obtient la valeur de la propriété originatorCookie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginatorCookie() {
        return originatorCookie;
    }

    /**
     * Définit la valeur de la propriété originatorCookie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginatorCookie(String value) {
        this.originatorCookie = value;
    }

    /**
     * Obtient la valeur de la propriété browserFormPost.
     * 
     * @return
     *     possible object is
     *     {@link BrowserFormPost }
     *     
     */
    public BrowserFormPost getBrowserFormPost() {
        return browserFormPost;
    }

    /**
     * Définit la valeur de la propriété browserFormPost.
     * 
     * @param value
     *     allowed object is
     *     {@link BrowserFormPost }
     *     
     */
    public void setBrowserFormPost(BrowserFormPost value) {
        this.browserFormPost = value;
    }

    /**
     * Obtient la valeur de la propriété followup.
     * 
     * @return
     *     possible object is
     *     {@link Followup }
     *     
     */
    public Followup getFollowup() {
        return followup;
    }

    /**
     * Définit la valeur de la propriété followup.
     * 
     * @param value
     *     allowed object is
     *     {@link Followup }
     *     
     */
    public void setFollowup(Followup value) {
        this.followup = value;
    }

    /**
     * Obtient la valeur de la propriété selectedService.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedService() {
        return selectedService;
    }

    /**
     * Définit la valeur de la propriété selectedService.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedService(String value) {
        this.selectedService = value;
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

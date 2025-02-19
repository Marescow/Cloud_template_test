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
    "travelDetailOrFeeDetailOrLaborDetailOrExtrinsic"
})
@XmlRootElement(name = "SpendDetail")
public class SpendDetail {

    @XmlElements({
        @XmlElement(name = "TravelDetail", required = true, type = TravelDetail.class),
        @XmlElement(name = "FeeDetail", required = true, type = FeeDetail.class),
        @XmlElement(name = "LaborDetail", required = true, type = LaborDetail.class),
        @XmlElement(name = "Extrinsic", required = true, type = Extrinsic.class)
    })
    protected List<Object> travelDetailOrFeeDetailOrLaborDetailOrExtrinsic;

    /**
     * Gets the value of the travelDetailOrFeeDetailOrLaborDetailOrExtrinsic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the travelDetailOrFeeDetailOrLaborDetailOrExtrinsic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTravelDetailOrFeeDetailOrLaborDetailOrExtrinsic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TravelDetail }
     * {@link FeeDetail }
     * {@link LaborDetail }
     * {@link Extrinsic }
     * 
     * 
     */
    public List<Object> getTravelDetailOrFeeDetailOrLaborDetailOrExtrinsic() {
        if (travelDetailOrFeeDetailOrLaborDetailOrExtrinsic == null) {
            travelDetailOrFeeDetailOrLaborDetailOrExtrinsic = new ArrayList<Object>();
        }
        return this.travelDetailOrFeeDetailOrLaborDetailOrExtrinsic;
    }

}

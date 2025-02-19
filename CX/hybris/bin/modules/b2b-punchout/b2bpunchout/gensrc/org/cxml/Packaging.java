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
    "packagingCodeOrDimension",
    "description",
    "packagingLevelCode",
    "packageTypeCodeIdentifierCode",
    "shippingContainerSerialCode",
    "shippingContainerSerialCodeReference",
    "packageID",
    "shippingMark",
    "orderedQuantity",
    "dispatchQuantity",
    "freeGoodsQuantity",
    "quantityVarianceNote",
    "bestBeforeDate",
    "assetInfo",
    "storeCode",
    "extrinsic"
})
@XmlRootElement(name = "Packaging")
public class Packaging {

    @XmlElements({
        @XmlElement(name = "PackagingCode", required = true, type = PackagingCode.class),
        @XmlElement(name = "Dimension", required = true, type = Dimension.class)
    })
    protected List<Object> packagingCodeOrDimension;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "PackagingLevelCode")
    protected String packagingLevelCode;
    @XmlElement(name = "PackageTypeCodeIdentifierCode")
    protected String packageTypeCodeIdentifierCode;
    @XmlElement(name = "ShippingContainerSerialCode")
    protected String shippingContainerSerialCode;
    @XmlElement(name = "ShippingContainerSerialCodeReference")
    protected String shippingContainerSerialCodeReference;
    @XmlElement(name = "PackageID")
    protected PackageID packageID;
    @XmlElement(name = "ShippingMark")
    protected List<ShippingMark> shippingMark;
    @XmlElement(name = "OrderedQuantity")
    protected OrderedQuantity orderedQuantity;
    @XmlElement(name = "DispatchQuantity")
    protected DispatchQuantity dispatchQuantity;
    @XmlElement(name = "FreeGoodsQuantity")
    protected FreeGoodsQuantity freeGoodsQuantity;
    @XmlElement(name = "QuantityVarianceNote")
    protected String quantityVarianceNote;
    @XmlElement(name = "BestBeforeDate")
    protected BestBeforeDate bestBeforeDate;
    @XmlElement(name = "AssetInfo")
    protected List<AssetInfo> assetInfo;
    @XmlElement(name = "StoreCode")
    protected String storeCode;
    @XmlElement(name = "Extrinsic")
    protected List<Extrinsic> extrinsic;

    /**
     * Gets the value of the packagingCodeOrDimension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packagingCodeOrDimension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackagingCodeOrDimension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackagingCode }
     * {@link Dimension }
     * 
     * 
     */
    public List<Object> getPackagingCodeOrDimension() {
        if (packagingCodeOrDimension == null) {
            packagingCodeOrDimension = new ArrayList<Object>();
        }
        return this.packagingCodeOrDimension;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété packagingLevelCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackagingLevelCode() {
        return packagingLevelCode;
    }

    /**
     * Définit la valeur de la propriété packagingLevelCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackagingLevelCode(String value) {
        this.packagingLevelCode = value;
    }

    /**
     * Obtient la valeur de la propriété packageTypeCodeIdentifierCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageTypeCodeIdentifierCode() {
        return packageTypeCodeIdentifierCode;
    }

    /**
     * Définit la valeur de la propriété packageTypeCodeIdentifierCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageTypeCodeIdentifierCode(String value) {
        this.packageTypeCodeIdentifierCode = value;
    }

    /**
     * Obtient la valeur de la propriété shippingContainerSerialCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingContainerSerialCode() {
        return shippingContainerSerialCode;
    }

    /**
     * Définit la valeur de la propriété shippingContainerSerialCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingContainerSerialCode(String value) {
        this.shippingContainerSerialCode = value;
    }

    /**
     * Obtient la valeur de la propriété shippingContainerSerialCodeReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingContainerSerialCodeReference() {
        return shippingContainerSerialCodeReference;
    }

    /**
     * Définit la valeur de la propriété shippingContainerSerialCodeReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingContainerSerialCodeReference(String value) {
        this.shippingContainerSerialCodeReference = value;
    }

    /**
     * Obtient la valeur de la propriété packageID.
     * 
     * @return
     *     possible object is
     *     {@link PackageID }
     *     
     */
    public PackageID getPackageID() {
        return packageID;
    }

    /**
     * Définit la valeur de la propriété packageID.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageID }
     *     
     */
    public void setPackageID(PackageID value) {
        this.packageID = value;
    }

    /**
     * Gets the value of the shippingMark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shippingMark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShippingMark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShippingMark }
     * 
     * 
     */
    public List<ShippingMark> getShippingMark() {
        if (shippingMark == null) {
            shippingMark = new ArrayList<ShippingMark>();
        }
        return this.shippingMark;
    }

    /**
     * Obtient la valeur de la propriété orderedQuantity.
     * 
     * @return
     *     possible object is
     *     {@link OrderedQuantity }
     *     
     */
    public OrderedQuantity getOrderedQuantity() {
        return orderedQuantity;
    }

    /**
     * Définit la valeur de la propriété orderedQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderedQuantity }
     *     
     */
    public void setOrderedQuantity(OrderedQuantity value) {
        this.orderedQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété dispatchQuantity.
     * 
     * @return
     *     possible object is
     *     {@link DispatchQuantity }
     *     
     */
    public DispatchQuantity getDispatchQuantity() {
        return dispatchQuantity;
    }

    /**
     * Définit la valeur de la propriété dispatchQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link DispatchQuantity }
     *     
     */
    public void setDispatchQuantity(DispatchQuantity value) {
        this.dispatchQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété freeGoodsQuantity.
     * 
     * @return
     *     possible object is
     *     {@link FreeGoodsQuantity }
     *     
     */
    public FreeGoodsQuantity getFreeGoodsQuantity() {
        return freeGoodsQuantity;
    }

    /**
     * Définit la valeur de la propriété freeGoodsQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeGoodsQuantity }
     *     
     */
    public void setFreeGoodsQuantity(FreeGoodsQuantity value) {
        this.freeGoodsQuantity = value;
    }

    /**
     * Obtient la valeur de la propriété quantityVarianceNote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantityVarianceNote() {
        return quantityVarianceNote;
    }

    /**
     * Définit la valeur de la propriété quantityVarianceNote.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantityVarianceNote(String value) {
        this.quantityVarianceNote = value;
    }

    /**
     * Obtient la valeur de la propriété bestBeforeDate.
     * 
     * @return
     *     possible object is
     *     {@link BestBeforeDate }
     *     
     */
    public BestBeforeDate getBestBeforeDate() {
        return bestBeforeDate;
    }

    /**
     * Définit la valeur de la propriété bestBeforeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link BestBeforeDate }
     *     
     */
    public void setBestBeforeDate(BestBeforeDate value) {
        this.bestBeforeDate = value;
    }

    /**
     * Gets the value of the assetInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetInfo }
     * 
     * 
     */
    public List<AssetInfo> getAssetInfo() {
        if (assetInfo == null) {
            assetInfo = new ArrayList<AssetInfo>();
        }
        return this.assetInfo;
    }

    /**
     * Obtient la valeur de la propriété storeCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * Définit la valeur de la propriété storeCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreCode(String value) {
        this.storeCode = value;
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

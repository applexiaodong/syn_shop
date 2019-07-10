package com.dong.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

public class EcOrderInfo {
    private BigDecimal orderId;

    private String mbrRemark;

    private String invoiceTitle;

    private BigDecimal addrProvinceId;

    private BigDecimal addrCityId;

    private BigDecimal addrDistrictId;

    private String addrPostcode;

    private String addrAddress;

    private String contactName;

    private String contactTel;

    private String sellerRemark;

    private String discountReason;

    private BigDecimal logiCompanyId;

    private String logiSn;

    private String logiContact;

    private Date deliveryTime;

    public BigDecimal getOrderId() {
        return orderId;
    }

    public void setOrderId(BigDecimal orderId) {
        this.orderId = orderId;
    }

    public String getMbrRemark() {
        return mbrRemark;
    }

    public void setMbrRemark(String mbrRemark) {
        this.mbrRemark = mbrRemark == null ? null : mbrRemark.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public BigDecimal getAddrProvinceId() {
        return addrProvinceId;
    }

    public void setAddrProvinceId(BigDecimal addrProvinceId) {
        this.addrProvinceId = addrProvinceId;
    }

    public BigDecimal getAddrCityId() {
        return addrCityId;
    }

    public void setAddrCityId(BigDecimal addrCityId) {
        this.addrCityId = addrCityId;
    }

    public BigDecimal getAddrDistrictId() {
        return addrDistrictId;
    }

    public void setAddrDistrictId(BigDecimal addrDistrictId) {
        this.addrDistrictId = addrDistrictId;
    }

    public String getAddrPostcode() {
        return addrPostcode;
    }

    public void setAddrPostcode(String addrPostcode) {
        this.addrPostcode = addrPostcode == null ? null : addrPostcode.trim();
    }

    public String getAddrAddress() {
        return addrAddress;
    }

    public void setAddrAddress(String addrAddress) {
        this.addrAddress = addrAddress == null ? null : addrAddress.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark == null ? null : sellerRemark.trim();
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason == null ? null : discountReason.trim();
    }

    public BigDecimal getLogiCompanyId() {
        return logiCompanyId;
    }

    public void setLogiCompanyId(BigDecimal logiCompanyId) {
        this.logiCompanyId = logiCompanyId;
    }

    public String getLogiSn() {
        return logiSn;
    }

    public void setLogiSn(String logiSn) {
        this.logiSn = logiSn == null ? null : logiSn.trim();
    }

    public String getLogiContact() {
        return logiContact;
    }

    public void setLogiContact(String logiContact) {
        this.logiContact = logiContact == null ? null : logiContact.trim();
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
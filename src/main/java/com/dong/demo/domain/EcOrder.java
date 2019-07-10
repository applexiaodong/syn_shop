package com.dong.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

public class EcOrder {
    private BigDecimal orderId;

    private BigDecimal mbrId;

    private BigDecimal shopId;

    private String orderNo;

    private Date orderTime;

    private Long orderStatus;

    private BigDecimal deliveryTypeId;

    private Short isInvoiceRequired;

    private BigDecimal goodsSumAmount;

    private BigDecimal goodsPromoAmount;

    private BigDecimal deliveryFeeAmount;

    private Short isFreeShipping;

    private BigDecimal shopCashCouponDiffAmount;

    private BigDecimal shopBalanceDiffAmount;

    private BigDecimal shopCreditsDiffAmount;

    private BigDecimal totalAmount;

    private BigDecimal payTypeId;

    private Date payTime;

    private BigDecimal paidAmount;

    private Short payStatus;

    private Date receiveTime;

    private BigDecimal srcId;

    private BigDecimal payId;

    private BigDecimal pCashCouponDiffAmount;

    private Long pPointCount;

    private BigDecimal pPointDiffAmount;

    private BigDecimal pBalanceDiffAmount;

    private BigDecimal pCreditsDiffAmount;

    private BigDecimal pDiffAmount;

    private BigDecimal payableAmount;

    private Short isPointProduced;

    private Date pointProduceTime;

    private Short isSync;

    private String sapNo;

    private Short orderType;

    private BigDecimal cashCouponId;

    private Long orderRefundStatus;

    private Date payExpireTime;

    private BigDecimal distriShopId;

    private BigDecimal productLineId;

    private String orderMode;

    public BigDecimal getOrderId() {
        return orderId;
    }

    public void setOrderId(BigDecimal orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getMbrId() {
        return mbrId;
    }

    public void setMbrId(BigDecimal mbrId) {
        this.mbrId = mbrId;
    }

    public BigDecimal getShopId() {
        return shopId;
    }

    public void setShopId(BigDecimal shopId) {
        this.shopId = shopId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(BigDecimal deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public Short getIsInvoiceRequired() {
        return isInvoiceRequired;
    }

    public void setIsInvoiceRequired(Short isInvoiceRequired) {
        this.isInvoiceRequired = isInvoiceRequired;
    }

    public BigDecimal getGoodsSumAmount() {
        return goodsSumAmount;
    }

    public void setGoodsSumAmount(BigDecimal goodsSumAmount) {
        this.goodsSumAmount = goodsSumAmount;
    }

    public BigDecimal getGoodsPromoAmount() {
        return goodsPromoAmount;
    }

    public void setGoodsPromoAmount(BigDecimal goodsPromoAmount) {
        this.goodsPromoAmount = goodsPromoAmount;
    }

    public BigDecimal getDeliveryFeeAmount() {
        return deliveryFeeAmount;
    }

    public void setDeliveryFeeAmount(BigDecimal deliveryFeeAmount) {
        this.deliveryFeeAmount = deliveryFeeAmount;
    }

    public Short getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Short isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public BigDecimal getShopCashCouponDiffAmount() {
        return shopCashCouponDiffAmount;
    }

    public void setShopCashCouponDiffAmount(BigDecimal shopCashCouponDiffAmount) {
        this.shopCashCouponDiffAmount = shopCashCouponDiffAmount;
    }

    public BigDecimal getShopBalanceDiffAmount() {
        return shopBalanceDiffAmount;
    }

    public void setShopBalanceDiffAmount(BigDecimal shopBalanceDiffAmount) {
        this.shopBalanceDiffAmount = shopBalanceDiffAmount;
    }

    public BigDecimal getShopCreditsDiffAmount() {
        return shopCreditsDiffAmount;
    }

    public void setShopCreditsDiffAmount(BigDecimal shopCreditsDiffAmount) {
        this.shopCreditsDiffAmount = shopCreditsDiffAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(BigDecimal payTypeId) {
        this.payTypeId = payTypeId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Short getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Short payStatus) {
        this.payStatus = payStatus;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public BigDecimal getSrcId() {
        return srcId;
    }

    public void setSrcId(BigDecimal srcId) {
        this.srcId = srcId;
    }

    public BigDecimal getPayId() {
        return payId;
    }

    public void setPayId(BigDecimal payId) {
        this.payId = payId;
    }

    public BigDecimal getpCashCouponDiffAmount() {
        return pCashCouponDiffAmount;
    }

    public void setpCashCouponDiffAmount(BigDecimal pCashCouponDiffAmount) {
        this.pCashCouponDiffAmount = pCashCouponDiffAmount;
    }

    public Long getpPointCount() {
        return pPointCount;
    }

    public void setpPointCount(Long pPointCount) {
        this.pPointCount = pPointCount;
    }

    public BigDecimal getpPointDiffAmount() {
        return pPointDiffAmount;
    }

    public void setpPointDiffAmount(BigDecimal pPointDiffAmount) {
        this.pPointDiffAmount = pPointDiffAmount;
    }

    public BigDecimal getpBalanceDiffAmount() {
        return pBalanceDiffAmount;
    }

    public void setpBalanceDiffAmount(BigDecimal pBalanceDiffAmount) {
        this.pBalanceDiffAmount = pBalanceDiffAmount;
    }

    public BigDecimal getpCreditsDiffAmount() {
        return pCreditsDiffAmount;
    }

    public void setpCreditsDiffAmount(BigDecimal pCreditsDiffAmount) {
        this.pCreditsDiffAmount = pCreditsDiffAmount;
    }

    public BigDecimal getpDiffAmount() {
        return pDiffAmount;
    }

    public void setpDiffAmount(BigDecimal pDiffAmount) {
        this.pDiffAmount = pDiffAmount;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Short getIsPointProduced() {
        return isPointProduced;
    }

    public void setIsPointProduced(Short isPointProduced) {
        this.isPointProduced = isPointProduced;
    }

    public Date getPointProduceTime() {
        return pointProduceTime;
    }

    public void setPointProduceTime(Date pointProduceTime) {
        this.pointProduceTime = pointProduceTime;
    }

    public Short getIsSync() {
        return isSync;
    }

    public void setIsSync(Short isSync) {
        this.isSync = isSync;
    }

    public String getSapNo() {
        return sapNo;
    }

    public void setSapNo(String sapNo) {
        this.sapNo = sapNo == null ? null : sapNo.trim();
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getCashCouponId() {
        return cashCouponId;
    }

    public void setCashCouponId(BigDecimal cashCouponId) {
        this.cashCouponId = cashCouponId;
    }

    public Long getOrderRefundStatus() {
        return orderRefundStatus;
    }

    public void setOrderRefundStatus(Long orderRefundStatus) {
        this.orderRefundStatus = orderRefundStatus;
    }

    public Date getPayExpireTime() {
        return payExpireTime;
    }

    public void setPayExpireTime(Date payExpireTime) {
        this.payExpireTime = payExpireTime;
    }

    public BigDecimal getDistriShopId() {
        return distriShopId;
    }

    public void setDistriShopId(BigDecimal distriShopId) {
        this.distriShopId = distriShopId;
    }

    public BigDecimal getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(BigDecimal productLineId) {
        this.productLineId = productLineId;
    }

    public String getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode == null ? null : orderMode.trim();
    }
}
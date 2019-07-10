package com.dong.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

public class EcCat {
    private BigDecimal catId;

    private BigDecimal parentCatId;

    private String catName;

    private String catIdPath;

    private String catNamePath;

    private String remark;

    private Long orderNum;

    private Short isLeaf;

    private Short lvl;

    private Long buyType;

    private Short isUse;

    private Short isAudit;

    private BigDecimal createShopId;

    private Date createDate;

    public BigDecimal getCatId() {
        return catId;
    }

    public void setCatId(BigDecimal catId) {
        this.catId = catId;
    }

    public BigDecimal getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(BigDecimal parentCatId) {
        this.parentCatId = parentCatId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public String getCatIdPath() {
        return catIdPath;
    }

    public void setCatIdPath(String catIdPath) {
        this.catIdPath = catIdPath == null ? null : catIdPath.trim();
    }

    public String getCatNamePath() {
        return catNamePath;
    }

    public void setCatNamePath(String catNamePath) {
        this.catNamePath = catNamePath == null ? null : catNamePath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Short getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Short isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Short getLvl() {
        return lvl;
    }

    public void setLvl(Short lvl) {
        this.lvl = lvl;
    }

    public Long getBuyType() {
        return buyType;
    }

    public void setBuyType(Long buyType) {
        this.buyType = buyType;
    }

    public Short getIsUse() {
        return isUse;
    }

    public void setIsUse(Short isUse) {
        this.isUse = isUse;
    }

    public Short getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Short isAudit) {
        this.isAudit = isAudit;
    }

    public BigDecimal getCreateShopId() {
        return createShopId;
    }

    public void setCreateShopId(BigDecimal createShopId) {
        this.createShopId = createShopId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
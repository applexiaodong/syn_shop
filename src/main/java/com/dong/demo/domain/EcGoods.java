package com.dong.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

public class EcGoods {
    private BigDecimal goodsId;

    private BigDecimal catId;

    private BigDecimal brandId;

    private BigDecimal shopId;

    private BigDecimal goodsTypeId;

    private String goodsIdPath;

    private String catIdPath;

    private String goodsName;

    private BigDecimal maxPrice;

    private BigDecimal minPrice;

    private Long stockQty;

    private BigDecimal dlvTmplId;

    private Short isRecommended;

    private Short isPlatformRecommended;

    private Long orderNum;

    private String iconUrl;

    private String goodsNo;

    private BigDecimal provinceId;

    private BigDecimal cityId;

    private BigDecimal districtId;

    private Date createTime;

    private Date pubTime;

    private BigDecimal stPop;

    private BigDecimal stSaleCnt;

    private BigDecimal stRank;

    private String remark;

    private Short goodsStatus;

    private Short stRankService;

    private Long prodYear;

    private BigDecimal goodsWeight;

    private BigDecimal goodsVolumn;

    private Date stUpdateTime;

    private BigDecimal stSaleMonthCnt;

    private Short isDivide;

    private String goodsTag;

    private BigDecimal buyoutPrice;

    private BigDecimal dipositPrice;

    private Long buyType;

    private BigDecimal productLineId;

    private BigDecimal usedDegree;

    private BigDecimal usedYears;

    private Short isRent;

    private Short isPurchase;

    private BigDecimal hours;

    private String goodsTopTag;

    private String divideCount;

    private String downPayment;

    private String partInfo;

    public BigDecimal getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(BigDecimal goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getCatId() {
        return catId;
    }

    public void setCatId(BigDecimal catId) {
        this.catId = catId;
    }

    public BigDecimal getBrandId() {
        return brandId;
    }

    public void setBrandId(BigDecimal brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getShopId() {
        return shopId;
    }

    public void setShopId(BigDecimal shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(BigDecimal goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsIdPath() {
        return goodsIdPath;
    }

    public void setGoodsIdPath(String goodsIdPath) {
        this.goodsIdPath = goodsIdPath == null ? null : goodsIdPath.trim();
    }

    public String getCatIdPath() {
        return catIdPath;
    }

    public void setCatIdPath(String catIdPath) {
        this.catIdPath = catIdPath == null ? null : catIdPath.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public Long getStockQty() {
        return stockQty;
    }

    public void setStockQty(Long stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getDlvTmplId() {
        return dlvTmplId;
    }

    public void setDlvTmplId(BigDecimal dlvTmplId) {
        this.dlvTmplId = dlvTmplId;
    }

    public Short getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Short isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Short getIsPlatformRecommended() {
        return isPlatformRecommended;
    }

    public void setIsPlatformRecommended(Short isPlatformRecommended) {
        this.isPlatformRecommended = isPlatformRecommended;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public BigDecimal getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(BigDecimal provinceId) {
        this.provinceId = provinceId;
    }

    public BigDecimal getCityId() {
        return cityId;
    }

    public void setCityId(BigDecimal cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getDistrictId() {
        return districtId;
    }

    public void setDistrictId(BigDecimal districtId) {
        this.districtId = districtId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public BigDecimal getStPop() {
        return stPop;
    }

    public void setStPop(BigDecimal stPop) {
        this.stPop = stPop;
    }

    public BigDecimal getStSaleCnt() {
        return stSaleCnt;
    }

    public void setStSaleCnt(BigDecimal stSaleCnt) {
        this.stSaleCnt = stSaleCnt;
    }

    public BigDecimal getStRank() {
        return stRank;
    }

    public void setStRank(BigDecimal stRank) {
        this.stRank = stRank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Short goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Short getStRankService() {
        return stRankService;
    }

    public void setStRankService(Short stRankService) {
        this.stRankService = stRankService;
    }

    public Long getProdYear() {
        return prodYear;
    }

    public void setProdYear(Long prodYear) {
        this.prodYear = prodYear;
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getGoodsVolumn() {
        return goodsVolumn;
    }

    public void setGoodsVolumn(BigDecimal goodsVolumn) {
        this.goodsVolumn = goodsVolumn;
    }

    public Date getStUpdateTime() {
        return stUpdateTime;
    }

    public void setStUpdateTime(Date stUpdateTime) {
        this.stUpdateTime = stUpdateTime;
    }

    public BigDecimal getStSaleMonthCnt() {
        return stSaleMonthCnt;
    }

    public void setStSaleMonthCnt(BigDecimal stSaleMonthCnt) {
        this.stSaleMonthCnt = stSaleMonthCnt;
    }

    public Short getIsDivide() {
        return isDivide;
    }

    public void setIsDivide(Short isDivide) {
        this.isDivide = isDivide;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag == null ? null : goodsTag.trim();
    }

    public BigDecimal getBuyoutPrice() {
        return buyoutPrice;
    }

    public void setBuyoutPrice(BigDecimal buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }

    public BigDecimal getDipositPrice() {
        return dipositPrice;
    }

    public void setDipositPrice(BigDecimal dipositPrice) {
        this.dipositPrice = dipositPrice;
    }

    public Long getBuyType() {
        return buyType;
    }

    public void setBuyType(Long buyType) {
        this.buyType = buyType;
    }

    public BigDecimal getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(BigDecimal productLineId) {
        this.productLineId = productLineId;
    }

    public BigDecimal getUsedDegree() {
        return usedDegree;
    }

    public void setUsedDegree(BigDecimal usedDegree) {
        this.usedDegree = usedDegree;
    }

    public BigDecimal getUsedYears() {
        return usedYears;
    }

    public void setUsedYears(BigDecimal usedYears) {
        this.usedYears = usedYears;
    }

    public Short getIsRent() {
        return isRent;
    }

    public void setIsRent(Short isRent) {
        this.isRent = isRent;
    }

    public Short getIsPurchase() {
        return isPurchase;
    }

    public void setIsPurchase(Short isPurchase) {
        this.isPurchase = isPurchase;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public String getGoodsTopTag() {
        return goodsTopTag;
    }

    public void setGoodsTopTag(String goodsTopTag) {
        this.goodsTopTag = goodsTopTag == null ? null : goodsTopTag.trim();
    }

    public String getDivideCount() {
        return divideCount;
    }

    public void setDivideCount(String divideCount) {
        this.divideCount = divideCount == null ? null : divideCount.trim();
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment == null ? null : downPayment.trim();
    }

    public String getPartInfo() {
        return partInfo;
    }

    public void setPartInfo(String partInfo) {
        this.partInfo = partInfo == null ? null : partInfo.trim();
    }
}
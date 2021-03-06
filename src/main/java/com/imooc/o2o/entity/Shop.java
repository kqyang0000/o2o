package com.imooc.o2o.entity;

import java.util.Date;

/**
 * <p>商户表</p>
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/1/29 21:39
 */
public class Shop {
    /**
     * ID
     */
    private Long shopId;
    /**
     * 商铺名称
     */
    private String shopName;
    /**
     * 商铺描述
     */
    private String shopDesc;
    /**
     * 商铺具体地址
     */
    private String shopAddr;
    /**
     * 商铺联系方式
     */
    private String phone;
    /**
     * 商铺图片
     */
    private String shopImg;
    /**
     * 权重
     */
    private Integer priority;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastEditTime;
    /**
     * 商铺状态：-1:不可用 0:审核中 1:可用
     */
    private Integer enableStatus;
    /**
     * 超级管理员给商户的提醒
     */
    private String advice;
    /**
     * 所属区域
     */
    private Area area;
    /**
     * 所属用户
     */
    private PersonInfo owner;
    /**
     * 商铺种类
     */
    private ShopCategory shopCategory;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public PersonInfo getOwner() {
        return owner;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }
}

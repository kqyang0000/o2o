package com.imooc.o2o.entity;

import java.util.Date;

/**
 * <p>顾客消费的商品映射
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/5/28 22:47
 */
public class UserProductMap {
    /**
     * 主键id
     */
    private Long userProductId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 消费商品所获得的积分
     */
    private Integer point;
    /**
     * 顾客信息实体类
     */
    private PersonInfo user;
    /**
     * 商品信息实体类
     */
    private Product product;
    /**
     * 店铺信息实体类
     */
    private Shop shop;
    /**
     * 操作员信息实体类
     */
    private PersonInfo operator;

    public Long getUserProductId() {
        return userProductId;
    }

    public void setUserProductId(Long userProductId) {
        this.userProductId = userProductId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public PersonInfo getUser() {
        return user;
    }

    public void setUser(PersonInfo user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public PersonInfo getOperator() {
        return operator;
    }

    public void setOperator(PersonInfo operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "UserProductMap{" +
                "userProductId=" + userProductId +
                ", createTime=" + createTime +
                ", point=" + point +
                ", user=" + user +
                ", product=" + product +
                ", shop=" + shop +
                ", operator=" + operator +
                '}';
    }
}

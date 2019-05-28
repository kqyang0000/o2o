package com.imooc.o2o.entity;

import java.util.Date;

/**
 * <p>店铺商品日销量表
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/5/28 23:20
 */
public class ProductSellDaily {
    /**
     * 哪天的销量，精确到天
     */
    private Date createTime;
    /**
     * 销量
     */
    private Integer total;
    /**
     * 商品信息实体类
     */
    private Product product;
    /**
     * 店铺信息实体类
     */
    private Shop shop;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "ProductSellDaily{" +
                "createTime=" + createTime +
                ", total=" + total +
                ", product=" + product +
                ", shop=" + shop +
                '}';
    }
}

package com.imooc.o2o.dto;

import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    /**
     * 结果标识
     */
    private int state;
    /**
     * 状态标识
     */
    private String stateInfo;
    /**
     * productCategory 列表
     */
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {
    }

    /**
     * <p>操作失败的时候使用的构造器
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/3/19 13:56
     */
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * <p>操作成功的时候使用的构造器
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/3/19 13:59
     */
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}

package com.imooc.o2o.service;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    /**
     * <p>查询指定某个店铺下的所有商品类别信息
     *
     * @param shopId
     * @return 商品类别列表
     * @author kqyang
     * @version 1.0
     * @date 2019/3/18 19:28
     */
    List<ProductCategory> getProductCategoryList(long shopId);
}

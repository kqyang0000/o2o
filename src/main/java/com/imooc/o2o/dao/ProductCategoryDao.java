package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * <p>通过shopId 查询店铺商品类别
     *
     * @param shopId
     * @return 商品类别列表
     * @author kqyang
     * @version 1.0
     * @date 2019/3/18 19:06
     */
    List<ProductCategory> queryProductCategoryList(long shopId);
}

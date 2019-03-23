package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;

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

    /**
     * <p>批量添加商品分类
     *
     * @param productCategoryList
     * @return 批量添加商品分类状态
     * @throws ProductCategoryOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/3/19 13:49
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}

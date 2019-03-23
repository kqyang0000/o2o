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

    /**
     * <p>将此类别下的商品里的类别id 置为空，再删除掉该商品类别
     *
     * @param productCategoryId
     * @param shopId
     * @return 删除商品分类状态
     * @throws ProductCategoryOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/3/23 13:45
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;
}

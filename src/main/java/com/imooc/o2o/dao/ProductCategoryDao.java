package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

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

    /**
     * <p>批量新增商品类别
     *
     * @param productCategoryList
     * @return 新增影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/3/19 13:25
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * <p>删除指定商品类别
     *
     * @param productCategoryId
     * @param shopId
     * @return 删除影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/3/23 12:45
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}

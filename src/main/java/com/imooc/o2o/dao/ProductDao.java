package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * <p>插入商品
     *
     * @param product
     * @return 新增影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/3/24 13:12
     */
    int insertProduct(Product product);

    /**
     * <p>通过productId 查询唯一的商品信息
     *
     * @param productId
     * @return 商品信息
     * @author kqyang
     * @version 1.0
     * @date 2019/3/29 23:41
     */
    Product queryProductById(long productId);

    /**
     * <p>更新商品信息
     *
     * @param product
     * @return 修改影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/3/29 23:43
     */
    int updateProduct(Product product);

    /**
     * <p>查询商品列表并分页，可输入条件有：商品名（模糊）商品状态 店铺id 商品类别
     *
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return 商品列表
     * @author kqyang
     * @version 1.0
     * @date 2019/4/2 23:16
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * <p>查询对应的商品总数
     *
     * @param productCondition
     * @return 对应条件的商品总条数
     * @author kqyang
     * @version 1.0
     * @date 2019/4/2 23:21
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * <p>删除商品类别之前，将商品类别id 置为空
     *
     * @param productCategoryId
     * @return 删除影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/4/5 0:08
     */
    int updateProductCategoryToNull(long productCategoryId);
}

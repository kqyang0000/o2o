package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;

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
}

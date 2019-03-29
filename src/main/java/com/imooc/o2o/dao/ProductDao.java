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
}

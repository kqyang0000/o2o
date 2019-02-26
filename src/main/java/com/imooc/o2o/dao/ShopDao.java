package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {

    /**
     * <p>新增店铺
     *
     * @param shop
     * @return 插入数量
     * @author kqyang
     * @version 1.0
     * @date 2019/2/22 13:16
     */
    int insertShop(Shop shop);

    /**
     * <p>更新店铺
     *
     * @param shop
     * @return 更新数量
     * @author kqyang
     * @version 1.0
     * @date 2019/2/25 13:38
     */
    int updateShop(Shop shop);
}

package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * <p>通过shopId 查询店铺
     *
     * @param shopId
     * @return shop
     * @author kqyang
     * @version 1.0
     * @date 2019/3/13 19:25
     */
    Shop queryByShopId(long shopId);

    /**
     * <p>分页查询店铺，可以输入的条件有：商铺名称（模糊）、店铺状态、店铺类别、区域Id、owner
     * <p>注解@Param() 的作用：当参数多于1个时，需要用此注解指明参数名称，只有一个时不需要指明
     *
     * @param pageSize      返回的条数
     * @param rowIndex      从第几行开始取
     * @param shopCondition 查询的条件
     * @return 店铺列表
     * @author kqyang
     * @version 1.0
     * @date 2019/3/14 13:46
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * <p>获取店铺数量
     *
     * @param shopCondition 返回的条数
     * @return 店铺数量
     * @author kqyang
     * @version 1.0
     * @date 2019/3/14 19:15
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}

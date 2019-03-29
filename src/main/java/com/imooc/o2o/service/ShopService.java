package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;


public interface ShopService {
    /**
     * <p>添加商铺
     *
     * @param shop
     * @param shopImg 商铺图片
     * @return 商铺添加状态
     * @throws ShopOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 19:31
     */
    ShopExecution addShop(Shop shop, ImageHolder shopImg) throws ShopOperationException;

    /**
     * <p>通过店铺Id 获取店铺信息
     *
     * @param shopId
     * @return shop
     * @author kqyang
     * @version 1.0
     * @date 2019/3/13 20:01
     */
    Shop getByShopId(long shopId);

    /**
     * <p>更新店铺信息，包括对图片的处理
     *
     * @param shop
     * @param shopImg 商铺图片
     * @return 商铺更新状态
     * @throws ShopOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/3/13 20:02
     */
    ShopExecution modifyShop(Shop shop, ImageHolder shopImg) throws ShopOperationException;

    /**
     * <p>根据shopCondition 分页返回相应店铺列表
     *
     * @param pageSize      返回的条数
     * @param pageIndex     从第几行开始取
     * @param shopCondition 查询的条件
     * @author kqyang
     * @version 1.0
     * @date 2019/3/14 19:28
     */
    ShopExecution queryShopList(Shop shopCondition, int pageIndex, int pageSize);
}

package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;


public interface ShopService {
    /**
     * <p>添加商铺
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return 商铺添加状态
     * @throws ShopOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 19:31
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

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
     * @param shopImgInputStream
     * @param fileName
     * @return 商铺更新状态
     * @throws ShopOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/3/13 20:02
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}

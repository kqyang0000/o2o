package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ShopService {
    /**
     * <p>添加商铺
     *
     * @param shop
     * @param shopImg
     * @return 商铺添加状态
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 19:31
     */
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}

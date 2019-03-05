package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    /**
     * <p>spring 要想事物回滚，必须抛出ShopOperationException或其子类的异常，否则事物不会回滚
     * <p>对于java的所有方法来说，参数的传递都是以值传递的方式，参数是基本类型的传递是当前基
     * 本类型的拷贝，所以基本类型参数的改变不会影响参数的值。如果参数是引用类型的话，则传递的是
     * 堆该对象堆内存中引用的拷贝，所以对该对象改变会影响参数的值
     *
     * @param shop
     * @param shopImg
     * @return
     */
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        // 空值判断
        if (null == shop) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            // 赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(shop.getCreateTime());
            // 添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (null != shopImg) {
                    try {
                        // 存储图片
                        //addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    // 更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnai(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}

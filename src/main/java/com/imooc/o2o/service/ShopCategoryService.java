package com.imooc.o2o.service;

import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.exceptions.ShopCategoryOperationException;

import java.util.List;

public interface ShopCategoryService {
    String SHOPCATEGORYLIST = "shopcategorylist";

    /**
     * <p>根据传入条件获取商品类别列表
     *
     * @param shopCategoryCondition
     * @return 商品类别列表
     * @throws ShopCategoryOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/4/11 20:08
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) throws ShopCategoryOperationException;
}

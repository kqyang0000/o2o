package com.imocc.o2o.dao;

import com.imocc.o2o.BaseTest;
import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
        Assert.assertEquals(1, shopCategoryList.size());

        ShopCategory shopCategory = new ShopCategory();
        ShopCategory shopCategoryParent = new ShopCategory();
        shopCategoryParent.setShopCategoryId(1L);
        shopCategory.setParent(shopCategoryParent);
        shopCategoryList = shopCategoryDao.queryShopCategory(shopCategory);
        Assert.assertEquals(1, shopCategoryList.size());
    }
}

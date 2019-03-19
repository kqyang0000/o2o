package com.imocc.o2o.dao;

import com.imocc.o2o.BaseTest;
import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryProductCategoryList() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺商品类别数为：" + productCategoryList.size());
    }

}

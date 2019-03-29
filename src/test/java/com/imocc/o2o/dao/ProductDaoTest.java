package com.imocc.o2o.dao;

import com.imocc.o2o.BaseTest;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void testAInsertProduct() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(4L);
        Product product = new Product();
        product.setProductName("测试1");
        product.setProductDesc("测试Desc1");
        product.setImgAddr("test1");
        product.setNormalPrice("100");
        product.setPriority(1);
        product.setEnableStatus(1);
        product.setCreateTime(new Date());
        product.setLastEditTime(product.getCreateTime());
        product.setPromotionPrice("90");
        product.setShop(shop);
        product.setProductCategory(productCategory);
        int effectedNum = productDao.insertProduct(product);
        Assert.assertEquals(1, effectedNum);
    }
}

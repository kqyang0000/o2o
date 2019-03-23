package com.imocc.o2o.dao;

import com.imocc.o2o.BaseTest;
import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>@FixMethodOrder 注解：junit 默认的测试执行顺序是随机的，所以此注解的作用就是让单元测试按我们设定的顺序执行
 * <p>此注解有三种执行顺序：1）MethodSorters.JVM  按照我们的JVM 的类加载顺序执行（一般不常用）
 *                      2）MethodSorters.NAME_ASCENDING 按照方法的名字去执行
 *                      3）MethodSorters.DEFAULT 默认
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/3/23 13:34
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Ignore
    public void testBQueryProductCategoryList() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺商品类别数为：" + productCategoryList.size());
    }

    @Test
    @Ignore
    public void testABatchInsertProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("商品类别1");
        productCategory1.setPriority(1);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(1L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别2");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategoryList = new ArrayList<>(32);
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        Assert.assertEquals(2, effectedNum);
    }

    @Test
    public void testCDeleteProductCategory() {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory pc : productCategoryList) {
            if ("商品类别11".equals(pc.getProductCategoryName()) || "商品类别12".equals(pc.getProductCategoryName())
                    || "商品类别13".equals(pc.getProductCategoryName())) {
                int effectNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
                Assert.assertEquals(1, effectNum);
            }
        }
    }
}

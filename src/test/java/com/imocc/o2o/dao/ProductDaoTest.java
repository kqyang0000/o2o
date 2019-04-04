package com.imocc.o2o.dao;

import com.imocc.o2o.BaseTest;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    @Ignore
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

    @Test
    @Ignore
    public void testBQueryProductByProductId() {
        long productId = 1L;
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片三");
        productImg1.setImgDesc("测试图片三");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(productId);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片四");
        productImg2.setImgDesc("测试图片四");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(productId);
        List<ProductImg> productImgList = new ArrayList<>(32);
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        Assert.assertEquals(2, effectedNum);
        Product product = productDao.queryProductById(productId);
        Assert.assertEquals(8, product.getProductImgList().size());
    }

    @Test
    @Ignore
    public void testDUpdateProduct() {
        long productId = 1L;
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(1L);
        productCategory.setProductCategoryId(5L);
        product.setProductId(productId);
        product.setProductName("第二个商品");
        product.setShop(shop);
        product.setProductCategory(productCategory);
        int effectNum = productDao.updateProduct(product);
        Assert.assertEquals(1, effectNum);
    }

    @Test
    public void testCQueryProductList(){
        Product productCondition = new Product();
        List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
        Assert.assertEquals(3, productList.size());
        productCondition.setProductName("测试1");
        List<Product> productList2 = productDao.queryProductList(productCondition, 0, 3);
        Assert.assertEquals(2, productList2.size());
    }
}

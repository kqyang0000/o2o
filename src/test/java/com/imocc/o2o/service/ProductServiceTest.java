package com.imocc.o2o.service;

import com.imocc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.service.ProductService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAAddProduct() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(4L);
        Product product = new Product();
        product.setProductName("测试商品1");
        product.setProductDesc("商品详情1");
        product.setPriority(10);
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        File file = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\xiaohuangren.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(), fileInputStream);
        File file1 = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\login_1.jpg");
        FileInputStream fileInputStream1 = new FileInputStream(file1);
        ImageHolder imageHolder1 = new ImageHolder(file1.getName(), fileInputStream1);
        File file2 = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\login_2.jpg");
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        ImageHolder imageHolder2 = new ImageHolder(file2.getName(), fileInputStream2);
        List<ImageHolder> imageList = new ArrayList<>(32);
        imageList.add(imageHolder1);
        imageList.add(imageHolder2);
        ProductExecution pe = productService.addProduct(product, imageHolder, imageList);
        Assert.assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }
}

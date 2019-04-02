package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    /**
     * <p>添加商品信息及图片处理
     *
     * @param product
     * @param thumbnail      缩略图文件
     * @param productImgList 商品详情图片文件集合
     * @return 商品添加状态
     * @throws ProductOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/3/24 23:50
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;

    /**
     * <p>通过商品id 获取唯一商品信息
     *
     * @param productId
     * @return 商品信息
     * @author kqyang
     * @version 1.0
     * @date 2019/3/30 22:45
     */
    Product getProductById(long productId);

    /**
     * <p>修改商品信息及图片处理
     *
     * @param product
     * @param imageHolder
     * @param productImgList
     * @return 修改执行结果信息
     * @throws ProductOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/3/30 22:47
     */
    ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> productImgList)
            throws ProductOperationException;
}
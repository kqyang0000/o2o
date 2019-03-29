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
}

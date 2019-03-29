package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    /**
     * 1.处理缩略图，获取缩略图相对路径并赋值给product
     * 2.往tb_product 写入商品信息，并获取productId
     * 3.结合productId 批量处理商品详情图
     * 4.将商品详情图列表批量插入tb_product_img 中
     *
     * @param product
     * @param thumbnail      缩略图文件
     * @param productImgList 商品详情图片文件集合
     * @throws ProductOperationException
     */
    @Transactional
    @Override
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException {
        // 空值判断
        if (null != product && null != product.getShop() && null != product.getShop().getShopId()) {
            // 给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(product.getCreateTime());
            product.setEnableStatus(1);
            // 如果商品缩略图不为空则添加
            if (null != thumbnail) {
                addThumbnail(product, thumbnail);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("添加商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("添加商品失败" + e.toString());
            }
            // 如果商品详情图列表不为空则添加
            if (null != productImgList && productImgList.size() > 0) {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            // 如果传参为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.PARAM_EMPTY);
        }
    }

    /**
     * 添加商品缩略图
     *
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String desc = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnai(thumbnail, desc);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * 批量添加商品图片
     *
     * @param product
     * @param productImgHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String desc = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>(32);
        for (ImageHolder imageHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder, desc);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败：" + e.toString());
            }
        }
    }
}

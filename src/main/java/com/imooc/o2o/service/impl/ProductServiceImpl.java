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
import com.imooc.o2o.util.PageCalculator;
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

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    /**
     * 1. 若缩略图桉树有值，则处理缩略图；若原先存在缩略图则先删除再添加新图，之后获取缩略图路径赋值给product
     * 2. 若商品详情图列表参数有值，对商品详情图列表进行同样操作
     * 3. 将tb_product_img 下面的该商品详情图记录全部删除
     * 4. 更新tb_product 的信息
     *
     * @param product
     * @param imageHolder
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    @Transactional
    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> productImgList)
            throws ProductOperationException {
        // 空值判断
        if (null != product && null != product.getShop() && null != product.getShop().getShopId()) {
            // 给商品设置默认属性
            product.setLastEditTime(new Date());
            // 若商品缩略图不等于空且原有缩略图不等于空则删除原有缩略图并添加新图
            if (null != imageHolder) {
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (null != tempProduct.getImgAddr()) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, imageHolder);
            }
            // 如果有新存入的商品详情图，则将原来的删除，并添加你的图片
            if (null != productImgList && productImgList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgList);
            }
            try {
                // 更新商品信息失败
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.PARAM_EMPTY);
        }
    }

    /**
     * <p>删除某个商品下的所有详情图
     *
     * @param productId
     * @author kqyang
     * @version 1.0
     * @date 2019/3/30 23:11
     */
    private void deleteProductImgList(long productId) {
        // 根据productId 获取原来的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        // 删掉原来的图片
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        // 删除数据库里原有的的所有图片信息
        productImgDao.deleteProductImgByProductId(productId);
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        // 页码转换成数据库的行码，并调用dao层取回指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        // 基于同样的查询条件返回该查询条件下的商品总数
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }
}

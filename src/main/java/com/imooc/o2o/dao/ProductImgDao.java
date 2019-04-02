package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    /**
     * <p>批量添加商品详情图片
     *
     * @param productImgList
     * @return 批量新增影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/3/24 13:14
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * <p>删除指定商品下的商品详情图
     *
     * @param productId
     * @return 删除影响的行数
     * @author kqyang
     * @version 1.0
     * @date 2019/3/29 23:44
     */
    int deleteProductImgByProductId(long productId);

    /**
     * <p>查询对应product_id 下的所有商品详情图信息
     *
     * @param productId
     * @return 商品详情图列表
     * @author kqyang
     * @version 1.0
     * @date 2019/3/30 23:17
     */
    List<ProductImg> queryProductImgList(long productId);
}

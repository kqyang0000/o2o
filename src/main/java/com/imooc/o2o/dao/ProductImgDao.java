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
}

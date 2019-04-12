package com.imooc.o2o.dao;

import com.imooc.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {

    /**
     * <p>根据传入的查询条件（头条名查头条）
     *
     * @param headLineCondition
     * @return 头条列表
     * @author kqyang
     * @version 1.0
     * @date 2019/4/11 19:41
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}

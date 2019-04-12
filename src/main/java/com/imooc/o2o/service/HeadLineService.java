package com.imooc.o2o.service;

import com.imooc.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {
    /**
     * <p>根据传入的条件返回指定的头条列表
     *
     * @param headLineCondition
     * @return 头条列表
     * @throws IOException
     * @author kqyang
     * @version 1.0
     * @date 2019/4/11 20:06
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}

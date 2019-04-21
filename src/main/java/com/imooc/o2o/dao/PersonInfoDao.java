package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;

public interface PersonInfoDao {

    /**
     * <p>通过用户id 查询用户
     *
     * @param userId
     * @return PersonInfo
     * @author kqyang
     * @version 1.0
     * @date 2019/4/21 22:54
     */
    PersonInfo queryPersonInfoById(long userId);

    /**
     * <p>添加用户信息
     *
     * @param personInfo
     * @return 添加影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/4/21 22:55
     */
    int insertPersonInfo(PersonInfo personInfo);
}

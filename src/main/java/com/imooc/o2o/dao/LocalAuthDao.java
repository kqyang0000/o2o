package com.imooc.o2o.dao;

import com.imooc.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LocalAuthDao {
    /**
     * <p>通过账号和密码查询对应信息，登录用
     *
     * @param username
     * @param password
     * @return 本地用户信息
     * @author kqyang
     * @version 1.0
     * @date 2019/4/28 20:58
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * <p>通过用户id查询对应的LocalAuth
     *
     * @param userId
     * @return 本地用户信息
     * @author kqyang
     * @version 1.0
     * @date 2019/4/28 21:01
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    /**
     * <p>添加平台账号
     *
     * @param localAuth
     * @return 操作影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/4/28 21:03
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * <p>通过userId,username,password更改密码
     *
     * @param userId
     * @param userName
     * @param password
     * @param newPassword
     * @param lastEditTime
     * @return 操作影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/4/28 21:06
     */
    int updateLocalAuth(@Param("userId") Long userId, @Param("userName") String userName, @Param("password") String password,
                        @Param("newPassword") String newPassword, @Param("lastEditTime") Date lastEditTime);
}

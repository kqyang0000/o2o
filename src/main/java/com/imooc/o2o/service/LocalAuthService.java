package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
    /**
     * <p>通过账号和密码获取平台账号信息
     *
     * @param userName
     * @param password
     * @return 账号信息
     * @author kqyang
     * @version 1.0
     * @date 2019/4/29 20:53
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    /**
     * <p>通过userId获取平台账号信息
     *
     * @param userId
     * @return 账号信息
     * @author kqyang
     * @version 1.0
     * @date 2019/4/29 20:55
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * <p>绑定微信，生成平台专属的账号
     *
     * @param localAuth
     * @return 操作执行结果
     * @throws LocalAuthOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/4/29 21:01
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
     * <p>修改平台账号信息
     *
     * @param userId
     * @param userName
     * @param password
     * @param newPassword
     * @return 操作执行结果
     * @throws LocalAuthOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/4/29 21:05
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword) throws LocalAuthOperationException;
}

package com.imooc.o2o.service;

import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.exceptions.WechatAuthOperationException;

public interface WechatAuthService {

    /**
     * <p>通过openId 查找平台对应的微信账号
     *
     * @param openId
     * @return WechatAuth
     * @author kqyang
     * @version 1.0
     * @date 2019/4/21 23:37
     */
    WechatAuth getWechatAuthByOpenId(String openId);

    /**
     * <p>注册本平台的微信账号
     *
     * @param wechatAuth
     * @return 接口执行结果信息
     * @throws WechatAuthOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/4/21 23:40
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}

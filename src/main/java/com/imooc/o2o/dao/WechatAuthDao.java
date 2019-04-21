package com.imooc.o2o.dao;

import com.imooc.o2o.entity.WechatAuth;

public interface WechatAuthDao {

    /**
     * <p>通过openId 查询对应本平台的微信账号
     *
     * @param openId
     * @return WechatAuth
     * @author kqyang
     * @version 1.0
     * @date 2019/4/21 22:57
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /**
     * <p>添加对应本平台的微信账号
     *
     * @param wechatAuth
     * @return 添加影响行数
     * @author kqyang
     * @version 1.0
     * @date 2019/4/21 22:58
     */
    int insertWechatAuth(WechatAuth wechatAuth);
}

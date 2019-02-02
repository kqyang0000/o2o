package com.imooc.o2o.entity;

import java.util.Date;

/**
 * <p>微信账号实体</p>
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/1/29 20:12
 */
public class WechatAuth {
    /**
     * 微信账号id
     */
    private Long wechatAuthId;
    /**
     * 微信公众号id
     */
    private String openId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户实体
     */
    private PersonInfo personInfo;


    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}

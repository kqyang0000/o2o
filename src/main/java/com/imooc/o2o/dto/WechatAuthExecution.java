package com.imooc.o2o.dto;

import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;

import java.util.List;

public class WechatAuthExecution {
    /**
     * 结果状态
     */
    private int state;
    /**
     * 状态标识
     */
    private String stateInfo;
    /**
     * 数量
     */
    private int count;
    /**
     * 操作的wechatAuth（增删改店铺的时候用到）
     */
    private WechatAuth wechatAuth;
    /**
     * wechatAuth列表（查询列表的时候使用）
     */
    private List<WechatAuth> wechatAuthList;

    public WechatAuthExecution() {
    }

    /**
     * <p>操作失败时候使用的的构造器
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 13:56
     */
    public WechatAuthExecution(WechatAuthStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * <p>操作成功时候使用的的构造器
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 13:57
     */
    public WechatAuthExecution(WechatAuthStateEnum stateEnum, WechatAuth wechatAuth) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.wechatAuth = wechatAuth;
    }

    /**
     * <p>操作成功时候使用的的构造器
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 14:25
     */
    public WechatAuthExecution(WechatAuthStateEnum stateEnum, List<WechatAuth> wechatAuthList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.wechatAuth = wechatAuth;
        this.wechatAuthList = wechatAuthList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WechatAuth getWechatAuth() {
        return wechatAuth;
    }

    public void setWechatAuth(WechatAuth wechatAuth) {
        this.wechatAuth = wechatAuth;
    }

    public List<WechatAuth> getWechatAuthList() {
        return wechatAuthList;
    }

    public void setWechatAuthList(List<WechatAuth> wechatAuthList) {
        this.wechatAuthList = wechatAuthList;
    }
}

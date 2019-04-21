package com.imooc.o2o.enums;

public enum WechatAuthStateEnum {
    LOGINFAIL(-1, "openId输入有误"),
    SUCCESS(0, "操作成功"),
    NULL_AUTH_INFO(2, "空的用户信息");

    private int state;
    private String stateInfo;

    private WechatAuthStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * <p>依据传入的state返回相应的enum值
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 13:52
     */
    public static WechatAuthStateEnum stateOf(int state) {
        for (WechatAuthStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}

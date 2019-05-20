package com.imooc.o2o.enums;

public enum LocalAuthStateEnum {
    SUCCESS(1, "操作成功"),
    NULL_AUTH_INFO(-1001, "账户信息为空"),
    ONLY_ONE_ACCOUNT(-1002, "用户已绑定过平台账号");

    private int state;
    private String stateInfo;

    private LocalAuthStateEnum(int state, String stateInfo) {
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
    public static LocalAuthStateEnum stateOf(int state) {
        for (LocalAuthStateEnum stateEnum : values()) {
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

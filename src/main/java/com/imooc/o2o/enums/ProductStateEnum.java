package com.imooc.o2o.enums;

public enum ProductStateEnum {
    SUCCESS(0, "操作成功"),
    FAIL(-1001, "操作失败"),
    PARAM_EMPTY(-1002, "参数为空");

    private int state;
    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ProductStateEnum stateOf(int state) {
        for (ProductStateEnum StateEnum : values()) {
            if (StateEnum.getState() == state) {
                return StateEnum;
            }
        }
        return null;
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
}

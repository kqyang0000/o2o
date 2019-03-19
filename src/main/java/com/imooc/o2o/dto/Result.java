package com.imooc.o2o.dto;

/**
 * <p>封装json 对象，所有返回结果都使用它
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/3/18 19:45
 */
public class Result<T> {
    /**
     * 是否成功标志
     */
    private boolean success;
    /**
     * 成功时返回的数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 错误码
     */
    private int errorCode;

    public Result() {
    }

    /**
     * 成功时的构造器
     *
     * @param success
     * @param data
     */
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /**
     * 错误时的构造器
     *
     * @param success
     * @param errorCode
     * @param errorMsg
     */
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

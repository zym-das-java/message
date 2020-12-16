package com.message.common;

public class ResultUtil {
    /**
     * 接口是否执行成功
     */
    private boolean success;
    /**
     * 结果描述
     */
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultUtil(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public ResultUtil() {
    }
    @Override
    public String toString() {
        return "BaseResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}

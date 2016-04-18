package com.offer.demo.pojo;

import java.io.Serializable;

/**
 * Created by lance on 4/18/16.
 */
public class APIResult<T> implements Serializable {
    private int code = -1;

    private String msg;

    private T data;

    private boolean success = false;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (code == 0) {
            success = true;
        }
        return success;
    }

    @Override
    public String toString() {
        return "APIResult{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

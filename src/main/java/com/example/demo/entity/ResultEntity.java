package com.example.demo.entity;

import com.example.demo.constants.ReturnCodeEnum;

public class ResultEntity<T> {

    private String code = ReturnCodeEnum.SUCCESS.getCode();

    private String desc = ReturnCodeEnum.SUCCESS.getDesc();

    private T data;

    public ResultEntity(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(ReturnCodeEnum returnCodeEnum) {
        this.code = returnCodeEnum.getCode();
        this.desc = returnCodeEnum.getDesc();
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}

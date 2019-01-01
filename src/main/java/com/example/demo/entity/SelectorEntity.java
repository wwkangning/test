package com.example.demo.entity;

/**
 * @author lij381
 * @date 2019/1/1 11:35
 * @description
 */
public class SelectorEntity {

    private String code;

    private String msg;

    public SelectorEntity(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

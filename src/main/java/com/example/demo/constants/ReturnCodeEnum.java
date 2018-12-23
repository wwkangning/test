package com.example.demo.constants;

public enum ReturnCodeEnum {

    SUCCESS("0000", "成功"),
    LOGIN_USER_NAME_EMPTY("0001", "用户名为空"),
    LOGIN_USER_NOT_EXIST("0002", "账号不存在"),
    LOGIN_USER_PWD_ERROR("0003", "密码不正确"),
    LOGIN_USER_VALIDATE_FAIL("0004", "用户验证失败"),
    LOGIN_VERIFY_CODE_ERROR("0005", "验证码错误"),

    ;

    private String code;

    private String desc;

    private ReturnCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
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
}

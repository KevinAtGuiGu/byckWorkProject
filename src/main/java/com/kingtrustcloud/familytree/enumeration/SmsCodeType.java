package com.kingtrustcloud.familytree.enumeration;

/**
 * 验证码类型
 */
public enum SmsCodeType implements BaseEnum<String> {

    REGISTER("register","注册"),
    FORGET_PASSWORD("forgetpassword","忘记密码"),
    MODIFYMYPHONE("modifyMyPhone","修改手机号"),
    MODIFYIDCARD("modifyIdCard","修改身份证");

    private String code;

    private String desc;

    SmsCodeType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}

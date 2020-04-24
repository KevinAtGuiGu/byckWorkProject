package com.kingtrustcloud.familytree.enumeration;

public enum SysRoleEnum implements BaseEnum<String> {
    SUPER_MANAGE("超级管理员","超级管理员"),
    BRANCH_MANAGE("分支管理员","分支管理员"),
    USER("注册用户","注册用户")
    ;

    private String code;
    private String desc;

    SysRoleEnum(String code,String desc) {
        this.code=code;
        this.desc=desc;
    }
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

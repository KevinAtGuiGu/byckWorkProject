package com.kingtrustcloud.familytree.enumeration;

/**
 * @author kevin
 * @create 2020-03-16 10:37
 * @Description:
 */
public enum FamilyCode implements BaseEnum<Integer> {
    /**
     *   田氏族谱代码
     */
    TIAN_SHI_FAMILY_CODE(1001, "田氏族谱");


    private Integer code;

    private String desc;

    FamilyCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }}

package com.kingtrustcloud.familytree.enumeration;

/**
 * @author kevin
 * @create 2020-03-16 11:41
 * @Description:
 */
public enum FamilyBranchCode implements BaseEnum<Integer> {;




    private Integer code;

    private String desc;

    FamilyBranchCode(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }}

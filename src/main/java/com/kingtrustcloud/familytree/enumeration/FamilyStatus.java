package com.kingtrustcloud.familytree.enumeration;

/**
 * @author kevin
 * @create 2020-03-16 10:53
 * @Description:
 */
public enum  FamilyStatus implements BaseEnum<Integer> {
    /**
     * 申请中
     */
    in_approval(0,"申请中"),
    /**
     * 有效
     */
    saved(1,"已通过"),
    /**
     * 无效
     */
    invalid(2,"无效");
    private Integer code;

    private String desc;

    FamilyStatus(Integer code,String desc){
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
    }
}

package com.kingtrustcloud.familytree.enumeration;

/**
 * @author kevin
 * @create 2020-03-16 11:27
 * @Description:
 */
public enum FamilyBasicIndex implements BaseEnum<Integer> {
    /**
     * 世子辈
     */
    BASIC_INDEX_SHI(1, "世"),
    BASIC_INDEX_NONG(2, "农"),
    BASIC_INDEX_SI(3, "思"),
    BASIC_INDEX_ZHONG(4, "中"),
    BASIC_INDEX_DAO(5, "道"),
    BASIC_INDEX_JI(6, "继"),
    BASIC_INDEX_XUE(7, "学");
    private Integer code;

    private String desc;

    FamilyBasicIndex(Integer code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}

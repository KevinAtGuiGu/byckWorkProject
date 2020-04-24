package com.kingtrustcloud.familytree.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version V1.0
 * @Title: MyInfoVO
 * @Package com.kingtrustcloud.familytree.vo
 * @Description: (我的页面信息)
 * @author: tanyong
 * @date: 2020/3/31 11:04
 */
@Data
public class MyInfoVO {
    /**
     * 头像
     */
    String headImageUrl;
    /**
     * 姓名
     */
    @ApiModelProperty(name = "userName", value = "姓名")
    private String userName;
    /**
     * 家谱名
     */
    @ApiModelProperty(name = "familyName", value = "家谱名")
    private String familyName;
    /**
     * 曾用名
     */
    @ApiModelProperty(name = "otherName", value = "曾用名")
    private String otherName;
    /**
     * 身份证号码
     */
    @ApiModelProperty(name = "idCardNo", value = "身份证号码")
    private String idCardNo;
    /**
     * 手机号
     */
    @ApiModelProperty(name = "mobileNo", value = "手机号")
    private String mobileNo;
    /**
     * 账号
     */
    @ApiModelProperty(name = "account", value = "账号")
    private String account;
    /**
     * 性别 0:男 1:女
     */
    @ApiModelProperty(name = "genderId", value = "性别")
    private Integer genderId;
    /**
     *
     * 地址
     */
    @ApiModelProperty(name = "address", value = "地址")
    private String address;
    /**
     * 国家
     */
    @ApiModelProperty(name = "countryId", value = "国家")
    private Long countryId;

    /**
     * 省份
     */
    @ApiModelProperty(name = "provinceId", value = "省份")
    private Long provinceId;
    /**
     * 城市
     */
    @ApiModelProperty(name = "cityId", value = "城市")
    private Long cityId;
    /**
     * 区/县
     */
    @ApiModelProperty(name = "districtId", value = "区/县")
    private Long districtId;
    /**
     * 商业区
     */
    @ApiModelProperty(name = "businessZoneId", value = "商业区")
    private Long businessZoneId;
    /**
     * 家族编号
     */
    String familyCode;
    /**
     * 用户Id
     */
    private Long memberId;

}

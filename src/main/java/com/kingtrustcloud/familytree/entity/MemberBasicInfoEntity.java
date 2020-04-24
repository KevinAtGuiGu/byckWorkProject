package com.kingtrustcloud.familytree.entity;
import com.kingtrustcloud.familytree.enumeration.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 会员基础信息实体类
 */
@Data
@Entity
@Table(name="member_basic_info")
@ApiModel(value = "MemberBasicInfoEntity实体", description = "家族成员表")
public class MemberBasicInfoEntity extends BaseEntity{
    /**
     * 会员ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "memberId", value = "会员ID")
    private Long memberId;
    /**
     * 分组ID
     */
    @ApiModelProperty(name = "groupId", value = "分组ID")
    private Long groupId;
    /**
     * 角色id
     */
    @ApiModelProperty(name = "roleId", value = "角色id")
    private Long roleId;
    /**
     * 来源
     */
    @ApiModelProperty(name = "sourceId", value = "来源")
    private Long sourceId;
    /**
     * 账号
     */
    @ApiModelProperty(name = "account", value = "账号")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty(name = "password", value = "密码")
    private String password;
    /**
     * 会员属性
     */
    @Column(name="member_prop_1_id")
    private Long memberProp1Id;
    @Column(name="member_prop_2_id")
    private Long memberProp2Id;
    @Column(name="member_prop_3_id")
    private Long memberProp3Id;
    @Column(name="member_prop_4_id")
    private Long memberProp4Id;
    @Column(name="member_prop_5_id")
    private Long memberProp5Id;
    /**
     * 计数属性
     */
    @Column(name="count_prop_1")
    private Long countProp1;
    @Column(name="count_prop_2")
    private Long countProp2;
    @Column(name="count_prop_3")
    private Long countProp3;
    @Column(name="count_prop_4")
    private Long countProp4;
    @Column(name="count_prop_5")
    private Long countProp5;
    /**
     * 姓名
     */
    @ApiModelProperty(name = "userName", value = "姓名")
    private String userName;
    /**
     *头像
     */
    @ApiModelProperty(name = "headThumb", value = "头像")
    private String headThumb;
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
     * 出生地点
     */
    @ApiModelProperty(name = "birthdayPlace", value = "出生地点")
    private String birthdayPlace;
    /**
     * 性别 0:男 1:女
     */
    @ApiModelProperty(name = "genderId", value = "性别")
    private Integer genderId;
    /**
     * 年龄段
     */
    @ApiModelProperty(name = "ageRangeId", value = "年龄段")
    private Integer ageRangeId;
    /**
     * 年龄
     */
    @ApiModelProperty(name = "age", value = "年龄")
    private String age;
    /**
     * 出生日期
     */
    @ApiModelProperty(name = "birthday", value = "出生日期")
    private String birthday;
    /**
     * 手机号
     */
    @ApiModelProperty(name = "mobileNo", value = "手机号")
    private String mobileNo;
    /**
     * 是否死亡
     */
    @ApiModelProperty(name = "isDead", value = "是否死亡")
    private Long isDead;
    /**
     * 死亡时间
     */
    @ApiModelProperty(name = "deadDate", value = "死亡时间")
    private String deadDate;
    /**
     * 死亡地点
     */
    @ApiModelProperty(name = "deadPlace", value = "死亡地点")
    private String deadPlace;
    /**
     * 埋葬地点
     */
    @ApiModelProperty(name = "tombPlace", value = "埋葬地点")
    private String tombPlace;
    /**
     * 电邮
     */
    @ApiModelProperty(name = "email", value = "电邮")
    private String email;
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
     *
     * 地址
     */
    @ApiModelProperty(name = "address", value = "地址")
    private String address;
    /**
     * 描述
     */
    @ApiModelProperty(name = "description", value = "描述")
    private String description;
    /**
     * 门店
     */
    @ApiModelProperty(name = "storeId", value = "门店")
    private Long storeId;
    /**
     * 状态
     */
    @ApiModelProperty(name = "statusId", value = "状态")
    private Long statusId;
    /**
     * 备用
     */
    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    private String col6;
    private String col7;
    private String col8;
    private String col9;
    private String col10;

    public enum Role implements BaseEnum<Long> {
        Super_manage(1L,"超级管理员"),
        Branch_manage(2L,"分支管理员"),
        user(3L,"注册用户");
        Long code;
        String desc;

        Role(Long code,String desc) {
            this.code = code;
            this.desc=desc;
        }

        @Override
        public Long getCode() {
            return code;
        }

        @Override
        public String getDesc() {
            return desc;
        }
    }
}
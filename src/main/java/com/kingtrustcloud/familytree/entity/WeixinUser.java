package com.kingtrustcloud.familytree.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @version V1.0
 * @Title: WeixinUser
 * @Package com.kingtrustcloud.familytree.entity
 * @Description: ()
 * @author: tanyong
 * @date: 2020/3/4 16:10
 */
@Data
@Entity
@Table(name="weixin_user")
@ApiModel(value = "WeixinUser实体", description = "微信用户信息表")
public class WeixinUser extends BaseEntity {
    /**
     * 微信用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "userId", value = "微信用户ID")
    private Long userId;
    /**
     * 会员ID
     */
    @ApiModelProperty(name = "memberId", value = "会员ID")
    private Long memberId;
    /**
     * 分组ID
     */
    @ApiModelProperty(name = "groupId", value = "分组ID")
    private Long groupId;
    /**
     * 公众号识别用户标识
     */
    @ApiModelProperty(name = "openId", value = "公众号标识")
    private String openId;
    /**
     * 昵称
     */
    @ApiModelProperty(name = "nickName", value = "昵称")
    private String nickName;
    /**
     * 性别
     */
    @ApiModelProperty(name = "sex", value = "性别")
    private String sex;
    /**
     * 语言
     */
    @ApiModelProperty(name = "language", value = "语言")
    private String language;
    /**
     * 省份
     */
    @ApiModelProperty(name = "province", value = "省份")
    private String province;
    /**
     * 市
     */
    @ApiModelProperty(name = "city", value = "市")
    private String city;
    /**
     * 国家
     */
    @ApiModelProperty(name = "country", value = "国家")
    private String country;
    /**
     * 头像
     */
    @ApiModelProperty(name = "headImageUrl", value = "头像")
    private String headImageUrl;
    /**
     * 联合ID
     */
    @ApiModelProperty(name = "unionId", value = "联合ID")
    private String unionId;
    /**
     * 提交时间戳
     */
    @ApiModelProperty(name = "subscribeTime", value = "提交时间戳")
    private LocalDateTime subscribeTime;
    /**
     * 备注
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
    /**
     * 权限
     */
    @ApiModelProperty(name = "privilege", value = "权限")
    private String privilege;
    /**
     * 是否是网页授权用户
     */
    @ApiModelProperty(name = "isWebAuth", value = "授权")
    private Boolean isWebAuth;
    /**
     * 备用(保存用户状态)
     */
    @Column(name="col1")
    @Size(max = 100,message = "备用1长度不能超过100个字符")
    @ApiModelProperty(name = "col1", value = "备用1")
    private String col1;
    //如果拒绝，保存拒绝原因
    @Column(name="col2")
    @Size(max = 100,message = "备用2长度不能超过100个字符")
    @ApiModelProperty(name = "col2", value = "备用2")
    private String col2;

    @Column(name="col3")
    @Size(max = 100,message = "备用3长度不能超过100个字符")
    @ApiModelProperty(name = "col3", value = "备用3")
    private String col3;

    @Column(name="col4")
    @Size(max = 100,message = "备用4长度不能超过100个字符")
    @ApiModelProperty(name = "col4", value = "备用4")
    private String col4;

    @Column(name="col5")
    @Size(max = 100,message = "备用5长度不能超过100个字符")
    @ApiModelProperty(name = "col5", value = "备用5")
    private String col5;
}

package com.kingtrustcloud.familytree.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kingtrustcloud.familytree.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 家族实体类
 *
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:19:15
 */
@Data
@Entity
@Table(name = "family")
@ApiModel(value = "Family实体", description = "家族信息表")
public class Family extends BaseEntity {
    /**
     * 家族ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "familyId", value = "家族ID")
    private Long familyId;
    /**
     * 会员ID
     */
    @ApiModelProperty(name = "memberId", value = "会员ID")
    @Column(name = "member_id")
    private Long memberId;

    @ApiModelProperty(name = "familyName", value = "家族名称")
    @Column(name = "family_name")
    @Size(max = 255, message = "家族名称长度不能超过255个字符")
    private String familyName;

    @ApiModelProperty(name = "code", value = "家族代码")
    @Column(name = "code")
    @Size(max = 50, message = "家族代码长度不能超过50个字符")
    private String code;
    @ApiModelProperty(name = "statusId", value = "家族状态")
    @Column(name = "status_id")
    private Integer statusId;
    @ApiModelProperty(name = "col1", value = "备用1")
    @Column(name = "col1")
    @Size(max = 100, message = "备用1长度不能超过100个字符")
    private String col1;
    @ApiModelProperty(name = "col2", value = "备用2")
    @Column(name = "col2")
    @Size(max = 100, message = "备用2长度不能超过100个字符")
    private String col2;
    @ApiModelProperty(name = "col3", value = "备用3")
    @Column(name = "col3")
    @Size(max = 100, message = "备用3长度不能超过100个字符")
    private String col3;
    @ApiModelProperty(name = "col4", value = "备用4")
    @Column(name = "col4")
    @Size(max = 100, message = "备用4长度不能超过100个字符")
    private String col4;
    @ApiModelProperty(name = "col5", value = "备用5")
    @Column(name = "col5")
    @Size(max = 100, message = "备用5长度不能超过100个字符")
    private String col5;

}

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
 * 家族辈份实体类
 *
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:19:15
 */
@Data
@Entity
@Table(name = "family_basic")
@ApiModel(value = "FamilyBasic实体", description = "家族辈分信息表")
public class FamilyBasic extends BaseEntity {
    /**
     * 辈分ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "familyBasicId", value = "辈分ID")
    private Long familyBasicId;
    /**
     * 家族ID
     */
    @ApiModelProperty(name = "familyId", value = "家族ID")
    @Column(name = "family_id")
    private Long familyId;
    /**
     * 会员ID
     */
    @ApiModelProperty(name = "memberId", value = "会员ID")
    @Column(name = "member_id")
    private Long memberId;
    /**
     * 辈分排序
     */
    @ApiModelProperty(name = "basicIndex", value = "辈分排序")
    @Column(name = "basic_index")
    private Integer basicIndex;
    //世、农、思、中、道、继、学
    @ApiModelProperty(name = "familyBasicName", value = "辈分名称")
    @Column(name = "family_basic_name")
    @Size(max = 255, message = " 辈分名称长度不能超过255个字符")
    private String familyBasicName;
    @ApiModelProperty(name = "description", value = "世袭设置")
    @Column(name = "description")
    @Size(max = 4000, message = "辈分简介长度不能超过4000个字符")
    private String description;
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
    @ApiModelProperty(name = "col14", value = "备用4")
    @Column(name = "col4")
    @Size(max = 100, message = "备用4长度不能超过100个字符")
    private String col4;
    @ApiModelProperty(name = "col5", value = "备用5")
    @Column(name = "col5")
    @Size(max = 100, message = "备用5长度不能超过100个字符")
    private String col5;


}

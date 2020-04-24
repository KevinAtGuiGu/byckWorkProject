package com.kingtrustcloud.familytree.entity;

import com.kingtrustcloud.familytree.enumeration.BaseEnum;
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
 * 家族分支实体类
 *
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:19:15
 */
@Data
@Entity
@Table(name = "family_branch")
@ApiModel(value = "FamilyBranch实体", description = "家族分支信息表")
public class FamilyBranch extends BaseEntity {
    /**
     * 家族分支ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "familyBranchId", value = "家族分支ID")
    private Long familyBranchId;
    /**
     * 家族ID
     */
    @ApiModelProperty(name = "familyId", value = "家族ID")
    @Column(name = "family_id")
    private Long familyId;

    @ApiModelProperty(name = "code", value = "家族分支代码")
    @Column(name = "code")
    @Size(max = 50, message = "分支代码长度不能超过50个字符")
    private String code;
    @ApiModelProperty(name = "countryId", value = "国家")
    @Column(name = "country_id")
    private Integer countryId;

    @ApiModelProperty(name = "provinceId", value = "省份")
    @Column(name = "province_id")
    private Integer provinceId;
    @ApiModelProperty(name = "cityId", value = "城市")
    @Column(name = "city_id")
    private Integer cityId;
    /**
     * 区/县
     */
    @ApiModelProperty(name = "districtId", value = "地区")
    @Column(name = "district_id")
    private Integer districtId;

    @ApiModelProperty(name = "branchName", value = "分支名称")
    @Column(name = "branch_name")
    @Size(max = 255, message = "分支名称长度不能超过255个字符")
    private String branchName;

    @ApiModelProperty(name = "description", value = "分支简介")
    @Column(name = "description")
    @Size(max = 4000, message = "分支简介长度不能超过4000个字符")
    private String description;
    @ApiModelProperty(name = "statusId", value = "分支状态")
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

//    public enum Status implements BaseEnum<Integer> {
//        /**
//         * 申请中
//         */
//        in_approval(0,"申请中"),
//        /**
//         * 正常保存状态，即正常的家族分支与分支管理员的关联关系
//         */
//        saved(1,"保存状态"),
//        /**
//         * 无效，申请创建分支后此家族超级管理员拒绝时的状态
//         */
//        invalid(2,"无效"),
//        /**
//         * 申请创建家族分支通过状态
//         */
//        approved(3,"审批通过");
//
//        Integer code;
//        String desc;
//
//        Status(Integer code,String desc) {
//            this.code = code;
//            this.desc=desc;
//        }
//
//        @Override
//        public Integer getCode() {
//            return code;
//        }
//
//        @Override
//        public String getDesc() {
//            return desc;
//        }
//    }
}

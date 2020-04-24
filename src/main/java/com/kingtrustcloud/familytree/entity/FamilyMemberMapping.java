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
 * 家族用户关联实体类
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:19:14
 */
@Data
@Entity
@Table(name="family_member_mapping")
@ApiModel(value = "FamilyMemberMapping实体", description = "家族成员关系映射表")
public class FamilyMemberMapping extends BaseEntity {
   /**
    * 家族用户关联ID
    */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @ApiModelProperty(name = "familyMemberMappingId", value = "家族用户关联实体ID")
   private Long familyMemberMappingId;
   /**
    * 父ID
    */
   @Column(name="parent_member_id")
   @ApiModelProperty(name = "parentMemberId", value = "父ID")
   private Long parentMemberId;
   /**
    * 伴侣id  null或0，这代表是丈夫  有值的话就是她丈夫ID
    */
   @ApiModelProperty(name = "mateId", value = "伴侣ID")
   private Long mateId;
   /**
    * 会员ID
    */
   @ApiModelProperty(name = "memberId", value = "会员ID")
   @Column(name="member_id")
   private Long memberId;
   /**
    * 家族分支ID
    */
   @ApiModelProperty(name = "familyBranchId", value = "家族分支ID")
   @Column(name="family_branch_id")
   private Long familyBranchId;
   /**
    * 辈分ID
    */
   @ApiModelProperty(name = "familyBasicId", value = "家族辈分ID")
   @Column(name="family_basic_id")
   private Long familyBasicId;
   /**
    * 家族ID
    */
   @ApiModelProperty(name = "familyId", value = "家族ID")
   @Column(name="family_id")
   private Long familyId;

   @ApiModelProperty(name = "description", value = "描述")
   @Column(name="description")
   @Size(max = 2000,message = "关联说明长度不能超过2000个字符")
   private String description;

   @ApiModelProperty(name = "statusId", value = "状态")
   @Column(name="status_id")
   private Integer statusId;

   /**
    * 存储是那个微信用户的申请
    */
   @ApiModelProperty(name = "col1", value = "备用1")
   @Column(name="col1")
   @Size(max = 100,message = "备用1长度不能超过100个字符")
   private String col1="1";

   /**
    * 存储拒绝申请的理由
    */
   @ApiModelProperty(name = "col2", value = "备用2")
   @Column(name="col2")
   @Size(max = 100,message = "备用2长度不能超过100个字符")
   private String col2;

   @ApiModelProperty(name = "col3", value = "备用3")
   @Column(name="col3")
   @Size(max = 100,message = "备用3长度不能超过100个字符")
   private String col3;

   @ApiModelProperty(name = "col4", value = "备用4")
   @Column(name="col4")
   @Size(max = 100,message = "备用4长度不能超过100个字符")
   private String col4;

   @ApiModelProperty(name = "col5", value = "备用5")
   @Column(name="col5")
   @Size(max = 100,message = "备用5长度不能超过100个字符")
   private String col5;

   public enum Status implements BaseEnum<Integer> {
      /**
       * 申请中
       */
      in_approval(0,"申请中"),
      /**
       * 正常保存状态，即正常的家族分支与成员的关联关系
       */
      saved(1,"保存状态"),
      /**
       * 无效，申请加入此家族分支被管理员拒绝时的状态
       */
      invalid(2,"无效"),
      /**
       * 申请加入家族分支通过状态
       */
      approved(3,"审批通过");

      Integer code;
      String desc;

      Status(Integer code,String desc) {
         this.code = code;
         this.desc=desc;
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

}

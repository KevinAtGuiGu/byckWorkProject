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
 * 分支管理员ID实体类
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:19:14
 */
@Data
@Entity
@Table(name="family_branch_admin")
@ApiModel(value = "FamilyBranchAdmin实体", description = "家族分支管理员信息表")
public class FamilyBranchAdmin extends BaseEntity {
   /**
    * 分支管理员ID
    */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @ApiModelProperty(name = "familyBranchAdminId", value = "家族分支管理员ID")
   private Long familyBranchAdminId;
   /**
    * 家族分支ID（分支管理员属于哪一个家族）
    */
   @Column(name="family_branch_id")
   @ApiModelProperty(name = "familyBranchId", value = "家族分支ID")
   private Long familyBranchId;
   /**
    * 会员ID（分支管理员属于哪一个用户）
    */
   @Column(name="member_id")
   @ApiModelProperty(name = "memberId", value = "会员ID")
   private Long memberId;
   /**
    * 状态
    */
   @Column(name="status_id")
   @ApiModelProperty(name = "statusId", value = "状态")
   private Integer statusId;

   /**
    * 保存申请成为分支管理员的微信用户Weixin_User id
    */
   @Column(name="col1")
   @Size(max = 100,message = "备用1长度不能超过100个字符")
   @ApiModelProperty(name = "col1", value = "备用1")
   private String col1;

   /**
    * 保存拒绝理由
    */
   @Column(name="col2")
   @Size(max = 100,message = "备用2长度不能超过100个字符")
   @ApiModelProperty(name = "col2", value = "备用2")
   private String col2;

   @Column(name="col3")
   @Size(max = 100, message = "备用3长度不能超过100个字符")
   @ApiModelProperty(name = "col3", value = "备用3")
   private String col3;

   @Column(name="col4")
   @Size(max = 100,message = "备用4长度不能超过100个字符")
   @ApiModelProperty(name = "col4", value = "备用4")
   private String col4;

   @Column(name="col5")
   @Size(max = 100,message = "备用5长度不能超过100个字符")
   @ApiModelProperty(name = "col4", value = "备用4")
   private String col5;


//   public enum Status implements BaseEnum<Integer> {
//      /**
//       * 申请中
//       */
//      in_approval(0,"申请中"),
//      /**
//       * 正常保存状态，即正常的家族分支与分支管理员的关联关系
//       */
//      saved(1,"保存状态"),
//      /**
//       * 无效，申请创建分支后此家族超级管理员拒绝时的状态
//       */
//      invalid(2,"无效"),
//      /**
//       * 申请创建家族分支通过状态
//       */
//      approved(3,"审批通过");
//
//      Integer code;
//      String desc;
//
//      Status(Integer code,String desc) {
//         this.code = code;
//         this.desc=desc;
//      }
//
//      @Override
//      public Integer getCode() {
//         return code;
//      }
//
//      @Override
//      public String getDesc() {
//         return desc;
//      }
//   }
}

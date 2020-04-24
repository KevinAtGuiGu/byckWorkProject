package com.kingtrustcloud.familytree.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**   
 * 实体类
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-31 10:01:29
 */
@Data
@Entity
@Table(name="sys_role")
@ApiModel(value = "SysRole实体", description = "角色表")
public class SysRole extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long roleId;

   @ApiModelProperty(name = "parentRoleId" , value = "角色父ID")
   @Column(name="parent_role_id")
   private Long parentRoleId;

   @ApiModelProperty(name = "roleType" , value = "角色类型")
   @Column(name="role_type")
   @Size(max = 50,message = "角色类型长度不能超过50个字符")
   private String roleType;

   @ApiModelProperty(name = "roleName" , value = "角色名称")
   @Column(name="role_name")
   @Size(max = 200,message = "角色名称长度不能超过200个字符")
   private String roleName;

   @ApiModelProperty(name = "description" , value = "描述")
   @Column(name="description")
   @Size(max = 200,message = "描述长度不能超过200个字符")
   private String description;

   @ApiModelProperty(name = "col1" , value = "备用1")
   @Column(name="col1")
   @Size(max = 100,message = "备用1长度不能超过100个字符")
   private String col1;

   @ApiModelProperty(name = "col2" , value = "备用2")
   @Column(name="col2")
   @Size(max = 100,message = "备用2长度不能超过100个字符")
   private String col2;

   @ApiModelProperty(name = "col3" , value = "备用3")
   @Column(name="col3")
   @Size(max = 100,message = "备用3长度不能超过100个字符")
   private String col3;

   @ApiModelProperty(name = "col4" , value = "备用4")
   @Column(name="col4")
   @Size(max = 100,message = "备用4长度不能超过100个字符")
   private String col4;

   @ApiModelProperty(name = "col5" , value = "备用5")
   @Column(name="col5")
   @Size(max = 100,message = "备用5长度不能超过100个字符")
   private String col5;


}

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
 * 短信认证表实体类
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-17 10:12:37
 */
@Data
@Entity
@Table(name="member_rand_code")
@ApiModel(value = "MemberRandCode实体", description = "短信认证表")
public class MemberRandCode extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @ApiModelProperty(name = "memberRandCodeId", value = "ID")
   private Long memberRandCodeId;

   @Column(name="mobile_no")
   @Size(max = 255,message = "手机号长度不能超过255个字符")
   @ApiModelProperty(name = "mobileNo", value = "手机号")
   private String mobileNo;

   @Column(name="code")
   @Size(max = 50,message = "验证码长度不能超过50个字符")
   @ApiModelProperty(name = "code", value = "验证码")
   private String code;

   @Column(name="status_id")
   @ApiModelProperty(name = "statusId", value = "状态")
   private Integer statusId;

   @Column(name="sort_order")
   @ApiModelProperty(name = "sortOrder", value = "顺序")
   private Integer sortOrder;

   @Column(name="col1")
   @Size(max = 100,message = "备用1长度不能超过100个字符")
   @ApiModelProperty(name = "col1", value = "备用1")
   private String col1;

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

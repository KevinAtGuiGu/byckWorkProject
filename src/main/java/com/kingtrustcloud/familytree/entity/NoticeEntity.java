package com.kingtrustcloud.familytree.entity;

import com.kingtrustcloud.familytree.enumeration.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author kevin
 * @create 2020-03-17 16:51
 * @Description:
 */
@Data
@Entity
@Table(name = "notice")
@ApiModel(value = "NoticeEntity实体", description = "公告表")
public class NoticeEntity extends BaseEntity {
    /**
     * 公告Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "noticeId", value = "公告Id")
    private Long noticeId;
    /**
     * 公告名称
     */
    @Column(name = "notice_name")
    @Size(max = 200, message = " 公告名称长度不能超过255个字符")
    @ApiModelProperty(name = "noticeName", value = "公告名称")
    private String noticeName;
    /**
     * 公告内容
     */
    @ApiModelProperty(name = "noticeContent", value = "公告内容")
    @Column(name = "notice_content")
    private String noticeContent;

    /**
     * 公告状态
     */
    @ApiModelProperty(name = "statusId", value = "公告状态")
    @Column(name = "status_Id")
    private Integer statusId;

    @Column(name = "col1")
    @Size(max = 100, message = "备用1长度不能超过100个字符")
    @ApiModelProperty(name = "col1", value = "备用1")
    private String col1;

    @Column(name = "col2")
    @Size(max = 100, message = "备用2长度不能超过100个字符")
    @ApiModelProperty(name = "col2", value = "备用2")
    private String col2;

    @Column(name = "col3")
    @Size(max = 100, message = "备用3长度不能超过100个字符")
    @ApiModelProperty(name = "col3", value = "备用3")
    private String col3;

    @Column(name = "col4")
    @Size(max = 100, message = "备用4长度不能超过100个字符")
    @ApiModelProperty(name = "col4", value = "备用4")
    private String col4;

    @Column(name = "col5")
    @Size(max = 100, message = "备用5长度不能超过100个字符")
    @ApiModelProperty(name = "col5", value = "备用5")
    private String col5;

    public enum NoticeStatus implements BaseEnum<Integer> {
        uncheckd(1,"已提交未审核"),
        approval(2,"已发布");

        private Integer code;
        private String desc;

         NoticeStatus (Integer code,String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }
}

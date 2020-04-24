package com.kingtrustcloud.familytree.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * 基类
 */
@Data
public class BaseEntity {

    @ApiModelProperty(value = "主键")
	private Long ownerId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(updatable = false)
	private String createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人ID")
    @Column(updatable = false)
    private Long createById;
    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private String lastModifyTime;
    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long lastModifyById;
    /**
     * 是否删除（false=0）
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Boolean isDelete = false;
    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时的时间")
    private LocalDateTime deleteTime;
    /**
     * 删除人
     */
    @ApiModelProperty(value = "删除人的Id")
    private Long deleteById;

}

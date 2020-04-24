package com.kingtrustcloud.familytree.vo;

import lombok.Data;

/**
 * @author kevin
 * @create 2020-04-03 18:16
 * @Description: 公告试图列表
 */
@Data
public class NoticeVO {
    private Long noticeId;
    private String noticeName;
    private String content;
    private Integer status;
    private String createTime;
}

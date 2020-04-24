package com.kingtrustcloud.familytree.service;

import com.kingtrustcloud.familytree.entity.NoticeEntity;
import com.kingtrustcloud.familytree.vo.NoticeVO;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-17 17:06
 * @Description:
 */
public interface NoticeService {

    List<NoticeVO> getNoticeList(Long noticeId);

    void saveNotice(NoticeEntity entity);

    void approvalNotice(Long noticeId);
}

package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.common.HelpUtil;
import com.kingtrustcloud.familytree.entity.NoticeEntity;
import com.kingtrustcloud.familytree.mapper.NoticeMapper;
import com.kingtrustcloud.familytree.service.NoticeService;
import com.kingtrustcloud.familytree.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @create 2020-03-17 17:06
 * @Description:
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<NoticeVO> getNoticeList(Long noticeId) {
        List<Long> noticeList = new ArrayList<>();
        if (noticeId == null) {
            List<NoticeEntity> noticeEntities1 = noticeMapper.selectAll();
            noticeList.addAll(noticeEntities1.stream().map((i) -> {
                return i.getNoticeId();
            }).collect(Collectors.toList()));
        } else {
            noticeList.add(noticeId);
        }
        List<NoticeVO> noticeEntities = noticeMapper.getNoticeList(noticeList);
        return noticeEntities;
    }

    @Override
    public void saveNotice(NoticeEntity noticeEntity) {
        if (noticeEntity.getNoticeId() == null) {
            noticeEntity.setCreateTime(HelpUtil.getDateTime());
            noticeEntity.setStatusId(NoticeEntity.NoticeStatus.uncheckd.getCode());
            noticeMapper.insert(noticeEntity);
        } else {
            NoticeEntity noticeEntityOld = noticeMapper.selectByPrimaryKey(noticeEntity.getNoticeId());
            noticeEntityOld.setNoticeName(noticeEntity.getNoticeName());
            noticeEntityOld.setNoticeContent(noticeEntity.getNoticeContent());
            noticeEntityOld.setStatusId(noticeEntity.getStatusId());
            noticeMapper.updateByPrimaryKeySelective(noticeEntityOld);
        }

    }

    @Override
    public void approvalNotice(Long noticeId) {
        NoticeEntity noticeEntity = noticeMapper.selectByPrimaryKey(noticeId);
        noticeEntity.setStatusId(NoticeEntity.NoticeStatus.approval.getCode());
        noticeMapper.updateByPrimaryKeySelective(noticeEntity);
    }
}

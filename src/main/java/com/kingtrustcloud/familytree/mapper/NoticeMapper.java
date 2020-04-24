package com.kingtrustcloud.familytree.mapper;

import com.kingtrustcloud.familytree.entity.NoticeEntity;
import com.kingtrustcloud.familytree.vo.NoticeVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**   
 * 公告数据访问层
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:21:48
 */
@Repository
public interface NoticeMapper extends Mapper<NoticeEntity> {
    /**
     * 获取所有未审核的公告
     * @return
     */
    List<NoticeVO> getNoticeList(List<Long> noticeList);
}

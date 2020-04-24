package com.kingtrustcloud.familytree.mapper;

import com.kingtrustcloud.familytree.entity.WeixinUser;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @version V1.0
 * @Title: WeixinUserMapper
 * @Package com.kingtrustcloud.familytree.mapper
 * @Description: ()
 * @author: tanyong
 * @date: 2020/3/4 16:23
 */
@Repository
public interface WeixinUserMapper extends Mapper<WeixinUser> {

    @Update("update weixin_user set member_id=null where member_id=#{memberId}")
    Integer updateMemberIdToNullByMemberId(Long memberId);
}

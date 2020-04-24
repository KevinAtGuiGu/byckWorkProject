package com.kingtrustcloud.familytree.common;

import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import lombok.Data;

/**
 * @version V1.0
 * @Title: SessionUserInfo
 * @Package com.kingtrustcloud.familytree.common
 * @Description: ()
 * @author: tanyong
 * @date: 2020/4/10 18:44
 */
@Data
public class SessionUserInfo {

    private WeixinUser weixinUser;

    private MemberBasicInfoEntity memberBasicInfoEntity;

    /**
     * 角色
     */
    private String userRole;

}

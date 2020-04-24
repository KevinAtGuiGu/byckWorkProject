package com.kingtrustcloud.common;

import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2020/2/20.
 */
public class HelpUtil {
    public static String getDateTime() {
        String vDateTime = "";
        SimpleDateFormat vTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date vOrderDateTemp = new Date();
        vDateTime = vTime.format(vOrderDateTemp);
        return vDateTime;
    }

    /**
     * 检查超级管理员权限
     * @param memberBasicInfoEntity
     * @return
     */
    public static boolean checkMemberPermission(MemberBasicInfoEntity memberBasicInfoEntity) {
        Long roleId = memberBasicInfoEntity.getRoleId();
        if (roleId == 2L || roleId == 3L || roleId == null){
            return false;
        }
        return  true;
    }
}

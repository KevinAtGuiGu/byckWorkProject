package com.kingtrustcloud.common;

/**
 * @version V1.0
 * @Title: Constant
 * @Package com.kingtrustcloud.common
 * @Description: (常量类，统一管理)
 * @author: tanyong
 * @date: 2020/3/18 16:39
 */
public interface Constant {
    /**
     * session里面保存用户信息的属性名称。
     */
    String SESSION_USER = "user";

    /**
     * 图片验证码
     */
    String KAPTCHA_CODE = "kaptchaCode";

    /**
     * 短信验证码
     */
    String SMS_CODE = "smsCode";

    /**
     * 用户角色
     */
    String SYS_ROLE = "userRole";

    /**
     * 申请加入家族分支状态
     */
    String APPROVEL_JOIN_FAMILY="approvelJoinFamily";

    /**
     * 申请创建家族分支状态
     */
    String APPROVEL_CREATE_FAMILY_BRANCH="approvelCreateFamilyBranch";
    /**
     * 申请加入家族分支被拒绝后状态
     */
    String APPROVEL_JOIN_FAMILY_REJECT="approvelJoinFamilyReject";
    /**
     * 申请创建家族分支被拒绝后状态
     */
    String APPROVEL_CREATE_FAMILY_BRANCH_REJECT="approvelCreateFamilyBranchReject";

    /**
     * 正常状态
     */
    String NORMAL="normal";

    /**
     * 新建状态
     */
    String NEW = "new";

    /**
     * 田氏家族id
     */
    Long FAMILY_ID_TIAN=1L;
}

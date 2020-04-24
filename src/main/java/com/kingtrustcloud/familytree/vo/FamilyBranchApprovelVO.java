package com.kingtrustcloud.familytree.vo;

import lombok.Data;

/**
 * @version V1.0
 * @Title: FamilyBranchApprovelVO
 * @Package com.kingtrustcloud.familytree.vo
 * @Description: (家族分支加入申请视图对象)
 * @author: tanyong
 * @date: 2020/3/19 9:27
 */
@Data
public class FamilyBranchApprovelVO {

    private Long familyMemberMappingId;

    private String familyBasicName;

    private String userName;

    private String familyName;

    private Long memberId;

    private Long familyBranchId;

    private String branchName;

    private String otherName;

    private String provinceId;

    private String cityId;

    private String birthdayPlace;

    private String fatherName;
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headImageUrl;
    /**
     * 性别
     */
    private String sex;

    private String address;

    private String createTime;

    private Long familyBranchAdminId;

    private Long familyId;

    private Long familyBasicId;

}

package com.kingtrustcloud.familytree.vo;

import lombok.Data;

/**
 * @author kevin
 * @create 2020-04-03 18:16
 * @Description: 分支管理员视图对象
 */
@Data
public class FamilyBranchAdminVO {

    private Long familyBranchAdminId;

    private Long memberId;

    private Long familyId;

    private Long familyBranchId;

    private Long familyBasicId;

    private String branchName;

    private String familyBasicName;

    private String userName;

    private String provinceId;

    private String cityId;

    private String birthdayPlace;

    private String fatherName;

    private String otherName;

    private String sex;

    private String address;

    private String createTime;

}

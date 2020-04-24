package com.kingtrustcloud.familytree.vo;

import lombok.Data;

/**
 * @version V1.0
 * @Title: SearchFamilyBranchVO
 * @Package com.kingtrustcloud.familytree.vo
 * @Description: ()
 * @author: tanyong
 * @date: 2020/3/20 11:13
 */
@Data
public class SearchFamilyBranchVO {

    /**
     * 家族分支ID
     */
    private Long familyBranchId;
    /**
     * 家族ID
     */
    private Long familyId;

    private Integer countryId;

    private Integer provinceId;

    private Integer cityId;
    /**
     * 区/县
     */
    private Integer districtId;

    private String branchName;

    private String description;

    //分支名、代、辈、族谱名、姓名
    private String familyBasicName;

    private String userName;

    private String familyName;

    private Long memberId;

    private String nickName;

    private String parentName;

}

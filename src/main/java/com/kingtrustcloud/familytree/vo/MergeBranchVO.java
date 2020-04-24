package com.kingtrustcloud.familytree.vo;

import lombok.Data;

/**
 * @author kevin
 * @date 2020-04-15 11:34
 * @description:
 */
@Data
public class MergeBranchVO {

    private String branchName;

    private Long familyBranchId;

    private Long memberId;

    private Long familyMemberMappingId;

}

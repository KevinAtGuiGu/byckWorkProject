package com.kingtrustcloud.familytree.vo;

import lombok.Data;

/**
 * author kevin
 * date 2020-4-13 11:41
 * description 分支合并时返回给前端的数据
 */
@Data
public class SearchBranchResultVO {

    private Long memberId;

    private String familyName;

    private Long familyBasicId;

    private String branchName;

    private Long familyBranchId;
}

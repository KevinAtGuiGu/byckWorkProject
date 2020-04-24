package com.kingtrustcloud.familytree.dto;

import lombok.Data;

/**
 * @version V1.0
 * @Title: UpdateBranchAdminDTO
 * @Package com.kingtrustcloud.familytree.dto
 * @Description: ()
 * @author: tanyong
 * @date: 2020/4/14 11:30
 */
@Data
public class UpdateBranchAdminDTO {

    private Long familyBranchAdminId;

    private Long familyBranchId;

    private Long memberId;

    /**
     * 是否删除，如果是true，表示是删除，如果是0，表示是不变
     */
    private Boolean isDelete;

}

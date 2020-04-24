package com.kingtrustcloud.familytree.service;

import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.vo.MergeBranchVO;
import com.kingtrustcloud.familytree.vo.SearchBranchResultVO;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 17:52
 * @Description:
 */
public interface FamilyMemberMappingService {
    void saveFamilyMemberMapping(FamilyMemberMapping familyMemberMapping);

    FamilyMemberMapping getFamilyMemberMapping(Long familyMemberMappingId);

    void deleteFamilyMemberMapping(Long familyMemberMappingId);

    List<SearchBranchResultVO> searchBranchResult();

    /**
     * 搜索可合并的分支
     * @param familyBranchId
     * @param familyName
     * @return
     */
    List<MergeBranchVO> branchSearch(Long familyBranchId, String familyName);
}

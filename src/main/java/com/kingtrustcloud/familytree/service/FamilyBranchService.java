package com.kingtrustcloud.familytree.service;

import com.kingtrustcloud.familytree.entity.FamilyBranch;
import com.kingtrustcloud.familytree.entity.FamilyBranchAdmin;
import com.kingtrustcloud.familytree.vo.FamilyBranchApprovelVO;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 17:09
 * @Description:
 */
public interface FamilyBranchService {
    FamilyBranchAdmin saveFamilyBranch(FamilyBranch famliyBranch);

    FamilyBranch getFamilyBranch(Long familyBranchId);

    List<FamilyBranch> getFamilyBranchs(Long familyId);

    void deleteFamilyBranch(Long familyId);

    void postApprovel(Long familyBranchId,Long memberId,String type);

    List<FamilyBranchApprovelVO> listApprovel();

    void approvel(Long familyMemberMappingId);

    List<FamilyBranch> getFamilyBranchList();

    void updateFamilyBranchById(Long familyBranchId, String familyBranchName);

    void refuseApprovel(Long familyMemberMappingId,String messsage);

    /**
     * 合并分支页面搜索家族分支
     * @param keyWord 关键字
     * @return
     */
    List<FamilyBranch> searchFamilyBranchList(String keyWord);
}

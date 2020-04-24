package com.kingtrustcloud.familytree.service;

import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.dto.UpdateBranchAdminDTO;
import com.kingtrustcloud.familytree.entity.FamilyBranchAdmin;
import com.kingtrustcloud.familytree.vo.FamilyBranchAdminVO;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 13:09
 * @Description:
 */
public interface FamilyBranchAdminService {

    FamilyBranchAdmin getFamilyBranchAdmin(Long id);

    List<FamilyBranchAdmin> getFamilyBranchAdmins(Long familyBranchId);

    ResponseResult saveFamilyBranchAdmin(FamilyBranchAdmin familyBranchAdmin);

    void deleteFamilyBranchAdmin(Long familyBranchAdminId);

    List<FamilyBranchAdminVO> listFamilyBranchAdminInfo(Long familyBranchAdminId);

    void submmitFamilyBranchAdminApproval(Long memberId, Long familyBranchAdminId);

    void approvaledfamilyBranchAdminInfo(Long familyBranchAdminId);

    void refuseFamilyBranchAdminInfo(Long familyBranchAdminId, String message);

    void updateBranchAdmin(List<UpdateBranchAdminDTO> branchAdminList);
}

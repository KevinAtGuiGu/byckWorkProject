package com.kingtrustcloud.familytree.service;


import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.vo.MemberVO;

import java.util.List;

public interface MemberService {
    List<MemberBasicInfoEntity> getMembers(long groupId);

    ResponseResult saveMember(MemberBasicInfoEntity memberBasicInfo, FamilyMemberMapping familyMemberMapping, Long memberId,String relation);

    MemberBasicInfoEntity getMember(long memberId);

    void modifyMyInfo(MemberBasicInfoEntity memberBasicInfoEntity);

    List<MemberVO> getMembersByFamilyBranchId(Long familyBranchId);

    void mergeFamilyBranch(List<Long> familyBranchList);

    void deleteMember(Long memberId, Long familyBranchId);

    void saveMember2(Long familyId, Long familyBranchId, MemberBasicInfoEntity entity,Long familyBasicId);
}

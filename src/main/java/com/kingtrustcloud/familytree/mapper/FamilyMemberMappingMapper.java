package com.kingtrustcloud.familytree.mapper;

import com.kingtrustcloud.familytree.entity.FamilyBranch;
import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.vo.FamilyBranchAdminVO;
import com.kingtrustcloud.familytree.vo.FamilyBranchApprovelVO;
import com.kingtrustcloud.familytree.vo.MergeBranchVO;
import com.kingtrustcloud.familytree.vo.SearchBranchResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 家族用户关联数据访问层
 *
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:21:48
 */
@Repository
public interface FamilyMemberMappingMapper extends Mapper<FamilyMemberMapping> {
    List<FamilyBranchApprovelVO> listApprovel(List<Long> list);

    List<FamilyBranchAdminVO>  listFamilyBranchAdminInfo(List<Long> familyBranchAdminIds);

    List<SearchBranchResultVO> searchListMergeBranch();

    List<MergeBranchVO> branchSearch(@Param("familyBasicId") Long familyBasicId, @Param("familyName") String familyName);

    SearchBranchResultVO searchRootMember(Long familyBranchId);

    Boolean selectFamilyBranchMemberIsExsit( @Param("familyBranchId") Long familyBranchId);
}

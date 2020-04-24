package com.kingtrustcloud.familytree.mapper;

import com.kingtrustcloud.familytree.dto.SearchFamilyBranchDTO;
import com.kingtrustcloud.familytree.entity.FamilyBranch;
import com.kingtrustcloud.familytree.vo.FamilyBranchApprovelVO;
import com.kingtrustcloud.familytree.vo.SearchFamilyBranchVO;
import com.kingtrustcloud.familytree.vo.SelectBranchAdminVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 家族分支数据访问层
 *
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:21:48
 */
@Repository
public interface FamilyBranchMapper extends Mapper<FamilyBranch> {
    List<SearchFamilyBranchVO> searchFamilyBranch(SearchFamilyBranchDTO searchFamilyBranchDTO);

    List<SelectBranchAdminVO> listSelectBranchAdminVO(@Param("familyBranchId") Long familyBranchId,@Param("username") String username);

    List<SelectBranchAdminVO> listBranchAdminVO(Long familyBranchId);

    List<FamilyBranchApprovelVO> listApprovel(@Param("isSuperAdmin") boolean isSuperAdmin, @Param("memberId") Long memberId);

	/**
	 * 合并分支页面搜索
	 *
	 * @param keyWord 关键字
	 * @return
	 */
    List<FamilyBranch> searchFamilyBranchList(@Param("keyWord") String keyWord);

    boolean existByBranchName(String branchName);
}

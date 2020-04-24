package com.kingtrustcloud.familytree.mapper;

import com.kingtrustcloud.familytree.vo.FamilyBranchAdminVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import com.kingtrustcloud.familytree.entity.FamilyBranchAdmin;

import java.util.List;


/**   
 * 分支管理员ID数据访问层
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:21:48
 */
@Repository
public interface FamilyBranchAdminMapper extends Mapper<FamilyBranchAdmin> {

    List<FamilyBranchAdminVO> listFamilyBranchAdminInfo(List<Long> familyAdminIds);

    int updateIsDeleteByMemberIdAndFamilyBranchId(Long memberId,Long familyBranchId);

    boolean isApplyingCreateBranch(Long memberId);

    boolean isRefusedCreateBranch(Long memberId);
}

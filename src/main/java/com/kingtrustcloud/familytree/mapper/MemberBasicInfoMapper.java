package com.kingtrustcloud.familytree.mapper;

import com.kingtrustcloud.familytree.entity.FamilyBranch;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.vo.MemberVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MemberBasicInfoMapper extends Mapper<MemberBasicInfoEntity>{

    List<MemberVO> getMembersByFamilyBranchId(Long familyBranchId);

    List<FamilyBranch> listFamilyBranchByMemberId(Long memberId);

    @Select("select case when count(1)>0 then true else false end from member_basic_info where account=#{account} and is_delete=false ")
    boolean existByAccount(String account);
}

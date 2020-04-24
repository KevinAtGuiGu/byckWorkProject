package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.mapper.FamilyMemberMappingMapper;
import com.kingtrustcloud.familytree.service.FamilyMemberMappingService;
import com.kingtrustcloud.familytree.vo.MergeBranchVO;
import com.kingtrustcloud.familytree.vo.SearchBranchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 17:53
 * @Description:
 */
@Service
public class FamilyMemberMappingServiceImpl implements FamilyMemberMappingService {
    @Autowired
    FamilyMemberMappingMapper familyMemberMappingMapper;

    @Autowired
    SessionManage sessionManage;

    @Override
    public void saveFamilyMemberMapping(FamilyMemberMapping familyMemberMapping) {
      //  if (familyMemberMapping.getMemberId() != null) {
            Example example = new Example(FamilyMemberMapping.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("memberId", familyMemberMapping.getMemberId());
            criteria.andEqualTo("isDelete", false);

            FamilyMemberMapping familyMemberMappingOld = familyMemberMappingMapper.selectOneByExample(example);
            if (familyMemberMappingOld != null) {
                familyMemberMappingOld.setFamilyBranchId(familyMemberMapping.getFamilyBranchId());
                familyMemberMappingOld.setStatusId(familyMemberMapping.getStatusId());
                familyMemberMappingOld.setFamilyId(familyMemberMapping.getFamilyId());
                familyMemberMappingOld.setDescription(familyMemberMapping.getDescription());
                familyMemberMappingOld.setFamilyBasicId(familyMemberMapping.getFamilyBasicId());
                familyMemberMappingOld.setParentMemberId(familyMemberMapping.getParentMemberId());
                familyMemberMappingOld.setCol1(familyMemberMapping.getCol1());
                familyMemberMappingOld.setCol2(familyMemberMapping.getCol2());
                familyMemberMappingOld.setCol3(familyMemberMapping.getCol3());
                familyMemberMappingOld.setCol4(familyMemberMapping.getCol4());
                familyMemberMappingOld.setCol5(familyMemberMapping.getCol5());
                familyMemberMappingMapper.updateByPrimaryKeySelective(familyMemberMappingOld);
            }
        //}
        else {
           if (familyMemberMapping.getCreateById() == null){
               familyMemberMappingMapper.insert(familyMemberMapping);
           }
        }
    }

    @Override
    public FamilyMemberMapping getFamilyMemberMapping(Long familyMemberMappingId) {

        FamilyMemberMapping familyMemberMapping = familyMemberMappingMapper.selectByPrimaryKey(familyMemberMappingId);
        return familyMemberMapping;
    }

    @Override
    public void deleteFamilyMemberMapping(Long familyMemberMappingId) {
        familyMemberMappingMapper.deleteByPrimaryKey(familyMemberMappingId);
    }

    @Override
    public List<SearchBranchResultVO> searchBranchResult() {
        List<SearchBranchResultVO> list = familyMemberMappingMapper.searchListMergeBranch();
        return list;
    }

    @Override
    public List<MergeBranchVO> branchSearch(Long familyBasicId, String familyName) {

        List<MergeBranchVO> mergeBranchVOS = familyMemberMappingMapper.branchSearch(familyBasicId, familyName);
        return mergeBranchVOS;
    }
}

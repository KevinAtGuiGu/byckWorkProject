package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.common.HelpUtil;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.Family;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.mapper.FamilyMapper;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.service.FamilyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author kevin
 * @create 2020-03-13 15:41
 * @Description:
 */
@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    FamilyMapper familyMapper;

    @Autowired
    SessionManage sessionManage;
    @Autowired
    MemberBasicInfoMapper memberBasicInfoMapper;

    @Override
    public void saveFamily(Family family) {
        MemberBasicInfoEntity member = memberBasicInfoMapper.selectByPrimaryKey(sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId());
        if (!HelpUtil.checkMemberPermission(member)) {
            throw new BusinessException("没有权限");
        }
        Example example = new Example(Family.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", family.getMemberId());
        criteria.andEqualTo("isDelete", false);
        // List<Family> familyList = famliyMapper.selectByExample(example);
        Family familyOld = familyMapper.selectOneByExample(example);
        if (familyOld != null) {
            familyOld.setMemberId(family.getMemberId());
            familyOld.setFamilyName(family.getFamilyName());
            familyOld.setCode(family.getCode());
            familyOld.setStatusId(family.getStatusId());
            familyOld.setCol1(family.getCol1());
            familyOld.setCol2(family.getCol2());
            familyOld.setCol3(family.getCol3());
            familyOld.setCol4(family.getCol4());
            familyOld.setCol5(family.getCol5());
            familyMapper.updateByPrimaryKeySelective(familyOld);
        } else {
            if (family.getCreateById() == null) {
                familyMapper.insert(family);
                //保存用户为超级管理员
                member.setRoleId(1L);
                memberBasicInfoMapper.updateByPrimaryKey(member);
            }
        }
    }

    @Override
    public Family getFamily(Long familyId) {

        Family famliy = familyMapper.selectByPrimaryKey(familyId);
        return famliy;
    }

    @Override
    public void deleteFamily(Long familyId) {
        familyMapper.deleteByPrimaryKey(familyId);
    }

    @Override
    public List<Family> listFamily(Long memberId, String familyName) {
        Example example = new Example(Family.class);
        Example.Criteria criteria = example.createCriteria();
        if (memberId != null) {
            criteria.andEqualTo("memberId", memberId);
        }
        if (StringUtils.isNotBlank(familyName)) {
            criteria.andLike("familyName", familyName);
        }
        return familyMapper.selectByExample(example);
    }
    @Override
    public Family getFamilyByName(String familyName){
        Example example = new Example(Family.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("familyName",familyName);
        criteria.andEqualTo("isDelete", false);

        Family family = familyMapper.selectOneByExample(example);

        return family;
    }
}

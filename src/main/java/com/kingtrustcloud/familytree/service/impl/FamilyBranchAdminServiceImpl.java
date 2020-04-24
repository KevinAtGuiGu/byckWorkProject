package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.common.Constant;
import com.kingtrustcloud.common.HelpUtil;
import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.dto.UpdateBranchAdminDTO;
import com.kingtrustcloud.familytree.entity.*;
import com.kingtrustcloud.familytree.enumeration.FamilyStatus;
import com.kingtrustcloud.familytree.enumeration.SysRoleEnum;
import com.kingtrustcloud.familytree.mapper.*;
import com.kingtrustcloud.familytree.service.FamilyBranchAdminService;
import com.kingtrustcloud.familytree.vo.FamilyBranchAdminVO;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @create 2020-03-13 13:10
 * @Description:
 */
@Service
public class FamilyBranchAdminServiceImpl implements FamilyBranchAdminService {
    @Autowired
    FamilyBranchAdminMapper familyBranchAdminMapper;

    @Autowired
    SessionManage sessionManage;

    @Autowired
    MemberBasicInfoMapper memberBasicInfoMapper;

    @Autowired
    WeixinUserMapper weixinUserMapper;

    @Autowired
    FamilyMemberMappingMapper familyMemberMappingMapper;
    @Autowired
    FamilyBranchMapper familyBranchMapper;

    @Override
    public FamilyBranchAdmin getFamilyBranchAdmin(Long id) {
        FamilyBranchAdmin familyBranchAdmin = familyBranchAdminMapper.selectByPrimaryKey(id);
        return familyBranchAdmin;
    }

    @Override
    public List<FamilyBranchAdmin> getFamilyBranchAdmins(Long familyBranchId) {

        Example example = new Example(FamilyBranchAdmin.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("familyBranchId", familyBranchId);
        criteria.andEqualTo("isDelete",false);
        example.orderBy("memberId").asc();
        return familyBranchAdminMapper.selectByExample(example);
    }

    @Override
    public ResponseResult saveFamilyBranchAdmin(FamilyBranchAdmin familyBranchAdmin) {
        MemberBasicInfoEntity member = memberBasicInfoMapper.selectByPrimaryKey(sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId());
        if (HelpUtil.checkMemberPermission(member)){
            throw new BusinessException("没有操作权限");
        }
            Example example = new Example(FamilyBranchAdmin.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("memberId", familyBranchAdmin.getMemberId());
            criteria.andEqualTo("isDelete", false);

            FamilyBranchAdmin familyBranchAdminOld = familyBranchAdminMapper.selectOneByExample(example);
            if (familyBranchAdminOld != null) {
                familyBranchAdminOld.setMemberId(familyBranchAdmin.getMemberId());
                familyBranchAdminOld.setFamilyBranchId(familyBranchAdmin.getFamilyBranchId());
                familyBranchAdminOld.setStatusId(familyBranchAdmin.getStatusId());
                familyBranchAdminOld.setCol1(familyBranchAdmin.getCol1());
                familyBranchAdminOld.setCol2(familyBranchAdmin.getCol2());
                familyBranchAdminOld.setCol3(familyBranchAdmin.getCol3());
                familyBranchAdminOld.setCol4(familyBranchAdmin.getCol4());
                familyBranchAdminOld.setCol5(familyBranchAdmin.getCol5());
                familyBranchAdminOld.setIsDelete(false);
                familyBranchAdminOld.setOwnerId(familyBranchAdmin.getOwnerId());
                familyBranchAdminMapper.updateByPrimaryKeySelective(familyBranchAdminOld);
            }
        else {
            if (familyBranchAdmin.getCreateById() != null) {
                familyBranchAdminMapper.insert(familyBranchAdmin);
            }
        }
        return ResponseResult.ok();
    }

    @Override
    public void deleteFamilyBranchAdmin(Long familyBranchAdminId) {
        familyBranchAdminMapper.deleteByPrimaryKey(familyBranchAdminId);
    }

    @Override
    public List<FamilyBranchAdminVO> listFamilyBranchAdminInfo(Long familyBranchAdminId) {
        List<Long> familyAdminIds = new ArrayList<>();
        if (familyBranchAdminId == null){
            if(!SysRoleEnum.SUPER_MANAGE.getCode().equals(sessionManage.getSessionUserInfo().getUserRole())) {
                // 非超级管理员，直接返回空
                return new ArrayList<>();
            }
        }else {
            familyAdminIds.add(familyBranchAdminId);
        }
        List<FamilyBranchAdminVO> familyBranchAdminVOS =familyBranchAdminMapper.listFamilyBranchAdminInfo(familyAdminIds);
        return familyBranchAdminVOS;
    }

    @Override
    public void submmitFamilyBranchAdminApproval(Long memberId, Long familyBranchAdminId) {
            Example build = Example.builder(FamilyBranchAdmin.class)
            .where(Sqls.custom().andEqualTo("familyBranchAdminId", familyBranchAdminId)
                     .andEqualTo("memberId", memberId).andEqualTo("isDelete",false)).build();
        FamilyBranchAdmin familyBranchAdmin = familyBranchAdminMapper.selectOneByExample(build);

        List<FamilyBranchAdmin> familyBranchAdmins = familyBranchAdminMapper.selectByExample(build);

        familyBranchAdmin.setStatusId(FamilyStatus.in_approval.getCode());
        familyBranchAdminMapper.updateByPrimaryKeySelective(familyBranchAdmin);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void approvaledfamilyBranchAdminInfo(Long familyBranchAdminId) {
        FamilyBranchAdmin familyBranchAdmin = familyBranchAdminMapper.selectByPrimaryKey(familyBranchAdminId);
        familyBranchAdmin.setStatusId(FamilyStatus.saved.getCode());
        familyBranchAdminMapper.updateByPrimaryKeySelective(familyBranchAdmin);
        FamilyBranch familyBranch = familyBranchMapper.selectByPrimaryKey(familyBranchAdmin.getFamilyBranchId());
        familyBranch.setFamilyBranchId(familyBranchAdmin.getFamilyBranchId());
        familyBranch.setStatusId(FamilyStatus.saved.getCode());
        familyBranchMapper.updateByPrimaryKeySelective(familyBranch);
        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(familyBranchAdmin.getCol1());
        // 设置用户状态，使用一个备用字段
        memberBasicInfoEntity.setCol1(Constant.NORMAL);
        memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
        // 自动添加本人到分支成员,作为初始成员
        List<FamilyMemberMapping> familyMemberMappingList = familyMemberMappingMapper.selectByExample(Example.builder(FamilyMemberMapping.class)
                .where(Sqls.custom().andEqualTo("memberId", memberBasicInfoEntity.getMemberId())
                        .andEqualTo("statusId", FamilyMemberMapping.Status.saved.getCode())
                        .andEqualTo("isDelete", 0)
                        .andIsNull("familyBranchId")));
        if(familyMemberMappingList.size()>0) {
            FamilyMemberMapping familyMemberMapping = familyMemberMappingList.get(0);
            FamilyMemberMapping familyMemberMapping2=new FamilyMemberMapping();
            familyMemberMapping2.setMemberId(memberBasicInfoEntity.getMemberId());
            familyMemberMapping2.setStatusId(FamilyMemberMapping.Status.saved.getCode());
            familyMemberMapping2.setFamilyId(familyBranch.getFamilyId());
            familyMemberMapping2.setFamilyBasicId(familyMemberMapping.getFamilyBasicId());
            familyMemberMapping2.setFamilyBranchId(familyBranch.getFamilyBranchId());
            familyMemberMappingMapper.insertSelective(familyMemberMapping2);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refuseFamilyBranchAdminInfo(Long familyBranchAdminId, String message) {
        FamilyBranchAdmin familyBranchAdmin = familyBranchAdminMapper.selectByPrimaryKey(familyBranchAdminId);
        familyBranchAdmin.setCol2(message);
        familyBranchAdmin.setStatusId(FamilyStatus.invalid.getCode());
        familyBranchAdminMapper.updateByPrimaryKeySelective(familyBranchAdmin);
        FamilyBranch familyBranch = new FamilyBranch();
        familyBranch.setFamilyBranchId(familyBranchAdmin.getFamilyBranchId());
        familyBranch.setStatusId(FamilyStatus.invalid.getCode());
        familyBranchMapper.updateByPrimaryKeySelective(familyBranch);
        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(familyBranchAdmin.getCol1());
        // 如果用户申请了多个，并通过了其中一个，那么用户的状态也是正常
        if(!Constant.NORMAL.equals(memberBasicInfoEntity.getCol1())) {
            // 设置用户状态，使用一个备用字段
            memberBasicInfoEntity.setCol1(Constant.APPROVEL_CREATE_FAMILY_BRANCH_REJECT);
            memberBasicInfoEntity.setCol2(message);
            memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
        }
    }

    @Override
    public void updateBranchAdmin(List<UpdateBranchAdminDTO> branchAdminList) {
        for(UpdateBranchAdminDTO item:branchAdminList) {
            if(item.getFamilyBranchAdminId()==null && !item.getIsDelete()) {
                // 新增
                FamilyBranchAdmin familyBranchAdmin=new FamilyBranchAdmin();
                familyBranchAdmin.setStatusId(FamilyStatus.saved.getCode());
                familyBranchAdmin.setFamilyBranchId(item.getFamilyBranchId());
                familyBranchAdmin.setMemberId(item.getMemberId());
                familyBranchAdminMapper.insert(familyBranchAdmin);
            } else if(item.getFamilyBranchAdminId()!=null && item.getIsDelete()) {
                // 删除
                FamilyBranchAdmin familyBranchAdmin=new FamilyBranchAdmin();
                familyBranchAdmin.setFamilyBranchAdminId(item.getFamilyBranchAdminId());
                familyBranchAdmin.setIsDelete(true);
                familyBranchAdminMapper.updateByPrimaryKeySelective(familyBranchAdmin);
            }
            // 其他情况不变
        }
    }
}

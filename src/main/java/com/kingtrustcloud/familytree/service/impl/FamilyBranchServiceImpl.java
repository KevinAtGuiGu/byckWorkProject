package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.common.Constant;
import com.kingtrustcloud.common.HelpUtil;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.*;
import com.kingtrustcloud.familytree.enumeration.FamilyStatus;
import com.kingtrustcloud.familytree.enumeration.SysRoleEnum;
import com.kingtrustcloud.familytree.mapper.*;
import com.kingtrustcloud.familytree.service.FamilyBranchService;
import com.kingtrustcloud.familytree.vo.FamilyBranchApprovelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author kevin
 * create 2020-03-13 17:09
 * Description:
 */
@Service
public class FamilyBranchServiceImpl implements FamilyBranchService {
    @Autowired
    FamilyBranchMapper familyBranchMapper;
    @Autowired
    private SessionManage sessionManage;
    @Autowired
    private FamilyBranchAdminMapper familyBranchAdminMapper;
    @Autowired
    private FamilyMemberMappingMapper familyMemberMappingMapper;
    @Autowired
    private WeixinUserMapper weixinUserMapper;
    @Autowired
    private MemberBasicInfoMapper memberBasicInfoMapper;
    @Autowired
    private FamilyBasicMapper familyBasicMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public FamilyBranchAdmin saveFamilyBranch(FamilyBranch familyBranch) {
        //获取数据库所有分支
        if(familyBranchMapper.existByBranchName(familyBranch.getBranchName())) {
            throw new BusinessException("分支名已存在!");
        }
        MemberBasicInfoEntity memberBasicInfoEntity = sessionManage.getSessionUserInfo().getMemberBasicInfoEntity();
        // 保存本身为分支管理员
        FamilyBranchAdmin familyBranchAdmin = new FamilyBranchAdmin();
        if (familyBranch.getStatusId() == null) {
            // 新用户申请创建分支
            familyBranch.setStatusId(FamilyStatus.in_approval.getCode());
            familyBranchAdmin.setStatusId(FamilyStatus.in_approval.getCode());
            // 设置用户状态，使用一个备用字段
            memberBasicInfoEntity.setCol1(Constant.APPROVEL_CREATE_FAMILY_BRANCH);
            memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
        } else {
            familyBranchAdmin.setStatusId(FamilyStatus.saved.getCode());
        }
        familyBranchMapper.insert(familyBranch);
        familyBranchAdmin.setMemberId(memberBasicInfoEntity.getMemberId());
        familyBranchAdmin.setFamilyBranchId(familyBranch.getFamilyBranchId());
        familyBranchAdmin.setCol1(memberBasicInfoEntity.getMemberId().toString());
        familyBranchAdminMapper.insert(familyBranchAdmin);
        return familyBranchAdmin;
    }

    @Override
    public FamilyBranch getFamilyBranch(Long familyBranchId) {

        FamilyBranch familyBranch = familyBranchMapper.selectByPrimaryKey(familyBranchId);
        return familyBranch;
    }

    @Override
    public List<FamilyBranch> getFamilyBranchs(Long familyId) {
        List<FamilyBranch> famliyBranches;
        // 查询本人拥有的分支
        if (SysRoleEnum.SUPER_MANAGE.getCode().equals(sessionManage.getRole())) {
            // 超级管理员，查询所有分支
            famliyBranches = familyBranchMapper.selectByExample(Example.builder(FamilyBranch.class).where(Sqls.custom()
                    .andEqualTo("statusId", FamilyStatus.saved.getCode())
                    .andEqualTo("isDelete", false)).build());
        } else {
            List<FamilyBranchAdmin> familyBranchAdminList = familyBranchAdminMapper.selectByExample(Example.builder(FamilyBranchAdmin.class)
                    .where(Sqls.custom().andEqualTo("memberId", sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId())
                            .andEqualTo("statusId", FamilyStatus.saved.getCode())
                            .andEqualTo("isDelete", false)).build());
            List<Long> familyBranchIds = new ArrayList<>();
            familyBranchAdminList.forEach(item -> {
                familyBranchIds.add(item.getFamilyBranchId());
            });
            if(familyBranchIds.isEmpty()) {
                return new ArrayList<>();
            }
            famliyBranches = familyBranchMapper.selectByExample(Example.builder(FamilyBranch.class).where(Sqls.custom().andIn("familyBranchId", familyBranchIds).andEqualTo("isDelete",false)).build());
        }
        return famliyBranches;
    }

    @Override
    public void deleteFamilyBranch(Long familyId) {
        familyBranchMapper.deleteByPrimaryKey(familyId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void postApprovel(Long familyBranchId, Long memberId,String type) {
        //申请用户的信息
        MemberBasicInfoEntity memberBasicInfoEntity = sessionManage.getSessionUserInfo().getMemberBasicInfoEntity();
        Example build = Example.builder(FamilyMemberMapping.class)
                .where(Sqls.custom().andEqualTo("familyBranchId", familyBranchId)
                        .andEqualTo("memberId", memberId)
                        .andEqualTo("statusId", FamilyMemberMapping.Status.saved.getCode())
                        .andEqualTo("isDelete", false)).build();
        //关联用户的mapping记录
        FamilyMemberMapping familyMemberMapping1 = familyMemberMappingMapper.selectOneByExample(build);
        //创建一条申请记录，用于给管理员审批
        FamilyMemberMapping familyMemberMapping = new FamilyMemberMapping();
        familyMemberMapping.setFamilyBranchId(familyBranchId);
        familyMemberMapping.setFamilyId(familyMemberMapping1.getFamilyId());
        if("father".equals(type) || "brother".equals(type)) {
            // 父亲，则自动添加本人信息挂到父亲节点下
            familyMemberMapping.setFamilyBasicId(familyBasicMapper.getChildBasicId(familyMemberMapping1.getFamilyBasicId()));
            familyMemberMapping.setParentMemberId(familyMemberMapping1.getMemberId());
            familyMemberMapping.setMemberId(memberBasicInfoEntity.getMemberId());
        } else {
            familyMemberMapping.setFamilyBasicId(familyMemberMapping1.getFamilyBasicId());
            familyMemberMapping.setParentMemberId(familyMemberMapping1.getParentMemberId());
            familyMemberMapping.setMemberId(familyMemberMapping1.getMemberId());
        }
        familyMemberMapping.setStatusId(FamilyMemberMapping.Status.in_approval.getCode());
        // 此处用一个备用字段存储是那个用户的申请(申请用户的memberId)，审批时找到申请人的信息
        familyMemberMapping.setCol1(memberBasicInfoEntity.getMemberId().toString());
        familyMemberMappingMapper.insert(familyMemberMapping);
        // 设置用户状态，使用一个备用字段
        memberBasicInfoEntity.setCol1(Constant.APPROVEL_JOIN_FAMILY);
        //用户状态改变后，及时更新
        memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
    }

    @Override
    public List<FamilyBranchApprovelVO> listApprovel() {
        return familyBranchMapper.listApprovel(SysRoleEnum.SUPER_MANAGE.getCode().equals(sessionManage.getSessionUserInfo().getUserRole()),sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void approvel(Long familyMemberMappingId) {
        //找到提交申请时的familyMemberMapping记录
        FamilyMemberMapping familyMemberMapping = familyMemberMappingMapper.selectByPrimaryKey(familyMemberMappingId);
        //修改申请状态
        familyMemberMapping.setStatusId(FamilyMemberMapping.Status.approved.getCode());
        familyMemberMappingMapper.updateByPrimaryKeySelective(familyMemberMapping);
        //当前申请人的memberId
        String id = familyMemberMapping.getCol1();
        //关联人的信息
        MemberBasicInfoEntity news = memberBasicInfoMapper.selectByPrimaryKey(familyMemberMapping.getMemberId());
        if(!id.equals(familyMemberMapping.getMemberId().toString())) {
            // 申请人不是申请关联父亲
            //申请人的信息
            MemberBasicInfoEntity old = memberBasicInfoMapper.selectByPrimaryKey(id);
            //将申请人的账号密码设置为关联人的账号密码
            news.setAccount(old.getAccount());
            news.setPassword(old.getPassword());
            news.setMobileNo(old.getMobileNo());
            //申请人的is_delete设置为过期状态
            old.setIsDelete(true);
            memberBasicInfoMapper.updateByPrimaryKeySelective(old);
            //查询到与申请用户关联的微信号
            List<WeixinUser> weixinUserList = weixinUserMapper.selectByExample(Example.builder(WeixinUser.class).where(Sqls.custom()
                    .andEqualTo("memberId", old.getMemberId())
                    .andEqualTo("isDelete", false)).build());
            if (!weixinUserList.isEmpty()) {
                WeixinUser weixinUser = weixinUserList.get(0);
                //将微信信息与关联用户绑定(此时数据库中weixinUser的memberId还没有更改)
                weixinUser.setMemberId(news.getMemberId());
                // 取消原先绑定的微信用户（将关联用户原先关联的微信用户取消，memberId置空）
                weixinUserMapper.updateMemberIdToNullByMemberId(news.getMemberId());
                //设置关联用户与之前申请人关联的微信号关联
                weixinUserMapper.updateByPrimaryKeySelective(weixinUser);
            }
        }
        // 设置关联用户的状态，使用一个备用字段
        news.setCol1(Constant.NORMAL);
        memberBasicInfoMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public List<FamilyBranch> getFamilyBranchList() {
        List<FamilyBranch> familyBranches = familyBranchMapper.selectAll();
        return familyBranches;
    }

    @Override
    public void updateFamilyBranchById(Long familyBranchId, String familyBranchName) {
        FamilyBranch familyBranch = familyBranchMapper.selectByPrimaryKey(familyBranchId);
        familyBranch.setBranchName(familyBranchName);
        familyBranchMapper.updateByPrimaryKey(familyBranch);

    }

    @Override
    public void refuseApprovel(Long familyMemberMappingId, String message) {
        FamilyMemberMapping familyMemberMapping = familyMemberMappingMapper.selectByPrimaryKey(familyMemberMappingId);
        familyMemberMapping.setCol2(message);
        familyMemberMapping.setStatusId(FamilyMemberMapping.Status.invalid.getCode());
        familyMemberMappingMapper.updateByPrimaryKeySelective(familyMemberMapping);
        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(familyMemberMapping.getCol1());
        // 如果用户申请了多个，并通过了其中一个，那么用户的状态也是正常
        if (!Constant.NORMAL.equals(memberBasicInfoEntity.getCol1())) {
            // 设置用户状态，使用一个备用字段
            memberBasicInfoEntity.setCol1(Constant.APPROVEL_JOIN_FAMILY_REJECT);
            // 拒绝理由
            memberBasicInfoEntity.setCol2(message);
            memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
        }
    }

    @Override
    public List<FamilyBranch> searchFamilyBranchList(String keyWord) {
        List<FamilyBranch> familyBranches = familyBranchMapper.searchFamilyBranchList(keyWord);
        return familyBranches;
    }
}

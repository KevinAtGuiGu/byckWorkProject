package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.*;
import com.kingtrustcloud.familytree.enumeration.FamilyStatus;
import com.kingtrustcloud.familytree.enumeration.SysRoleEnum;
import com.kingtrustcloud.familytree.mapper.*;
import com.kingtrustcloud.familytree.service.MemberService;
import com.kingtrustcloud.familytree.vo.MemberVO;
import com.kingtrustcloud.familytree.vo.MergeBranchVO;
import com.kingtrustcloud.familytree.vo.SearchBranchResultVO;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberBasicInfoMapper memberBasicInfoMapper;
    @Autowired
    private FamilyMemberMappingMapper familyMemberMappingMapper;
    @Autowired
    private SessionManage sessionManage;
    @Autowired
    private FamilyBranchMapper familyBranchMapper;
    @Autowired
    private FamilyBranchAdminMapper familyBranchAdminMapper;
    @Autowired
    private FamilyBasicMapper familyBasicMapper;
    @Autowired
    private WeixinUserMapper weixinUserMapper;

    @Override
    public List<MemberBasicInfoEntity> getMembers(long groupId) {
        Example example = new Example(MemberBasicInfoEntity.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("groupId", groupId);
        example.orderBy("memberId").asc();
        return memberBasicInfoMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult saveMember(MemberBasicInfoEntity memberBasicInfo, FamilyMemberMapping familyMemberMapping1, Long mmemberId, String relation) {
        if (memberBasicInfo.getMemberId() == null) {
            memberBasicInfo.setRoleId(MemberBasicInfoEntity.Role.user.getCode());
            memberBasicInfo.setFamilyName(memberBasicInfo.getUserName());
            memberBasicInfoMapper.insert(memberBasicInfo);
            //更新本人与新增用户的关联关系
            Example example = Example.builder(FamilyMemberMapping.class).where(Sqls.custom()
                    .andEqualTo("memberId", mmemberId)
                    .andEqualTo("familyBranchId", familyMemberMapping1.getFamilyBranchId())
                    .andEqualTo("familyId", familyMemberMapping1.getFamilyId())
                    .andEqualTo("statusId", FamilyMemberMapping.Status.saved.getCode())
                    .andEqualTo("isDelete", false)).build();
            FamilyMemberMapping familyMemberMapping2 = familyMemberMappingMapper.selectOneByExample(example);

            //当前用户辈分信息
            Integer basicIndex = 0;
            Example build = Example.builder(FamilyBasic.class).where(Sqls.custom()
                    .andEqualTo("familyId", familyMemberMapping2.getFamilyId())
                    .andEqualTo("isDelete", false)).build();
            List<FamilyBasic> familyBasics = familyBasicMapper.selectByExample(build);
            if (familyBasics != null && familyBasics.size() > 0) {
                for (FamilyBasic familyBasic : familyBasics) {
                    if (familyBasic.getFamilyBasicId().equals(familyMemberMapping2.getFamilyBasicId())) {
                        basicIndex = familyBasic.getBasicIndex();
                    }
                }
            }
            int basicIndexInfo;
            //新增用户为当前用户的父亲，更新当前用户的Mapping关系表
            FamilyMemberMapping familyMemberMapping = new FamilyMemberMapping();
            if ("father".equals(relation)) {
                familyMemberMapping2.setParentMemberId(memberBasicInfo.getMemberId());
                basicIndexInfo = basicIndex - 1;
                Long familyBasicId = getMemberBasicInfo(familyBasics, basicIndexInfo);
                familyMemberMapping.setFamilyBasicId(familyBasicId);
                familyMemberMappingMapper.updateByPrimaryKeySelective(familyMemberMapping2);
            }
            //新增用户为当前用户的母亲
            if ("mather".equals(relation)) {
                if (familyMemberMapping2.getParentMemberId() != null) {
                    familyMemberMapping.setMateId(familyMemberMapping2.getParentMemberId());
                }
                basicIndexInfo = basicIndex - 1;
                Long familyBasicId = getMemberBasicInfo(familyBasics, basicIndexInfo);
                familyMemberMapping.setFamilyBasicId(familyBasicId);
                familyMemberMappingMapper.updateByPrimaryKeySelective(familyMemberMapping2);
            }
            // 建立关联关系
            familyMemberMapping.setMemberId(memberBasicInfo.getMemberId());
            familyMemberMapping.setFamilyId(familyMemberMapping1.getFamilyId());
            familyMemberMapping.setFamilyBranchId(familyMemberMapping1.getFamilyBranchId());
            familyMemberMapping.setFamilyBasicId(familyMemberMapping1.getFamilyBasicId());
            //新增妻子
            if ("wife".equals(relation)) {
                familyMemberMapping.setMateId(mmemberId);
                Long familyBasicId = getMemberBasicInfo(familyBasics, basicIndex);
                familyMemberMapping.setFamilyBasicId(familyBasicId);
            }
            //新增兄弟姐妹
            if ("brother".equals(relation)) {
                familyMemberMapping.setParentMemberId(familyMemberMapping2.getParentMemberId());
                Long familyBasicId = getMemberBasicInfo(familyBasics, basicIndex);
                familyMemberMapping.setFamilyBasicId(familyBasicId);
            }
            //子女
            if ("child".equals(relation)) {
                familyMemberMapping.setParentMemberId(mmemberId);
                basicIndexInfo = basicIndex + 1;
                Long familyBasicId = getMemberBasicInfo(familyBasics, basicIndexInfo);
                familyMemberMapping.setFamilyBasicId(familyBasicId);
            }
            familyMemberMapping.setStatusId(FamilyMemberMapping.Status.saved.getCode());
            familyMemberMappingMapper.insert(familyMemberMapping);
        } else {
            memberBasicInfoMapper.updateByPrimaryKey(memberBasicInfo);
            // 更新关联关系
            /*Example example = Example.builder(FamilyMemberMapping.class).where(Sqls.custom()
                    .andEqualTo("memberId", memberBasicInfo.getMemberId())
                    .andEqualTo("familyBranchId", familyMemberMapping1.getFamilyBranchId())).build();
            FamilyMemberMapping familyMemberMappings = familyMemberMappingMapper.selectOneByExample(example);
            familyMemberMappings.setParentMemberId(familyMemberMapping1.getParentMemberId());
            familyMemberMappings.setFamilyBasicId(familyMemberMapping1.getFamilyBasicId());
            familyMemberMappings.setMateId(familyMemberMapping1.getMateId());
            familyMemberMappingMapper.updateByPrimaryKey(familyMemberMappings);*/
        }
        return ResponseResult.ok();

    }

    @Override
    public MemberBasicInfoEntity getMember(long memberId) {
        return memberBasicInfoMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public void modifyMyInfo(MemberBasicInfoEntity memberBasicInfoEntity) {
//        MemberBasicInfoEntity entity = memberBasicInfoMapper.selectByPrimaryKey(memberBasicInfoEntity.getMemberId());
//        entity.setMemberId(memberBasicInfoEntity.getMemberId());
//        entity.setUserName(memberBasicInfoEntity.getUserName());
//        entity.setPassword(memberBasicInfoEntity.getPassword());
//        entity.setGenderId(memberBasicInfoEntity.getGenderId());
//        entity.setMobileNo(memberBasicInfoEntity.getMobileNo());
//        entity.setAccount(memberBasicInfoEntity.getAccount());
//        entity.setAddress(memberBasicInfoEntity.getAddress());
//        entity.setAge(memberBasicInfoEntity.getAge());
//        entity.setAgeRangeId(memberBasicInfoEntity.getAgeRangeId());
//        entity.setBirthday(memberBasicInfoEntity.getBirthday());
//        entity.setBirthdayPlace(memberBasicInfoEntity.getBirthdayPlace());
//        entity.setCountryId(memberBasicInfoEntity.getCountryId());
//        entity.setProvinceId(memberBasicInfoEntity.getProvinceId());
//        entity.setCityId(memberBasicInfoEntity.getCityId());
//        entity.setDistrictId(memberBasicInfoEntity.getDistrictId());
//        entity.setOtherName(memberBasicInfoEntity.getOtherName());
        memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
    }

    @Override
    public List<MemberVO> getMembersByFamilyBranchId(Long familyBranchId) {
        if (familyBranchId == null) {
            Long memberId = sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId();
            List<FamilyBranch> familyBranches = memberBasicInfoMapper.listFamilyBranchByMemberId(memberId);
            if (familyBranches.size() == 0) {
                // 如果是分支管理员，需要查询出分支管理员的第一个分支
                List<FamilyBranchAdmin> familyBranchAdminList = familyBranchAdminMapper.selectByExample(Example.builder(FamilyBranchAdmin.class)
                        .where(Sqls.custom()
                                .andEqualTo("memberId", memberId)
                                .andEqualTo("statusId", FamilyStatus.saved.getCode())
                                .andEqualTo("isDelete", false)).build());
                if (familyBranchAdminList.isEmpty()) {
                    return null;
                }
                familyBranchId = familyBranchAdminList.get(0).getFamilyBranchId();
            } else {
                familyBranchId = familyBranches.get(0).getFamilyBranchId();
            }
        }
        return memberBasicInfoMapper.getMembersByFamilyBranchId(familyBranchId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void mergeFamilyBranch(List<Long> familyBranchIdList) {
        //存放每一个分支数据
        List<List<MemberBasicInfoEntity>> lists = new ArrayList<>();
        //分支根节点集合
        Map<Long, String> map = new HashMap<>();

        Map<Long, SearchBranchResultVO> branchResultVOMap = new HashMap<>();
        //分支数据中所有的mapping关系
        Map<Long, List<FamilyMemberMapping>> mapFamilyMapping = new HashMap<>();

        List<FamilyMemberMapping> familyMemberMappings = new ArrayList<>();
        //合并后的分支数据
        //  if (familyBranchIdList != null && familyBranchIdList.size() > 0) {
        for (int i = 0; i < familyBranchIdList.size(); i++) {
            Boolean aBoolean = familyMemberMappingMapper.selectFamilyBranchMemberIsExsit(familyBranchIdList.get(i));
            if (!aBoolean){
                throw new BusinessException("合并的分支中分支数据不能为空");
            }
        }
        for (Long familyBranchId : familyBranchIdList) {
            FamilyBranch familyBranch = familyBranchMapper.selectByPrimaryKey(familyBranchId);
            //获取到每一个分支的根节点
            if (familyBranch != null) {
                SearchBranchResultVO searchBranchResultVO = familyMemberMappingMapper.searchRootMember(familyBranch.getFamilyBranchId());
                if (searchBranchResultVO == null) {
                    continue;
                }
                map.put(familyBranch.getFamilyBranchId(), searchBranchResultVO.getFamilyName());
                branchResultVOMap.put(familyBranch.getFamilyBranchId(), searchBranchResultVO);
                List<MemberBasicInfoEntity> memberBasicInfoEntities1 = new ArrayList<>();
                //获取每一个分支所在的分支成员数据
                Example build = Example.builder(FamilyMemberMapping.class)
                        .where(Sqls.custom()
                                .andEqualTo("familyBranchId", familyBranch.getFamilyBranchId())
                                .andEqualTo("familyId", familyBranch.getFamilyId())
                                .andEqualTo("statusId", 1)
                                .andEqualTo("isDelete", false)).build();
                familyMemberMappings = familyMemberMappingMapper.selectByExample(build);
                mapFamilyMapping.put(familyBranch.getFamilyBranchId(), familyMemberMappings);
                for (FamilyMemberMapping familyMemberMapping : familyMemberMappings) {
                    Long memberId = familyMemberMapping.getMemberId();
                    MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(memberId);
                    memberBasicInfoEntities1.add(memberBasicInfoEntity);
                }
                lists.add(memberBasicInfoEntities1);
            }
        }
        // }
        //如果分支数据完全相同，删除其他分支(获取最长的分支，删除其他分支)
        List<String> rootMemberList = new ArrayList<>();
        for (String value : map.values()) {
            rootMemberList.add(value);
        }
        Long memberId2 = null;
        if (rootMemberList.size() > 0) {
            if (new HashSet<Object>(rootMemberList).size() == 1) {
                int maxSize = 0;
                for (int i = 0; i < lists.size(); i++) {
                    if (maxSize <= lists.get(i).size()) {
                        maxSize = lists.get(i).size();
                        memberId2 = lists.get(i).get(0).getMemberId();
                    }
                }
                //要保留的分支的Id
                //Long familyBranchId = getKey(map, familyName);
                Long familyBranchId = getKey(branchResultVOMap, memberId2);
                List<FamilyMemberMapping> familyMemberMappings2 = mapFamilyMapping.get(familyBranchId);

                for (Long key1 : map.keySet()) {
                    if (familyBranchId != null) {
                        if (!familyBranchId.equals(key1)) {
                            //父分支的所有mapping数据
                            List<FamilyMemberMapping> familyMemberMappings1 = mapFamilyMapping.get(key1);
                            Long memberIdparent = null;
                            Long familyBranchIdParent = null;
                            for (FamilyMemberMapping familyMemberMapping : familyMemberMappings2) {
                                if (familyMemberMapping.getParentMemberId() == null) {
                                    memberIdparent = familyMemberMapping.getMemberId();
                                    familyBranchIdParent = familyMemberMapping.getFamilyBranchId();
                                }
                            }
                            List<MemberBasicInfoEntity> memberParentList = new ArrayList<>();
                            boolean isFlag = false;
                            //获取父分支中的所有成员数据
                            for (List<MemberBasicInfoEntity> list : lists) {
                                if (!isFlag) {
                                    for (MemberBasicInfoEntity memberBasicInfoEntity : list) {
                                        if (memberBasicInfoEntity.getMemberId().equals(memberIdparent)) {
                                            memberParentList = list;
                                            isFlag = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            Long memberId = null;
                            for (FamilyMemberMapping familyMemberMapping : familyMemberMappings1) {
                                if (familyMemberMapping.getParentMemberId() == null) {
                                    memberId = familyMemberMapping.getMemberId();
                                    continue;
                                }
                                if (familyMemberMapping.getParentMemberId() != null && familyMemberMapping.getParentMemberId().equals(memberId)) {
                                    MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(familyMemberMapping.getMemberId());
                                    //根据memberBasicInfoEntity,在父类集合中查找
                                    List<MemberBasicInfoEntity> collect = memberParentList.stream().filter(entity -> entity.getFamilyName().equals(memberBasicInfoEntity.getFamilyName())).collect(Collectors.toList());
                                    if (collect.size() == 0) {
                                        familyMemberMapping.setParentMemberId(memberIdparent);
                                        familyMemberMapping.setFamilyBranchId(familyBranchIdParent);
                                        familyMemberMappingMapper.updateByPrimaryKeySelective(familyMemberMapping);
                                    }
                                }
                            }
                            //清除被合并的分支
                            FamilyBranch familyBranch = familyBranchMapper.selectByPrimaryKey(key1);
                            familyBranch.setStatusId(FamilyStatus.invalid.getCode());
                            familyBranchMapper.updateByPrimaryKeySelective(familyBranch);
                            //变更分支管理员
                            Example example = Example.builder(FamilyBranchAdmin.class).where(Sqls.custom()
                                    .andEqualTo("familyBranchId", familyBranch.getFamilyBranchId())
                                    .andEqualTo("isDelete", false)).build();
                            List<FamilyBranchAdmin> familyBranchAdmins = familyBranchAdminMapper.selectByExample(example);
                            if (familyBranchAdmins != null && familyBranchAdmins.size() > 0) {
                                for (FamilyBranchAdmin familyBranchAdmin : familyBranchAdmins) {
                                    familyBranchAdmin.setFamilyBranchId(familyBranchIdParent);
                                    familyBranchAdminMapper.updateByPrimaryKeySelective(familyBranchAdmin);
                                }
                            }
                        }
                    }
                }
            } else {
                boolean flag = false;
                for (SearchBranchResultVO value : branchResultVOMap.values()) {

                    //找到当前分支可以合并的分支
                    List<MergeBranchVO> familyBranches = familyMemberMappingMapper.branchSearch(value.getFamilyBasicId(), value.getFamilyName());

                    if (familyBranches != null && familyBranches.size() > 0) {
                        flag = true;
                        MergeBranchVO mergeBranchVO = familyBranches.get(0);
                        //找到所在分支的所有mapping数据
                        Example example = Example.builder(FamilyMemberMapping.class).where(Sqls.custom()
                                .andEqualTo("familyBranchId", value.getFamilyBranchId())
                                .andEqualTo("statusId", 1)
                                .andEqualTo("isDelete", false)).build();
                        List<FamilyMemberMapping> familyMemberMappings1 = familyMemberMappingMapper.selectByExample(example);
                        // 遍历Mappings
                        for (FamilyMemberMapping familyMemberMapping : familyMemberMappings1) {
                            //不是根元素
                            if (familyMemberMapping.getParentMemberId() != null) {
                                //更改根节点
                                familyMemberMapping.setParentMemberId(mergeBranchVO.getMemberId());
                                familyMemberMapping.setFamilyBranchId(mergeBranchVO.getFamilyBranchId());
                                familyMemberMappingMapper.updateByPrimaryKeySelective(familyMemberMapping);
                            }
                        }
                        FamilyBranch familyBranch1 = familyBranchMapper.selectByPrimaryKey(value.getFamilyBranchId());
                        familyBranch1.setStatusId(FamilyStatus.invalid.getCode());
                        familyBranchMapper.updateByPrimaryKeySelective(familyBranch1);
                        //边跟分支管理员
                        Example example1 = Example.builder(FamilyBranchAdmin.class).where(Sqls.custom()
                                .andEqualTo("familyBranchId", familyBranch1.getFamilyBranchId())
                                .andEqualTo("isDelete", false)).build();
                        List<FamilyBranchAdmin> familyBranchAdmins = familyBranchAdminMapper.selectByExample(example1);
                        if (familyBranchAdmins != null && familyBranchAdmins.size() > 0) {
                            for (FamilyBranchAdmin familyBranchAdmin : familyBranchAdmins) {
                                familyBranchAdmin.setFamilyBranchId(mergeBranchVO.getFamilyBranchId());
                                familyBranchAdminMapper.updateByPrimaryKeySelective(familyBranchAdmin);
                            }
                        }
                    }
                }
                if (!flag) {
                    throw new BusinessException("分支内容无关联性，不可进行合并");
                }
            }
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMember(Long memberId, Long familyBranchId) {
        // 先检查此成员是否是叶子节点，如果不是，不能删除
        List<FamilyMemberMapping> familyMemberMappingList1 = familyMemberMappingMapper.selectByExample(Example.builder(FamilyMemberMapping.class)
                .where(Sqls.custom().andEqualTo("parentMemberId", memberId)
                        .andEqualTo("isDelete", false)
                        .andEqualTo("statusId", FamilyMemberMapping.Status.saved.getCode())).build());
        if (!familyMemberMappingList1.isEmpty()) {
            throw new BusinessException("此成员有子女，请先删除子女！");
        }
        // 删除成员关系，先查看此成员是否关联了微信用户，如果关联了，只删除关联关系，没有关联则删除。如果有用户正在申请关联这个成员，那么不能删除，提示请先拒绝此成员申请。
        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(memberId);
        List<FamilyMemberMapping> familyMemberMappingList3 = familyMemberMappingMapper.selectByExample(Example.builder(FamilyMemberMapping.class)
                .where(Sqls.custom().andEqualTo("memberId", memberId).andEqualTo("familyBranchId", familyBranchId)
                        .andEqualTo("statusId", FamilyMemberMapping.Status.saved.getCode()).andEqualTo("isDelete", false)).build());
        FamilyMemberMapping familyMemberMapping;
        if (!familyMemberMappingList3.isEmpty()) {
            familyMemberMapping = familyMemberMappingList3.get(0);
        } else {
            return;
        }
        // 权限检查
//        Long editMemberId = sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId();
//        if (!(SysRoleEnum.SUPER_MANAGE.getCode().equals(sessionManage.getRole())
//                || SysRoleEnum.BRANCH_MANAGE.getCode().equals(sessionManage.getRole()))) {
//            List<FamilyMemberMapping> familyMemberMappingList2 = familyMemberMappingMapper.selectByExample(Example.builder(FamilyMemberMapping.class)
//                    .where(Sqls.custom().andEqualTo("memberId", editMemberId)
//                            .andEqualTo("isDelete", false)
//                            .andEqualTo("statusId", FamilyMemberMapping.Status.saved.getCode())).build());
//            FamilyMemberMapping editFamilyMemberMapping = familyMemberMappingList2.get(0);
//            // 判断编辑人跟被编辑人的关系，父 母 同胞 子 夫妻
//            if (!(editMemberId.equals(familyMemberMapping.getParentMemberId()) // 子
//                    || editMemberId.equals(familyMemberMapping.getMemberId()) // 自己
//                    || editMemberId.equals(familyMemberMapping.getMateId()) // 妻子
//                    || familyMemberMapping.getParentMemberId().equals(editFamilyMemberMapping.getParentMemberId()) // 同胞
//                    || familyMemberMapping.getMemberId().equals(editFamilyMemberMapping.getParentMemberId()) // 父
//                    || (familyMemberMapping.getMateId() != null && familyMemberMapping.getMateId().equals(editFamilyMemberMapping.getParentMemberId())))) { //母
//                throw new BusinessException("抱歉，您无修改此成员的权限！");
//            }
//        }
        Example build = Example.builder(WeixinUser.class).where(Sqls.custom().andEqualTo("memberId", memberId).andEqualTo("isDelete", false)).build();
        List<WeixinUser> weixinUserList = weixinUserMapper.selectByExample(build);
        List<FamilyMemberMapping> familyMemberMappingList = familyMemberMappingMapper
                .selectByExample(Example.builder(FamilyMemberMapping.class)
                        .where(Sqls.custom().andEqualTo("memberId", memberId)
                                .andEqualTo("statusId", FamilyMemberMapping.Status.in_approval.getCode())
                                .andEqualTo("isDelete", false)).build());
        if (!familyMemberMappingList.isEmpty()) {
            throw new BusinessException("有微信用户申请关联此成员，请先拒绝微信用户关联此成员的申请");
        }
        if (!weixinUserList.isEmpty() || memberBasicInfoEntity.getAccount() != null) {
            // 有微信用户关联了此成员，或此成员有账号，只删除关联关系
            familyMemberMapping.setIsDelete(true);
            familyMemberMappingMapper.updateByPrimaryKey(familyMemberMapping);
        } else {
            familyMemberMapping.setIsDelete(true);
            familyMemberMappingMapper.updateByPrimaryKey(familyMemberMapping);
            memberBasicInfoEntity.setIsDelete(true);
            memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
        }

    }

    @Override
    public void saveMember2(Long familyId, Long familyBranchId, MemberBasicInfoEntity entity, Long familyBasicId) {
//        char c = entity.getUserName().charAt(1);
//        Example build = Example.builder(FamilyBasic.class).where(Sqls.custom().andEqualTo("familyBasicName", c).andEqualTo("isDelete", false)).build();
//        FamilyBasic familyBasic = familyBasicMapper.selectOneByExample(build);
//        if (familyBasic == null) {
//            throw new BusinessException("输入的辈分信息错误");
//        }
        entity.setRoleId(MemberBasicInfoEntity.Role.user.getCode());
        entity.setFamilyName(entity.getUserName());
        memberBasicInfoMapper.insert(entity);
        //建立映射关系
        FamilyMemberMapping familyMemberMapping = new FamilyMemberMapping();
        familyMemberMapping.setFamilyId(familyId);
        familyMemberMapping.setFamilyBranchId(familyBranchId);
        familyMemberMapping.setFamilyBasicId(familyBasicId);
        familyMemberMapping.setMemberId(entity.getMemberId());
        familyMemberMapping.setStatusId(FamilyMemberMapping.Status.saved.getCode());
        familyMemberMappingMapper.insert(familyMemberMapping);
    }

    /**
     * 获取成员的辈分信息
     *
     * @param familyBasics 家族辈分列表
     * @return 获取家族辈分信息
     */
    public Long getMemberBasicInfo(List<FamilyBasic> familyBasics, Integer basicIndex) {
        if (familyBasics != null && familyBasics.size() > 0) {
            for (FamilyBasic familyBasic : familyBasics) {
                if (familyBasic.getBasicIndex().equals(basicIndex)) {
                    return familyBasic.getFamilyBasicId();
                }
            }
        }
        return null;
    }

    private static Long getKey(@NotNull Map<Long, SearchBranchResultVO> map, Long memberId2) {
        Long key;
        for (Map.Entry<Long, SearchBranchResultVO> entry : map.entrySet()) {
            if (memberId2.equals(entry.getValue().getMemberId())) {
                key = entry.getKey();
                return key;
            }
        }
        return null;
    }

}

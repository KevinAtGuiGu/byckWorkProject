package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.common.Constant;
import com.kingtrustcloud.common.PageUtils;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.FamilyBasic;
import com.kingtrustcloud.familytree.mapper.FamilyBasicMapper;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.service.FamilyBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 16:29
 * @Description:
 */
@Service
public class FamilyBasicServiceImpl implements FamilyBasicService {
    @Autowired
    FamilyBasicMapper familyBasicMapper;

    @Autowired
    SessionManage sessionManage;

    @Autowired
    MemberBasicInfoMapper memberBasicInfoMapper;

    @Override
    public void saveFamilyBasic(FamilyBasic familyBasic) {
        //查询用户的权限
        if (null != familyBasic.getFamilyBasicId()) {
            FamilyBasic familyBasicOld = familyBasicMapper.selectByPrimaryKey(familyBasic.getFamilyBasicId());
            familyBasicOld.setMemberId(familyBasic.getMemberId());
            familyBasicOld.setFamilyId(familyBasic.getFamilyId());
            familyBasicOld.setBasicIndex(familyBasic.getBasicIndex());
            familyBasicOld.setFamilyBasicName(familyBasic.getFamilyBasicName());
            familyBasicOld.setDescription(familyBasic.getDescription());
            familyBasicOld.setCol1(familyBasic.getCol1());
            familyBasicOld.setCol2(familyBasic.getCol2());
            familyBasicOld.setCol3(familyBasic.getCol3());
            familyBasicOld.setCol4(familyBasic.getCol4());
            familyBasicOld.setCol5(familyBasic.getCol5());
            familyBasicMapper.updateByPrimaryKeySelective(familyBasicOld);
        } else {
            List<FamilyBasic> familyBasicList = familyBasicMapper.selectByExample(Example.builder(FamilyBasic.class)
                    .where(Sqls.custom().andEqualTo("familyId", Constant.FAMILY_ID_TIAN)
                            .andEqualTo("isDelete", false)).orderBy("basicIndex").build());
            if (familyBasicList.size() > 0) {
                FamilyBasic familyBasic1 = familyBasicList.get(familyBasicList.size() - 1);
                if (familyBasic1.getBasicIndex() != null) {
                    familyBasic.setBasicIndex(familyBasic1.getBasicIndex() + 1);
                }
            } else {
                familyBasic.setBasicIndex(1);
            }
            familyBasic.setMemberId(sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId());
            familyBasicMapper.insert(familyBasic);
        }
    }

    @Override
    public FamilyBasic getFamilyBasic(Long familyBasicId) {
        FamilyBasic famliyBasic = familyBasicMapper.selectByPrimaryKey(familyBasicId);
        return famliyBasic;
    }

    @Override
    public void deleteFamilyBasic(Long familyBasicId) {
        familyBasicMapper.deleteByPrimaryKey(familyBasicId);
    }

    @Override
    public void updateHereditaryById(Long familyBasicId, String familyBasicName) {

        FamilyBasic familyBasic = familyBasicMapper.selectByPrimaryKey(familyBasicId);
        familyBasic.setFamilyBasicName(familyBasicName);
        familyBasicMapper.updateByPrimaryKeySelective(familyBasic);
    }

    @Override
    public PageUtils getHereditaryList(Long familyId, Integer pageSize, Integer currPage) {
        //从数据库中获取所有数据
        Example example = new Example(FamilyBasic.class);
        Example.Criteria criteria = example.createCriteria();
        if (familyId == null) {
            criteria.andEqualTo("familyId", Constant.FAMILY_ID_TIAN);
        } else {
            criteria.andEqualTo("familyId", familyId);
        }
        example.orderBy("basicIndex").asc();
        //获取到所有数据
        List<FamilyBasic> famliyBasics = familyBasicMapper.selectByExample(example);
        int totalCount = famliyBasics.size();
        PageUtils pageUtils = new PageUtils(currPage, pageSize, totalCount);
        //获取startIndex
        int startIndex = pageUtils.getStartIndex();
        pageUtils.setRows(familyBasicMapper.selectPage(startIndex, pageSize,familyId));
        return pageUtils;
    }


}

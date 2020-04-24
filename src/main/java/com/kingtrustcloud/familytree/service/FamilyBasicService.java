package com.kingtrustcloud.familytree.service;

import com.kingtrustcloud.common.PageUtils;
import com.kingtrustcloud.familytree.entity.FamilyBasic;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 16:28
 * @Description:
 */
public interface FamilyBasicService {
    void saveFamilyBasic(FamilyBasic famliyBasic);

    FamilyBasic getFamilyBasic(Long familyBasicId);

    void deleteFamilyBasic(Long familyBasicId);

    void updateHereditaryById(Long familyBasicId, String hereditaryName);


    PageUtils getHereditaryList(Long familyId, Integer pageSize, Integer currPage);
}

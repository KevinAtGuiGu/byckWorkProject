package com.kingtrustcloud.familytree.service;

import com.kingtrustcloud.familytree.entity.Family;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 15:40
 * @Description:
 */
public interface FamilyService {
    void saveFamily(Family famliy);

    Family getFamily(Long familyId);

    void deleteFamily(Long familyId);

    List<Family> listFamily(Long memberId, String familyName);

    Family getFamilyByName(String familyName);
}

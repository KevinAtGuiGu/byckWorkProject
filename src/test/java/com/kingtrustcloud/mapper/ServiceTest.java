package com.kingtrustcloud.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingtrustcloud.FamilytreeAppTest;
import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.service.MemberService;
import com.kingtrustcloud.familytree.vo.MemberVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @version V1.0
 * @Title: ServiceTest
 * @Package com.kingtrustcloud.mapper
 * @Description: ()
 * @author: tanyong
 * @date: 2020/3/19 16:33
 */
public class ServiceTest extends FamilytreeAppTest{

    @Autowired
    MemberService memberService;

    @Test
    public void test_saveMember() {
//        MemberBasicInfoEntity memberBasicInfo = new MemberBasicInfoEntity();
//        memberBasicInfo.setUserName("田雷");
//        memberBasicInfo.setFamilyName("田雷");
//        memberBasicInfo.setGenderId(1);
//        FamilyMemberMapping familyMemberMapping1 = new FamilyMemberMapping();
//        familyMemberMapping1.setFamilyBranchId(1L);
//        familyMemberMapping1.setFamilyBasicId(7L);
//        familyMemberMapping1.setFamilyId(1L);
//        familyMemberMapping1.setParentMemberId(26L);
//        familyMemberMapping1.setMateId(34L);
//        memberService.saveMember(memberBasicInfo,familyMemberMapping1);
    }

    @Test
    public void test_getMembersByFamilyBranchId() {
        List<MemberVO> membersByFamilyBranchId = memberService.getMembersByFamilyBranchId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(membersByFamilyBranchId));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

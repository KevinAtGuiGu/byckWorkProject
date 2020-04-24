package com.kingtrustcloud.familytree.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.common.SessionUserInfo;
import com.kingtrustcloud.familytree.entity.*;
import com.kingtrustcloud.familytree.enumeration.SmsCodeType;
import com.kingtrustcloud.familytree.mapper.FamilyBranchMapper;
import com.kingtrustcloud.familytree.mapper.FamilyMapper;
import com.kingtrustcloud.familytree.mapper.FamilyMemberMappingMapper;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.service.*;
import com.kingtrustcloud.familytree.vo.MergeBranchVO;
import com.kingtrustcloud.familytree.vo.MyInfoVO;
import com.kingtrustcloud.familytree.vo.NoticeVO;
import com.kingtrustcloud.familytree.vo.SearchBranchResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(value = "我的页面Api接口层", tags = "我的页面Api接口类")
@Controller
public class MyController {
    @Autowired
    MemberService memberService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    SessionManage sessionManage;

    @Autowired
    private MemberBasicInfoMapper memberBasicInfoMapper;
    @Autowired
    private FamilyMemberMappingMapper familyMemberMappingMapper;
    @Autowired
    private FamilyBranchMapper familyBranchMapper;
    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private SmsService smsService;

    @Autowired
    private FamilyMemberMappingService familyMemberMappingService;

    @Autowired
    private FamilyBranchService familyBranchService;

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String index() {
        return "my/index";
    }

    @ResponseBody
    @ApiOperation(value = "修改个人信息", notes = "修改个人信息")
    @RequestMapping(value = "/modifyMyInfo", method = RequestMethod.POST)
    public ResponseResult modifyMyInfo(MemberBasicInfoEntity memberBasicInfoEntity) {
        memberService.modifyMyInfo(memberBasicInfoEntity);
        return ResponseResult.ok();
    }

    @RequestMapping(value = "/myFamily", method = RequestMethod.POST)
    public ResponseResult getMyFamily(Family famliy) {
        MemberBasicInfoEntity basicInfoEntity = new MemberBasicInfoEntity();

        return ResponseResult.ok();
    }

    @ApiOperation(value = "保存公告", notes = "保存公告")
    @ResponseBody
    @PostMapping(value = "/saveNotice")
    public ResponseResult saveNotice(@RequestBody NoticeEntity noticeEntity) {
        noticeService.saveNotice(noticeEntity);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "公告列表", notes = "公告列表")
    @ResponseBody
    @GetMapping("/noticeList")
    public ResponseResult getNoticeList(Long noticeId) {
        List<NoticeVO> noticeList = noticeService.getNoticeList(noticeId);
        if (noticeList != null && noticeList.size() > 0) {
            return ResponseResult.ok(noticeList);
        }
        return ResponseResult.error("没有公告");
    }

    /**
     * 审核公告
     */
    @ApiOperation(value = "审核公告", notes = "审核公告")
    @PostMapping("/approvalNotice")
    public ResponseResult approvalNotice(Long noticeId) {
        noticeService.approvalNotice(noticeId);
        return ResponseResult.ok();
    }

    /**
     * 获取我的页面信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取我的页面信息", notes = "获取我的页面信息")
    @GetMapping("/getMyInfo")
    public ResponseResult getMyInfo() {
        SessionUserInfo sessionUserInfo = sessionManage.getSessionUserInfo();
        Long memberId = sessionUserInfo.getMemberBasicInfoEntity().getMemberId();
        MyInfoVO myInfoVO = new MyInfoVO();
        if (memberId != null) {
            MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(memberId);
            myInfoVO.setMemberId(memberBasicInfoEntity.getMemberId());
            myInfoVO.setAccount(memberBasicInfoEntity.getAccount());
            myInfoVO.setAddress(memberBasicInfoEntity.getAddress());
            myInfoVO.setBusinessZoneId(memberBasicInfoEntity.getBusinessZoneId());
            myInfoVO.setCityId(memberBasicInfoEntity.getCityId());
            myInfoVO.setCountryId(memberBasicInfoEntity.getCountryId());
            myInfoVO.setDistrictId(memberBasicInfoEntity.getDistrictId());
            myInfoVO.setGenderId(memberBasicInfoEntity.getGenderId());
            myInfoVO.setIdCardNo(memberBasicInfoEntity.getIdCardNo());
            myInfoVO.setHeadImageUrl(memberBasicInfoEntity.getHeadThumb());
            myInfoVO.setMobileNo(memberBasicInfoEntity.getMobileNo());
            myInfoVO.setOtherName(memberBasicInfoEntity.getOtherName());
            myInfoVO.setProvinceId(memberBasicInfoEntity.getProvinceId());
            myInfoVO.setUserName(memberBasicInfoEntity.getUserName());
            myInfoVO.setFamilyName(memberBasicInfoEntity.getFamilyName());
            List<FamilyMemberMapping> familyMemberMappingList = familyMemberMappingMapper.selectByExample(Example.builder(FamilyMemberMapping.class).where(Sqls.custom().andEqualTo("memberId", memberId).andEqualTo("isDelete", false)).build());
            if (familyMemberMappingList.size() > 0) {
                FamilyMemberMapping familyMemberMapping = familyMemberMappingList.get(0);
                FamilyBranch familyBranch = familyBranchMapper.selectByPrimaryKey(familyMemberMapping.getFamilyBranchId());
                Family family = familyMapper.selectByPrimaryKey(familyBranch.getFamilyId());
                myInfoVO.setFamilyName(family.getFamilyName());
                myInfoVO.setFamilyCode(family.getCode());
            }
            return ResponseResult.ok(myInfoVO);
        }
        return null;
    }

    /**
     * 修改手机号
     */
    @ResponseBody
    @ApiOperation(value = "我的页面中修改手机号", notes = "我的页面中修改手机号")
    @PostMapping("/modifyMyPhone")
    public ResponseResult modifyMyPhone(Long memberId, String phone, String smsCode) {
        //调用图形验证码，图形验证后发送短信，确定时验证短信
        sessionManage.checkSmsCode(phone,smsCode, SmsCodeType.MODIFYMYPHONE.getCode());
        MyInfoVO myInfoVO = new MyInfoVO();
        if (memberId != null) {
            MemberBasicInfoEntity infoEntity = memberBasicInfoMapper.selectByPrimaryKey(memberId);
            infoEntity.setMobileNo(phone);
            infoEntity.setAccount(phone);
            myInfoVO.setAccount(phone);
            myInfoVO.setMobileNo(phone);
            memberBasicInfoMapper.updateByPrimaryKey(infoEntity);
            return ResponseResult.ok(myInfoVO);
        }
        return null;
    }

//    /**
//     * 获取短信验证码
//     */
//    @ResponseBody
//    @ApiOperation(value = "我的页面中发送短信验证码", notes = "我的页面中发送短信验证码")
//    @PostMapping("/mySendMessagge")
//    public ResponseResult mySendMessagge(HttpServletRequest request) {
//        Long memberId = sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId();
//        MemberBasicInfoEntity basicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(memberId);
//        String mobileNo = basicInfoEntity.getMobileNo();
//        try {
//            String smsCode2 = smsService.sendSms(request, mobileNo);
//            return ResponseResult.ok(smsCode2);
//        } catch (Exception e) {
//            return ResponseResult.error(e.getMessage());
//        }
//    }

    /**
     * 身份证验证
     */
    @ResponseBody
    @ApiOperation(value = "我的页面中修改身份证", notes = "我的页面中修改身份证")
    @PostMapping("/modifyMyIdCard")
    public ResponseResult modifyIdCard(String idCard, String smsCode) {
        sessionManage.checkSmsCode(sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getAccount(),smsCode,SmsCodeType.MODIFYIDCARD.getCode());
        MyInfoVO myInfoVO = new MyInfoVO();
        MemberBasicInfoEntity entity = memberBasicInfoMapper.selectByPrimaryKey(sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId());
        entity.setIdCardNo(idCard);
        myInfoVO.setIdCardNo(idCard);
        memberBasicInfoMapper.updateByPrimaryKey(entity);
        return ResponseResult.ok(myInfoVO);
    }

    /**
     * 查找到所有分支的根节点
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "列出所有可合并的分支", notes = "列出所有可合并的分支")
    @GetMapping("/searchListMergeBranch")
    public ResponseResult searchBranchResultVOS() {
        List<SearchBranchResultVO> searchBranchResultVOS = familyMemberMappingService.searchBranchResult();
        return ResponseResult.ok(searchBranchResultVOS);
    }

    /**
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "可合并分支", notes = "可合并分支")
    @GetMapping("/branchSearch")
    public ResponseResult branchSearch(Long familyBasicId, String familyName) {
        List<SearchBranchResultVO> list = familyMemberMappingService.searchBranchResult();
        List<MergeBranchVO> familyBranches = new ArrayList<>();
        familyBranches = familyMemberMappingService.branchSearch(familyBasicId, familyName);
      /*  for (SearchBranchResultVO searchBranchResultVO : list) {
            Long familyBasicId = searchBranchResultVO.getFamilyBasicId();
            String familyName = searchBranchResultVO.getFamilyName();
            familyBranches = familyMemberMappingService.branchSearch(familyBasicId, familyName);
        }*/
        return ResponseResult.ok(familyBranches);
    }

    /**
     * 合并分支（页面上有两个相同分支）
     */
    @ResponseBody
    @ApiOperation(value = "合并分支", notes = "合并分支")
    @PostMapping("/mergeFamilyBranch")
    public ResponseResult mergeFamilyBranch(@RequestParam(name = "familyBranchIdList[]") Long[] familyBranchIdList) {
        try {
            memberService.mergeFamilyBranch(Arrays.asList(familyBranchIdList));
        } catch (Exception e) {
            return ResponseResult.error(e.getMessage());
        }
        return ResponseResult.ok();
    }

    /**
     * 合并分支页面搜索分支
     *
     * @param keyWord 关键字
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "搜索分支", notes = "搜索分支")
    @PostMapping("/searchFamilyBranchList")
    public ResponseResult searchFamilyBranch(@RequestParam(value = "keyWord") String keyWord,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 0 : pageNum, pageSize == null ? 10 : pageSize);
        PageInfo<FamilyBranch> pageInfo = new PageInfo<>(familyBranchService.searchFamilyBranchList(keyWord));
        return ResponseResult.ok(pageInfo);
    }

    @ApiOperation(value = "退出登陆", notes = "退出登陆")
    @ResponseBody
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        SessionUserInfo sessionUserInfo = sessionManage.getSessionUserInfo();
        if (sessionUserInfo.getWeixinUser() != null) {
            // 微信登陆用户，只删除memberBasicInfoEntity和角色信息
            sessionUserInfo.setMemberBasicInfoEntity(null);
            sessionUserInfo.setUserRole(null);
        } else {
            // 否则删除所有session信息
            request.getSession().invalidate();
        }
    }
}

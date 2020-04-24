package com.kingtrustcloud.familytree.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.common.Constant;
import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.common.SessionUserInfo;
import com.kingtrustcloud.familytree.dto.SearchFamilyBranchDTO;
import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.enumeration.SmsCodeType;
import com.kingtrustcloud.familytree.mapper.FamilyBranchMapper;
import com.kingtrustcloud.familytree.mapper.FamilyMemberMappingMapper;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.mapper.WeixinUserMapper;
import com.kingtrustcloud.familytree.service.SmsService;
import com.kingtrustcloud.familytree.vo.SearchFamilyBranchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Api(value = "注册Api接口层", tags = "注册Api接口类")
@Controller
public class IndexController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private SmsService smsService;

    @Autowired
    private MemberBasicInfoMapper memberBasicInfoMapper;
    @Autowired
    private WeixinUserMapper weixinUserMapper;
    @Autowired
    private SessionManage sessionManage;
    @Autowired
    private FamilyBranchMapper familyBranchMapper;
    @Autowired
    private FamilyMemberMappingMapper familyMemberMappingMapper;

    @Value("${spring.profiles.active}")
    private String profiles;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "auth/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 生成图形验证码
     *
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "图形验证码", notes = "图形验证码")
    @ResponseBody
    @GetMapping(value = "/drawCaptcha")
    public void drawCaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //得到验证码 生成指定验证码
        String code = defaultKaptcha.createText();
        sessionManage.setKaptchaCode(code);
        // 生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(code);
        response.setContentType("image/png");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    /**
     * 发送短信
     * @param type 发送的验证码类型
     * @param kaptchaCode 图片校验码
     * @param phone  手机号
     * @param request
     * @return 短信验证码
     */
    @ApiOperation(value = "发送短信", notes = "发送短信")
    @ResponseBody
    @PostMapping(value = "/sendSms")
    public ResponseResult sendSms(String type,String kaptchaCode, String phone, HttpServletRequest request) {
        String code = sessionManage.getKaptchaCode();
        if (code == null) {
            return ResponseResult.error("验证码不正确！");
        }
        if (code.equals(kaptchaCode)) {
            // 发送短信
            if(SmsCodeType.REGISTER.getCode().equals(type)) {
                // 注册，检查此用户是否已注册，如果已注册，则提示用户直接登陆
                if(memberBasicInfoMapper.existByAccount(phone)) {
                    return ResponseResult.error("此手机号已注册，请直接登陆！");
                }
            } else if(SmsCodeType.FORGET_PASSWORD.getCode().equals(type)){
                // 忘记密码，检查此用户是否已注册，如果没有注册，提示用户注册
                if(!memberBasicInfoMapper.existByAccount(phone)) {
                    return ResponseResult.error("此手机号还没注册，请先注册！");
                }
            } else if(SmsCodeType.MODIFYMYPHONE.getCode().equals(type)) {
                if(sessionManage.getSessionUserInfo()==null) {
                    return ResponseResult.error("请先登陆！");
                }
                if(memberBasicInfoMapper.existByAccount(phone)) {
                    return ResponseResult.error("此手机号已注册，请直接登陆！");
                }
            } else if(SmsCodeType.MODIFYIDCARD.getCode().equals(type)) {
                if(sessionManage.getSessionUserInfo()==null) {
                    return ResponseResult.error("请先登陆！");
                }
            } else {
                return ResponseResult.error("请填写正确的type，标识验证码类型！");
            }
            try {
                String smsCode = smsService.sendSms(request, phone,type);
                if(profiles!=null && ("dev").equals(profiles)) {
                    return ResponseResult.ok(smsCode);
                }
                return ResponseResult.ok();
            } catch (BusinessException e) {
                return ResponseResult.error(e.getMessage());
            }
        } else {
            sessionManage.setKaptchaCode("");
            return ResponseResult.error("验证码输入错误！");
        }
    }

    /**
     * 注册接口
     *
     * @param phone 注册手机号
     * @param checkCode 短信验证码
     * @param password 密码
     * @param username 账号
     * @return
     */
    @ApiOperation(value = "注册", notes = "注册")
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult register(@RequestParam String phone,
                                   @RequestParam String checkCode,
                                   @RequestParam String password,
                                   @RequestParam(required = false) String username) {
        sessionManage.checkSmsCode(phone,checkCode,SmsCodeType.REGISTER.getCode());
        Example build = Example.builder(MemberBasicInfoEntity.class).where(Sqls.custom().andEqualTo("account", phone).andEqualTo("isDelete", false)).build();
        if (memberBasicInfoMapper.selectByExample(build).size() > 0) {
            throw new BusinessException("账号已存在，请直接登陆！");
        }
        SessionUserInfo sessionUserInfo = sessionManage.getSessionUserInfo();
        if(sessionUserInfo == null) {
            sessionUserInfo = new SessionUserInfo();
            sessionManage.setSessionUserInfo(sessionUserInfo);
        }
        WeixinUser user = sessionUserInfo.getWeixinUser();
        MemberBasicInfoEntity entity = new MemberBasicInfoEntity();
        entity.setAccount(phone);
        entity.setMobileNo(phone);
        entity.setPassword(password);
        entity.setUserName(username);
        entity.setRoleId(3L);
        entity.setFamilyName(username);
        if(user != null) {
            entity.setHeadThumb(user.getHeadImageUrl());
            String sex = user.getSex();
            if(sex!=null) {
                try {
                    Integer s=Integer.valueOf(sex);
                    entity.setGenderId(s-1);
                } catch (Exception e) {
                }
            }
        }
//        entity.setCol1(Constant.NEW);
        memberBasicInfoMapper.insert(entity);
        sessionUserInfo.setMemberBasicInfoEntity(entity);
        if (null != user) {
            user.setMemberId(entity.getMemberId());
            weixinUserMapper.updateByPrimaryKeySelective(user);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "完善个人信息", notes = "完善个人信息")
    @ResponseBody
    @RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
    public ResponseResult completeInfo(Long familyId,Long familyBasicId,String userName,Long provinceId,Long cityId,Long districtId) {
        SessionUserInfo sessionUserInfo = sessionManage.getSessionUserInfo();
        MemberBasicInfoEntity memberBasicInfoEntity = sessionUserInfo.getMemberBasicInfoEntity();
        memberBasicInfoEntity.setUserName(userName);
        memberBasicInfoEntity.setFamilyName(userName);
        memberBasicInfoEntity.setProvinceId(provinceId);
        memberBasicInfoEntity.setCityId(cityId);
        memberBasicInfoEntity.setDistrictId(districtId);
        memberBasicInfoMapper.updateByPrimaryKeySelective(memberBasicInfoEntity);
        FamilyMemberMapping familyMemberMapping = new FamilyMemberMapping();
        familyMemberMapping.setStatusId(FamilyMemberMapping.Status.saved.getCode());
        familyMemberMapping.setMemberId(memberBasicInfoEntity.getMemberId());
        familyMemberMapping.setFamilyId(familyId);
        familyMemberMapping.setFamilyBasicId(familyBasicId);
        familyMemberMappingMapper.insertSelective(familyMemberMapping);
        return ResponseResult.ok();
    }

    /**
     * 搜索家族分支
     *
     * @param searchFamilyBranchDTO
     * @return
     */
    @ApiOperation(value = "搜索家族分支", notes = "搜索家族分支")
    @ResponseBody
    @RequestMapping(value = "/searchFamilyBranch", method = RequestMethod.POST)
    public ResponseResult searchFamilyBranch(SearchFamilyBranchDTO searchFamilyBranchDTO,Integer pageNum,Integer pageSize) {
        // 根据name or fatherName模糊搜索MemberBasicInfoEntity，如果有countryId再and搜索FamilyBranch ，得到FamilyBranch，名字必填。
//        if (StringUtils.isBlank(searchFamilyBranchDTO.getName())) {
//            throw new BusinessException("名字不能为空！");
//        }
        if(StringUtils.isNotBlank(searchFamilyBranchDTO.getBrotherName())) {
            searchFamilyBranchDTO.setUserName(searchFamilyBranchDTO.getBrotherName());
        } else if(StringUtils.isNotBlank(searchFamilyBranchDTO.getFatherName())) {
            searchFamilyBranchDTO.setUserName(searchFamilyBranchDTO.getFatherName());
        }
        PageHelper.startPage(pageNum==null?0:pageNum, pageSize==null?10:pageSize);
        PageInfo<SearchFamilyBranchVO> pageInfo = new PageInfo<>(familyBranchMapper.searchFamilyBranch(searchFamilyBranchDTO));
        return ResponseResult.ok(pageInfo);
    }


}

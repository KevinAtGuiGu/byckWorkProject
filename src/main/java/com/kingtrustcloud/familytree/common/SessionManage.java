package com.kingtrustcloud.familytree.common;

import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.common.Constant;
import com.kingtrustcloud.familytree.entity.FamilyBranchAdmin;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.entity.SysRole;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.enumeration.FamilyStatus;
import com.kingtrustcloud.familytree.enumeration.SysRoleEnum;
import com.kingtrustcloud.familytree.mapper.FamilyBranchAdminMapper;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.mapper.SysRoleMapper;
import com.kingtrustcloud.familytree.mapper.WeixinUserMapper;
import com.kingtrustcloud.familytree.service.WeixinUserService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Title: SessionManage
 * @Package com.kingtrustcloud.familytree.common
 * @Description: (session存取统一管理，方便迁移)
 * @author: tanyong
 * @date: 2020/3/18 16:51
 */
@Component
public class SessionManage {

    @Value("${spring.profiles.active}")
    private String profiles;

    @Autowired
    private MemberBasicInfoMapper memberBasicInfoMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private FamilyBranchAdminMapper familyBranchAdminMapper;

    public void setSessionUserInfo(SessionUserInfo sessionUserInfo) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        request.getSession().setAttribute(Constant.SESSION_USER,sessionUserInfo);
        sessionUserInfo.setUserRole(getRole());
    }

    public SessionUserInfo getSessionUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        return (SessionUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
    }

    public void setKaptchaCode(String code) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        request.getSession().setAttribute(Constant.KAPTCHA_CODE,code);
    }

    public String getKaptchaCode() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        return (String)request.getSession().getAttribute(Constant.KAPTCHA_CODE);
    }

    public void setSmsCode(String phone,String code,String type) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        Map<String,Object> smsInfo=(Map<String,Object>)request.getSession().getAttribute(Constant.SMS_CODE);
        if(smsInfo==null) {
            smsInfo=new HashMap<>(4);
        }
        Map<String,Object> info=new HashMap<>(3);
        // 存储手机号，用户验证码，发送时间
        info.put("phone",phone);
        info.put("smsCode",code);
        info.put("createTime",System.currentTimeMillis());
        smsInfo.put(type,info);
        request.getSession().setAttribute(Constant.SMS_CODE,smsInfo);
    }

    public void checkSmsCode(String phone,String smsCode,String type) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        Map<String,Object> smsInfo=(Map<String,Object>) request.getSession().getAttribute(Constant.SMS_CODE);
        if(smsInfo == null) {
            throw new BusinessException("验证码不存在！");
        }
        Map<String,Object> info=(Map<String,Object>)smsInfo.get(type);
        if(info==null) {
            throw new BusinessException("验证码不存在！");
        }
        if(!phone.equals(info.get("phone")) || !smsCode.equals(info.get("smsCode"))) {
            throw new BusinessException("验证码错误！");
        }
        long createTime=(long)info.get("createTime");
        if(System.currentTimeMillis()-createTime>1000*10*5) {
            throw new BusinessException("验证码已过期！");
        }
    }

    /**
     * 获取用户角色
     * @return
     */
    public String getRole() {
        SessionUserInfo sessionUserInfo = getSessionUserInfo();
        if(sessionUserInfo.getUserRole()!=null) {
            return sessionUserInfo.getUserRole();
        }
        MemberBasicInfoEntity memberBasicInfoEntity = sessionUserInfo.getMemberBasicInfoEntity();
        if(memberBasicInfoEntity==null) {
            return SysRoleEnum.USER.getCode();
        }
        Long memberId = memberBasicInfoEntity.getMemberId();
        if(memberId!=null) {
            MemberBasicInfoEntity entity = memberBasicInfoMapper.selectByPrimaryKey(memberId);
            Long roleId = entity.getRoleId();
            if(roleId!=null) {
                SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
                if(sysRole!=null) {
                    if(sysRole.getRoleName().equals(SysRoleEnum.SUPER_MANAGE.getCode()) || sysRole.getRoleName().equals(SysRoleEnum.BRANCH_MANAGE.getCode())) {
                        return sysRole.getRoleName();
                    }
                }
            }
            List<FamilyBranchAdmin> familyBranchAdminList = familyBranchAdminMapper.selectByExample(Example
                    .builder(FamilyBranchAdmin.class)
                    .where(Sqls.custom().andEqualTo("memberId", memberId)
                    .andEqualTo("statusId", FamilyStatus.saved.getCode())
                    .andEqualTo("isDelete",false)).build());
            if(familyBranchAdminList.size()>0) {
                return SysRoleEnum.BRANCH_MANAGE.getCode();
            }
        }
        return SysRoleEnum.USER.getCode();
    }

}

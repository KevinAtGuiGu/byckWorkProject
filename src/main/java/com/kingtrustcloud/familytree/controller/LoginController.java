package com.kingtrustcloud.familytree.controller;

import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.common.SessionUserInfo;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.enumeration.SmsCodeType;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.mapper.WeixinUserMapper;
import com.kingtrustcloud.familytree.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author kevin
 * @create 2020-03-11 9:45
 * @Description:
 */
@Api(value = "登陆Api接口层", tags = "登陆Api接口类")
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    MemberBasicInfoMapper memberBasicInfoMapper;
    @Autowired
    WeixinUserMapper weixinUserMapper;
    @Autowired
    private SessionManage sessionManage;
    @Autowired
    WechatController wechatController;

    @ApiOperation(value = "登录页面", notes = "登录页面")
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword(){
        return "login/forget";
    }

    @GetMapping("/reset")
    public String reset(){
        return "login/resetpassword";
    }

    @ApiOperation(value = "根据手机号和密码登录", notes = "根据手机号和密码登录")
    @ResponseBody
    @RequestMapping(value = "/loginByPhoneAndPassword", method = RequestMethod.POST)
    public ResponseResult loginByPhoneAndPassword(String phone, String password, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {
        if (phone != null && password != null) {
            MemberBasicInfoEntity memberBasicInfoEntity = loginService.loginByPhoneAndPassword(phone, password);
            if (memberBasicInfoEntity != null) {
                URIBuilder uri = new URIBuilder("../familytree");
                response.sendRedirect(uri.toString());
                return ResponseResult.ok();
            } else {

                return ResponseResult.error("手机号或者密码错误");
            }
        } else {
            return ResponseResult.error("参数异常");
        }


    }
    @ApiOperation(value = "根据手机号和验证码登录", notes = "根据手机号和验证码登录")
    @ResponseBody
    @GetMapping(value = "/loginByPhoneAndSmsCode")
    public ResponseResult loginByPhoneAndSmsCode(String phone, String smsCode, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {
        String checkCode = (String) request.getSession().getAttribute("smsCode");
        MemberBasicInfoEntity memberBasicInfoEntity = loginService.loginByPhone(phone);
        if (memberBasicInfoEntity != null && checkCode.equals(smsCode)) {

            URIBuilder uri = new URIBuilder("../familytree");
            response.sendRedirect(uri.toString());
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("手机号或验证码错误");
        }
    }

    /**
     * 通过account和密码登陆，登陆成功，自动绑定当前微信号到登陆账号
     *
     * @param account
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "账号密码登录", notes = "账号密码登录")
    @RequestMapping(value = "loginAccountAndPassword", method = RequestMethod.POST)
    public ResponseResult login(String account, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberBasicInfoEntity login = loginService.login(account, password);
        SessionUserInfo sessionUserInfo = sessionManage.getSessionUserInfo();
        if(sessionUserInfo == null) {
            sessionUserInfo = new SessionUserInfo();
        }
        sessionUserInfo.setMemberBasicInfoEntity(login);
        WeixinUser user = sessionUserInfo.getWeixinUser();
        if(user!=null) {
            //微信进入的
            user.setMemberId(login.getMemberId());
            // 取消原先绑定的人
            weixinUserMapper.updateMemberIdToNullByMemberId(login.getMemberId());
            // 绑定登陆的账号
            weixinUserMapper.updateByPrimaryKeySelective(user);
        }
        // 清空角色信息
        sessionUserInfo.setUserRole(null);
        // 重新存储用户信息
        sessionManage.setSessionUserInfo(sessionUserInfo);
        Map<String, String> map = wechatController.queryStatusPage(sessionUserInfo.getMemberBasicInfoEntity());
        return ResponseResult.ok(map);
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @ResponseBody
    @PostMapping("/newPassword")
    public ResponseResult resetPassword(String resetPassword,String smsCode,String phone) {
        sessionManage.checkSmsCode(phone,smsCode, SmsCodeType.FORGET_PASSWORD.getCode());
        loginService.resetPassword(resetPassword,phone);
        return ResponseResult.ok();
    }

}

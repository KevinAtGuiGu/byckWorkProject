package com.kingtrustcloud.familytree.controller;

import com.kingtrustcloud.common.Constant;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.common.SessionUserInfo;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.mapper.WeixinUserMapper;
import com.kingtrustcloud.familytree.service.WeixinUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Title: WechatController
 * @Package com.kingtrustcloud.familytree.controller
 * @Description: (微信交互接口)
 * @author: tanyong
 * @date: 2020/3/4 10:53
 */
@Api(value = "微信Api接口层", tags = "微信Api接口类")
@Slf4j
@Controller
@RequestMapping("/wxapi")
public class WechatController {

    @Autowired
    private WxMpService wxService;
    @Autowired
    private WeixinUserService weixinUserService;
    @Autowired
    private SessionManage sessionManage;
    @Autowired
    private WeixinUserMapper weixinUserMapper;
    @Autowired
    private MemberBasicInfoMapper memberBasicInfoMapper;


    /**
     * 此接口用于微信服务器认证
     */
    @ApiOperation(value = "微信服务器认证", notes = "微信服务器认证")
    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {

        log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
                timestamp, nonce, echostr);
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    /**
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx70670710d9335112&redirect_uri=http%3A%2F%2Ffamilytree.kingtrustcloud.com%2Fwxapi%2FgetUserInfo&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
     * 微信公众号重定向地址,首页
     * 点击微信商城时，其链接为
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect 若提示“该链接无法访问”，请检查参数是否填写错误，是否拥有scope参数对应的授权作用域权限。
     * 其中REDIRECT_URI为http://域名/wx/wxapi/redirect/rimpire
     * @param code
     * @return
     */
    @ApiOperation(value = "获取微信用户信息", notes = "获取微信用户信息")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public void getUserInfo(@RequestParam String code, HttpServletResponse response, HttpServletRequest request) throws IOException {
        try {
            request.getSession().invalidate();
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            WeixinUser weixinUser = new WeixinUser();
            weixinUser.setOpenId(user.getOpenId());
            weixinUser.setUnionId(user.getUnionId());
            weixinUser.setCountry(user.getCountry());
            weixinUser.setCity(user.getCity());
            weixinUser.setHeadImageUrl(user.getHeadImgUrl());
            weixinUser.setLanguage(user.getLanguage());
            weixinUser.setNickName(user.getNickname());
            weixinUser.setProvince(user.getProvince());
            weixinUser.setRemark(user.getRemark());
            weixinUser.setSex(String.valueOf(user.getSex()));
            WeixinUser result = weixinUserService.saveOrUpdate(weixinUser);
            SessionUserInfo sessionUserInfo = new SessionUserInfo();
            sessionUserInfo.setWeixinUser(result);
            sessionManage.setSessionUserInfo(sessionUserInfo);
            if(result.getMemberId() == null) {
                // 未注册,跳转登陆页
                response.sendRedirect("/login");
                return;
            } else {
                // 已注册 ，已自动登陆，进入主页
                MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(result.getMemberId());
                sessionUserInfo.setMemberBasicInfoEntity(memberBasicInfoEntity);
                Map<String, String> map = queryStatusPage(memberBasicInfoEntity);
                // 清空角色信息
                sessionUserInfo.setUserRole(null);
                // 重新存储用户信息
                sessionManage.setSessionUserInfo(sessionUserInfo);
                String url=map.get("url");
                if(!url.startsWith("/") && !url.startsWith(".")) {
                    url="/"+url;
                }
                response.sendRedirect(url);
                return;
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("login/login");
    }

    /**
     * 模拟微信登陆 http://localhost:8888/wxapi/testUserInfo?code=46
     * @param code
     * @param response
     * @param request
     */
    @ApiOperation(value = "获取微信用户信息", notes = "获取微信用户信息")
    @RequestMapping(value = "/testUserInfo",method = RequestMethod.GET)
    public void testUserInfo(String code, HttpServletResponse response, HttpServletRequest request) throws IOException {
       //根据当前code从数据库中查找weixinUser(code是weixinUser在数据库中的user_id)
        WeixinUser weixinUser = weixinUserMapper.selectByPrimaryKey(code);
        SessionUserInfo sessionUserInfo = new SessionUserInfo();
        sessionUserInfo.setWeixinUser(weixinUser);
        sessionManage.setSessionUserInfo(sessionUserInfo);
        if(weixinUser.getMemberId() == null) {
            // 未注册,跳转登陆页
            response.sendRedirect("/login");
            return;
        } else {
            // 已注册 ，已自动登陆，进入主页
            // 已注册需要分情况考虑，如果用户申请过加入某个家族或申请过创建分支，那么进入注册流程中的成功页，如果失败了进入注册流程中的错误页
            // 标识进入注册流程那个页面
            // 已注册 ，已自动登陆，进入主页
            MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectByPrimaryKey(weixinUser.getMemberId());
            sessionUserInfo.setMemberBasicInfoEntity(memberBasicInfoEntity);
            Map<String, String> map = queryStatusPage(memberBasicInfoEntity);
            // 清空角色信息
            sessionUserInfo.setUserRole(null);
            // 重新存储用户信息
            sessionManage.setSessionUserInfo(sessionUserInfo);
            String url=map.get("url");
            if(!url.startsWith("/") && !url.startsWith(".")) {
                url="/"+url;
            }
            response.sendRedirect(url);
            return;
        }
    }

    public Map<String,String> queryStatusPage(MemberBasicInfoEntity memberBasicInfoEntity) throws IOException {
        Map<String,String> res=new HashMap<>();
        // 查询当前登陆用户是否已加入家族分支或创建家族分支成功
        if(Constant.NEW.equals(memberBasicInfoEntity.getCol1())) {
//            modelAndView.addObject("page","searchFamilyBranch");
//            modelAndView.setViewName("/auth/index");
            res.put("url","/register#searchFamilyBranch");
        } else if(Constant.APPROVEL_JOIN_FAMILY.equals(memberBasicInfoEntity.getCol1()) || Constant.APPROVEL_CREATE_FAMILY_BRANCH.equals(memberBasicInfoEntity.getCol1())) {
//            modelAndView.addObject("page","successPage");
//            modelAndView.setViewName("/auth/index");
            res.put("url","/register#successPage");
        } else if(Constant.APPROVEL_JOIN_FAMILY_REJECT.equals(memberBasicInfoEntity.getCol1()) || Constant.APPROVEL_CREATE_FAMILY_BRANCH_REJECT.equals(memberBasicInfoEntity.getCol1())) {
//            modelAndView.addObject("page","warnPage");
//            modelAndView.addObject("rejectMessage",memberBasicInfoEntity.getCol2());
//            modelAndView.setViewName("/auth/index");
            res.put("url","/register?rejectMessage="+ URLEncoder.encode(memberBasicInfoEntity.getCol2(),"utf-8")+"#warnPage");
        } else {
            sessionManage.getRole();
//            modelAndView.setViewName("home");
            res.put("url","home");
        }
        return res;
    }

}

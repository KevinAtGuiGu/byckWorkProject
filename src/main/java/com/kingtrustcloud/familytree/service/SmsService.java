package com.kingtrustcloud.familytree.service;

import com.alibaba.fastjson.JSONObject;
import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.familytree.cache.CacheService;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.config.SmsProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Title: SmsService
 * @Package com.kingtrustcloud.common
 * @Description: (短信服务类)
 * @author: tanyong
 * @date: 2020/3/4 16:48
 */
@Slf4j
@EnableConfigurationProperties(SmsProperties.class)
@Component
public class SmsService {

    @Qualifier("localCacheServiceImpl")
    @Autowired
    CacheService cacheService;

    @Autowired
    SmsProperties smsProperties;

    @Autowired
    SessionManage sessionManage;

    @Value("${spring.profiles.active}")
    private String profiles;

    /**
     * 根据短信类型用途发送短信并在session中存储发送的信息
     * @param request
     * @param phone
     * @param type
     * @return
     */
    public String sendSms(HttpServletRequest request,String phone,String type) {
        // ip校验，防止恶意接口攻击 超过10秒后才可再次获取验证码
        if(!sendCheck(request)) {
            throw new BusinessException("发送短信验证码太频繁了，请稍后再试！");
        }
        //6位验证码
        String smsCode = randomNumber(6);
        sessionManage.setSmsCode(phone,smsCode,type);
        // 调用接口发送短信
        // TODO: 2020/3/6
        if("sit".equals(profiles)) {
            sendSms(phone,smsCode,type);
        }
        return smsCode;
    }

    private void sendSms(String phone,String code,String type) {
        try {
            Credential cred = new Credential(smsProperties.getSecretid(), smsProperties.getSecretkey());
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppid(smsProperties.getAppid());
            req.setSign(smsProperties.getSign());
            req.setSessionContext(phone);
            req.setTemplateID(smsProperties.getTemplateid());
            req.setPhoneNumberSet(new String[]{"+86".concat(phone)});
            req.setTemplateParamSet(new String[]{code});
            SendSmsResponse res = client.SendSms(req);
            log.info("发送短信给{}，验证码{},发送结果{}：",phone,code,SendSmsResponse.toJsonString(res));
        } catch (TencentCloudSDKException e) {
            log.error("发送短信失败，错误原因{}",e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean sendCheck(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        log.info("ip：{}发送短信请求",remoteAddr);
        if(!cacheService.isExpire(remoteAddr)) {
            log.info("ip：{}发送短信请求太频繁",remoteAddr);
            return false;
        }
        cacheService.setKey(remoteAddr,10000);
        return true;
    }

    /**
     * 得到随机数字串
     * @param n
     * @return
     */
    private String randomNumber(int n) {
        String str1 = "1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }
}

package com.kingtrustcloud.familytree.service;

import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;

/**
 * @author kevin
 * @create 2020-03-11 10:01
 * @Description:
 */

public interface LoginService {

     MemberBasicInfoEntity loginByPhoneAndPassword(String phone,String password);

     MemberBasicInfoEntity loginByPhone(String phone);

     MemberBasicInfoEntity login(String account,String password);


    void resetPassword(String resetPassword,String phone);
}

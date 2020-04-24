package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.mapper.MemberBasicInfoMapper;
import com.kingtrustcloud.familytree.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author kevin
 * @create 2020-03-11 10:07
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    MemberBasicInfoMapper memberBasicInfoMapper;

    @Autowired
    SessionManage sessionManage;

    @Override
    public MemberBasicInfoEntity loginByPhoneAndPassword(String phone, String password) {

        Example example = new Example(MemberBasicInfoEntity.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("mobileNo",phone);
        criteria.andEqualTo("password",password);

        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectOneByExample(example);

        return memberBasicInfoEntity;
    }

    @Override
    public MemberBasicInfoEntity loginByPhone(String phone) {
        Example example = new Example(MemberBasicInfoEntity.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("mobileNo",phone);
        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectOneByExample(example);

        return memberBasicInfoEntity;
    }

    @Override
    public MemberBasicInfoEntity login(String account, String password) {
        Example example = new Example(MemberBasicInfoEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account",account);
        criteria.andEqualTo("isDelete",false);
        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectOneByExample(example);
        if(memberBasicInfoEntity == null) {
            throw new BusinessException("账号错误!");
        }
        if(!StringUtils.equals(password,memberBasicInfoEntity.getPassword())) {
            throw new BusinessException("密码错误!");
        }
        return memberBasicInfoEntity;
    }

    @Override
    public void resetPassword(String newPassword,String phone) {

        Example example = new Example(MemberBasicInfoEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account",phone);
        criteria.andEqualTo("isDelete",false);
        MemberBasicInfoEntity memberBasicInfoEntity = memberBasicInfoMapper.selectOneByExample(example);
        if (memberBasicInfoEntity == null){
            throw new BusinessException("手机号错误");
        }
        memberBasicInfoEntity.setPassword(newPassword);
        memberBasicInfoMapper.updateByPrimaryKey(memberBasicInfoEntity);
    }
}

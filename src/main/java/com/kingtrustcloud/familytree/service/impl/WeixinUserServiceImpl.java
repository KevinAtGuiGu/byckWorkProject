package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.mapper.WeixinUserMapper;
import com.kingtrustcloud.familytree.service.WeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class WeixinUserServiceImpl implements WeixinUserService {
    @Autowired
    private WeixinUserMapper weixinUserMapper;


    @Override
    public WeixinUser saveOrUpdate(WeixinUser weixinUser) {
        Example example = new Example(WeixinUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openId", weixinUser.getOpenId());
        criteria.andEqualTo("isDelete", false);
        List<WeixinUser> weixinUserList = weixinUserMapper.selectByExample(example);
        WeixinUser result;
        if(weixinUserList.size()>0) {
            result = weixinUserList.get(0);
            result.setUnionId(weixinUser.getUnionId());
            result.setCountry(weixinUser.getCountry());
            result.setCity(weixinUser.getCity());
            result.setHeadImageUrl(weixinUser.getHeadImageUrl());
            result.setLanguage(weixinUser.getLanguage());
            result.setNickName(weixinUser.getNickName());
            result.setProvince(weixinUser.getProvince());
            result.setRemark(weixinUser.getRemark());
            result.setSex(String.valueOf(weixinUser.getSex()));
            weixinUserMapper.updateByPrimaryKey(result);
        } else {
            weixinUserMapper.insertSelective(weixinUser);
            result=weixinUser;
        }
        return result;
    }
}

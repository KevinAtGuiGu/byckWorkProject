package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.familytree.entity.MemberRandCode;
import com.kingtrustcloud.familytree.mapper.MemberRandCodeMapper;
import com.kingtrustcloud.familytree.service.MemberRandCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 短信认证表服务实现层
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-17 10:12:37
 */
@Service
public class MemberRandCodeServiceImpl implements MemberRandCodeService  {

    @Autowired
    private MemberRandCodeMapper memberRandCodeMapper;
}
package com.kingtrustcloud.familytree.service.impl;

import com.kingtrustcloud.familytree.mapper.SysRoleMapper;
import com.kingtrustcloud.familytree.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 服务实现层
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-31 10:01:29
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
}
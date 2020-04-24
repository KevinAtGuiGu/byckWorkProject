package com.kingtrustcloud.familytree.common;

import com.kingtrustcloud.common.HelpUtil;
import com.kingtrustcloud.familytree.entity.BaseEntity;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @version V1.0
 * @Title: InsertIntercept
 * @Package com.kingtrustcloud.familytree.common
 * @Description: (拦截插入和修改语句，添加创建时间和修改时间)
 * @author: tanyong
 * @date: 2020/3/19 11:32
 */
@Component
@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class})
})
public class InsertIntercept implements Interceptor {

    @Autowired
    private SessionManage sessionManage;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        // 获取 SQL
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if(SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
            // 获取参数
            Object parameter = invocation.getArgs()[1];
            if(parameter instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) parameter;
                SessionUserInfo sessionUserInfo = sessionManage.getSessionUserInfo();
                if(SqlCommandType.INSERT.equals(sqlCommandType)) {
                    baseEntity.setCreateTime(HelpUtil.getDateTime());
                    if(sessionUserInfo!=null && sessionUserInfo.getMemberBasicInfoEntity()!=null) {
                        baseEntity.setCreateById(sessionUserInfo.getMemberBasicInfoEntity().getMemberId());
//                        baseEntity.setOwnerId(sessionUserInfo.getMemberBasicInfoEntity().getMemberId());
                    }
                } else {
                    baseEntity.setLastModifyTime(HelpUtil.getDateTime());
                    if(sessionUserInfo!=null && sessionUserInfo.getMemberBasicInfoEntity()!=null) {
                        baseEntity.setLastModifyById(sessionUserInfo.getMemberBasicInfoEntity().getMemberId());
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}

package com.kingtrustcloud.mapper;

import com.kingtrustcloud.FamilytreeAppTest;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.mapper.WeixinUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @version V1.0
 * @Title: MapperTest
 * @Package com.kingtrustcloud.mapper
 * @Description: ()
 * @author: tanyong
 * @date: 2020/3/10 17:13
 */
public class MapperTest extends FamilytreeAppTest {

    @Autowired
    WeixinUserMapper weixinUserMapper;

    @Test
    public void testInsert() {
        new WeixinUser();test();
//        WeixinUser weixinUser = new WeixinUser();
//        weixinUser.setNickName("aaaa");
//        weixinUserMapper.insertSelective(weixinUser);
////        weixinUserMapper.insert(weixinUser);
   /*     weixinUserMapper.updateByPrimaryKeySelective(weixinUser2);*/
        ArrayList<String> strings = new ArrayList<>();
        strings.add("日本");
        strings.add("美国");
        strings.add("中国");
        for (String string : strings) {
            if (string.equals("中国")){
                System.out.printf("中国");
            }
            return;
        }
    }
}

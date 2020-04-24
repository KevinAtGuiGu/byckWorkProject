package com.kingtrustcloud.familytree.enumeration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kevin
 * @create 2020-03-16 10:42
 * @Description:
 */
public interface BaseEnum<T> {

    /**
     * 编码
     *
     * @return
     */
    T getCode();

    /**
     * 描述
     *
     * @return
     */
    String getDesc();

    /**
     * 获取枚举键值对列表
     * 使用方式：BaseEnum.getKeyValueList(Enum.values())
     * @param list
     * @return
     */
    static List<Map<String,Object>> getKeyValueList(BaseEnum[] list) {
        List<Map<String,Object>> res=new ArrayList<>();
        for(BaseEnum baseEnum:list) {
            Map<String,Object> map=new HashMap<>();
            map.put("value",baseEnum.getCode());
            map.put("label",baseEnum.getDesc());
            res.add(map);
        }
        return res;
    }
}

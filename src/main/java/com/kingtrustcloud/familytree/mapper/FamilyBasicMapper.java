package com.kingtrustcloud.familytree.mapper;

import com.kingtrustcloud.familytree.entity.FamilyBasic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**   
 * 家族辈份数据访问层
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:21:48
 */
@Repository
public interface FamilyBasicMapper extends Mapper<FamilyBasic> {
    /**
     * 查询分页
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<FamilyBasic> selectPage(@Param("statIndex") Integer startIndex, @Param("pageSize") Integer pageSize,@Param("familyId") Long familyId);

    Long getChildBasicId(Long basicId);
}

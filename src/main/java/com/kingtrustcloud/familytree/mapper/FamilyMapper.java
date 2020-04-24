package com.kingtrustcloud.familytree.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import com.kingtrustcloud.familytree.entity.Family;


/**   
 * 家族数据访问层
 * @version: 1.0
 * @author: tan
 * @date: 2020-03-12 11:21:48
 */
@Repository
public interface FamilyMapper extends Mapper<Family> {
	
}

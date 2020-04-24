package com.kingtrustcloud.familytree.vo;

import lombok.Data;

/**
 * @version V1.0
 * @Title: MemberVO
 * @Package com.kingtrustcloud.familytree.vo
 * @Description: (家族分支成员树视图对象)
 * @author: tanyong
 * @date: 2020/3/19 16:01
 */
@Data
public class MemberVO {
    //mateId memberId parentMemberId userName headThumb genderId isDead

    Long mateId;

    Long memberId;

    Long parentMemberId;

    String userName;

    String headThumb;

    Long genderId;

    Integer isDead;

    Long familyBranchId;

    Long familyBasicId;

    Long familyId;

}

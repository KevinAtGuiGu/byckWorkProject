package com.kingtrustcloud.familytree.controller;

import com.kingtrustcloud.common.BusinessException;
import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.entity.MemberBasicInfoEntity;
import com.kingtrustcloud.familytree.mapper.FamilyBranchMapper;
import com.kingtrustcloud.familytree.service.MemberService;
import com.kingtrustcloud.familytree.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value = "家族成员Api接口层", tags = "家族成员Api接口类")
@Controller
@RequestMapping("/member")
public class  MemberController {
     @Autowired
    private MemberService memberService;
     @Autowired
     private FamilyBranchMapper familyBranchMapper;

    @ApiOperation(value = "根据groupId查询", notes = "根据groupId查询")
    @ResponseBody
    @RequestMapping(value = "/getMembers/{groupId}", method = RequestMethod.GET)
    public ResponseResult getMembers(@PathVariable long groupId) {
        List<MemberBasicInfoEntity> entities = memberService.getMembers(groupId);
        return ResponseResult.ok(entities);
    }

    /**
     * 获取家族分支数据
     *
     * @param familyBranchId
     * @return
     */
    @ApiOperation(value = "获取家族分支数据", notes = "获取家族分支数据")
    @ResponseBody
    @RequestMapping(value = "/getMembersByFamilyBranchId", method = RequestMethod.GET)
    public ResponseResult getMembersByFamilyBranchId(Long familyBranchId) {
        List<MemberVO> entities = memberService.getMembersByFamilyBranchId(familyBranchId);
        ResponseResult ok = ResponseResult.ok(entities);
        if(entities!=null&&!entities.isEmpty()) {
            ok.setExtra(familyBranchMapper.selectByPrimaryKey(entities.get(0).getFamilyBranchId()).getBranchName());
        }
        return ok;
    }

    @ApiOperation(value = "根据memberId查询", notes = "根据memberId查询")
    @ResponseBody
    @RequestMapping(value = "/getMember/{memberId}", method = RequestMethod.GET)
    public ResponseResult getMember(@PathVariable long memberId) {
        MemberBasicInfoEntity entity = memberService.getMember(memberId);
        return ResponseResult.ok(entity);
    }

    /**
     * 分支管理员添加分支成员
     *
     * @param entity              新增成员
     * @param familyMemberMapping 关联关系
     * @param mmemberId           当前成员的memberId
     * @param relation            新增成员与当前成员的关系
     * @return
     */
    @ApiOperation(value = "添加分支成员", notes = "添加分支成员")
    @ResponseBody
    @RequestMapping(value = "/saveMember", method = RequestMethod.POST)
    public ResponseResult saveMember(MemberBasicInfoEntity entity, FamilyMemberMapping familyMemberMapping,Long mmemberId,String relation) {
        memberService.saveMember(entity, familyMemberMapping,mmemberId,relation);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "下载", notes = "下载")
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseResult upload(MemberBasicInfoEntity entity) {
        //TODO;
        return ResponseResult.ok();
    }

    /**
     *删除分支成员
     */
    @ApiOperation(value = "删除分支成员", notes = "删除分支成员")
    @ResponseBody
    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public ResponseResult deleteMember(Long memberId,Long familyBranchId){
        memberService.deleteMember(memberId,familyBranchId);
        return ResponseResult.ok();
    }

    /**
     *
     */
    @ApiOperation(value = "添加成员（从零开始）", notes = "添加成员（从零开始）")
    @ResponseBody
    @RequestMapping(value = "/saveMember2", method = RequestMethod.POST)
    public ResponseResult saveMember2(Long familyId,Long familyBranchId,MemberBasicInfoEntity entity,Long familyBasicId){
      try {
          memberService.saveMember2(familyId,familyBranchId,entity,familyBasicId);
      }catch (BusinessException e){
          return ResponseResult.error(e.getMessage());
      }
        return ResponseResult.ok();
    }
}

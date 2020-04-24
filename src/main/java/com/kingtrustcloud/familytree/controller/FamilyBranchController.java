package com.kingtrustcloud.familytree.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.dto.UpdateBranchAdminDTO;
import com.kingtrustcloud.familytree.entity.FamilyBranch;
import com.kingtrustcloud.familytree.mapper.FamilyBranchMapper;
import com.kingtrustcloud.familytree.service.FamilyBranchAdminService;
import com.kingtrustcloud.familytree.service.FamilyBranchService;
import com.kingtrustcloud.familytree.service.FamilyService;
import com.kingtrustcloud.familytree.vo.SearchFamilyBranchVO;
import com.kingtrustcloud.familytree.vo.SelectBranchAdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 17:01
 * @Description:
 */
@Api(value = "家族分支Api接口层", tags = "家族分支Api接口类")
@Controller
public class FamilyBranchController {

    @Autowired
    FamilyBranchService familyBranchService;
    @Autowired
    FamilyBranchAdminService familyBranchAdminService;

    @Autowired
    FamilyService familyService;

    @Autowired
    SessionManage sessionManage;
    @Autowired
    FamilyBranchMapper familyBranchMapper;

    /**
     * 创建分支
     *
     * @param familyBranch 新分支
     * @return
     */
    @ApiOperation(value = "添加分支", notes = "添加分支")
    @ResponseBody
    @PostMapping(value = "/saveFamilyBranch")
    public ResponseResult saveFamilyBranch(FamilyBranch familyBranch) {
        return ResponseResult.ok(familyBranchService.saveFamilyBranch(familyBranch));
    }

    @ApiOperation(value = "更新分支", notes = "更新分支")
    @ResponseBody
    @PostMapping(value = "/updateFamilyBranch")
    public ResponseResult updateFamilyBranch(FamilyBranch familyBranch) {
        familyBranchMapper.updateByPrimaryKeySelective(familyBranch);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "家族分支列表", notes = "根据家族Id查询")
    @ResponseBody
    @GetMapping("getFamilyBranchs")
    public ResponseResult getFamilyBranchs(@RequestParam(value = "familyId", required = false) Long familyId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 0 : pageNum, pageSize == null ? 10 : pageSize);
        PageInfo pageInfo = new PageInfo(familyBranchService.getFamilyBranchs(familyId));
        return ResponseResult.ok(pageInfo);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    @PostMapping("/deleteFamilyBranch/{familyId}")
    public ResponseResult deleteFamilyBranch(@PathVariable Long familyId) {
        familyBranchService.deleteFamilyBranch(familyId);
        return ResponseResult.ok();
    }

    /**
     * 成员加入分支的申请
     *
     * @param familyBranchId 申请加入的分支ID
     * @param memberId       申请加入的分支对应的成员ID
     * @return
     */
    @ApiOperation(value = "提交申请", notes = "提交申请")
    @ResponseBody
    @PostMapping("/postApprovel")
    public ResponseResult postApprovel(Long familyBranchId, Long memberId,String type) {
        familyBranchService.postApprovel(familyBranchId, memberId,type);
        return ResponseResult.ok();
    }

    /**
     * 列出所有待审批加入申请
     * pageNum     当前的页数
     * pageSize    每页显示的记录数
     *
     * @return
     */
    @ApiOperation(value = "所有待审批列表", notes = "所有待审批列表")
    @ResponseBody
    @GetMapping("listApprovel")
    public ResponseResult listApprovel(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 0 : pageNum, pageSize == null ? 10 : pageSize);
        PageInfo pageInfo = new PageInfo(familyBranchService.listApprovel());
        return ResponseResult.ok(pageInfo);
    }

    /**
     * 分支管理员同意成员的申请
     *
     * @param familyMemberMappingId 关联Id
     * @return
     */
    @ApiOperation(value = "通过成员的申请", notes = "通过成员的申请")
    @ResponseBody
    @PostMapping("/approvel")
    public ResponseResult approvel(Long familyMemberMappingId) {
        familyBranchService.approvel(familyMemberMappingId);
        return ResponseResult.ok();
    }

    /**
     * 分支管理员拒绝成员的申请
     *
     * @param familyMemberMappingId 关联Id
     * @param message               拒绝原因
     * @return
     */
    @ApiOperation(value = "拒绝加入分支申请", notes = "拒绝加入分支申请")
    @ResponseBody
    @PostMapping("/refuseApprovel")
    public ResponseResult refuseApprovel(Long familyMemberMappingId, String message) {
        familyBranchService.refuseApprovel(familyMemberMappingId, message);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据FamilyBranchId修改分支名", notes = "根据FamilyBranchId修改分支名")
    @ResponseBody
    @PostMapping("/modifyFamilyBranchName")
    public ResponseResult updateFamilyBranchById(Long familyBranchId, String familyBranchName) {
        familyBranchService.updateFamilyBranchById(familyBranchId, familyBranchName);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据FamilyBranchId获取成员列表", notes = "根据FamilyBranchId获取成员列表")
    @ResponseBody
    @GetMapping("/listSelectBranchAdminVO")
    public ResponseResult listSelectBranchAdminVO(Long familyBranchId, String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 0 : pageNum, pageSize == null ? 10 : pageSize);
        PageInfo<SelectBranchAdminVO> pageInfo = new PageInfo<>(familyBranchMapper.listSelectBranchAdminVO(familyBranchId, username));
        // return ResponseResult.ok(familyBranchMapper.listSelectBranchAdminVO(familyBranchId,username));
        return ResponseResult.ok(pageInfo);
    }

    @ApiOperation(value = "根据FamilyBranchId获取分支管理员列表", notes = "根据FamilyBranchId获取分支管理员列表")
    @ResponseBody
    @GetMapping("/listBranchAdminVO")
    public ResponseResult listBranchAdminVO(Long familyBranchId) {
        return ResponseResult.ok(familyBranchMapper.listBranchAdminVO(familyBranchId));
    }

    @ApiOperation(value = "根据FamilyBranchId更新分支管理员", notes = "根据FamilyBranchId更新分支管理员")
    @ResponseBody
    @PostMapping("/updateBranchAdmin")
    public ResponseResult updateBranchAdmin(@RequestBody List<UpdateBranchAdminDTO> branchAdminList) {
        familyBranchAdminService.updateBranchAdmin(branchAdminList);
        return ResponseResult.ok();
    }

}

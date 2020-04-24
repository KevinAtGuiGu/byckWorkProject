package com.kingtrustcloud.familytree.controller;

import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.FamilyBranchAdmin;
import com.kingtrustcloud.familytree.service.FamilyBranchAdminService;
import com.kingtrustcloud.familytree.vo.FamilyBranchAdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 13:00
 * @Description:分支管理员的增删改查
 */
@Api(value = "分支管理员Api接口层",tags="分支管理员Api接口类" )
@Controller
@ResponseBody
public class FamilyBranchAdminController {

    @Autowired
    FamilyBranchAdminService familyBranchAdminService;

    @ApiOperation(value = "添加或更新",notes = "添加或更新")
    @PostMapping(value = "/saveFamilyBranchAdmin")
    public ResponseResult saveOrUpdateFamilyBranchAdmin(@RequestBody FamilyBranchAdmin familyBranchAdmin) {
        familyBranchAdminService.saveFamilyBranchAdmin(familyBranchAdmin);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据Id查询",notes = "根据Id查询")
    @GetMapping(value = "/getFamilyBranchAdminById")
    public ResponseResult getFamilyBranchAdmin(@RequestParam("familyBranchAdminId") Long familyBranchAdminId) {

        FamilyBranchAdmin familyBranchAdmin = familyBranchAdminService.getFamilyBranchAdmin(familyBranchAdminId);
        return ResponseResult.ok(familyBranchAdmin);
    }
    @ApiOperation(value = "根据辈分Id查询",notes = "根据辈分Id查询")
    @GetMapping("/getFamilyBranchAdminByBranchId")
    public ResponseResult getFamilyBranchAdmins(@RequestParam("familyBranchId") Long familyBranchId) {
        List<FamilyBranchAdmin> familyBranchAdmins = familyBranchAdminService.getFamilyBranchAdmins(familyBranchId);
        return ResponseResult.ok(familyBranchAdmins);
    }
    @ApiOperation(value = "删除",notes = "删除")
    @PostMapping("/deleteFamilyBranchAdmin/{familyBranchAdminId}")
    public ResponseResult deleteFamilyBranchAdmin(@PathVariable Long familyBranchAdminId) {
        familyBranchAdminService.deleteFamilyBranchAdmin(familyBranchAdminId);
        return ResponseResult.ok();
    }

    /**
     * 获取待审批的所有分支管理员的信息
     */
    @ApiOperation(value = "获取待审批的分支管理员信息",notes = "获取待审批的分支管理员信息")
    @PostMapping("/getFamilyBranchAdminInfo")
    public ResponseResult getFamilyBranchAdminInfo(Long familyBranchAdminId){
        List<FamilyBranchAdminVO> familyBranchAdminVOS = familyBranchAdminService.listFamilyBranchAdminInfo(familyBranchAdminId);
        return ResponseResult.ok(familyBranchAdminVOS);
    }

    /**
     * 通过创建分支管理员的申请
     */
    @ApiOperation(value = "通过创建分支管理员的申请",notes = "通过创建分支管理员的申请")
    @PostMapping("/approvaledFamilyBranchAdminInfo")
    public ResponseResult ApprovaledfamilyBranchAdminInfo(Long familyBranchAdminId){
        familyBranchAdminService.approvaledfamilyBranchAdminInfo(familyBranchAdminId);
        return ResponseResult.ok();
    }

    /**
     *拒绝创建分支管理员的申请
     */
    @ApiOperation(value = "拒绝创建分支管理员的申请",notes = "拒绝创建分支管理员的申请")
    @PostMapping("/refuseFamilyBranchAdminInfo")
    public ResponseResult refuseFamilyBranchAdminInfo(Long familyBranchAdminId,String message ){
        familyBranchAdminService.refuseFamilyBranchAdminInfo(familyBranchAdminId,message);
        return ResponseResult.ok();
    }
}

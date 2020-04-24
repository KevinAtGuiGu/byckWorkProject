package com.kingtrustcloud.familytree.controller;

import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.FamilyMemberMapping;
import com.kingtrustcloud.familytree.service.FamilyMemberMappingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
 * @create 2020-03-13 17:50
 * @Description:
 */
@Api(value = "家族成员关系映射Api接口层", tags = "家族成员关系映射Api接口类")
@ResponseBody
@Controller
public class FamilyMemberMappingController {

    @Autowired
    FamilyMemberMappingService familyMemberMappingService;

    @Autowired
    SessionManage sessionManage;
    @ApiOperation(value = "保存或更新", notes = "保存或更新")
    @PostMapping("/saveFamilyMemberMapping")
    public ResponseResult saveFamilyMemberMapping(@RequestBody FamilyMemberMapping familyMemberMapping) {
        familyMemberMappingService.saveFamilyMemberMapping(familyMemberMapping);
        return ResponseResult.ok();
    }
    @ApiOperation(value = "根据Id查询", notes = "根据Id查询")
    @GetMapping("getFamilyMemberMapping")
    public ResponseResult getFamilyMemberMapping(@RequestParam("familyMemberMappingId") Long familyMemberMappingId) {
        FamilyMemberMapping familyMemberMapping = familyMemberMappingService.getFamilyMemberMapping(familyMemberMappingId);
        return ResponseResult.ok(familyMemberMapping);
    }
    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/deleteFamilyMemberMapping/{id}")
    public ResponseResult deleteFamilyMemberMapping(@PathVariable("id") Long familyMemberMappingId ){

        familyMemberMappingService.deleteFamilyMemberMapping(familyMemberMappingId);
        return ResponseResult.ok();
    }

}

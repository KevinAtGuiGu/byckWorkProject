package com.kingtrustcloud.familytree.controller;

import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.Family;
import com.kingtrustcloud.familytree.entity.WeixinUser;
import com.kingtrustcloud.familytree.service.FamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "家族Api接口层", tags = "家族Api接口类")
@Controller
public class FamilyTreeController {

    @Autowired
    FamilyService familyService;

    @Autowired
    private SessionManage sessionManage;

    @ApiOperation(value = "家族树", notes = "家族树")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    /**
     * 创建家族的用户自动晋升为超级管理员
     *
     * @param famliy
     * @return
     */
    @ApiOperation(value = "保存或更新", notes = "保存或更新")
    @ResponseBody
    @PostMapping("/saveFamily")
    public ResponseResult saveFamily(@RequestBody Family famliy) {
        famliy.setMemberId(sessionManage.getSessionUserInfo().getMemberBasicInfoEntity().getMemberId());
        familyService.saveFamily(famliy);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据Id查询", notes = "根据Id查询")
    @ResponseBody
    @GetMapping("/getFamily")
    public ResponseResult getFamily(@RequestParam("familyId") Long familyId) {
        Family family = familyService.getFamily(familyId);
        return ResponseResult.ok(family);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    @PostMapping("/deleteFamily/{familyId}")
    public ResponseResult deleteFamily(@PathVariable Long familyId) {
        familyService.deleteFamily(familyId);
        return ResponseResult.ok();
    }

    /**
     * 查找我的family列表
     *
     * @param familyName
     * @return
     */
    @ApiOperation(value = "我的family列表", notes = "我的family列表")
    @ResponseBody
    @GetMapping("/listMyFamily")
    public ResponseResult listMyFamily(String familyName) {
        WeixinUser user = sessionManage.getSessionUserInfo().getWeixinUser();
        return ResponseResult.ok(familyService.listFamily(user.getMemberId(), familyName));
    }

    /**
     * 查找family列表
     *
     * @param familyName
     * @return
     */
    @ApiOperation(value = "家族列表", notes = "家族列表")
    @ResponseBody
    @GetMapping("/listFamily")
    public ResponseResult listFamily(String familyName) {
        return ResponseResult.ok(familyService.listFamily(null, familyName));
    }

}

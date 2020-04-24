package com.kingtrustcloud.familytree.controller;

import com.kingtrustcloud.common.PageUtils;
import com.kingtrustcloud.common.ResponseResult;
import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.entity.FamilyBasic;
import com.kingtrustcloud.familytree.mapper.FamilyBasicMapper;
import com.kingtrustcloud.familytree.service.FamilyBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

/**
 * @author kevin
 * @create 2020-03-13 16:19
 * @Description:
 */
@Api(value = "家族辈分Api接口层", tags = "家族辈分Api接口类")
@ResponseBody
@Controller
public class FamilyBasicController {

    @Autowired
    FamilyBasicService familyBasicService;
    @Autowired
    FamilyBasicMapper familyBasicMapper;

    /**
     * 添加辈份，只能是超级管理员操作
     *
     * @param FamilyBasic
     * @return
     */
    @ApiOperation(value = "添加或更新", notes = "添加或更新")
    @PostMapping(value = "/saveFamilyBasic", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseResult saveFamilyBasic(FamilyBasic FamilyBasic) {
        familyBasicService.saveFamilyBasic(FamilyBasic);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据辈分Id查询", notes = "根据辈分Id查询")
    @GetMapping("/getFamilyBasic")
    public ResponseResult getFamilyBasic(@RequestParam("familyBasicId") Long familyBasicId) {
        FamilyBasic familyBasic = familyBasicService.getFamilyBasic(familyBasicId);
        return ResponseResult.ok(familyBasic);
    }

    /**
     * 根据家族id查询辈份列表
     *
     * @param familyId
     * @return
     */
    @ApiOperation(value = "根据家族Id查询", notes = "根据家族Id查询")
    @GetMapping("/getFamilyBasics")
    public ResponseResult getFamilyBasics(@RequestParam(value = "familyId", required = false) Long familyId, Integer currPage) {
        int pageSize = 15;
        PageUtils pageUtils = familyBasicService.getHereditaryList(familyId, pageSize, currPage);
        return ResponseResult.ok(pageUtils);
    }

    /**
     * 根据家族id查询所有辈份列表
     *
     * @param familyId
     * @return
     */
    @ApiOperation(value = "根据家族id查询所有辈份列表", notes = "根据家族id查询所有辈份列表")
    @GetMapping("/getAllFamilyBasics")
    public ResponseResult getAllFamilyBasics(@RequestParam(value = "familyId", required = false) Long familyId) {
        List<FamilyBasic> famliyBasics = familyBasicMapper.selectByExample(Example.builder(FamilyBasic.class)
                .where(Sqls.custom().andEqualTo("familyId",familyId).andEqualTo("isDelete",false)).orderBy("basicIndex").build());
        return ResponseResult.ok(famliyBasics);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/deleteFamilyBasic/{familyBasicId}")
    public ResponseResult deleteFamilyBasic(@PathVariable Long familyBasicId) {
        familyBasicService.deleteFamilyBasic(familyBasicId);
        return ResponseResult.ok();
    }

    /**
     * 世袭设置
     */
    @ApiOperation(value = "修改辈分", notes = "修改修改辈分")
    @PostMapping("/modifyHereditary")
    public ResponseResult modifyHereditary(Long familyBasicId, String familyBasicName) {
        familyBasicService.updateHereditaryById(familyBasicId, familyBasicName);
        return ResponseResult.ok();
    }

}

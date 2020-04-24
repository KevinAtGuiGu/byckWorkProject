package com.kingtrustcloud.familytree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version V1.0
 * @Title: SearchFamilyBranchDTO
 * @Package com.kingtrustcloud.familytree.dto
 * @Description: ()
 * @author: tanyong
 * @date: 2020/3/20 10:32
 */
@Data
@ApiModel(value = "SearchFamilyBranchDTO", description = "家族分支传输对象")
public class SearchFamilyBranchDTO {
    @ApiModelProperty(name = "name", value = "分支名")
    String name;
    @ApiModelProperty(name = "provinceId", value = "省份")
    Integer provinceId;
    @ApiModelProperty(name = "cityId", value = "城市")
    Integer cityId;
    @ApiModelProperty(name = "districtId", value = "地区")
    Integer districtId;
    @ApiModelProperty(name = "fatherName", value = "父亲名")
    String fatherName;
    @ApiModelProperty(name = "userName", value = "用户名")
    String userName;
    @ApiModelProperty(name = "birthPlaceProvinceId", value = "出生省份")
    Integer birthPlaceProvinceId;
    @ApiModelProperty(name = "birthPlaceCityId", value = "出生城市")
    Integer birthPlaceCityId;
    @ApiModelProperty(name = "birthPlaceDistrictId", value = "出生地区")
    Integer birthPlaceDistrictId;

    @ApiModelProperty(name = "brotherName", value = "兄弟名称")
    String brotherName;
}

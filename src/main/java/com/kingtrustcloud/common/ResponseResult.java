package com.kingtrustcloud.common;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {
    
	private static final long serialVersionUID = 4327308112193807123L;
    @ApiModelProperty(name = "statusCode", value = "响应编码:200-请求处理成功,其他表示请求失败")
	private Integer statusCode;
    @ApiModelProperty(name = "success", value = "是否成功")
    private boolean success;
    @ApiModelProperty(name = "message", value = "提示消息")
    private String message;
    @ApiModelProperty(name = "serverTime", value = "系统时间")
    private String serverTime;
    @ApiModelProperty(name = "data", value = "响应数据")
    private Object data;
    @ApiModelProperty(name = "extra", value = "额外附带信息")
    private Object extra;

    public ResponseResult (){
        this.statusCode=200;
        this.success = true;
        this.message = "操作成功";
        this.serverTime= HelpUtil.getDateTime();
    }
    public static ResponseResult  ok(){
        ResponseResult vResponseResult=new ResponseResult();
        vResponseResult.setStatusCode(200);
        vResponseResult.setSuccess(true);
        vResponseResult.setMessage("操作成功");
        return vResponseResult;
    }

    public static ResponseResult  ok(Object data){
        ResponseResult vResponseResult=new ResponseResult();
        vResponseResult.setStatusCode(200);
        vResponseResult.setSuccess(true);
        vResponseResult.setMessage("操作成功");
        vResponseResult.setData(data);
        return vResponseResult;
    }
    public static ResponseResult  ok(int statusCode, boolean isOK, String msg,Object data){
        ResponseResult vResponseResult=new ResponseResult();
        vResponseResult.setStatusCode(statusCode);
        vResponseResult.setSuccess(isOK);
        vResponseResult.setMessage(msg);
        vResponseResult.setData(data);
        return vResponseResult;
    }
    public static ResponseResult  error(int statusCode, boolean isOK, String msg){
        ResponseResult vResponseResult=new ResponseResult();
        vResponseResult.setStatusCode(statusCode);
        vResponseResult.setSuccess(isOK);
        vResponseResult.setMessage(msg);
        return vResponseResult;
    }
    public static ResponseResult  error(String msg){
        ResponseResult vResponseResult=new ResponseResult();
        vResponseResult.setStatusCode(500);
        vResponseResult.setSuccess(false);
        vResponseResult.setMessage(msg);
        return vResponseResult;
    }
    public static ResponseResult  error(int statusCode, String msg,Object data){
        ResponseResult vResponseResult=new ResponseResult();
        vResponseResult.setStatusCode(statusCode);
        vResponseResult.setSuccess(false);
        vResponseResult.setMessage(msg);
        vResponseResult.setData(data);
        return vResponseResult;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public ResponseResult setExtra(Object extra) {
        this.extra = extra;
        return this;
    }
}

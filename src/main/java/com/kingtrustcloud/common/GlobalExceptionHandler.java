package com.kingtrustcloud.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tan
 * @createTime 2019-11-4 17:04
 */
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕捉业务错误提醒异常
     * @param be
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException be) {
        log.warn("BizException:",be.getMessage());
        return ResponseResult.error(be.getMessage());
    }

    /**
     * 其他异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult otherExceptionHandler(Exception ex) {
        log.warn("Exception:", ex);
        return ResponseResult.error("请求失败，服务器出错！");
    }

}

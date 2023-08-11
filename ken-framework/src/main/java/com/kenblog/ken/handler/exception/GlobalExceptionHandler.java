package com.kenblog.ken.handler.exception;

import com.kenblog.ken.config.ResponseResult;
import com.kenblog.ken.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        //打印异常信息
        log.error("出现了异常！{}",e);
        //从异常对象中获取异常提示封装返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }
}

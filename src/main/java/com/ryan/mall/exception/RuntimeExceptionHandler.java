package com.ryan.mall.exception;

import com.ryan.mall.enums.ResponseEnum;
import com.ryan.mall.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ryan Li
 * @date 2019/12/25
 */
// @ControllerAdvice 捕获exception，捕获错误才可以避免意外的信息返回给前端，导致前端解析错误
@ControllerAdvice
public class RuntimeExceptionHandler {

//    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseVo handle(RuntimeException e) {
        return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
    }
}

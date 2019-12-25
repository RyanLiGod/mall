package com.ryan.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryan.mall.enums.ResponseEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * @author Ryan Li
 * @date 2019/12/25
 * <p>
 * JsonSerialize 序列化
 * JsonInclude(value = JsonInclude.Include.NON_NULL) 不为null的才返回
 */
@Data
//@JsonSerialize
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    private Integer status;

    private String msg;

    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }

    public static <T> ResponseVo<T> success(String msg) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum) {
        return new ResponseVo<>(responseEnum.getCode(), responseEnum.getDesc());
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg) {
        return new ResponseVo<>(responseEnum.getCode(), msg);
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, BindingResult bindingResult) {
        return new ResponseVo<>(responseEnum.getCode(),
                Objects.requireNonNull(bindingResult.getFieldError()).getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }
}
package com.ryan.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Ryan Li
 * @date 2019/12/25
 */
@Data
public class UserForm {

    // @NotEmpty 用于集合
    // @NotBlank 用于String，会判断空格
    // @NotBlank(message = "用户名不能为空")
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}

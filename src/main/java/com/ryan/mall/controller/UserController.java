package com.ryan.mall.controller;

import com.ryan.mall.enums.ResponseEnum;
import com.ryan.mall.form.UserForm;
import com.ryan.mall.pojo.User;
import com.ryan.mall.service.IUserService;
import com.ryan.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Ryan Li
 * @date 2019/12/25
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

//    // 前端请求方式为urlencoded时候使用@RequestParam
//    @PostMapping("/register")
//    public void register(@RequestParam String username) {
//        log.info("username={}", username);
//    }

    // @Valid 和 BindingResult搭配使用校验是否为空，在UserForm中需要有@NotBlank注解
    @PostMapping("/register")
    public ResponseVo register(@Valid @RequestBody UserForm userForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("注册提交的参数有误，{} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
        }

        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        return userService.register(user);
    }
}

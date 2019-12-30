package com.ryan.mall.controller;

import com.ryan.mall.consts.MallConst;
import com.ryan.mall.enums.ResponseEnum;
import com.ryan.mall.form.UserLoginForm;
import com.ryan.mall.form.UserRegisterForm;
import com.ryan.mall.pojo.User;
import com.ryan.mall.service.impl.UserServiceImpl;
import com.ryan.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Ryan Li
 * @date 2019/12/25
 */
@RestController
@Slf4j
// @RequiredArgsConstructor(onConstructor = @__(@Autowired)) 可以用lombok的这个注解替代构造函数
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

//    // 前端请求方式为urlencoded时候使用@RequestParam
//    @PostMapping("/register")
//    public void register(@RequestParam String username) {
//        log.info("username={}", username);
//    }

    // @Valid 和 BindingResult搭配使用校验是否为空，在UserForm中需要有@NotBlank注解
    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userRegisterForm,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("注册提交的参数有误，{} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);

        return userService.register(user);
    }

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
        }
        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());

        // 设置session，保存在内存里，可改进为token+redis保存
        session.setAttribute(MallConst.CURRENT_USER, userResponseVo.getData());
        log.info("/user/login sessionId={}", session.getId());

        return userResponseVo;
    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session) {
        log.info("/user sessionId={}", session.getId());
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        if (user == null) {
            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }

        return ResponseVo.success(user);
    }

    @PostMapping("/user/logout")
    public ResponseVo<User> logout(HttpSession session) {
        log.info("/user/logout sessionId={}", session.getId());
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        if (user == null) {
            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();
    }
}

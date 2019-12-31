package com.ryan.mall.service.impl;

import com.ryan.mall.MallApplicationTests;
import com.ryan.mall.enums.ResponseEnum;
import com.ryan.mall.enums.RoleEnum;
import com.ryan.mall.pojo.User;
import com.ryan.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ryan Li
 * @date 2019/12/25
 */
@Transactional
public class UserServiceImplTest extends MallApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    public static final String USERNAME = "Ryan";
    public static final String PASSWORD = "123456";
    public static final String EMAIL = "ryan@qq.com";

    @Before
    public void register() {
        User user = new User(USERNAME, PASSWORD, EMAIL, RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
        ResponseVo<User> responseVo = userService.login(USERNAME, PASSWORD);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}
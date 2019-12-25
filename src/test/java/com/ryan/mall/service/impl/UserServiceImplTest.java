package com.ryan.mall.service.impl;

import com.ryan.mall.MallApplicationTests;
import com.ryan.mall.enums.RoleEnum;
import com.ryan.mall.pojo.User;
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

    @Test
    public void register() {
        User user = new User("jack", "123456", "jack@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
    }
}
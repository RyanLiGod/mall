package com.ryan.mall.service;

import com.ryan.mall.pojo.User;
import com.ryan.mall.vo.ResponseVo;

/**
 * @author Ryan Li
 * @date 2019/12/25
 */
public interface IUserService {

    /**
     * 注册
     */
    ResponseVo<User> register(User user);

    /**
     * 登录
     */
    ResponseVo<User> login(String username, String password);
}

package com.ryan.mall.service.impl;

import com.ryan.mall.dao.UserMapper;
import com.ryan.mall.enums.ResponseEnum;
import com.ryan.mall.enums.RoleEnum;
import com.ryan.mall.pojo.User;
import com.ryan.mall.service.IUserService;
import com.ryan.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author Ryan Li
 * @date 2019/12/25
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 注册
     *
     * @param user 注册用户信息
     */
    @Override
    public ResponseVo<User> register(User user) {
        //username，email不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }

        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0) {
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }

        //MD5摘要算法（spring自带）
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)
        ));

        user.setRole(RoleEnum.CUSTOMER.getCode());

        //写入数据库
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }

        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            //用户不存在，应提示"用户名或密码错误"
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        //判断密码是否一致
        if (!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            //密码错误
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        user.setPassword("");
        return ResponseVo.success(user);
    }
}

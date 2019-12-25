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

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 注册
     *
     * @param user 注册用户信息
     */
    @Override
    public ResponseVo register(User user) {
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
    public void login(User user) {

    }
}

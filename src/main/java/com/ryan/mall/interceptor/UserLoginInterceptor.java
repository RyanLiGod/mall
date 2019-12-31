package com.ryan.mall.interceptor;

import com.ryan.mall.consts.MallConst;
import com.ryan.mall.exception.UserLoginException;
import com.ryan.mall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Li
 * @date 2019/12/30
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * @return true 表示继续， false 表示中断
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        User user = (User) request.getSession().getAttribute(MallConst.CURRENT_USER);
        if (user == null) {
            log.info("user = null");
            //response.getWriter().print("error"); 不推荐，因为我们已经封装了错误信息，没必要重新封装，改用抛出自定义异常
            throw new UserLoginException();
        }

        return true;
    }
}

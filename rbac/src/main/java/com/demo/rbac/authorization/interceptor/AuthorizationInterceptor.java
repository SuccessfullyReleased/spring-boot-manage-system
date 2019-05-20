package com.demo.rbac.authorization.interceptor;

import com.demo.rbac.authorization.annotation.NoAuthorization;
import com.demo.rbac.authorization.config.AuthorizationConstants;
import com.demo.rbac.authorization.manager.TokenManager;
import com.demo.rbac.authorization.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AuthorizationInterceptor
 * @description 拦截所有请求，解析请求头中的authorization字段，验证token
 * @date 2019/5/20 15:20
 **/
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    TokenManager manager;


    /**
     * @param request  请求
     * @param response 响应
     * @param handler  处理者
     * @return boolean
     * @author 戴俊明
     * @description 拦截请求，验证token
     * @date 2019/5/20 15:45
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        log.info("AuthorizationInterceptor::preHandle::request = [{}], response = [{}], handler = [{}]", request, response, handler);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getHeader(AuthorizationConstants.AUTHORIZATION);
        log.info("{}:{}", AuthorizationConstants.AUTHORIZATION, authorization);
        //验证token
        Token model = manager.decode(authorization);
        if (manager.checkToken(model)) {
//            //如果token验证成功，将token对应的用户username存在response中，便于之后注入
            log.info("{}:{}", AuthorizationConstants.CURRENT_USER, model.toString());
            response.setHeader(AuthorizationConstants.CURRENT_USER, model.getUsername());
            return true;
        } else {
            //如果验证token失败，并且方法注明了NoAuthorization，返回401错误
            if (method.getAnnotation(NoAuthorization.class) == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            } else {
                return true;
            }
        }
    }
}

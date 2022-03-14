package com.torch.app.config;

import com.torch.app.util.tools.JudgeCookieToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
    /**
     * 进行用户登录，请求合法性的校验
     * 进行缓存判断及读取行为
     */
    private JudgeCookieToken judgeCookieToken;

    @Autowired
    public Interceptor(JudgeCookieToken judgeCookieToken) {
        this.judgeCookieToken = judgeCookieToken;

    }

    public Interceptor() {

    }

    /**
     * 在这里进行用户登录校验，若用户为登录，则通过response返回一个error
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            response.setStatus(404);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"loginCode:\":-100}");
            response.getWriter().flush();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.liujin.travel.web.filter;

import com.liujin.travel.domain.User;
import com.liujin.travel.service.UserService;
import com.liujin.travel.service.impl.UserServiceImpl;
import com.liujin.travel.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //确保session中有用户的bean对象
        HttpServletRequest request = (HttpServletRequest) req;

        //获取资源请求路径
        String uri = request.getRequestURI();

        //放行登录和注册相关的资源
        if (uri.contains("/login.html")||uri.contains("/loginServlet")||uri.contains("/css/")
                ||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/images/")||uri.contains("/img/")
                ||uri.contains("/register.html")||uri.contains("/registUserServlet")||uri.contains("/checkCode")
                ||uri.contains("/exitServlet")){
            chain.doFilter(req, resp);
        }else {
            Object obj = request.getSession().getAttribute("user");
            if (obj == null) {
                //需要向session中存入用户的been对象
                //获取用户名和密码 -- cookie
                Cookie[] cookies = request.getCookies();
                Cookie cookie = CookieUtil.findCookie(cookies, "autologin");
                if (cookie != null&& cookie.getValue()!= "") {
                    //用户存入过，获取用户名和密码实现登录逻辑
                    String value = cookie.getValue();
                    String username = value.split("#")[0];
                    String password = value.split("#")[1];
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);

                    UserService service = new UserServiceImpl();
                    User loginUser = service.login(user);
                    request.getSession().setAttribute("user", loginUser);
                }
            }
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

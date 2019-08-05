package com.liujin.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liujin.travel.domain.ResultInfo;
import com.liujin.travel.domain.User;
import com.liujin.travel.service.UserService;
import com.liujin.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码效验
        //获取验证码数据
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info数据序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //1.获取数据（前端请求）
        Map<String, String[]> map = request.getParameterMap();

        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service = new UserServiceImpl();
        User user1 = service.login(user);
        ResultInfo info = new ResultInfo();

        if (user1 == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码有误！");
        }else {
            if ("Y".equals(user1.getStatus())){
                String autologin = request.getParameter("autologin");
                System.out.println(autologin);
                if ("true".equals(request.getParameter("autologin"))){
                    //回写cookie,将用户的用户名和密码回写到客户端
                    Cookie cookie = new Cookie("autologin",user.getUsername()+"#"+user.getPassword());

                    //-1 默认只在一次会话有效的cookie
                    //设置cookie持久化时间
                    cookie.setMaxAge(60*60*24*30);

                    //设置访问当前项目下的资源才有效 也可以不设置
                    //cookie.setPath(request.getContextPath());

                    response.addCookie(cookie);
                }
                request.getSession().setAttribute("user",user1); //登录成功标记
                info.setFlag(true);
            }else {
                info.setFlag(false);
                info.setErrorMsg("您尚未激活，请激活！");
            }
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

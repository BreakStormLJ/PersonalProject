package com.liujin.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
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

@WebServlet("/user/*")  // /user/add  /user/find
public class UserServlet extends BaseServlet {
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void regist(HttpServletRequest request,HttpServletResponse response) throws IOException {
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

        //3.调用service完成注册
        UserService service = new UserServiceImpl();
        String json = service.register(user);

        //4.响应结果,将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
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

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void exit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1.销毁session
        request.getSession().invalidate();

        //清除浏览器持久化的cookie -- 回写一个同名的cookie,设置maxAge为0即可
        Cookie cookie = new Cookie("autologin",null);

        //cookie.setMaxAge(0);
        //cookie.setPath(request.getContextPath());

        response.addCookie(cookie);

        //2.跳转页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    public void findUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //从session中获取用户信息
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);
    }

}

package com.liujin.travel.util;

import javax.servlet.http.Cookie;

/**
 * @program: myDay08
 * @description:
 * @author: liujin
 * @create: 2019-07-20 15:20
 **/
public class CookieUtil {
    public static Cookie findCookie(Cookie[] cookies, String cookieName){
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}

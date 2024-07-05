package com.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void addCookie(String cookieName, int time, HttpServletRequest request, HttpServletResponse response, String stuCode, String password) {

        //1.先获取当前Cookie
        Cookie cookie = getCookieByName(request, cookieName);
        if (cookie != null)
        {
            cookie.setValue(stuCode + "###" + password); //重新设置Cookie的值
        }else {
            cookie = new Cookie(cookieName, stuCode + "###" + password); //创建Cookie
        }

        //1.设置保存时间
        cookie.setMaxAge(time);

        //2.当前项目才能访问
        cookie.setPath(request.getContextPath()); // 默认

        //3.添加cookie
        response.addCookie(cookie);
    }

    //通过名字获取cookie
    public static Cookie getCookieByName(HttpServletRequest request, String cookieName)
    {
        //先获取所有的Cookie，防止每一次都要覆盖
        Cookie[] cookies = request.getCookies();

        //遍历
        if (cookies != null && cookies.length > 0)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(cookieName))
                {
                    return cookie;
                }
            }
        }
        return null;
    }
}


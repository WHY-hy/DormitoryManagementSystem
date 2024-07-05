package com.Servlet;

import com.Bean.User;
import com.Service.UserService;
import com.Service.UserServiceImpl;
import com.Util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 预备登录的检查选项
 */

@WebServlet(value = {"/PreLogin"})
public class PreLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查看有没有cookie
        Cookie cookie = CookieUtil.getCookieByName(request, "stuCode_password");

        //2.声明用户的工具类
        UserService service = new UserServiceImpl();

        //3.查看cookie是否存在
        if (cookie != null) //存在
        {
            //4.获取用户名和密码
            String stuCodePassword = cookie.getValue();
            String[] content = stuCodePassword.split("###");

            //5.查询这个用户是否可以登录
            User user = service.findBystuCodeAndPassword(content[0], content[1]);

            //6.查看用户是否存在
            if (user != null) //存在
            {
                //7.设置request的属性
                request.setAttribute("stuCode", content[0]); //账号
                request.setAttribute("password", content[1]); //密码
                request.setAttribute("role", user.getRole_id()); //角色
            }
        }

        //8.跳转
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

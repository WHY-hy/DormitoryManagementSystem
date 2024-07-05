package com.Servlet;

import com.Bean.User;
import com.Service.UserService;
import com.Service.UserServiceImpl;
import com.Util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 退出登录
 */

@WebServlet(value = {"/Logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.先获取用户
        User user = (User) request.getSession().getAttribute("session_user");

        //2.删除用户
        request.getSession().removeAttribute("session_user");

        //3.设置request的属性
        request.setAttribute("stuCode", user.getStu_code()); //账号
        request.setAttribute("role", user.getRole_id()); //角色

        //4.跳转
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

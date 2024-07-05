package com.Servlet;

import com.Bean.User;
import com.Service.UserService;
import com.Service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改密码的业务处理
 */

@WebServlet(value = {"/passwordAction"})
public class PasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String action = request.getParameter("action");

        //2.调用业务逻辑
        UserService userService = new UserServiceImpl();

        //3.分类判断
        switch (action)
        {
            case "preChange": //预修改
            {
                //1.先获取当前对象
                User user = (User) request.getSession().getAttribute("session_user");

                //2.设置参数
                request.setAttribute("currentUser", user);
                request.setAttribute("mainRight", "passwordChanged.jsp");
            }
            break;
        }

        //4.跳转
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String action = request.getParameter("action");

        //2.调用业务逻辑
        UserService userService = new UserServiceImpl();

        //3.分类判断
        switch (action)
        {
            case "change": //正式修改
            {
                //1.接收参数
                String newPassword = request.getParameter("newPassword"); //新密码
                String id = request.getParameter("id"); //用户的id

                //2.修改密码
                userService.changePassword(Integer.parseInt(id), newPassword);

                //3.回到首页
                request.setAttribute("mainRight", "home.jsp");
            }
            break;
        }

        //4.跳转
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}

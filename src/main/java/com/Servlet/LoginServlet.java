package com.Servlet;

import com.Bean.User;
import com.Service.UserService;
import com.Service.UserServiceImpl;
import com.Util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(value = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取表单的参数
        String stuCode = request.getParameter("stuCode"); //学号
        String password = request.getParameter("password"); //密码
        String roleID = request.getParameter("roleId"); //角色ID
        String remember = request.getParameter("remember"); //记住密码与否

        //2.查询用户名和密码是否正确
        UserService userService = new UserServiceImpl();

        User user = userService.findBystuCodeAndPassword(stuCode, password);

        //3.判断
        if (user != null) //成功
        {
            request.getSession().setAttribute("session_user", user);

            //如果是角色id的值相等
            if (user.getRole_id() == Byte.parseByte(roleID))
            {
                if (remember != null && remember.equals("remember-me"))
                {
                    //跳转之前，保存cookie
                    CookieUtil.addCookie("stuCode_password", 7 * 24 * 3600,
                            request, response, URLEncoder.encode(stuCode, "UTF-8"), URLEncoder.encode(password, "UTF-8"));
                }

                response.sendRedirect(getServletContext().getContextPath() + "/main.jsp");
            }else {
                //设置错误信息
                String error = "学号/工号或者密码错误!";
                request.setAttribute("error", error);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        }else { // 失败
            //设置错误信息
            String error = "学号/工号或者密码错误!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

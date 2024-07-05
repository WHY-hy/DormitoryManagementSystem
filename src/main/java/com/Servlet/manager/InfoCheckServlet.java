package com.Servlet.manager;

import com.Bean.DormBuild;
import com.Bean.User;
import com.Service.DormBuildService;
import com.Service.DormBuildServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 宿舍管理员的查看信息
 */

@WebServlet(value = {"/InfoCheckInManager"})
public class InfoCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String action = request.getParameter("action");

        //2.定义业务逻辑对象
        DormBuildService dormBuildService = new DormBuildServiceImpl();

        //3.判断
        switch (action)
        {
            case "list":
            {
                //获取当前对象
                User user = (User) request.getSession().getAttribute("session_user");

                //设置宿舍楼的名称
                DormBuild build = dormBuildService.findById(user.getDormBuildId());
                user.setName(build.getName());

                //设置属性
                request.setAttribute("manager", user);
                request.setAttribute("mainRight", "/Manager-JSP/infoCheck.jsp");
            }
            break;
        }

        //4.跳转
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}

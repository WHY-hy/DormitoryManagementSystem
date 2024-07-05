package com.Filter;

import com.Bean.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录拦截器
 */

@WebFilter(value = {"/*"})
public class LoginFilter implements Filter {

    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //过滤操作
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取实现类
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //2.获取资源路径
        String url = String.valueOf(request.getRequestURL());

        //3.判断
        if (url.contains("/login.jsp") || url.contains("/Login") || url.contains("/PreLogin")
                || url.contains("/css/") || url.contains("/js/") || url.contains("/fonts/")
                || url.contains(".jpg") || url.contains(".png")) //如果是登录页面、登录逻辑、预登录、退出逻辑就放行
        {
            filterChain.doFilter(servletRequest, servletResponse); //放行
        }else { //如果不是
            //查看用户是否存在
            User user = (User) request.getSession().getAttribute("session_user");
            if (user != null) //存在
            {
                //根据角色来进行过滤
                //先获取用户的角色
                Byte roleID = user.getRole_id();

                //只有超级管理员才能放行的业务,宿舍楼管理、宿舍管理员管理、学生管理和自己的信息查看
                if ((url.contains("/dormBuildAction")
                        || url.contains("/dormManagerAction")
                        || url.contains("/studentAction")
                        || url.contains("/InfoCheckAction")
                ) && roleID == 0)
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }

                //只有宿舍管理员才能放行的业务，单独的学生管理和单独的信息查看
                else if ((url.contains("/studentActionInManager") || url.contains("/InfoCheckInManager")) && (roleID == 1))
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }

                //只有学生才能放行的业务，单独的信息查看
                else if (url.contains("/InfoCheckInStudent") && roleID == 2)
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }

                //通用功能，一律放行
                else if ((url.contains("/main.jsp")) || (url.contains("/passwordAction"))
                        || (url.contains("/indexAction"))
                        || url.contains("/Logout")){
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                //跳转失败了就前往404页面
                else {
                    request.getRequestDispatcher("/404.jsp").forward(request, response);
                }

            }else { //不存在
                request.setAttribute("error", "请您先登录!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

    //销毁
    @Override
    public void destroy() {

    }
}

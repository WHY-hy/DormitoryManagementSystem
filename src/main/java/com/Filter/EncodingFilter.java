package com.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 编码过滤，解决乱码问题
 */

@WebFilter(value = "/*")
public class EncodingFilter implements Filter {

    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //过滤操作
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.创建HttPServletRequest和HttpServletResponse对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //2.request的动态代理，顺便改变response的写格式
        HttpServletRequest proxy_request = (HttpServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //设置读的乱码处理
                request.setCharacterEncoding("UTF-8");

                //设置Tomcat7及其以下版本的GET请求处理方式
                String ways = request.getMethod();

                if (ways.equalsIgnoreCase("GET"))
                {
                    //获取版本号
                    String versionInfo = request.getServletContext().getServerInfo();
                    String versionNumber = versionInfo.substring(versionInfo.indexOf("/") + 1, versionInfo.indexOf("."));

                    //如果版本号小于8
                    if (Integer.parseInt(versionNumber) < 8)
                    {
                        //增强getParameter方法
                        if (method.getName().equals("getParameter"))
                        {
                            //调用方法
                            String value = (String) method.invoke(request, args);
                            if (value != null && !(value.trim().equals("")))
                            {
                                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                            }
                            return value;
                        }else {
                            //如果不是getParameter函数，其他函数，正常执行
                            return method.invoke(request, args);
                        }
                    }else {
                        //如果Tomcat版本大于等于8，直接执行
                        return method.invoke(request, args);
                    }
                }else {
                    //如果是POST请求方式
                    return method.invoke(request, args);
                }

            }
        });

        //3.创建代理对象
        HttpServletResponse proxy_response = (HttpServletResponse) Proxy.newProxyInstance(response.getClass().getClassLoader(), response.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //设置写的乱码处理
                response.setContentType("text/html;charset=UTF-8");

                //执行函数，即response.print()或者response.write()
                return method.invoke(response, args);
            }
        });

        //4.放行
        filterChain.doFilter(proxy_request, proxy_response);
    }

    //销毁
    @Override
    public void destroy() {

    }
}

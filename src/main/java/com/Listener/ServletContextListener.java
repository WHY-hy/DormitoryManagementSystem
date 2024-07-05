package com.Listener;

import com.Util.DruidUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * ServletContextListener用于初始化数据库连接的
 */

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    //ServletContext域对象创建时的响应行为
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.初始化数据库连接池
        DruidUtil.init();
    }

    //ServletContext域对象销毁时的响应行为
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

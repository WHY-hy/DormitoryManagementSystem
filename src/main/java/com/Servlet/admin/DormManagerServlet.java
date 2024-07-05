package com.Servlet.admin;

import com.Bean.DormBuild;
import com.Bean.User;
import com.Service.DormBuildService;
import com.Service.DormBuildServiceImpl;
import com.Service.UserService;
import com.Service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍管理员的处理
 */

@WebServlet(value = {"/dormManagerAction"})
public class DormManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取参数
        String action = request.getParameter("action");

        //2.创建业务逻辑层对象
        UserService userService = new UserServiceImpl();
        DormBuildService dormBuildService = new DormBuildServiceImpl();

        //3.判断
        switch (action)
        {
            case "list": //展示所有
            {
                //1.先获取所有的宿管和他们管理的宿舍楼
                List<User> managers = userService.findAllDormManagers();

                for (User manager : managers)
                {
                    Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                    DormBuild b = null;

                    if (dormBuildId != null)
                    {
                        b = dormBuildService.findById(dormBuildId);
                    }else {
                        b = new DormBuild();
                    }

                    manager.setName(b.getName());
                }

                //2.再获取可用的宿舍楼
                List<DormBuild> builds = dormBuildService.findSurplus();

                //3.设置属性
                request.setAttribute("managers", managers);
                request.setAttribute("surplusBuilds", builds);
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");
            }
            break;

            case "preAdd": //添加宿舍管理员
            {
                //1.进入添加页面之前，需要把可用的宿舍楼也传递过去
                List<DormBuild> builds = dormBuildService.findSurplus();

                //2.设置属性
                request.setAttribute("surplusBuilds", builds);
                request.setAttribute("target", "add");
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerAddOrUpdate.jsp");
            }
            break;
            case "delete": //删除宿舍管理员
            {
                //1.获取参数
                String id = request.getParameter("id");

                //2.更新宿舍管理员的disabled属性
                userService.removeManager(Integer.parseInt(id));

                //3.获取所有的宿管和他们管理的宿舍楼
                List<User> managers = userService.findAllDormManagers();

                for (User manager : managers)
                {
                    Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                    DormBuild b = null;

                    if (dormBuildId != null)
                    {
                        b = dormBuildService.findById(dormBuildId);
                    }else {
                        b = new DormBuild();
                    }

                    manager.setName(b.getName());
                }

                //4.再获取可用的宿舍楼
                List<DormBuild> builds = dormBuildService.findSurplus();

                //5.设置属性
                request.setAttribute("managers", managers);
                request.setAttribute("surplusBuilds", builds);
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");
            }
            break;
            case "active": //激活被删除的宿舍管理员
            {
                //1.获取参数
                String id = request.getParameter("id");

                //2.更新宿舍管理员的disabled属性
                userService.activeManager(Integer.parseInt(id));

                //3.获取所有的宿管和他们管理的宿舍楼
                List<User> managers = userService.findAllDormManagers();

                for (User manager : managers)
                {
                    Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                    DormBuild b = null;

                    if (dormBuildId != null)
                    {
                        b = dormBuildService.findById(dormBuildId);
                    }else {
                        b = new DormBuild();
                    }

                    manager.setName(b.getName());
                }

                //4.再获取可用的宿舍楼
                List<DormBuild> builds = dormBuildService.findSurplus();

                //5.设置属性
                request.setAttribute("managers", managers);
                request.setAttribute("surplusBuilds", builds);
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");
            }
            break;
            case "preUpdate": //修改宿舍管理员
            {
                //1.获取属性
                String id = request.getParameter("id");


                //2.获取当前可用的宿舍楼
                List<DormBuild> builds = dormBuildService.findSurplus();

                //3.添加自身的宿舍楼
                User user = userService.findUserByID(Integer.parseInt(id));
                DormBuild build = dormBuildService.findById(user.getDormBuildId());
                if (build != null)
                {
                    builds.add(build);
                }

                //2.设置属性
                request.setAttribute("id", id);
                request.setAttribute("target", "update");
                request.setAttribute("updateUser",user);
                request.setAttribute("surplusBuilds", builds);
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerAddOrUpdate.jsp");
            }
            break;
        }


        //4.跳转
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String action = request.getParameter("action");

        //2.创建业务逻辑层对象
        UserService userService = new UserServiceImpl();
        DormBuildService dormBuildService = new DormBuildServiceImpl();

        //3.判断
        switch (action)
        {
            case "list": //条件查询
            {
                //1.获取数据
                String searchType = request.getParameter("searchType"); //获取查询类型
                String keyword = request.getParameter("keyword"); //获取关键词

                //2.分类判断
                switch (searchType)
                {
                    case "name": //根据姓名进行模糊查询
                    {
                        List<User> managers = new ArrayList<>();
                        if (keyword.trim().equals("")) //如果输入的内容等于空，则全部查询
                        {
                            managers = userService.findAllDormManagers();
                        }else { //如果输入的内容不等于空，则模糊查询
                            managers = userService.findDormManagersByName(keyword);
                        }

                        for (User manager : managers)
                        {
                            Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                            DormBuild build = null;

                            if (dormBuildId != null)
                            {
                                build = dormBuildService.findById(dormBuildId);
                            }else {
                                build = new DormBuild();
                            }

                            manager.setName(build.getName());
                        }

                        //再获取可用的宿舍楼
                        List<DormBuild> builds = dormBuildService.findSurplus();

                        //设置属性
                        request.setAttribute("keyword", keyword);
                        request.setAttribute("type", "name");
                        request.setAttribute("managers", managers);
                        request.setAttribute("surplusBuilds", builds);
                        request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");
                    }
                    break;

                    case "sex": //根据性别进行查询
                    {
                        List<User> managers = new ArrayList<>();
                        if (keyword.trim().equals("")) //如果输入的内容等于空，则全部查询
                        {
                            managers = userService.findAllDormManagers();
                        }else { //如果输入的内容不等于空，则模糊查询
                            managers = userService.findDormManagersBySex(keyword);
                        }

                        for (User manager : managers)
                        {
                            Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                            DormBuild build = null;

                            if (dormBuildId != null)
                            {
                                build = dormBuildService.findById(dormBuildId);
                            }else {
                                build = new DormBuild();
                            }

                            manager.setName(build.getName());
                        }

                        //再获取可用的宿舍楼
                        List<DormBuild> builds = dormBuildService.findSurplus();

                        //设置属性
                        request.setAttribute("keyword", keyword);
                        request.setAttribute("type", "sex");
                        request.setAttribute("managers", managers);
                        request.setAttribute("surplusBuilds", builds);
                        request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");
                    }
                    break;

                    case "tel": //根据电话号码进行模糊查询
                    {
                        List<User> managers = new ArrayList<>();
                        if (keyword.trim().equals("")) //如果输入的内容等于空，则全部查询
                        {
                            managers = userService.findAllDormManagers();
                        }else { //如果输入的内容不等于空，则模糊查询
                            managers = userService.findDormManagersByTel(keyword);
                        }

                        for (User manager : managers)
                        {
                            Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                            DormBuild build = null;

                            if (dormBuildId != null)
                            {
                                build = dormBuildService.findById(dormBuildId);
                            }else {
                                build = new DormBuild();
                            }

                            manager.setName(build.getName());
                        }

                        //再获取可用的宿舍楼
                        List<DormBuild> builds = dormBuildService.findSurplus();

                        //设置属性
                        request.setAttribute("keyword", keyword);
                        request.setAttribute("type", "tel");
                        request.setAttribute("managers", managers);
                        request.setAttribute("surplusBuilds", builds);
                        request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");
                    }
                    break;
                }
            }
            break;

            case "save": //保存数据
            {
                //1.先获取数据
                String stuCode = request.getParameter("stuCode"); //学号
                String name = request.getParameter("name"); //姓名
                String password = request.getParameter("passWord"); //密码
                String sex = request.getParameter("sex"); //性别
                String tel = request.getParameter("tel"); //电话号码
                String buildID = request.getParameter("buildID"); //选中的楼栋ID

                //2.获取当前的对象

                User currentUser = (User) request.getSession().getAttribute("session_user");
                //3.获取宿舍名
                DormBuild build = dormBuildService.findById(Integer.parseInt(buildID));

                //4.创建对象
                User user = new User(null, name, password, stuCode,
                        null, sex, tel, Integer.parseInt(buildID), (byte) 1, currentUser.getId(),
                        (byte) 0, build.getName());

                //5.插入对象
                int result = userService.saveManager(user);

                //6.判断插入是否成功
                if (result != 0)
                {
                    //先获取所有的宿管和他们管理的宿舍楼
                    List<User> managers = userService.findAllDormManagers();

                    for (User manager : managers)
                    {
                        Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                        DormBuild b = null;

                        if (dormBuildId != null)
                        {
                            b = dormBuildService.findById(dormBuildId);
                        }else {
                            b = new DormBuild();
                        }

                        manager.setName(b.getName());
                    }

                    //再获取可用的宿舍楼
                    List<DormBuild> builds = dormBuildService.findSurplus();

                    //设置属性
                    request.setAttribute("managers", managers);
                    request.setAttribute("surplusBuilds", builds);
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");
                }

                //7.如果插入失败
                else {
                    //获取可用的宿舍楼
                    List<DormBuild> builds = dormBuildService.findSurplus();

                    //设置错误信息
                    request.setAttribute("error", "登录名重复了!");
                    //跳转到宿舍管理员添加的页面
                    request.setAttribute("surplusBuilds", builds);
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerAddOrUpdate.jsp");
                }
            }
            break;

            case "update": //更新数据
            {
                //1.先获取数据
                String id = request.getParameter("id"); //ID
                String stuCode = request.getParameter("stuCode"); //登录名
                String name = request.getParameter("name"); //姓名
                String passWord = request.getParameter("passWord"); //密码
                String sex = request.getParameter("sex"); //性别
                String tel = request.getParameter("tel"); //电话号码
                String buildID = request.getParameter("buildID"); //选中的楼栋ID

                User currentUser = (User) request.getSession().getAttribute("session_user");
                //2.创建对象
                User user = new User(Integer.parseInt(id), name, passWord, stuCode, null,
                        sex, tel, Integer.parseInt(buildID), (byte) 2, currentUser.getId(),(byte) 0, null);

                //3.更新对象
                Integer result = userService.updateManager(user);

                //4.判断是否更新成功
                if (result != 0)
                {
                    //先获取所有的宿管和他们管理的宿舍楼
                    List<User> managers = userService.findAllDormManagers();

                    for (User manager : managers)
                    {
                        Integer dormBuildId = manager.getDormBuildId(); //获取宿舍楼的ID
                        DormBuild b = null;

                        if (dormBuildId != null)
                        {
                            b = dormBuildService.findById(dormBuildId);
                        }else {
                            b = new DormBuild();
                        }

                        manager.setName(b.getName());
                    }

                    //再获取可用的宿舍楼
                    List<DormBuild> builds = dormBuildService.findSurplus();

                    //设置属性
                    request.setAttribute("managers", managers);
                    request.setAttribute("surplusBuilds", builds);
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerList.jsp");

                }

                //5.更新失败
                else {
                    //设置错误信息
                    request.setAttribute("error", "登录名重复了!");
                    request.setAttribute("user",user);
                    //跳转到宿舍管理员更新的页面
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormManagerAddOrUpdate.jsp");
                }
            }
            break;
        }

        //4.跳转
        request.getRequestDispatcher("main.jsp").forward(request, response);

    }
}

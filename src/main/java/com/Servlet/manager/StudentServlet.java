package com.Servlet.manager;

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
import java.util.List;

/**
 * 宿舍管理员的学生业务处理
 */

@WebServlet(value = {"/studentActionInManager"})
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String action = request.getParameter("action");

        //2.创建业务员逻辑对象
        UserService userService = new UserServiceImpl();
        DormBuildService dormBuildService = new DormBuildServiceImpl();

        //3.获取当前的用户
        User manager = (User) request.getSession().getAttribute("session_user");
        DormBuild b = dormBuildService.findById(manager.getDormBuildId());
        manager.setName(b.getName());

        //4.分类判断
        switch (action)
        {
            case "list": //刚进来的时候展示的页面
            {
                //1.获取学生的列表
                List<User> students = userService.findUserByPage(1, String.valueOf(manager.getDormBuildId()), "name", "");
                //2.更新学生住的宿舍楼的名字
                for (User student : students)
                {
                    student.setName(manager.getName());
                }

                //3.获取总的数据量
                int count = userService.getStudentCount(String.valueOf(manager.getDormBuildId()), "name", "");

                //4.获取总的页数
                int totalPage = (int) Math.ceil(count / 3.0);

                //5.设置参数
                request.setAttribute("pageIndex", 1); //当前页码
                request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage);
                request.setAttribute("totalNum", count); //总的记录数量
                request.setAttribute("keyword", ""); //关键词
                request.setAttribute("searchType", "name"); //搜索框的选中项
                request.setAttribute("manager", manager); //管理员

                request.setAttribute("students", students);
                request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
            }
            break;

            case "first": //首页
            {
                //1.先获取参数
                String buildId = request.getParameter("buildId"); //楼栋的ID
                String searchType = request.getParameter("searchType"); //搜索类型
                String keyword = request.getParameter("keyword"); //检索内容

                //2.获取学生列表
                List<User> students = userService.findUserByPage(1, buildId, searchType, keyword);

                //3.更新学生住的宿舍楼的名字
                for (User student : students)
                {
                    student.setName(manager.getName());
                }

                //4.获取总的数量
                int count = userService.getStudentCount(buildId, searchType, keyword);

                //5.获取总的页数
                int totalPage = (int) Math.ceil(count / 3.0);

                //6.设置参数
                request.setAttribute("pageIndex", 1); //当前页码
                request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage);
                request.setAttribute("totalNum", count); //总的记录数量
                request.setAttribute("keyword", keyword); //关键词
                request.setAttribute("searchType", searchType); //搜索框的选中项
                request.setAttribute("manager", manager); //管理员

                request.setAttribute("students", students);
                request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
            }
            break;

            case "last": //尾页
            {
                //1.先获取参数
                String buildId = request.getParameter("buildId"); //楼栋的ID
                String searchType = request.getParameter("searchType"); //搜索类型
                String keyword = request.getParameter("keyword"); //检索内容

                //2.获取总的数量
                int count = userService.getStudentCount(buildId, searchType, keyword);

                //3.获取总的页数
                int totalPage = (int) Math.ceil(count / 3.0);
                totalPage = (totalPage == 0 ? 1 : totalPage);

                //4.获取学生列表
                List<User> students = userService.findUserByPage(totalPage, buildId, searchType, keyword);

                //5.更新学生住的宿舍楼的名字
                for (User student : students)
                {
                    student.setName(manager.getName());
                }

                //6.设置参数
                request.setAttribute("pageIndex", totalPage); //当前页码
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("totalNum", count); //总的记录数量
                request.setAttribute("keyword", keyword); //关键词
                request.setAttribute("searchType", searchType); //搜索框的选中项
                request.setAttribute("manager", manager); //管理员

                request.setAttribute("students", students);
                request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
            }
            break;

            case "newPage": //翻页
            {
                //1.先获取参数
                String page = request.getParameter("page"); //页码
                String buildId = request.getParameter("buildId"); //宿舍楼的ID
                String searchType = request.getParameter("searchType"); //搜索类型
                String keyword = request.getParameter("keyword"); //检索内容

                //2.获取学生列表
                List<User> students = userService.findUserByPage(Integer.parseInt(page), buildId, searchType, keyword);

                //3.更新学生住的宿舍楼的名字
                for (User student : students)
                {
                    student.setName(manager.getName());
                }

                //4.获取总的数据量
                int count = userService.getStudentCount(buildId, searchType, keyword);

                //5.获取总的页数
                int totalPage = (int) Math.ceil(count / 3.0);

                //6.设置参数
                request.setAttribute("pageIndex", page); //当前页码
                request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage);
                request.setAttribute("totalNum", count); //总的记录数量
                request.setAttribute("keyword", keyword); //关键词
                request.setAttribute("searchType", searchType); //搜索框的选中项
                request.setAttribute("manager", manager); //管理员

                request.setAttribute("students", students);
                request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
            }
            break;

            case "preAdd": //添加学生
            {
                //1.获取当前所有的宿舍
                List<DormBuild> builds = dormBuildService.findAll();
                //2.设置属性
                request.setAttribute("target", "add"); //添加标签
                request.setAttribute("manager", manager); //管理员
                request.setAttribute("builds", builds); //所有的宿舍楼
                request.setAttribute("mainRight", "/Manager-JSP/studentAddOrUpdate.jsp"); //右侧的显示栏

            }
            break;

            case "delete": //删除学生
            {
                //1.获取参数
                String id = request.getParameter("id"); //获取学生的id

                //2.更新学生的三个属性,disabled、dorm_Code和dormBuildId
                userService.deleteStudent(Integer.parseInt(id));

                //3.获取学生的列表
                List<User> students = userService.findUserByPage(1, String.valueOf(manager.getDormBuildId()), "name", "");

                //4.更新学生住的宿舍楼的名字
                for (User student : students)
                {
                    student.setName(manager.getName());
                }

                //5.获取总的数据量
                int count = userService.getStudentCount(String.valueOf(manager.getDormBuildId()), "name", "");

                //6.获取总的页数
                int totalPage = (int) Math.ceil(count / 3.0);

                //7.设置参数
                request.setAttribute("pageIndex", 1); //当前页码
                request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage);
                request.setAttribute("totalNum", count); //总的记录数量
                request.setAttribute("keyword", ""); //关键词
                request.setAttribute("searchType", "name"); //搜索框的选中项
                request.setAttribute("manager", manager); //管理员

                request.setAttribute("students", students);
                request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
            }
            break;

            case "active": //激活学生
            {
                //1.获取参数
                String id = request.getParameter("id"); //获取学生的ID

                //2.更新学生的1个属性，disabled
                userService.activeStudent(Integer.parseInt(id));

                //3.获取学生列表
                List<User> students = userService.findUserByPage(1, String.valueOf(manager.getDormBuildId()), "name", "");

                //4.更新学生住的宿舍楼的名字
                for (User student : students)
                {
                    student.setName(manager.getName());
                }

                //5.获取总的数据量
                int count = userService.getStudentCount(String.valueOf(manager.getDormBuildId()), "name", "");

                //6.获取总的页数
                int totalPage = (int) Math.ceil(count / 3.0);

                //7.设置参数
                request.setAttribute("pageIndex", 1); //当前页码
                request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage);
                request.setAttribute("totalNum", count); //总的记录数量
                request.setAttribute("keyword", ""); //关键词
                request.setAttribute("searchType", "name"); //搜索框的选中项
                request.setAttribute("manager", manager); //管理员

                request.setAttribute("students", students);
                request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
            }
            break;

            case "preUpdate": //进入更新用户的界面
            {
                //1.获取传入的参数
                String id = request.getParameter("id");

                //2.根据id查找相关的信息
                User user = userService.findUserByID(Integer.parseInt(id));
                //3.获取所有的宿舍楼
                List<DormBuild> builds = dormBuildService.findAll();
                //4.回传信息
                request.setAttribute("target", "update");
                request.setAttribute("updateUser", user);
                request.setAttribute("manager", manager);
                request.setAttribute("builds", builds);
                request.setAttribute("mainRight", "/Manager-JSP/studentAddOrUpdate.jsp");
            }
            break;
        }

        //5.跳转
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String action = request.getParameter("action");

        //2.创建业务员逻辑对象
        UserService userService = new UserServiceImpl();
        DormBuildService dormBuildService = new DormBuildServiceImpl();

        //3.获取当前的用户
        User manager = (User) request.getSession().getAttribute("session_user");
        DormBuild b = dormBuildService.findById(manager.getDormBuildId());
        manager.setName(b.getName());

        //4.判断
        switch (action)
        {
            case "list": //条件查询
            {
                //1.先获取参数
                String dormBuildId = request.getParameter("dormBuildId"); //宿舍楼的ID
                String searchType = request.getParameter("searchType"); //搜索类型
                String keyword = request.getParameter("keyword"); //搜索内容

                //2.获取学生列表
                List<User> students = userService.findUserByPage(1, dormBuildId, searchType, keyword);

                //3.更新学生住的宿舍楼的名字
                for (User student : students)
                {
                    student.setName(manager.getName());
                }

                //4.获取总的数据量
                int count = userService.getStudentCount(dormBuildId, searchType, keyword);

                //5.获取总的页数
                int totalPage = (int) Math.ceil(count / 3.0);

                //6.设置参数
                request.setAttribute("pageIndex", 1); //当前页码
                request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage);
                request.setAttribute("totalNum", count); //总的记录数量
                request.setAttribute("keyword", keyword); //关键词
                request.setAttribute("searchType", searchType); //搜索框的选中项
                request.setAttribute("manager", manager); //管理员

                request.setAttribute("students", students);
                request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
            }
            break;

            case "save": //保存用户
            {
                //1.先获取数据
                String stuCode = request.getParameter("stuCode"); //学号
                String name = request.getParameter("name"); //姓名
                String sex = request.getParameter("sex"); //性别
                String tel = request.getParameter("tel"); //手机号
                String passWord = request.getParameter("passWord"); //密码
                String dormBuildId = request.getParameter("dormBuildId"); //宿舍楼的ID
                String dormCode = request.getParameter("dormCode"); //寝室编号



                DormBuild build = dormBuildService.findById(Integer.parseInt(dormBuildId));
                //2.创建对象
                User user = new User(null, name, passWord, stuCode, dormCode, sex,
                        tel, Integer.parseInt(dormBuildId), (byte) 2, manager.getId(),
                        (byte) 0, build.getName());

                //3.插入对象
                int result = userService.saveStudent(user);


                //4.判断插入是否成功
                if (result != 0) //成功
                {
                    //获取学生列表
                    List<User> students = userService.findUserByPage(1, "", "name", "");

                    //更新学生住的宿舍楼的名字
                    for (User student : students)
                    {
                        Integer buildId = student.getDormBuildId(); //获取宿舍楼的ID
                        DormBuild buildTemporary = null;
                        if (buildId != null)
                        {
                            buildTemporary = dormBuildService.findById(buildId);
                        }else {
                            buildTemporary = new DormBuild();
                        }

                        student.setName(buildTemporary.getName());
                    }

                    //获取所有的宿舍楼
                    List<DormBuild> builds = dormBuildService.findAll();


                    //获取总的数据量
                    int count = userService.getStudentCount(String.valueOf(manager.getDormBuildId()), "name", "");

                    //获取总的页数
                    int totalPage = (int) Math.ceil(count / 3.0);

                    //设置参数
                    request.setAttribute("pageIndex", 1); //当前页码
                    request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage); //总页码
                    request.setAttribute("totalNum", count); //总的记录数量
                    request.setAttribute("keyword", ""); //关键词
                    request.setAttribute("searchType", "name"); //搜索框的选中项
                    request.setAttribute("manager", manager); //管理员
                    request.setAttribute("builds", builds);
                    request.setAttribute("students", students);
                    request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
                }
                else { //失败
                    //设置属性
                    request.setAttribute("error", "登录名重复了");
                    request.setAttribute("manager", manager); //管理员
                    request.setAttribute("mainRight", "/Manager-JSP/studentAddOrUpdate.jsp");
                }
            }
            break;

            case "update": //更新学生
            {
                //1.获取参数
                String id = request.getParameter("id"); //用户的ID
                String stuCode = request.getParameter("stuCode"); //学号
                String name = request.getParameter("name"); //用户名
                String sex = request.getParameter("sex"); //性别
                String tel = request.getParameter("tel"); //手机号
                String passWord = request.getParameter("passWord"); //密码
                String dormBuildId = request.getParameter("dormBuildId"); //宿舍楼的ID
                String dormCode = request.getParameter("dormCode"); //寝室编号

                //2.创建对象
                User updateUser = new User(Integer.parseInt(id), name, passWord, stuCode, dormCode, sex, tel,
                        Integer.parseInt(dormBuildId), (byte) 2, null, (byte) 0, null);

                //3.调用对象，进行更新
                Integer result = userService.updateStudent(updateUser);

                //4.判断是否执行成功
                if (result != 0) //成功
                {
                    //获取学生列表
                    List<User> students = userService.findUserByPage(1, String.valueOf(manager.getDormBuildId()), "name", "");

                    //更新学生住的宿舍楼的名字
                    for (User student : students)
                    {
                        student.setName(manager.getName());
                    }

                    //获取总的数据量
                    int count = userService.getStudentCount(String.valueOf(manager.getDormBuildId()), "name", "");

                    //获取总的页数
                    int totalPage = (int) Math.ceil(count / 3.0);

                    //设置参数
                    request.setAttribute("pageIndex", 1); //当前页码
                    request.setAttribute("totalPage", totalPage == 0 ? 1 : totalPage); //总页码
                    request.setAttribute("totalNum", count); //总的记录数量
                    request.setAttribute("keyword", ""); //关键词
                    request.setAttribute("searchType", "name"); //搜索框的选中项
                    request.setAttribute("manager", manager); //管理员

                    request.setAttribute("students", students);
                    request.setAttribute("mainRight", "/Manager-JSP/studentList.jsp");
                } else { //失败
                    //设置属性
                    request.setAttribute("error", "登录名重复了");
                    request.setAttribute("manager", manager); //管理员
                    request.setAttribute("mainRight", "/Manager-JSP/studentAddOrUpdate.jsp");
                }
            }
            break;
        }

        //5.跳转
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}

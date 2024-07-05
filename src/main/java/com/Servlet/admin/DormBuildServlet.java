package com.Servlet.admin;

import com.Bean.DormBuild;
import com.Service.DormBuildService;
import com.Service.DormBuildServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍的处理
 */

@WebServlet(value = {"/dormBuildAction"})
public class DormBuildServlet extends HttpServlet {

    private int upperLength = 0; //上面的下拉框的长度
    private int lowerLength = 0; //下面的条数

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取输入参数
        String action = request.getParameter("action");

        //2.创建业务逻辑层的对象
        DormBuildService dormBuildService = new DormBuildServiceImpl();

        //3.判断
        switch (action)
        {
            case "save": //保存宿舍楼
            {
                //1.获取数据
                String name = request.getParameter("name"); //名称
                String remark = request.getParameter("remark"); //备注

                //2.宿舍楼的名字不可以重复
                DormBuild build = dormBuildService.findByName(name);
                if (build != null) //宿舍楼存在
                {
                    request.setAttribute("error", "当前宿舍楼已存在，请重新输入");
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildAddOrUpdate.jsp");
                }else { //宿舍楼不存在，保存信息到数据库
                    DormBuild dormBuild = new DormBuild();
                    dormBuild.setName(name);
                    dormBuild.setRemark(remark);
                    dormBuild.setDisabled((byte) 0);
                    dormBuildService.save(dormBuild); //保存数据

                    //3.返回展示所有的界面
                    List<DormBuild> builds = dormBuildService.findAll();
                    List<DormBuild> buildSelects = dormBuildService.findAll();

                    request.setAttribute("builds", builds);
                    request.setAttribute("buildSelects", buildSelects);
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildList.jsp"); //跳转到展示所有的页面
                }
            }
            break;

            case "list": //显示
            {
                //1.获取数据
                String id = request.getParameter("id");

                List<DormBuild> builds = new ArrayList<>(); //下面的显示
                List<DormBuild> buildSelects = new ArrayList<>(); //上面的显示
                if (id == null || id.equals("")) //id为空的处理手段,就是查询所有
                {
                    builds = dormBuildService.findAll();
                }else { //id不为空的处理手段,单个查询
                    DormBuild build = dormBuildService.findById(Integer.parseInt(id));
                    builds.add(build);
                }

                buildSelects = dormBuildService.findAll(); //上面的显示,一直都是全部内容

                //更新这个值
                upperLength = buildSelects.size();
                lowerLength = builds.size();

                request.setAttribute("buildSelects", buildSelects);
                request.setAttribute("id", id);
                request.setAttribute("builds", builds);
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildList.jsp");
            }
            break;
            case "update": //更新
            {
                //1.获取数据
                String name = request.getParameter("name"); //名称
                String remark = request.getParameter("remark"); //备注
                String id = request.getParameter("id"); //ID

                //2.判断修改是否成功
                int result = dormBuildService.update(Integer.parseInt(id), name, remark);
                if (result == 0) //失败
                {
                    request.setAttribute("error", "当前宿舍楼已存在，请重新输入");
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildAddOrUpdate.jsp");
                }else { //成功
                    //3.返回展示的界面
                    //如果下拉框的内容和下面的表格行数的一样，那就展示全文
                    if (upperLength == lowerLength)
                    {
                        List<DormBuild> builds = dormBuildService.findAll();
                        List<DormBuild> buildSelects = dormBuildService.findAll();

                        request.setAttribute("builds", builds);
                        request.setAttribute("buildSelects", buildSelects);
                        request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildList.jsp"); //跳转到展示所有的页面
                    }
                    //如果这两个数不一样
                    else {

                        List<DormBuild> builds = new ArrayList<>(); //下面的显示
                        List<DormBuild> buildSelects = new ArrayList<>(); //上面的显示
                        if (id == null || id.equals("")) //id为空的处理手段,就是查询所有
                        {
                            builds = dormBuildService.findAll();
                        }else { //id不为空的处理手段,单个查询
                            DormBuild build = dormBuildService.findById(Integer.parseInt(id));
                            builds.add(build);
                        }

                        buildSelects = dormBuildService.findAll(); //上面的显示,一直都是全部内容

                        request.setAttribute("buildSelects", buildSelects);
                        request.setAttribute("id", id);
                        request.setAttribute("builds", builds);
                        request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildList.jsp");
                    }
                }
            }
            break;
        }

        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取输入参数
        String action = request.getParameter("action");

        //2.创建业务逻辑层的对象
        DormBuildService dormBuildService = new DormBuildServiceImpl();

        //3.判断
        switch (action)
        {
            case "list": //展示所有
            {
                List<DormBuild> builds = dormBuildService.findAll(); //寻找所有的楼栋
                List<DormBuild> buildSelects = dormBuildService.findAll(); //查询所有的宿舍楼

                upperLength = buildSelects.size();
                lowerLength = builds.size();

                request.setAttribute("buildSelects", buildSelects); //上面的下拉框
                request.setAttribute("builds", builds); //下面的内容
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildList.jsp");
            }
            break;
            case "preAdd": //添加宿舍楼
            {
                request.setAttribute("target", "add"); //添加
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildAddOrUpdate.jsp");
            }
            break;
            case "preUpdate": //更新
            {
                //获取参数
                String id = request.getParameter("id");
                //获取宿舍楼对象
                DormBuild build = dormBuildService.findById(Integer.parseInt(id));

                request.setAttribute("build", build); //添加到修改中
                request.setAttribute("target", "update"); //修改
                request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildAddOrUpdate.jsp");
            }
            break;
            case "deleteOrAcive": //删除或者激活
            {
                //获取参数
                String id = request.getParameter("id");
                String disabled = request.getParameter("disabled");

                //2.更新
                dormBuildService.deleteOrActive(Integer.parseInt(id), Byte.parseByte(disabled));

                //3.更新完了以后，展示所有
                //如果下拉框的内容和下面的表格行数的一样，那就展示全文
                if (upperLength == lowerLength)
                {
                    List<DormBuild> builds = dormBuildService.findAll();
                    List<DormBuild> buildSelects = dormBuildService.findAll();

                    request.setAttribute("builds", builds);
                    request.setAttribute("buildSelects", buildSelects);
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildList.jsp"); //跳转到展示所有的页面
                }
                //如果这两个数不一样
                else {

                    List<DormBuild> builds = new ArrayList<>(); //下面的显示
                    List<DormBuild> buildSelects = new ArrayList<>(); //上面的显示
                    if (id == null || id.equals("")) //id为空的处理手段,就是查询所有
                    {
                        builds = dormBuildService.findAll();
                    }else { //id不为空的处理手段,单个查询
                        DormBuild build = dormBuildService.findById(Integer.parseInt(id));
                        builds.add(build);
                    }

                    buildSelects = dormBuildService.findAll(); //上面的显示,一直都是全部内容

                    request.setAttribute("buildSelects", buildSelects);
                    request.setAttribute("id", id);
                    request.setAttribute("builds", builds);
                    request.setAttribute("mainRight", "/SuperAdmin-JSP/dormBuildList.jsp");
                }
            }
            break;
        }

        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

}

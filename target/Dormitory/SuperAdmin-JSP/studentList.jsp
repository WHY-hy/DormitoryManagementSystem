<%@ page import="com.DAO.UserDAOImpl" %>
<%@ page import="com.DAO.DormBuildDAOImpl" %>
<%@ page import="com.Bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%--学生管理的所有页面--%>
<script type="text/javascript">

    function studentDelete(studentId)
    {
        if(confirm("您确定要删除这个学生吗？")) {
            window.location.href = "studentAction?action=delete&id=" + studentId;
        }
    }

    function studentActive(studentId)
    {
        if(confirm("您确定要激活这个学生吗？")) {
            window.location.href = "studentAction?action=active&id=" + studentId;
        }
    }
    //表示左侧的栏被选中
    $(document).ready(function(){
        $("#student").addClass("active");
    });

    function prePage(index)
    {
        if (index === 1) //如果当前页数=1，什么都不做
        {
            alert('当前是第一页，不可以往前翻页');
        }
        else if (index >= 2) { //如果当前页数为2，则可以往上翻页
            index--;

            //获取宿舍楼下拉列表的值
            var buildId = document.getElementById("dormBuildId").value;
            //获取搜索类型的值
            var searchType = document.getElementById("searchType").value;
            //获取输入框的值
            var keyword = document.getElementById("keyword").value;

            window.location.href = "studentAction?action=newPage&page=" + index + "&buildId=" + buildId + "&searchType=" + searchType + "&keyword=" + keyword;
        }
    }

    function nextPage(index)
    {
        //1.先获取总的页数
        var num = Number("${totalPage}");

        if (index === num) //如果已经是最后一页了，什么都不做
        {
            alert('当前是最后一页，不可以往后翻页');
        }
        else if (index < num)
        {
            index++;

            //获取宿舍楼下拉列表的值
            var buildId = document.getElementById("dormBuildId").value;
            //获取搜索类型的值
            var searchType = document.getElementById("searchType").value;
            //获取输入框的值
            var keyword = document.getElementById("keyword").value;

            window.location.href = "studentAction?action=newPage&page=" + index + "&buildId=" + buildId + "&searchType=" + searchType + "&keyword=" + keyword;
        }
    }

    function firstPage()
    {
        //1.获取宿舍楼下拉列表的值
        var buildId = document.getElementById("dormBuildId").value;
        //2.获取搜索类型的值
        var searchType = document.getElementById("searchType").value;
        //3.获取输入框的值
        var keyword = document.getElementById("keyword").value;
        //4.发送
        window.location.href = "studentAction?action=first&buildId=" + buildId + "&searchType=" + searchType + "&keyword=" + keyword;
    }

    function lastPage()
    {
        //1.获取宿舍楼下拉列表的值
        var buildId = document.getElementById("dormBuildId").value;
        //2.获取搜索类型的值
        var searchType = document.getElementById("searchType").value;
        //3.获取输入框的值
        var keyword = document.getElementById("keyword").value;
        //4.发送
        window.location.href = "studentAction?action=last&buildId=" + buildId + "&searchType=" + searchType + "&keyword=" + keyword;
    }

    function updateStudent(disabled, id)
    {
        if (disabled === 1)
        {
            alert("请先激活学生，再来修改学生!");
        }
        else {
            window.location.href = "studentAction?action=preUpdate&id=" + id;
        }
    }


    $(document).ready(function(){
        $("#student").addClass("active");
    });

</script>
<div class="data_list">
    <div class="data_list_title">
        <p style="font-size: 1.5em">学生管理</p>
    </div>

    <form name="myForm" class="form-search" method="post" action="studentAction?action=list" style="padding-bottom: 12px">

        <button  style="width: 60px;height: 40px;border-radius: 8px;background-color:#B0333351;left: 24px;"
                 type="button" onclick="javascript:window.location.href = 'studentAction?action=preAdd'">添加</button> <!--添加学生-->
        <input style="height: 30px" type="button" class="btn-add" value="导出EXCEL">
        <span class="data_search">

              <select id="dormBuildId" name="dormBuildId" style="width: 110px;">
                  <option value="" ${dormBuildId eq "" ? "selected" : ""}>全部宿舍楼</option>
                  <c:forEach items="${builds}" var="build">
                      <option value="${build.id}" ${dormBuildId eq build.id ? "selected" : ""}>${build.name}</option>
                  </c:forEach>
              </select>

              <select id="searchType" name="searchType" style="width: 80px;">
                  <option value="name" ${searchType eq "name" ? "selected" : ""}>姓名</option>
                  <option value="stuCode" ${searchType eq "stuCode" ? "selected" : ""}>学号</option>
                  <option value="sex" ${searchType eq "sex" ? "selected" : ""}>性别</option>
                  <option value="tel" ${searchType eq "tel" ? "selected" : ""}>手机号</option>
              </select>

              &nbsp;<input id="keyword" name="keyword" value="${keyword}" type="text"  style="width:120px;height: 30px;" class="input-medium search-query">
              &nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
          </span>

    <div style="margin-top: 16px">
        <table class="table table-striped table-bordered table-hover datatable">
            <!--表头-->
            <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>宿舍楼</th>
                <th>寝室号</th>
                <th>电话</th>
                <th>操作</th>
            </tr>
            </thead>

            <!--表身子-->
            <tbody>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.stu_code}</td>
                    <td>${student.username}</td>
                    <td>${student.sex}</td>
                    <td>${student.disabled eq 1 ? "" : student.name}</td>
                    <td>${student.disabled eq 1 ? "" : student.dorm_Code}</td>
                    <td>${student.tel}</td>

                    <td>
                        <button class="btn btn-mini btn-info" type="button" onclick="updateStudent(${student.disabled}, ${student.id})">修改</button>&nbsp;

                        <c:if test="${student.disabled eq 0}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="studentDelete(${student.id})">删除</button>
                        </c:if>

                        <c:if test="${student.disabled eq 1}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="studentActive(${student.id})">激活</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </form>

    <div align="center"><font color="red"></font></div>

    <!--分页展示栏-->
    <div style="text-align: center;">
        <!--totalNum:查询出的总数据量     pageSize：每一页展示的行数    pageIndex:表示当前页面
          submitUrl：表示点击上一页下一页首页 尾页是发送的请求-->
        <a style="cursor: pointer" onclick="firstPage()">首页</a>
        <a style="cursor: pointer" onclick="prePage(${pageIndex})">上一页</a>
        <a style="cursor: pointer" onclick="nextPage(${pageIndex})">下一页</a>
        <a style="cursor: pointer" onclick="lastPage()">尾页</a>
        <br>
        <span>当前是第${pageIndex}页</span>
        <span>/共${totalPage}页</span>
        <span>/共${totalNum}条数据</span>
    </div>
</div>

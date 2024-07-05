<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%--宿舍管理员的展示所有页面--%>
<script type="text/javascript">

    function userDelete(id, disabled)
    {
        if(confirm("您确定要删除这个宿管吗？")) {
            window.location.href = "dormManagerAction?action=delete&id=" + id + "&disabled=" + disabled;
        }
    }

    function userActive(id, disabled)
    {
        if (confirm("您确定要激活这个宿管吗？"))
        {
            window.location.href = "dormManagerAction?action=active&id=" + id + "&disabled=" + disabled;
        }
    }

    //文档加载完后
    window.onload=function(){
        //获取后台保存的当前要修改的foodTypeId值
        var searchType = "";
        //获取菜系select标签
        var searchTypeSelect = document.getElementById("searchType");
        //获取下拉框中所有的option
        var options = searchTypeSelect.options;

        //遍历菜系select标签中所有的option标签
        $.each( options, function(i, option){
            $(option).attr("selected",option.value === "${type}");
        });
    }

    $(document).ready(function(){
        $("#dormManager").addClass("active");
    });

    //判断添加按钮是否可以使用
    function addManager()
    {
        //1.先获取可用的宿舍楼数量
        var num = Number("${surplusBuilds.size()}");

        //2.如果num>=1，则发送添加宿管的请求
        if (num >= 1)
        {
            window.location.href = "dormManagerAction?action=preAdd";
        }
        //3.如果num<1，则告知不能添加宿管，因为没有可用的宿管了。
        else {
            alert("当前没有多余的宿舍楼，所以无法添加宿舍管理员!");
        }
    };

    //先判断宿管可不可以修改
    function updateManager(disabled, id, dormBuildId)
    {
        //不能修改
        if (disabled === 1)
        {
            alert('无法对已删除的宿管进行修改，请先激活宿管');
        }else {
            //如果dormBuildId不存在(删除后，刚激活)
            if (dormBuildId === undefined) //为空
            {
                //查看剩余的可选宿舍楼
                var num = Number("${surplusBuilds.size()}");

                if (num < 1) //没有
                {
                    alert("当前没有多余的宿舍楼，所以无法修改宿舍管理员!");
                }
                //有
                else {
                    window.location.href = "dormManagerAction?action=preUpdate&id=" + id;
                }
            }
            //有dormBuildId，即正常状态下的宿管
            else {
                window.location.href = "dormManagerAction?action=preUpdate&id=" + id;
            }
        }
    }

</script>

<div class="data_list">

    <div class="data_list_title">
        <p style="font-size: 1.5em">宿舍管理员管理</p>
    </div>

    <!--表格顶部-->
    <form name="myForm" class="form-search" method="post" action="dormManagerAction?action=list">
        <button style="width: 60px;height: 40px;border-radius: 8px;left: 24px; background-color: rgba(176,51,51,0.32)" type="button" onclick="addManager()">添加</button> <!--添加宿舍管理员-->
        <input style="height: 30px" type="button" class="btn-add" value="导出EXCEL">
        <span class="data_search">
            <select id="searchType" name="searchType" style="width: 80px;">
                <option value="name" >姓名</option>
                <option value="sex" >性别</option>
                <option value="tel" >手机号</option>
            </select>
            &nbsp;<input id="keyword" name="keyword" type="text" value="${keyword}" style="width:120px;height: 30px;" class="input-medium search-query">
            &nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button> <!--条件查询-->
        </span>
    </form>

    <!--表格中显示的是宿舍管理员的详细信息-->
    <div>
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>用户名</th>
                <th>姓名</th>
                <th>性别</th>
                <th>电话</th>
                <th>宿舍楼</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${managers}"  var="manager" varStatus="stat">
                <tr>
                    <td>${manager.stu_code}</td>
                    <td>${manager.username}</td>
                    <td>${manager.sex}</td>
                    <td>${manager.tel}</td>
                    <td>${manager.name}</td>
                    <td>
                        <button class="btn btn-mini btn-info" type="button" onclick="updateManager(${manager.disabled}, ${manager.id}, ${manager.dormBuildId})">修改</button>&nbsp;

                        <c:if test="${manager.disabled eq 0}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="userDelete(${manager.id}, 1)">删除</button>
                        </c:if>

                        <c:if test="${manager.disabled eq 1}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="userActive(${manager.id}, 0)">激活</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div align="center"><font color="red"></font></div>
</div>

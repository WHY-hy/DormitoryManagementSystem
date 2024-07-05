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


<%--宿舍楼展示所有的页面--%>
<!--内嵌式的JS-->
<script type="text/javascript">
    function dormBuildDelete(dormBuildId,disabled) {
        if(confirm("您确定要删除这个宿舍楼吗？")) {
            window.location="dormBuildAction?action=deleteOrAcive&id="+dormBuildId+"&disabled="+disabled;
        }
    }
    function dormBuildActive(dormBuildId,disabled) {
        if(confirm("您确定要激活这个宿舍楼吗？")) {
            window.location="dormBuildAction?action=deleteOrAcive&id="+dormBuildId+"&disabled="+disabled;
        }
    }
    //表示激活选中
    $(document).ready(function(){
        $("#dormBuild").addClass("active");
    });
</script>

<div class="data_list">

    <div class="data_list_title">
        <p style="font-size: 1.5em">宿舍楼管理</p>
    </div>

    <form name="myForm" class="form-search" method="post" action="dormBuildAction?action=list">
        <button style="width: 60px;height: 40px;border-radius: 8px;left: 24px; background-color: rgba(176,51,51,0.32)" type="button" style="margin-right: 50px;" onclick="javascript:window.location='dormBuildAction?action=preAdd'">添加</button>

        <span class="data_search">
            <select name="id" style="width: 120px;">

                <option value="">查看全部</option>
                <c:forEach items="${buildSelects}"  var="build" varStatus="stat">
                    <option value="${build.id}" ${id eq build.id ? "selected" : ""}>${build.name}</option>
                </c:forEach>

            </select>

            &nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button> <!--按回车键触发-->
        </span>

    </form>

    <div>
        <table class="table table-striped table-bordered table-hover datatable">

            <thead>
            <tr>
                <th width="15%">序号</th>
                <th>名称</th>
                <th>简介</th>
                <th width="20%">操作</th>
            </tr>
            </thead>

            <tbody>

            <!--items:表示要循环遍历的元素   var:代表当前集合中每一个元素     varStatus代表循环状态的变量名-->
            <c:forEach items="${builds}"  var="build" varStatus="stat">
                <tr class="text-center">
                    <td>${stat.count}</td>
                    <td>${build.name}</td>
                    <td>${build.remark}</td>
                    <td>
                        <button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuildAction?action=preUpdate&id=${build.id}'">修改</button>&nbsp;
                        <!--等于0的时候，可以删除-->
                        <c:if test="${build.disabled eq 0}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDelete(${build.id}, 1)">删除</button>
                        </c:if>

                        <!--等于1的时候，可以激活-->
                        <c:if test="${build.disabled eq 1}">
                            <button class="btn btn-mini btn-danger" type="button" onclick="dormBuildActive(${build.id}, 0)">激活</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

    <div align="center"><font color="red"></font></div>
</div>

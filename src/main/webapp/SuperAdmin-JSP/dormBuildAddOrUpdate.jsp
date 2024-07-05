<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%--宿舍楼添加或者修改的页面--%>
<!--内嵌式的JS-->
<script type="text/javascript">
    function checkForm(){
        //检查用户是否输入宿舍楼名字
        var name=document.getElementById("name").value;
        if(name == null || name === ""){
            document.getElementById("error").innerHTML="名称不能为空！";
            return false;
        }
        return true;
    }

    //表示激活选中
    $(document).ready(function(){
        $("#dormBuild").addClass("active");
    });
</script>

<div class="data_list">
    <div class="data_list_title">
        <c:if test="${target eq 'add'}">
            <p style="font-size: 1.5em">添加宿舍楼</p>
        </c:if>

        <c:if test="${target eq 'update'}">
            <p style="font-size: 1.5em">修改宿舍楼</p>
        </c:if>
    </div>


    <form action="${target eq 'add' ? "dormBuildAction?action=save" : "dormBuildAction?action=update"}" method="post" onsubmit="return checkForm()">
        <div class="data_form table-container" >
            <table align="center">
                <tr>
                    <td><i class="fas fa-building" ></i>名称：</td>
                    <td><input type="text" id="name"  name="name" value="${build.name}"  style="margin-top:5px;height:36px;" /></td>
                </tr>
                <tr>
                    <td>&nbsp;简介：</td>
                    <td><textarea id="remark" name="remark" rows="10"></textarea></td>
                </tr>
                <tr style="font-size: 1.25em">
                    <td>
                        <input type="hidden" id="id" name="id" value="${build.id}"/>
                        <font id="error" color="red">${error}</font>
                    </td>
                </tr>

                <tr>
                    <td>
                        <input type="submit" style="width: 60px;height: 40px;border-radius: 8px;margin-top: 28px;left: 24px; background-color: rgba(176,51,51,0.32)" value="保存"/>
                        &nbsp;<button style="width: 60px;height: 40px;border-radius: 8px;margin-top: 28px;left: 24px; background-color: rgba(176,51,51,0.32)" type="button" onclick="javascript:window.location='dormBuildAction?action=list'">返回</button>
                    </td>
                </tr>
            </table>

        </div>
    </form>
</div>

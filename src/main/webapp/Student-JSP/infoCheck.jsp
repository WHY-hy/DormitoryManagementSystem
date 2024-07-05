<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<script type="text/javascript">

    $(document).ready(function(){
        $("#info").addClass("active");
    });

</script>
<%--查看学生的信息--%>
<div class="data_list" >

    <!--标题-->
    <div class="data_list_title">
        <p style="font-size: 1.5em">查看信息</p>
    </div>

    <div class="data_form table-container" >

        <table align="center" border="4px">
            <tr style="text-align: center; margin-top:5px;height:36px">
                <td style="width:125px">学号：</td>
                <td style="width:180px">${student.stu_code}</td>
            </tr>

            <tr style="text-align: center; margin-top:5px;height:36px">
                <td style="width:125px">姓名：</td>
                <td style="width:180px">${student.username}</td>
            </tr>

            <tr style="text-align: center; margin-top:5px;height:36px">
                <td style="width:125px">性别：</td>
                <td style="width:180px">${student.sex}</td>
            </tr>

            <tr style="text-align: center; margin-top:5px;height:36px">
                <td style="width:125px">手机号：</td>
                <td style="width:180px">${student.tel}</td>
            </tr>

            <tr style="text-align: center; margin-top:5px;height:36px">
                <td style="width:125px">宿舍楼：</td>
                <td style="width:180px">${student.name}</td>
            </tr>

            <tr style="text-align: center; margin-top:5px;height:36px">
                <td style="width:125px">寝室编号：</td>
                <td style="width:180px">${student.dorm_Code}</td>
            </tr>

        </table>
    </div>
</div>

<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
    <%--宿舍管理系统登录的主界面--%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>宿舍管理系统登录</title>

        <!--外联式的CSS样式设计-->
        <link href="./bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="css/login.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="./css/all.min.css">
        <!--外联式的JS-->
        <script type="text/javascript" src="./bootstrap/js/jQuery.js"></script>

        <!--内嵌式的JS-->
        <script type="text/javascript">
            function checkForm() {
                //判断用户是否输入的学号和密码
                var stuCode = document.getElementById("stuCode").value;
                var password = document.getElementById("password").value;
                if (stuCode == null || stuCode === "") {
                    document.getElementById("error").innerHTML = "学号/工号不能为空";
                    return false;
                }
                if (password == null || password === "") {
                    document.getElementById("error").innerHTML = "密码不能为空";
                    return false;
                }

                return true; //返回true，才提交表单
            }

            $(document).ready(function(){
                $("#login").addClass("active");
            });

        </script>

        <!--内嵌式的CSS-->


    </head>

    <%--网页的结构主体--%>
<body>
<div class="box">
    <!--表单-->
    <form name="myForm"  action="Login" method="post" onsubmit="return checkForm()">
        <h2 class="form-signin-heading">宿舍管理系统</h2>
        <input type="hidden"  name="action"  value="submit">

        <!--账号密码输入栏-->

        <i class="fas fa-user" style="margin-bottom: 4px;margin-left: 12px; font-size: 28px"></i>
        <div class="input-box">
            <!-- 如果想让控件像块级元素一样占满容器，就可以为它添加 .input-block-level 类。这样，控件不仅可以占满容器，还可以根据浏览器窗口自动调整尺寸 -->
            <input id="stuCode" name="stuCode" value="${stuCode}" type="text" class="input-block-level" placeholder="请输入学号/工号...">
        </div>

        <i class="fas fa-key" style="margin-bottom: 4px;margin-left: 12px;font-size: 28px"></i>
        <div class="input-box">
            <input id="password" name="password" value="${password}" type="password" class="input-block-level" placeholder="请输入密码..." >
        </div>
        <!--角色输入栏-->
        <div id="role" style="margin-top: 8px">
            <label style="display: inline-block">
                <input type="radio" name="roleId" id="role1" value="0" ${role eq 0 ? "checked" : ""} style="height:10px; vertical-align:top;"><span style="font-size: 1.25em">超级管理员</span>
            </label>

            <label style="display: inline-block">
                <input type="radio" name="roleId" value="1" ${role eq 1 ? "checked" : ""} style="height:10px; vertical-align:top;"><span style="font-size: 1.25em">宿舍管理员</span>
            </label>

            <label style="display: inline-block">
                <input type="radio" name="roleId" value="2" ${role eq 2 ? "checked" : ""} style="height:10px; vertical-align:top;"><span style="font-size: 1.25em">学生</span>
            </label>
        </div>


        <!--记住我和出错时的提示语-->
        <label class="checkbox" style="margin-top: 10px;font-size: 1.25em ">
            <input id="remember" name="remember" type="checkbox" value="remember-me" >记住我 &nbsp;&nbsp;&nbsp;&nbsp;
            <font id="error" color="red">${error}</font>
        </label>

        <!--下方的两个按钮-->
        <div class="btn-box">
            <div >
                &nbsp;&nbsp; <button  type="submit">登录</button> &nbsp;&nbsp;&nbsp; <button  type="button" id="resetBtn">重置</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>

        <script type="text/javascript">
            //重置按钮
            document.querySelector("#resetBtn").addEventListener("click", function ()
            {
                document.querySelector("#stuCode").value = '';
                document.querySelector("#password").value = '';
                document.querySelector("#role1").checked = true;
            });
        </script>

    </form>
</div>
</body>
</html>

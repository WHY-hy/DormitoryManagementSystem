<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<%--主页的界面--%>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>宿舍管理系统</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link href="./css/dorm.css" rel="stylesheet">
  <link href="css/main.css" rel="stylesheet">
  <link href="./css/all.min.css">
  <link href="./bootstrap/css/bootstrap.css" rel="stylesheet"><!--（含有bootstrap 所有css）  -->
  <!-- 日期控件所需的样式表 -->
  <link href="./bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
  <script type="text/javascript" src="./bootstrap/js/jQuery.js"></script>
  <script src="./bootstrap/js/bootstrap.js"></script>
  <!-- 日期控件所需的js -->
  <script type="text/javascript" src="./bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
  <!-- 如需支持简体中文的显示，就需要加载中文的资源文件 -->
  <script type="text/javascript" src="./bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
</head>

<%--主页的界面--%>
<body>

<!--正文-->
<div class=" navbar" >
  <input type="checkbox" id="checkbox">
  <label for="checkbox">
    <i class="fa fa-bars" aria-hidden="true"></i>
  </label>
  <ul>
    <!--超级管理员-->
    <c:if test="${session_user.role_id eq 0}">
      <li><img src="images/2.jpg" alt=""><span style="font-size: 1.25em">欢迎您！${session_user.username}</span></li>
      <li><a href="indexAction"><i class="fas fa-home" ></i><span>首页</span></a></li>
      <li id="dormManager">
        <a href="dormManagerAction?action=list"><i class="fas fa-user-cog" aria-hidden="true"></i><span>宿舍管理员</span></a>
      </li>
      <li id="student">
        <a href="studentAction?action=list"><i class="fas fa-address-book" ></i><span>学生管理</span></a>
      </li>
      <li id="dormBuild">
        <a href="dormBuildAction?action=list"><i class="far fa-building" aria-hidden="true"></i><span>宿舍楼管理</span></a>
      </li>
      <li id="info">
        <a href="InfoCheckAction?action=list"><i class="far fa-address-card" aria-hidden="true"></i><span>信息查看</span></a>
      </li>
      <li id="password">
        <a href="passwordAction?action=preChange"><i class="fas fa-user-shield" aria-hidden="true"></i><span>修改密码</span></a>
      </li>
      <li id="loginOut">
        <a href="Logout"><i class="fas fa-sign-out-alt" aria-hidden="true"></i><span>退出系统</span></a>
      </li>
    </c:if>

    <!--宿管-->
    <c:if test="${session_user.role_id eq 1}">
      <li><img src="images/2.jpg" alt=""><span style="font-size: 1.25em">欢迎您！${session_user.username}</span></li>
      <li><a href="indexAction"><i class="fas fa-home" ></i>首页</a></li>
      <li id="student">
        <a href="studentActionInManager?action=list"><i class="fas fa-address-book" ></i><span>学生管理</span></a>
      </li>
      <li id="info">
        <a href="InfoCheckInManager?action=list"><i class="far fa-address-card"></i><span>信息查看</span></a>
      </li>
      <li id="password">
        <a href="passwordAction?action=preChange"><i class="fas fa-user-shield" ></i><span>修改密码</span></a>
      </li>
      <li id="loginOut">
        <a href="Logout"><i class="fas fa-sign-out-alt" ></i><span>退出系统</span></a>
      </li>
    </c:if>

    <!--学生-->
    <c:if test="${session_user.role_id eq 2}">
      <li><img src="images/2.jpg" alt=""><span style="font-size: 1.25em">欢迎您！${session_user.username}</span></li>
      <li>
        <a href="indexAction"><i class="fas fa-home" ></i>首页</a>
      </li>
      <li id="info">
        <a href="InfoCheckInStudent?action=list"><i class="far fa-address-card"></i><span>信息查看</span></a>
      </li>
      <li id="password">
        <a href="passwordAction?action=preChange"><i class="fas fa-user-shield" ></i><span>修改密码</span></a>
      </li>
      <li id="loginOut">
        <a href="Logout"><i class="fas fa-sign-out-alt" ></i><span>退出系统</span></a>
      </li>
    </c:if>
  </ul>
</div>

<div class="right-box">
  <!--右侧内容,动态包含-->
  <jsp:include page="${mainRight == null? 'home.jsp' : mainRight}"></jsp:include>
</div>


</body>
</html>

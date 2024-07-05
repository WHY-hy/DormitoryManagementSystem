<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404,找不到页面</title>

    <!--内嵌式的CSS样式设计-->
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        .frem{
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            background: url('images/gifs.gif');
            background-position: center;
            background-repeat: no-repeat;
        }
        .frem p{
            position: absolute;
            top: 3rem;
            font-size: 7rem;
            color: #00000063;
        }
        .frem h2{
            position: absolute;
            bottom: 8rem;
            font-size: 34px;
        }
        .frem h5{
            position: absolute;
            bottom: 6rem;
            color: #9c9c9c;
        }
        .frem a{
            position: absolute;
            bottom: 1rem;
            background: linear-gradient(45deg, #2a98ff, #09ff00);
            padding: 12px;
            color: white;
            text-decoration: none;
            font-size: 20px;
            border-radius: 13px;
        }
    </style>

<body>
<div class="frem">
    <p>404</p>
    <h2>看起来你迷路了</h2>
    <h5>你所寻找的页面已丢失!</h5>
    <a href="main.jsp">返回首页</a>
</div>
</body>

</html>

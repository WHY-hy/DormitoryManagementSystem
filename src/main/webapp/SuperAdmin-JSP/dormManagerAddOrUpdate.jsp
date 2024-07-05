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

<%--宿舍管理员的添加或者展示页面--%>
<script type="text/javascript" >
    //校验
    function checkForm(){
        //通过ID获取输入框中用户输入的值
        var stuCode = document.getElementById("stuCode").value; //登录名
        var name = document.getElementById("name").value; //姓名输入框
        var password = document.getElementById("passWord").value; //密码输入框
        var rPassword = document.getElementById("rPassword").value; //确认密码输入框

        var sex = document.getElementById("sex").value; //性别
        var tel = document.getElementById("tel").value; //电话
        var buildID = document.getElementById("buildID").value; //管理楼栋

        if(stuCode === "" || name === "" || password === "" || rPassword === "" || sex === "" || tel === "" || buildID === ""){
            document.getElementById("error").innerHTML="信息填写不完整！";
            return false;
        } else if(password !== rPassword){
            document.getElementById("error").innerHTML="密码填写不一致！";
            return false;
        }else if(!/^1[34578]\d{9}$/.test(tel)){
            document.getElementById("error").innerHTML="手机号码格式错误！";
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        $("#dormManager").addClass("active");
    });

    function backMain()
    {
        window.location.href = "dormManagerAction?action=list";
    }
</script>


<div class="data_list">
    <div class="data_list_title">
        <c:if test="${target eq 'update'}">
            <p style="font-size: 1.5em">修改管理员</p>
        </c:if>
        <c:if test="${target eq 'add'}">
            <p style="font-size: 1.5em">添加管理员</p>
        </c:if>
    </div>


    <form action="${target eq 'add' ? "dormManagerAction?action=save" : "dormManagerAction?action=update"}" method="post" onsubmit="return checkForm()">
        <div class="data_form table-container" >

            <table align="center">
                <tr>
                    <td><i class="fas fa-user-alt"></i>用户名：</td>
                    <td><input type="text" id="stuCode"  name="stuCode" value="${updateUser.stu_code}"  style="margin-top:5px;height:36px;width: 360px" /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-smile" ></i>姓名：</td>
                    <td><input type="text" id="name"  name="name" value="${updateUser.username}"  style="margin-top:5px;height:36px;width: 360px" /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-key"></i>密码：</td>
                    <td><input type="password" id="passWord"  name="passWord" value="${updateUser.password}"  style="margin-top:5px;height:36px;width: 360px"/></td>
                </tr>

                <tr>
                    <td><i class="fas fa-key"></i>确认密码：</td>
                    <td><input type="password" id="rPassword"  name="rPassword" value=""  style="margin-top:5px;height:36px;width: 360px"/></td>
                </tr>

                <tr>
                    <td><i class="fas fa-venus-mars"></i>性别：</td>
                    <td>
                        <select id="sex" name="sex" style="width: 90px;">
                            <option value="男" ${updateUser.sex == "男 " ? 'selected' : ""} >男</option>
                            <option value="女" ${updateUser.sex == "女" ? 'selected' : ""}>女</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td><i class="fas fa-phone-alt" ></i>联系电话：</td>
                    <td><input type="text" id="tel"  name="tel" value="${updateUser.tel}"  style="margin-top:5px;height:36px;width: 360px" /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-building" ></i>管理楼栋：</td>
                    <td>
                        <!--遍历所有的宿舍楼-->
                        <select name="buildID" id="buildID" style="width: 90px;">
                            <c:forEach items="${surplusBuilds}" var="build" varStatus="stat">
                                <option value="${build.id}">${build.name}</option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
               <tr style="font-size: 1.25em">
                   <td>
                       <font id="error" color="red">${error}</font>
                       <input type="hidden" id="id" name="id" value="${id}"/>
                   </td>
               </tr>
                <tr>
                    <td >
                        <input type="submit" style="width: 60px;height: 40px;border-radius: 8px;margin-top: 28px;left: 24px; background-color: rgba(176,51,51,0.32)" value="保存"/>
                        &nbsp;<button style="width: 60px;height: 40px;border-radius: 8px;margin-top: 28px;margin-left: 24px; background-color: rgba(176,51,51,0.32)" type="button" onclick="backMain()">返回</button>
                    </td>
                </tr>
            </table>



        </div>
    </form>
</div>

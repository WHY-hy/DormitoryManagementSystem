<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%--学生修改的页面--%>
<script type="text/javascript">

    //1.检查表单是否填写完毕
    function checkForm(){
        var stuCode=document.getElementById("stuCode").value; //学号
        var name=document.getElementById("name").value; //姓名
        var sex=document.getElementById("sex").value; //性别
        var tel=document.getElementById("tel").value; //手机号
        var password=document.getElementById("passWord").value; //密码
        var rPassword=document.getElementById("rPassword").value; //确认密码
        var dormBuildId=document.getElementById("dormBuildId").value; //宿舍楼的id
        var dormCode=document.getElementById("dormCode").value; //寝室编号

        if(stuCode === "" || name === "" || sex === "" || tel === "" || password === "" || rPassword === "" || dormBuildId === "" || dormCode === "")
        {
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

    //文档加载完成后
    window.onload = function(){
        //获取当前要修改的学生居中的宿舍楼id
        var studentBuildId = "${userUpdate.dormBuildId}";

        var dormBuildIdSelect = document.getElementById("dormBuildId");
        var options = dormBuildIdSelect.options;

        //遍历所有的option，如果option中的值=学生居中的宿舍楼id，则该option被选中
        $.each(options,function(i, option){
            $(option).attr("selected", option.value === studentBuildId);
        })
    }

    //返回函数
    function backMain()
    {
        window.location.href = "studentActionInManager?action=list";
    }

    $(document).ready(function(){
        $("#student").addClass("active");
    });

</script>


<div class="data_list">

    <!--标题-->
    <div class="data_list_title">
        <c:if test="${target eq 'update'}">
            <p style="font-size: 1.5em">修改学生</p>
        </c:if>
        <c:if test="${target eq 'add'}">
            <p style="font-size: 1.5em">添加学生</p>
        </c:if>
    </div>

    <form action="${target eq 'add' ? "studentActionInManager?action=save" : "studentActionInManager?action=update"}" method="post" onsubmit="return checkForm()">

        <div class="data_form table-container" >

            <!--错误信息-->
            <table align="center">

                <tr>
                    <td><i class="fas fa-sort-numeric-down"></i>学号：</td>
                    <td><input type="text" id="stuCode"  name="stuCode" value="${updateUser.stu_code}" style="margin-top:5px;height:36px;width: 360px"  /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-smile" ></i>姓名：</td>
                    <td><input type="text" id="name"  name="name" value="${updateUser.username}"  style="margin-top:5px;height:36px;width: 360px" /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-venus-mars"></i>性别：</td>
                    <td>
                        <select id="sex" name="sex" style="width: 90px;">
                            <option value="男" ${updateUser.sex == "男 " ? 'selected' : ""}>男</option>
                            <option value="女" ${updateUser.sex == "女" ? 'selected' : ""}>女</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td><i class="fas fa-phone-alt" ></i>联系电话：</td>
                    <td><input type="text" id="tel"  name="tel" value="${updateUser.tel}"  style="margin-top:5px;height:36px;width: 360px"  /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-key"></i>密码：</td>
                    <td><input type="password" id="passWord"  name="passWord" value="${updateUser.password}"  style="margin-top:5px;height:36px;width: 360px"  /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-key"></i>确认密码：</td>
                    <td><input type="password" id="rPassword"  name="rPassword" value=""  style="margin-top:5px;height:36px;width: 360px"  /></td>
                </tr>

                <tr>
                    <td><i class="fas fa-building" ></i>宿舍楼：</td>
                    <td>
                        <select id="dormBuildId" name="dormBuildId" style="width: 90px;">
                            <c:forEach items="${builds}" var="build">
                                <option value="${build.id}">${build.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td><i class="fas fa-building" ></i>寝室编号：</td>
                    <td><input type="text" id="dormCode"  name="dormCode" value="${updateUser.dorm_Code}"  style="margin-top:5px;height:36px;width: 360px"  /></td>
                </tr>
                <!--错误信息-->
                <tr style="font-size: 1.25em">
                    <td>
                        <font id="error" color="red">${error}</font>
                        <input type="hidden" id="id"  name="id" value="${updateUser.id}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit"  style="width: 60px;height: 40px;border-radius: 8px;margin-top: 28px;left: 24px; background-color: rgba(176,51,51,0.32)" value="保存"/>
                        &nbsp;<button style="width: 60px;height: 40px;border-radius: 8px;margin-top: 28px;left: 24px; background-color: rgba(176,51,51,0.32)" type="button" onclick="backMain()">返回</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

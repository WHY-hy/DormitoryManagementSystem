<%--
  Created by IntelliJ IDEA.
  User: 星河皓月
  Date: 2024/6/20
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--修改密码的页面--%>
<script type="text/javascript" src="passwordChanged.jsp"></script>
<script type="text/javascript">
    function checkTable() {
        // 获取输入框元素
        var oldPassword = document.getElementById('oldPassword');
        var newPassword = document.getElementById('newPassword');
        var rPassword = document.getElementById('rPassword');
        var error = document.getElementById('error');

        // 清除之前的错误信息
        error.textContent = '';

        // 检查原密码是否为空
        if (oldPassword.value.trim() === '') {
            error.textContent = '原密码不能为空';
            return false;
        }

        // 检查新密码是否为空
        if (newPassword.value.trim() === '') {
            error.textContent = '新密码不能为空';
            return false;
        }

        // 检查确认新密码是否为空
        if (rPassword.value.trim() === '') {
            error.textContent = '请确认新密码';
            return false;
        }

        // 检查新密码和确认新密码是否一致
        if (newPassword.value !== rPassword.value) {
            error.textContent = '新密码和确认密码不一致';
            return false;
        }

        // 如果所有检查都通过
        return true;
    }
</script>


<div class="data_list">

    <div class="data_list_title " style="font-size: 2.5em">
        修改密码
    </div>

    <form action="passwordAction?action=change" method="post" onsubmit="return checkTable()">
        <div class="data_form table-container" >
            <table class="center-table">
                <tr>
                    <td style="font-size: 1.4em"><i class="fas fa-key"></i>原密码：</td>
                    <td><input type="password"  id="oldPassword"  name="oldPassword" style="margin-top:5px;height:36px;width: 360px" /></td>
                </tr>

                <tr>
                    <td style="font-size: 1.4em"><i class="fas fa-key"></i>新密码：</td>
                    <td><input type="password" id="newPassword"  name="newPassword" value="" style="margin-top:10px;height:36px;width: 360px" /></td>
                </tr>

                <tr>
                    <td style="font-size: 1.4em"><i class="fas fa-key"></i>确认新密码：</td>
                    <td><input type="password" id="rPassword"  name="rPassword" value="" style="margin-top:10px;height:36px;width: 360px" /></td>
                </tr>

                <tr style="margin-top: 28px;font-size: 1.4em">
                    <td >
                        <input type="submit" style="width: 100px;height: 40px;border-radius: 8px;background-color: rgba(176,51,51,0.32)" value="提交"/>
                    </td>
                    <td  >
                        <input type="hidden" id="id"  name="id" value="${currentUser.id}" />
                        <font id="error" color="red"></font>
                    </td>
                </tr>

            </table>


        </div>
    </form>
</div>

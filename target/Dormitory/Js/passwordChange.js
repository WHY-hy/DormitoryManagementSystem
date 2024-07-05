
    //校验函数
    function checkTable()
    {
        var realOldPassword = "${currentUser.password}"; //真正的原密码
        var oldPassword = document.getElementById("oldPassword").value; //原密码
        var newPassword = document.getElementById("newPassword").value; //新密码
        var rPassword = document.getElementById("rPassword").value; //确认新密码

        $("#error").html(""); //初始化错误信息
        if(oldPassword === "" || newPassword === "" || rPassword === "")
    {
        document.getElementById("error").innerHTML = "信息填写不完整！";
        return false;
    } else if(newPassword === oldPassword){
        document.getElementById("error").innerHTML = "修改前后密码不能一致！";
        return false;
    } else if(newPassword !== rPassword){
        document.getElementById("error").innerHTML = "新密码两次填写不一致！";
        return false;
    } else if (realOldPassword !== oldPassword)
    {
        document.getElementById("error").innerHTML = "原密码输入错误!";
        return false;
    }

        return true;
    }

    //选中
    $(document).ready(function(){
    $("#password").addClass("active");
});

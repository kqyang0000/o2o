$(function () {
    // 修改平台密码的url
    var modifyUrl = "/o2o/local/changelocalpwd";
    // 从地址栏的URL里获取usertype
    // usertype=1 则为前端展示展示系统，其余为店家管理系统
    var usertype = getQueryString("usertype");
    $("#submit").click(function () {
        // 获取账号
        var userName = $("#username").val();
        // 获取原密码
        var password = $("#password").val();
        // 获取新密码
        var newPassword = $("#newPassword").val();
        var confirmPassword = $("#confirmPassword").val();
        if (newPassword != confirmPassword) {
            $.toast("两次输入的新密码不一致");
            return;
        }
        // 添加表单数据
        var formData = new FormData();
        formData.append("userName", userName);
        formData.append("password", password);
        formData.append("newPassword", newPassword);
        // 获取验证码
        var verifyCodeActual = $("#j-kaptcha").val();
        if (!verifyCodeActual) {
            $.toast("请输入验证码");
            return;
        }
        formData.append("verifyCodeActual", verifyCodeActual);
        // 将参数post到后台去修改密码
        $.ajax({
            url: modifyUrl,
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $.toast("提交成功");
                    if (usertype == 1) {
                        // 若用户在前端展示系统页面则自动退回到前端展示系统首页
                        window.location.href = "/o2o/frontend/index";
                    } else {
                        // 若用户是在店家管理系统页面则自动退回到店铺列表页中
                        window.location.href = "/o2o/shopadmin/shoplist";
                    }
                } else {
                    $("#kaptcha-img").click();
                    $.toast(data.errorMsg);
                }
            }
        });
    });

    $("#back").click(function () {
        if (usertype == 1) {
            // 若用户在前端展示系统页面则自动退回到前端展示系统首页
            window.location.href = "/o2o/frontend/index";
        } else {
            // 若用户是在店家管理系统页面则自动退回到店铺列表页中
            window.location.href = "/o2o/shopadmin/shoplist";
        }
    });
});
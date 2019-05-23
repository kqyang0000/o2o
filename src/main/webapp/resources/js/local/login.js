$(function () {
    // 登录验证的url
    var loginUrl = "/o2o/local/logincheck";
    // 从地址栏的URL里获取usertype
    // usertype=1 则为前端展示展示系统，其余为店家管理系统
    var usertype = getQueryString("usertype");
    // 登录次数，累积登录三次失败之后弹出验证码要求输入
    var loginCount = 0;

    $("#submit").click(function () {
        // 获取输入的账号
        var userName = $("#username").val();
        // 获取输入的密码
        var password = $("#psw").val();
        // 获取验证码信息
        var verifyCodeActual = $("#j-kaptcha").val();
        // 是否需要验证码验证，默认为false，即不惜要
        var needVerify = false;
        // 如果登录三次都失败
        if (loginCount >= 3) {
            // 那么需要验证码验证校验
            if (!verifyCodeActual) {
                $.toast("请输入验证码");
                return;
            } else {
                needVerify = true;
            }
        }
        // 访问后台进程登录验证
        $.ajax({
            url: loginUrl,
            async: false,
            cache: false,
            type: "post",
            dataType: "json",
            data: {
                userName: userName,
                password: password,
                verifyCodeActual: verifyCodeActual,
                needVerify: needVerify
            },
            success: function (data) {
                if (data.success) {
                    $.toast("登录成功");
                    if (usertype == 1) {
                        // 若用户在前端展示系统页面则自动退回到前端展示系统首页
                        window.location.href = "/o2o/frontend/index";
                    } else {
                        // 若用户是在店家管理系统页面则自动退回到店铺列表页中
                        window.location.href = "/o2o/shopadmin/shoplist";
                    }
                } else {
                    $.toast(data.errorMsg);
                    loginCount++;
                    if (loginCount >= 3) {
                        // 登录失败三次需要验证码校验
                        $("#verifyPart").show();
                    }
                }
            }
        });
    });
});
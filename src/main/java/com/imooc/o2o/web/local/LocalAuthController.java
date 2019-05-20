package com.imooc.o2o.web.local;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.enums.LocalAuthStateEnum;
import com.imooc.o2o.service.LocalAuthService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "local")
public class LocalAuthController {
    @Autowired
    private LocalAuthService localAuthService;

    /**
     * <p>将用户信息与平台账号绑定
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/8 13:48
     */
    @RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> bindLocalAuth(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 验证码校验
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "验证码错误");
            return modelMap;
        }
        // 获取输入的账号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取输入的密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 获取当前用户信息（用户一旦通过微信登录之后，便能获取到用户的信息）
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        // 非空判断，要求账号密码以及当前的用户session非空
        if (userName != null && password != null && user != null && user.getUserId() != null) {
            LocalAuth localAuth = new LocalAuth();
            localAuth.setUsername(userName);
            localAuth.setPassword(password);
            localAuth.setPersonInfo(user);
            LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
            if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errorMsg", le.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "用户名密码均不能为空");
        }
        return modelMap;
    }

    /**
     * <p>修改密码
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/8 13:48
     */
    @RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 校验验证码
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 获取账号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取原密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 获取新密码
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        // 获取当前用户信息（用户一旦通过微信登录之后，便能获取到用户的信息）
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        if (userName != null && password != null && newPassword != null && user != null && user.getUserId() != null
                && !password.equals(newPassword)) {
            try {
                // 查看原先账号，看看与输入的账号是否一致，不一致则认为是非法操作
                LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
                if (null == localAuth || !userName.equals(localAuth.getUsername())) {
                    modelMap.put("success", false);
                    modelMap.put("errorMsg", "输入的账号非本次登录的账号");
                    return modelMap;
                }
                // 修改平台账号的用户密码
                LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), userName, password, newPassword);
                if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errorMsg", le.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "请输入密码");
        }
        return modelMap;
    }

    /**
     * <p>登录校验
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/20 13:39
     */
    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 获取是否需要进行验证码校验的标识符
        boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 获取输入的账号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取输入的密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 非空校验
        if (userName != null && password != null) {
            // 传入账号密码获取平台账号信息
            LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
            if (localAuth != null) {
                // 若能获取到账号信息则登录成功
                modelMap.put("success", true);
                // 同时在session里设置用户信息
                request.getSession().setAttribute("user", localAuth.getPersonInfo());
            } else {
                modelMap.put("success", false);
                modelMap.put("errorMsg", "用户名密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "用户名和密码均不能为空");
        }
        return modelMap;
    }

    /**
     * <p>当用户点击登出按钮时候注销session
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/20 13:41
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 将用户session置空
        request.getSession().setAttribute("user", null);
        modelMap.put("success", true);
        return modelMap;
    }
}
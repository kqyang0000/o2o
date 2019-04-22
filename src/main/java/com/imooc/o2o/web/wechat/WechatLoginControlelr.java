package com.imooc.o2o.web.wechat;

import com.imooc.o2o.dto.UserAccessToken;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.dto.WechatUser;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import com.imooc.o2o.service.PersonInfoService;
import com.imooc.o2o.service.WechatAuthService;
import com.imooc.o2o.util.wechat.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>获取关注微信公众号之后的微信用户信息的接口
 * <p>如果在微信浏览器里访问：https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx07006b78ff904080&redirect_uri=http://47.94.105.201/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * <p>则这里将会获取到code，之后再通过code获取到access_token 进而获取到用户信息
 *
 * @author kqyang
 * @company sl
 * @date 2019/4/19 16:25
 */
@Controller
@RequestMapping("wechatlogin")
public class WechatLoginControlelr {
    private static Logger logger = LoggerFactory.getLogger(WechatLoginControlelr.class);
    private static final String FRONTEND = "1";
    private static final String SHOPEND = "2";

    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private WechatAuthService wechatAuthService;

    @RequestMapping(value = "/logincheck", method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("weixin login get...");
        // 获取微信公众号传输过来的code，通过code可获取到access_token，进而获取用户信息
        String code = request.getParameter("code");
        // 自定义字段（此处用来标识用户身份类别 1:游客 2:店家）
        String roleType = request.getParameter("state");
        logger.debug("weixin login code:" + code);
        WechatUser user = null;
        String openId = null;
        WechatAuth auth = null;
        if (null != code) {
            UserAccessToken token = null;
            try {
                token = WechatUtil.getUserAccessToken(code);
                logger.debug("weixin login token:" + token.toString());
                // 通过token获取accessToken
                String accessToken = token.getAccessToken();
                // 通过token获取openId
                openId = token.getOpenId();
                // 通过access_token和openId获取用户昵称等信息
                user = WechatUtil.getUserInfo(accessToken, openId);
                logger.debug("weixin login user:" + user.toString());
                request.getSession().setAttribute("openId", openId);
                // 通过openId 到数据库判断该账号是否在我们的网站里有对应账号了，没有的话自动创建上，直接实现微信与网站的无缝对接
                auth = wechatAuthService.getWechatAuthByOpenId(openId);
            } catch (Exception e) {
                logger.error("error in getUserAccessToken or getUserInfo :" + e);
                e.printStackTrace();
            }
            if (null == auth) {
                PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
                if (FRONTEND.equals(roleType)) {
                    personInfo.setUserType(1);
                } else {
                    personInfo.setUserType(2);
                }
                auth = new WechatAuth();
                auth.setOpenId(openId);
                auth.setPersonInfo(personInfo);
                WechatAuthExecution we = wechatAuthService.register(auth);
                if (we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
                    return null;
                } else {
                    personInfo = we.getWechatAuth().getPersonInfo();
                    request.getSession().setAttribute("user", personInfo);
                }
            }
            // 若用户点击的是前端展示系统按钮则进入前端展示系统
            if (FRONTEND.equals(roleType)) {
                return "frontend/index";
            } else {
                return "shopadmin/shoplist";
            }
        }
        return null;
    }
}

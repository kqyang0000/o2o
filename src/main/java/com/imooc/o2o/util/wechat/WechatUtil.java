package com.imooc.o2o.util.wechat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.UserAccessToken;
import com.imooc.o2o.dto.WechatUser;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;

/**
 * 公众平台通用接口工具类
 *
 * @author kqyang
 * @company sl
 * @date 2019/4/19 17:43
 */
public class WechatUtil {
    private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    /**
     * 获取access_token
     *
     * @param code
     * @return
     */
    public static UserAccessToken getUserAccessToken(String code) {
        // 微信测试号里面的appId
        String appId = "wx07006b78ff904080";
        logger.debug("appId:" + appId);
        // 微信测试号里面的appsecret
        String appsecret = "05cf1037f5eb2a5c8ba04d5c232969cc";
        logger.debug("appsecret:" + appsecret);
        // 根据传入的code，拼接出访问微信定义好的接口url
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        // 向相应url发送请求获取token json字符串
        JSONObject jsonObject = httpsRequest(url, "GET", null);
        logger.debug("userAccessToken:" + jsonObject.toString());
        UserAccessToken token = new UserAccessToken();
        ObjectMapper objectMapper = new ObjectMapper();
        // 将json字符串转换成相应对象
        token.setAccessToken(jsonObject.getString("access_token"));
        token.setExpiresIn(jsonObject.getString("expires_in"));

        if (null == token) {
            logger.error("获取用户accessToken失败");
            return null;
        }
        return token;
    }

    public static WechatUser getUserInfo(String accessToken, String openId) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + accessToken + "&openid=" + openId + "&lang=zh_CN";
        JSONObject jsonObject = WechatUtil.httpsRequest(url, "GET", null);
        WechatUser user = new WechatUser();
        String openid = jsonObject.getString("openid");
        if (openid == null) {
            logger.debug("获取用户信息失败。");
            return null;
        }
        user.setOpenId(openid);
        user.setNickName(jsonObject.getString("nickname"));
        user.setSex(jsonObject.getInt("sex"));
        user.setProvince(jsonObject.getString("province"));
        user.setCity(jsonObject.getString("city"));
        user.setCountry(jsonObject.getString("country"));
        user.setHeadImgUrl(jsonObject.getString("headimgurl"));
        user.setPrivilege(null);
        // user.setUnionid(jsonObject.getString("unionid"));
        return user;
    }

    /**
     * 发起https请求并获得结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式
     * @param outputStr     提交的数据
     * @return
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
            httpsUrlConn.setSSLSocketFactory(ssf);

            httpsUrlConn.setDoOutput(true);
            httpsUrlConn.setDoInput(true);
            httpsUrlConn.setUseCaches(false);
            // 设置请求方式
            httpsUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpsUrlConn.connect();
            }

            // 当有数据进行提交时
            if (null != outputStr) {
                OutputStream outputStream = httpsUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转成字符串
            InputStream inputStream = httpsUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpsUrlConn.disconnect();
            logger.debug("https buffer:" + buffer.toString());
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException e) {
            logger.error("weixin server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}", e);
        }
        return jsonObject;
    }
}

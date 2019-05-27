package com.imooc.o2o.interceptor.shopadmin;

import com.imooc.o2o.entity.Shop;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>店家管理系统操作验证拦截器
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/5/27 20:06
 */
public class ShopPermissionInterception extends HandlerInterceptorAdapter {
    public ShopPermissionInterception() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从session中获取当前选择的店铺
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        // 从session中获取当前用户可操作的店铺列表
        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
        // 非空判断
        if (currentShop != null && shopList != null) {
            // 遍历可操作的店铺列表
            for (Shop shop : shopList) {
                // 如果当前店铺在可操作的列表里则返回true，进行接下来的用户操作
                if (shop.getShopId().equals(currentShop.getShopId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}

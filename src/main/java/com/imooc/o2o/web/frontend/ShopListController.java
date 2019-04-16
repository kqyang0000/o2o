package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopService shopService;

    /**
     * <p>返回商店列表页里的ShopCategory列表（二级或者一级），以及区域信息列表
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/4/13 13:33
     */
    @RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 试着从前端请求中获取parentId
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");
        List<ShopCategory> shopCategoryList = null;
        if (parentId != -1) {
            // 如果parentId 存在，则取出该一级ShopCategory 下的二级ShopCategory
            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.getMessage());
            }
        } else {
            // 如果parentId 不存在，则取出所有一级ShopCategory（用户在首页选择的是全部商店列表）
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.getMessage());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            // 获取区域列表信息
            areaList = areaService.getAreaList();
            modelMap.put("success", true);
            modelMap.put("areaList", areaList);
            return modelMap;
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * <p>获取指定查询条件下的店铺列表
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/4/13 13:49
     */
    @RequestMapping(value = "/listshops", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        // 获取一页显示数据条数
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        // 非空判断
        if (pageIndex > -1 && pageSize > -1) {
            // 试着获取一级类别id
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            // 试着获取特定二级类别id
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            // 试着获取区域id
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");
            // 试着获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            // 获取组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
            // 根据查询条件和分页信息获取店铺列表，并返回总数
            ShopExecution se = shopService.queryShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("success", true);
            modelMap.put("count", se.getCount());
            modelMap.put("shopList", se.getShopList());
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "empty pageSize or pageIndex");
        }
        return modelMap;
    }

    /**
     * 组合查询参数
     *
     * @param parentId
     * @param shopCategoryId
     * @param areaId
     * @param shopName
     * @return
     */
    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
        Shop shop = new Shop();
        if (parentId != -1L) {
            ShopCategory parent = new ShopCategory();
            ShopCategory shopCategory = new ShopCategory();
            parent.setShopCategoryId(parentId);
            shopCategory.setParent(parent);
            shop.setShopCategory(shopCategory);
        }
        if (shopCategoryId != -1L) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shop.setShopCategory(shopCategory);
        }
        if (areaId > -1L) {
            Area area = new Area();
            area.setAreaId(areaId);
            shop.setArea(area);
        }
        if (null != shopName) {
            shop.setShopName(shopName);
        }
        shop.setEnableStatus(1);
        return shop;
    }
}
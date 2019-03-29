package com.imooc.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/shopadmin")
@Controller
public class ProductManagementController {
    /**
     * 支持上传商品详情图的最大数量
     */
    private static final int IMAGE_MAX_COUNT = 6;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 校验验证码
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "输入验证码错误");
            return modelMap;
        }
        // 接收前端参数变量的初始化，包括商品，缩略图，详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String reqStr = HttpServletRequestUtil.getString(request, "reqStr");
        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder imageHolder = null;
        List<ImageHolder> productImgList = new ArrayList<>(32);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        try {
            // 若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
            if (multipartResolver.isMultipart(request)) {
                multipartRequest = (MultipartHttpServletRequest) request;
                // 取出缩略图并构建ImageHolder 对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                imageHolder = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
                // 取出详情图列表并构建List<ImageHolder> 列表对象，最多支持6张图片上传
                for (int i = 0; i < IMAGE_MAX_COUNT; i++) {
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
                    if (null != productImgFile) {
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
                        productImgList.add(productImg);
                    } else {
                        // 若第i个详情图文件流为空，则终止循环
                        break;
                    }
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errorMsg", "上传图片不能为空");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.toString());
            return modelMap;
        }
        try {
            // 尝试获取前端传来的String 流并将其转化成Product 实体类
            product = mapper.readValue(reqStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.toString());
            return modelMap;
        }
        // 若product信息，缩略图以及商品详情图列表为非空，则开始进行商品添加操作
        if (null != product && null != imageHolder && productImgList.size() > 0) {
            try {
                //  从session 中获取当前店铺额id 并赋值给product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                // 执行添加操作
                ProductExecution pe = productService.addProduct(product, imageHolder, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errorMsg", pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "请输入商品信息");
        }
        return modelMap;
    }
}
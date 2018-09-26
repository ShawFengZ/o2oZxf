package com.zxf.web.shopAdmin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxf.combineEntity.ShopCombine;
import com.zxf.dto.ShopCombineExecution;
import com.zxf.dto.ShopExecution;
import com.zxf.entity.Area;
import com.zxf.entity.PersonInfo;
import com.zxf.entity.Shop;
import com.zxf.entity.ShopCategory;
import com.zxf.enums.ProductCategoryStateEnum;
import com.zxf.enums.ShopStateEnum;
import com.zxf.exceptions.ShopOperationExceptions;
import com.zxf.service.AreaService;
import com.zxf.service.ShopCategoryService;
import com.zxf.service.ShopService;
import com.zxf.util.CodeUtil;
import com.zxf.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxf
 * @date 2018/9/20 13:52
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;
    /**
     * 注册
     * */
    @ResponseBody
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    public Map<String, Object> registerShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //判断验证码
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        //1.接受并转化相应的参数，店铺信息，图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        //接受图片
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        //判断request中是否有上传的文件流
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //2.注册店铺
        if (shop != null && shopImg != null) {
            //PersonInfo owner = new PersonInfo();
            //session to do
            //owner.setUserId(1L);
            PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwnerId(user.getUserId().intValue());
            ShopExecution se = shopService.addShop(shop, shopImg);
            if (se.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
                // 若shop创建成功，则加入session中作为权限使用
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if (shopList != null || shopList.size() > 0) {
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    shopList = new ArrayList<>();
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", se.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
        //3.返回结果
        return modelMap;
    }

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopInitInfo(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();

        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * 获取店铺信息
     *      测试成功
     * */
    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopById(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                ShopCombine shopCombine = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shopCombine);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }


    /**
     * 修改店铺信息
     * */
    @ResponseBody
    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    private Map<String, Object> modifyShop(HttpServletRequest request){
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        Map<String, Object> modelMap = new HashMap<>();
        //判断验证码
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        //1.接受并转化相应的参数，店铺信息，图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        MultipartHttpServletRequest multipartRequest = null;
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        //判断request中是否有上传的文件流
        if (commonsMultipartResolver.isMultipart(request)) {
            multipartRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
        }
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        shop.setShopId(currentShop.getShopId());
        //2.注册修改
        if (shop != null && shopImg != null) {
           filterAttribute(shop);
            try {
                ShopExecution se = shopService.modeifyShop(shop, shopImg);
                if (se.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", true);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");

        }
        //3.返回结果
        return modelMap;
    }

    /**
     * 获取店铺列表信息
     * */
    @RequestMapping(value = "/getshoplist" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user = new PersonInfo();
        user.setUserId(1L);//先设置一个默认值，防止后面再session中没有取到就直接报错了
        user.setName("Test");
        request.getSession().setAttribute("user", user);
        user = (PersonInfo) request.getSession().getAttribute("user");
        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwnerId(user.getUserId().intValue());
            ShopCombineExecution se = shopService.getShopList(shopCondition, 0, 100);
            //查询成功
            modelMap.put("shopList", se.getShopList());
            modelMap.put("user", user);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * 店铺管理:
     *      如果不是登录，或者从列表过来，我们就重定向到列表展示页
     *      如果登录过，就将重定向设为false
     * */
    @ResponseBody
    @RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    public Map<String, Object> getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId <= 0) {
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/shopadmin/shoplist");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect", false);
                modelMap.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            //因为这里有setAttribute，所以在ProductCategoryController中才有getAttribute("currentShop")
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }
        return modelMap;
    }


    /* ======================================= 辅助方法 ======================================= */

    /**
     * 将文件流转化为File类型，目的是将CommonsMultipartFile转化为File类型供Service层使用
     * 并不合理
     * */
    private static void InputStreamToFile(InputStream ins, File file){
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    ins.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("inputStreamToFile关闭io产生异常：" + e.getMessage());
            }
        }
    }

    private void filterAttribute(Shop shop){

    }
}

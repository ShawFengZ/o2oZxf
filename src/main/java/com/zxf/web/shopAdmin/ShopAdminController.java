package com.zxf.web.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zxf
 * @date 2018/9/20 14:38
 */
@Controller
@RequestMapping(value = "/shopadmin", method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopOperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shopList";
    }

    @RequestMapping(value = "/shopmanage")
    public String shopManage(){
        return "shop/shopManage";
    }

    @RequestMapping(value = "/productcategorymanage")
    public String productCategoryManage(){
        return "shop/productCategoryManage";
    }
}

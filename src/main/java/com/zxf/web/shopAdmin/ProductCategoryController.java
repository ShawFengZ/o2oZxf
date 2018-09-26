package com.zxf.web.shopAdmin;

import com.zxf.dto.ProductCategoryExecution;
import com.zxf.dto.Result;
import com.zxf.entity.ProductCategory;
import com.zxf.entity.Shop;
import com.zxf.enums.ProductCategoryStateEnum;
import com.zxf.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxf
 * @date 2018/9/26 13:45
 */
@Controller
@RequestMapping("/productcategory")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    /**
     * 1. 列表查询
     * */
    @ResponseBody
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    private Result<List<ProductCategory>> getProductCategorylist(HttpServletRequest request){
        //TO BE REOMVED
        Shop shop = new Shop();
        shop.setShopId(7L);
        request.getSession().setAttribute("currentShop", shop);

        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;
        ProductCategory productCategoryCondition = new ProductCategory();
        if (currentShop != null && currentShop.getShopId() != null) {
            productCategoryCondition.setShopId(currentShop.getShopId());
            list = productCategoryService.getProductCategoryList(productCategoryCondition, 0, 100);
            return new Result<List<ProductCategory>>(true, list);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, ps.getState(), ps.getStateInfo());
        }
    }

    /**
     * 2. 批量插入
     * */
    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProductCategoryList(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory pc: productCategoryList) {
            pc.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategoryList(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品的类别");
        }
        return modelMap;
    }

    //删除操作
    @ResponseBody
    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    private Map<String, Object> deleteProductCategory(Long productCategoryId, HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId!=null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少选择一个商品类别");
        }
        return modelMap;
    }
}

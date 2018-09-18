package com.zxf.web.superAdmin;

import com.zxf.model.Area;
import com.zxf.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxf
 * @date 2018/9/18 15:04
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> listArea(){
        Map<String,Object> model = new HashMap<>();
        try {
            List<Area> list = areaService.getAreaList();
            model.put("rows", list);
            model.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            model.put("success",false);
            model.put("errMsg", e.toString());
        }
        return model;
    }
}

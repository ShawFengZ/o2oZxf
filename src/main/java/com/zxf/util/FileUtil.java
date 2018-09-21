package com.zxf.util;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author zxf
 * @date 2018/9/19 9:21
 */
public class FileUtil {
    //获取系统文件的分隔符
    private static String seperator = "\\";
            //System.getProperty("File.seperator");
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat(
            "yyyyMMddHHmmss"); // 时间格式化的格式
    private static final Random r = new Random();

    //获取根目录
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "E:project/o2oZxf/src/main/resources";
        } else {//如果是linux，根目录
            basePath = "home...";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    //获取店铺图片的存储路径
    public static String getShopImagePath(long shopId){
        StringBuilder shopImagePathBuilder = new StringBuilder();
        shopImagePathBuilder.append("/upload/images/item/shop/");
        shopImagePathBuilder.append(shopId);
        shopImagePathBuilder.append("/");
        //String shopImagePath = shopImagePathBuilder.toString().replace("/",seperator);
        String shopImagePath = shopImagePathBuilder.toString();
        return shopImagePath;
    }
}

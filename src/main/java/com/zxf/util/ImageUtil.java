package com.zxf.util;

import com.sun.javafx.scene.shape.PathUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author zxf
 * @date 2018/9/19 9:06
 */
/**
 * 图片处理方法没有写完，后面要具体根据实际需要自己开发
 * */
public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    //处理缩略图，门面照&商品小图
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200)
                    .outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static String generateNormalImg(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(337, 640).outputQuality(0.5f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static List<String> generateNormalImgs(List<CommonsMultipartFile> imgs, String targetAddr) {
        int count = 0;
        List<String> relativeAddrList = new ArrayList<String>();
        if (imgs != null && imgs.size() > 0) {
            makeDirPath(targetAddr);
            for (CommonsMultipartFile img : imgs) {
                String realFileName = getRandomFileName();
                String extension = getFileExtension(img);
                String relativeAddr = targetAddr + realFileName + count + extension;
                File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
                count++;
                try {
                    Thumbnails.of(img.getInputStream()).size(600, 300).outputQuality(0.5f).toFile(dest);
                } catch (IOException e) {
                    throw new RuntimeException("创建图片失败：" + e.toString());
                }
                relativeAddrList.add(relativeAddr);
            }
        }
        return relativeAddrList;
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * */
    private static String getRandomFileName(){
        //获取5位随机数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     * */
    private static String getFileExtension(CommonsMultipartFile cFile){
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及的目录
     * */
    private static void makeDirPath(String targetAddr){
        String realFileParentPath = FileUtil.getImgBasePath() + targetAddr;
        File dirFile = new File(realFileParentPath);
        if (!dirFile.exists()) {
            boolean mkdirs = dirFile.mkdirs();
        }
    }

    /**
     * storePath是文件路径还是目录路径
     * 如果是文件路径则删除该文件
     * 如果是目录路径则删除该目录下的所有文件
     * */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(FileUtil.getImgBasePath() + storePath);
        if (fileOrPath.isDirectory()) {
            //删除所有文件
            File file[] = fileOrPath.listFiles();
            for (int i = 0; i < file.length; i++) {
                file[i].delete();
            }
            fileOrPath.delete();
        }
    }
    //给图片添加水印
    /*public static void main(String[] args) throws IOException {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("C:Users/97598/Desktop/image/timg.jpg"))
                .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "/watermark.jpg")),
                0.25f).toFile("C:Users/97598/Desktop/image/xiaohuangren.jpg");
    }*/

}

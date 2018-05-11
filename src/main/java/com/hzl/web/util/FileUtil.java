package com.hzl.web.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Arrays;

/**
 * 文件工具类
 */
public class FileUtil {

//    public static String classPath;
//    public static String uploadPath;

//    static {
//        File classPathFile = null;
//        try {
//            classPathFile = new File(ResourceUtils.getURL("classpath:").getPath());
//            if (!classPathFile.exists()) classPathFile = new File("");
//        } catch (Exception e) {
//            classPathFile = new File("");
//        }
//        classPath = classPathFile.getAbsolutePath();
//        uploadPath = classPath + File.separator + "upload";
//    }

    /**
     * 获取上传文件夹
     *
     * @return
     */
//    public static String getUploadPath() {
//        return uploadPath;
//    }

    /**
     * 获取真实短文件名
     *
     * @param originalFilename
     * @return
     */
    public static String getFileName(String originalFilename) {
        if (!StringUtil.isEmpty(originalFilename)) {
            if (originalFilename.contains(".")) {
                try {
                    return originalFilename.substring(0, originalFilename.indexOf("."));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 获取文件后缀名
     *
     * @param originalFilename
     * @return
     */
    public static String getFileExt(String originalFilename) {
        if (!StringUtil.isEmpty(originalFilename)) {
            if (originalFilename.contains(".")) {
                try {
                    return originalFilename.substring(originalFilename.lastIndexOf("."));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 是否是图片
     *
     * @param ext
     * @return
     */
    public static boolean isPic(String ext) {
        if (StringUtil.isEmpty(ext))
            return false;
        else {
            return Arrays.asList(".bmp", ".gif", ".jpeg", ".jpg", ".png", ".ico").contains(ext.toLowerCase());
        }
    }
}

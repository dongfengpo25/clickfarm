package com.hzl.web.controller.file;

import com.hzl.web.bean.response.FileResponseJson;
import com.hzl.web.bean.response.ResponseJson;
import com.hzl.web.config.DebugProperties;
import com.hzl.web.util.DateUtil;
import com.hzl.web.util.FileUtil;
import com.hzl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    DebugProperties debugProperties;

    private final String _file = "file";

    /**
     * 单文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public Object upload(@RequestParam(_file) MultipartFile file, HttpServletRequest request) {
        return uploadFile(file);
    }

    /**
     * 上传用户信息文件或图片
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload/userinfo")
    public Object uploadUser(@RequestParam(_file) MultipartFile file, HttpServletRequest request) {
        return uploadUserFile(file);
    }

    /**
     * 多文件上传
     *
     * @param request
     * @return
     */
    @PostMapping("/uploadFiles")
    public Object uploadFiles(HttpServletRequest request) throws IOException {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles(_file);
        return uploadFiles(files);
    }

    /**
     * 处理文件上传
     *
     * @param file
     * @param json
     * @param isUser 是否用户信息
     * @throws Exception
     */
    private void doUploadFile(MultipartFile file, FileResponseJson json, boolean isUser) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String savePath = getSavePath(originalFilename);
        json.setFileName(savePath);
        json.setOriginalName(originalFilename);
        String headPath = getHeadPath(isUser, originalFilename);
        if (StringUtil.isEmpty(headPath)) {
            throw new Exception("上传路径为空");
        }
        String filePath = headPath + savePath;
        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
        out.write(file.getBytes());
        out.flush();
        out.close();
    }

    private void doUploadFile(MultipartFile file, FileResponseJson json) throws Exception {
        doUploadFile(file, json, false);
    }

    /**
     * 处理单文件上传
     *
     * @param file
     * @return
     */
    private FileResponseJson uploadFile(MultipartFile file) {
        FileResponseJson json = new FileResponseJson();
        if (file.isEmpty()) {
            json.setSucc(false);
            json.setMsg("上传文件为空.");
            return json;
        } else {
            try {
                doUploadFile(file, json);
                //这一行很重要，否则bjui-all.js的18199行状态返回值为空，会抛null异常
                json.setStatusCode(200);
                return json;
            } catch (Exception e) {
                e.printStackTrace();
                json.setSucc(false);
                json.setMsg("上传失败:" + e.getMessage());
                return json;
            }
        }
    }

    /**
     * 处理单文件上传
     *
     * @param file
     * @return
     */
    private FileResponseJson uploadUserFile(MultipartFile file) {
        FileResponseJson json = new FileResponseJson();
        if (file.isEmpty()) {
            json.setSucc(false);
            json.setMsg("上传文件为空.");
            return json;
        } else {
            try {
                doUploadFile(file, json, true);
                //这一行很重要，否则bjui-all.js的18199行状态返回值为空，会抛null异常
                json.setStatusCode(200);
                return json;
            } catch (Exception e) {
                e.printStackTrace();
                json.setSucc(false);
                json.setMsg("上传失败:" + e.getMessage());
                return json;
            }
        }
    }

    /**
     * 处理多文件上传
     *
     * @param files
     * @return
     */
    private FileResponseJson uploadFiles(List<MultipartFile> files) {
        FileResponseJson json = new FileResponseJson();
        try {
            MultipartFile file = null;
            BufferedOutputStream stream = null;
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    try {
                        doUploadFile(file, json);
                    } catch (Exception e) {
                        if (stream != null) {
                            try {
                                stream.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            stream = null;
                        }
                        json.setMsg("第 " + i + " 个文件上传有错误:" + e.getMessage());
                        return json;
                    }
                } else {
                    json.setSucc(false);
                    json.setMsg("第 " + i + " 个文件为空");
                    return json;
                }
            }
            //这一行很重要，否则bjui-all.js的18199行状态返回值为空，会抛null异常
            json.setStatusCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            json.setSucc(false);
            json.setMsg("上传失败:" + e.getMessage());
            return json;
        }
        return json;
    }

    /**
     * 获取头部路径
     *
     * @param originalFilename 原始文件名
     * @return
     */
    private String getHeadPath(boolean isUser, String originalFilename) {
        if (isUser)
            return debugProperties.getUploadUserPicPath();
        if (!StringUtil.isEmpty(originalFilename)) {
            String ext = FileUtil.getFileExt(originalFilename);
            if (!StringUtil.isEmpty(ext)) {
                if (FileUtil.isPic(ext)) {
                    return debugProperties.getUploadPicPath();
                } else {
                    return debugProperties.getUploadFilesPath();
                }
            }
        }
        return "";
    }

    /**
     * 获取上传日期
     *
     * @return
     */
    private String getUploadDate() {
        return DateUtil.getUploadDate();
    }

    /**
     * 获取存储路径(文件名采用随机20位字符串+后缀)
     *
     * @param originalFilename 原始文件名
     * @return
     */
    private String getSavePath(String originalFilename) {
        if (!StringUtil.isEmpty(originalFilename)) {
            String ext = FileUtil.getFileExt(originalFilename);
            if (!StringUtil.isEmpty(ext)) {
                return getUploadDate() + File.separator + StringUtil.getRandomString(20) + ext;
            }
        }
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{datePath:.+}/{filePath:.+}")
    public ResponseEntity<?> getFile(@PathVariable String datePath, @PathVariable String filePath) {
        return viewPic(datePath, filePath, false);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/userinfo/{datePath:.+}/{filePath:.+}")
    public ResponseEntity<?> getUserInfoFile(@PathVariable String datePath, @PathVariable String filePath) {
        return viewPic(datePath, filePath, true);
    }

    private ResponseEntity<?> viewPic(String datePath, String filePath, boolean isUser) {
        try {
            return ResponseEntity.ok(loadResource(datePath + File.separator + filePath,  isUser));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 加载资源文件
     *
     * @param filePath
     * @param isUser
     * @return
     * @throws Exception
     */
    private Resource loadResource(String filePath, boolean isUser) throws Exception {
        try {
            String headPath = getHeadPath(isUser, filePath);
            File file = new File(headPath + filePath);
            if (!file.exists()) {
                throw new Exception(
                        "Could not found file: " + filePath);
            }
            Resource resource = new UrlResource(file.toURI());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception(
                        "Could not read file: " + filePath);
            }
        } catch (MalformedURLException e) {
            throw new Exception("Could not read file: " + filePath, e);
        }
    }

}
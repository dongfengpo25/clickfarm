package com.hzl.web.service;

import com.hzl.web.bean.response.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件服务接口
 */
public interface FileService {

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    public ResponseJson uploadFile(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param files
     * @return
     */
    public ResponseJson uploadFiles(List<MultipartFile> files);
}

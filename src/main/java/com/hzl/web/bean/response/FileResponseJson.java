package com.hzl.web.bean.response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.PublicKey;

public class FileResponseJson extends ResponseJson implements IFileResponse {


    public FileResponseJson() {
        super();
        JSONArray files = new JSONArray();
        put(_files, files);
    }

    public void addFile(String originalFilename, String savePath){
        JSONArray files = (JSONArray) get(_files);
        JSONObject file  = new JSONObject();
        file.put(_originalFilename, originalFilename);
        file.put(_savePath, savePath);
        files.add(file);
    }

}

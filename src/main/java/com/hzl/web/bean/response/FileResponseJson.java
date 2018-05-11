package com.hzl.web.bean.response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileResponseJson extends ResponseJson implements IFileResponse {


    public FileResponseJson() {
        super();
        put(_statusCode, 500);
        //JSONArray files = new JSONArray();
        //put(_files, files);
    }

//    public void addFile(String originalFilename, String savePath) {
//        JSONArray files = (JSONArray) get(_files);
//        JSONObject file = new JSONObject();
//        file.put(_originalName, originalFilename);
//        file.put(_filename, savePath);
//        files.add(file);
//    }

    public void setOriginalName(String originalName) {
        put(_originalName, originalName);
    }

    public void setFileName(String fileName) {
        put(_filename, fileName);
    }

    @Override
    public void setMsg(String msg) {
        super.setMsg(msg);
        put(_message, msg);
    }

    public void setStatusCode(int statusCode) {
        put(_statusCode, statusCode);
    }

}

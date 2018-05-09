package com.hzl.web.bean.response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.PublicKey;

public class FileResponseJson extends ResponseJson implements IFileResponse {
    public final String _statusCode = "statusCode";
    public final String _filename = "filename";

    public FileResponseJson() {
        super();
        put(_statusCode, 500);
        JSONArray files = new JSONArray();
        put(_files, files);
    }

    public void addFile(String originalFilename, String savePath) {
        JSONArray files = (JSONArray) get(_files);
        JSONObject file = new JSONObject();
        file.put(_originalFilename, originalFilename);
        file.put(_savePath, savePath);
        files.add(file);
    }

    @Override
    public void setMsg(String msg) {
        super.setMsg(msg);
        put(_message, msg);
    }

    public void setStatusCode(int statusCode) {
        put(_statusCode, statusCode);
    }

    public void setFilename(String filename) {
        put(_filename, filename);
    }
}

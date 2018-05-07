package com.hzl.web.bean.response;

import com.hzl.web.bean.IConstant;
import org.apache.commons.collections.map.LinkedMap;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ResponseJson extends JSONObject implements IConstant {

    public ResponseJson() {
        super();
        put(_succ, true);
    }

    public void setSucc(boolean succ) {
        put(_succ, succ);
    }

    public void setMsg(String msg) {
        put(_msg, msg);
    }
}
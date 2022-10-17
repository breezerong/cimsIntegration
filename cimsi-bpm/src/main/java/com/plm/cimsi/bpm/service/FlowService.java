package com.plm.cimsi.bpm.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.plm.cimsi.ibpm.IFlowService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class FlowService extends BPMServiceBase implements IFlowService {
    @Override
    public String pages(String accessToken, int page, int limit, String sort) throws IOException, InterruptedException, KeeperException {
        HttpPost httpPost = getHttpPost("flow/page",null);
        JSONObject jsObj = new JSONObject();
        jsObj.put("page", page);
        jsObj.put("limit",limit);
        jsObj.put("sort",sort);

        StringEntity strEntity=new StringEntity(jsObj.toJSONString());
        httpPost.setEntity(strEntity);
        HttpResponse response = getHttpClient().execute(httpPost);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        // 请求成功
        if (statusCode == 200) {
            HttpEntity responseResult = response.getEntity();
            String responseEntityStr = EntityUtils.toString(responseResult, "UTF-8");
            JSONObject jsonResult = JSON.parseObject(responseEntityStr);
            //执行成功
            if(jsonResult.containsKey("data") &&jsonResult.getString("code").equals("0")){
                if(jsonResult.containsKey("data")) {
                    String data = jsonResult.getString("data");
                    jsObj = new JSONObject();
                    jsObj.put("data", jsonResult.get("data"));
                    jsObj.put("recordsTotal",jsonResult.get("recordsTotal"));
                    return getSuccessResult(jsObj.toJSONString());
                }
            }else if(jsonResult.containsKey("message")){
                return getErrorResult(1099,"提交信息失败,"+jsonResult.getString("message"));
            }
        }
        return getErrorResult(1099,"提交信息失败,"+httpPost.getURI().getPath());
    }
}

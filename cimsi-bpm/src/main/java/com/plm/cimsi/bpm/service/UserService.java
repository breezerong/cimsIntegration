package com.plm.cimsi.bpm.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.plm.cimsi.ibpm.IUserService;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.http.HttpResponse;
import java.io.IOException;

/**
 * 用户服务
 */
public class UserService extends BPMServiceBase implements IUserService {

    @Override
    public String login(String loginName, String password, String tenantCode) throws IOException, InterruptedException, KeeperException {
        HttpPost httpPost = getHttpPost("user/login",null);
        JSONObject jsObj = new JSONObject();
        jsObj.put("loginName", loginName);
        jsObj.put("password",password);
        jsObj.put("tenantCode",tenantCode);

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
            //登录成功
            if(jsonResult.containsKey("data") &&jsonResult.getString("code").equals("0")){
                if(jsonResult.containsKey("data")) {
                    String data = jsonResult.getString("data");
                    return getSuccessResult(data);
                }
            }else{
                getErrorResult(1001,"登录失败");
            }
        }
        return getErrorResult(1099,"提交信息失败,"+httpPost.getURI().getPath());
    }
}

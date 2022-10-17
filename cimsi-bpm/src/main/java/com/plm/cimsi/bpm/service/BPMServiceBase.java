package com.plm.cimsi.bpm.service;

import com.alibaba.fastjson2.JSONObject;
import com.plm.cimsi.bpm.util.ConfigUtils;
import com.plm.cimsi.bpm.util.HTTPSTrustClient;
import com.ums.core.cplatform.ioc.ServiceBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class BPMServiceBase extends ServiceBase {
    /**
     * 获取HttpPost
     * @param url 只需要url中间部分，如：user/login
     * @param contentType 默认application/json;charset=utf-8
     * @return
     */
    public HttpPost getHttpPost(String url,String contentType){
        url = ConfigUtils.getSettings().getBPMBaseURL()+url+ConfigUtils.getSettings().getSuffix();
        HttpPost httpclient = new HttpPost(url);
        if(StringUtils.isEmpty(contentType)) {
            httpclient.addHeader("Content-Type", "application/json;charset=utf-8");
        }
        else{
            httpclient.addHeader("Content-Type", contentType);
        }
        RequestConfig timeoutConfig = RequestConfig.custom()
                .setConnectTimeout(10000).setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000).build();
        httpclient.setConfig(timeoutConfig);
        return httpclient;
    }

    /**
     * 获取HttpPut
     * @param url 只需要url中间部分，如：user/login
     * @param contentType 默认application/json;charset=utf-8
     * @return
     */
    public HttpPut getHttpPut(String url,String contentType){
        url = ConfigUtils.getSettings().getBPMBaseURL()+url+ConfigUtils.getSettings().getSuffix();
        HttpPut httpclient = new HttpPut(url);
        if(StringUtils.isEmpty(contentType)) {
            httpclient.addHeader("Content-Type", "application/json;charset=utf-8");
        }
        else{
            httpclient.addHeader("Content-Type", contentType);
        }
        RequestConfig timeoutConfig = RequestConfig.custom()
                .setConnectTimeout(10000).setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000).build();
        httpclient.setConfig(timeoutConfig);
        return httpclient;
    }

    /**
     * 获取HttpGet
     * @param url 只需要url中间部分，如：user/login
     * @param contentType 默认application/json;charset=utf-8
     * @return
     */
    public HttpGet getHttpGet(String url,String contentType){
        url = ConfigUtils.getSettings().getBPMBaseURL()+url+ConfigUtils.getSettings().getSuffix();
        HttpGet httpclient = new HttpGet(url);
        if(StringUtils.isEmpty(contentType)) {
            httpclient.addHeader("Content-Type", "application/json;charset=utf-8");
        }
        else{
            httpclient.addHeader("Content-Type", contentType);
        }
        RequestConfig timeoutConfig = RequestConfig.custom()
                .setConnectTimeout(10000).setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000).build();
        httpclient.setConfig(timeoutConfig);
        return httpclient;
    }

    /**
     * 获取Http客户端
     * @return
     */
    public CloseableHttpClient getHttpClient() {
        String url = ConfigUtils.getSettings().getBPMBaseURL().toLowerCase();
        if(url.startsWith("https://")){
            try {
                CloseableHttpClient client= new HTTPSTrustClient().init();
                return client;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return HttpClients.createDefault();
    }

    /**
     * 获取成功返回信息
     * @param data 返回数据
     * @return
     */
    public String getSuccessResult(String data){
        return getResult(200,data,null,true);
    }

    /**
     * 获取失败返回信息
     * @param code 错误代码
     * @param message 错误描述
     * @return
     */
    public String getErrorResult(int code, String message){
        return getResult(code,null,message,false);
    }

    /**
     * 获取返回信息
     * @param code
     * @param data
     * @param message
     * @param isSucceed
     * @return
     */
    public String getResult(int code,String data, String message,boolean isSucceed){
        JSONObject jsObj = new JSONObject();
        jsObj.put("StatusCode", code);
        jsObj.put("IsSucceed", isSucceed);
        jsObj.put("message",message==null?"":message);
        jsObj.put("Entity",data==null?"":data);
        return jsObj.toJSONString();
    }
}

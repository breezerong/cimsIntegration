package com.plm.cimsi.bpm.util;

import com.alibaba.fastjson2.JSON;
import com.plm.cimsi.bpm.entity.Settings;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;

public class ConfigUtils {
    private  static Settings settings = null;

    /**
     * 读取配置
     * @return
     */
    public static Settings getSettings(){
        if(settings ==null){
            InputStream inputStream = null;
            inputStream = ConfigUtils.class.getResourceAsStream("bpmSetting.json");
            try {
                String content =  IOUtils.toString(inputStream, Charsets.toCharset("UTF-8"));

                settings = JSON.parseObject(content,Settings.class);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return settings;
    }
}

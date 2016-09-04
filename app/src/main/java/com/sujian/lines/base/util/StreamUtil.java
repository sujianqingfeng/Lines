package com.sujian.lines.base.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sujian on 2016/9/4.
 * Mail:121116111@qq.com
 */
public class StreamUtil {
    public static String doGet(String urlStr) throws Exception {
        URL url;
        String html = "";
        try {
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();
                html = inToStringByByte(in);
            } else {
                throw new Exception("新闻服务器返回值不为200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("get请求失败");
        }
        return html;
    }


    public static String inToStringByByte(InputStream in) throws Exception {
        ByteArrayOutputStream outStr = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        StringBuilder content = new StringBuilder();
        while ((len = in.read(buffer)) != -1) {
            content.append(new String(buffer, 0, len, "UTF-8"));
        }
        outStr.close();
        return content.toString();
    }
}

package com.mik.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpServletUtil {

    public static final String X_REQUESTED_WITH = "X-Requested-With";

    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";


    /**
     * response响应数据写入
     * @param response
     * @param resultObj
     * @throws IOException
     */
    public static void writeData(HttpServletResponse response, Object resultObj) throws IOException {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        // 设置数据类型
        response.setContentType("application/json");
        String result = new ObjectMapper().writeValueAsString(resultObj);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(result.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e){
            throw e;
        } finally {
            if(null != out){
                out.close();
            }
        }
    }
}

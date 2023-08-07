package com.gstz.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gstz.entity.CodeLibrary;
import com.gstz.entity.HttpTemplate;
import com.gstz.service.CodeLibraryService;
import com.gstz.service.ExecuteInfoService;
import com.gstz.utils.HttpUtil;
import com.gstz.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yimu
 * @version 1.0
 * @description: 通知触发
 * @date 2023/7/28 10:54
 */
@RestController
@RequestMapping("h3bpm")
public class h3bpmController {

    Logger logger = Logger.getLogger(getClass());

    @Resource
    private ExecuteInfoService executeInfoService;

    @Resource
    private CodeLibraryService codeLibraryService;

    //加密一波，传过来 执行编号
    @GetMapping("test")
    public void queryByPage() {
        // 触发对应的操作
        // 执行编号查需要进行的操作
        HashMap<String, Object> map = new HashMap<>();
        int exeId = 1;
        List<HttpTemplate> httpTemplates = executeInfoService.queryTemByExeId(exeId);
        for (HttpTemplate httpTemplate : httpTemplates) {
            //请求模板 参数未关联模板 从固定码值取
            if (httpTemplate.getParamId() == null) {
                //get请求直接拼接
                String urlTemplate = httpTemplate.getUrlTemplate() + "?";
                List<CodeLibrary> codeLibraries = codeLibraryService.queryByParams(new CodeLibrary(httpTemplate.getTemplateId().toString()));
                for (CodeLibrary codeLibrary : codeLibraries) {
                    urlTemplate = urlTemplate + codeLibrary.getItemNo() + "=" + codeLibrary.getItemParam() + "&";
                }
                if (httpTemplate.getRequestType() != null && httpTemplate.getRequestType().equals("GET")) {
                    String s = HttpUtil.httpGet(urlTemplate);
                    if (s != null && !s.equals("")) {
                        JSONObject jobject = JSONObject.parseObject(s);
                        for (String key : jobject.keySet()) {
                            logger.info(key + " : " + jobject.getString(key));
                            map.put(key, jobject.getString(key));
                        }
                    }
                } else {
                }

            } else {
                if (httpTemplate.getRequestType().equals("POST")) {
                    //post请求 1：拼接参数；2.拼接json模板
                    String urlTemplate1 = httpTemplate.getUrlTemplate();
                    List<String> params = StringUtil.cutParams(urlTemplate1);
                    for (String param : params) {
                        urlTemplate1 = urlTemplate1.replaceFirst("\\{" + param + "\\}",map.get(param).toString());
                    }
                    logger.info("url Post ==> " + urlTemplate1);

                    //拼接json
                    String jsonTemplate = httpTemplate.getJsonTemplate();
                    // 创建一个ObjectMapper对象
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        // 将字符串解析为Map对象
                        Map<String, Object> jsonMap = objectMapper.readValue(jsonTemplate, Map.class);
                        // 打印Map对象的内容
                        for (String key : jsonMap.keySet()) {
                            logger.info(key + ": " + jsonMap.get(key));
                            //根据key值存入value

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    String s = HttpUtil.sendPost(urlTemplate1, jsonTemplate);
                    if (s != null && !s.equals("")) {
                        JSONObject jobject = JSONObject.parseObject(s);
                        for (String key : jobject.keySet()) {
                            logger.info(key + " : " + jobject.getString(key));

                        }
                    }
                }
            }
        }

    }


}

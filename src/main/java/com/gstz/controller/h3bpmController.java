package com.gstz.controller;

import com.alibaba.fastjson.JSONObject;
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
import java.util.HashMap;
import java.util.List;

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
                String urlTemplate = httpTemplate.getUrlTemplate()+"?";
                List<CodeLibrary> codeLibraries = codeLibraryService.queryByParams(new CodeLibrary(httpTemplate.getTemplateId().toString()));
                for (CodeLibrary codeLibrary : codeLibraries) {
                    urlTemplate = urlTemplate+codeLibrary.getItemNo()+"="+codeLibrary.getItemParam()+"&";
                }
                if (httpTemplate.getRequestType() != null && httpTemplate.getRequestType().equals("GET")) {
                    String s = HttpUtil.httpGet(urlTemplate);
                    if ( s != null && ! s.equals("")) {
                        JSONObject jobject = JSONObject.parseObject(s);
                        for (String key : jobject.keySet()) {
                            logger.info(key + " : " + jobject.getString(key));
                            map.put(key,jobject.getString(key));
                        }
                    }
                }else if (httpTemplate.getRequestType().equals("POST")) {
                    if (codeLibraries == null) {
                        
                    }
                    StringUtil.cutParams(httpTemplate.getUrlTemplate());
                    String jsonTemplate = httpTemplate.getJsonTemplate();
                    JSONObject jsonObject = JSONObject.parseObject(jsonTemplate);



                    String s = HttpUtil.sendPost(urlTemplate,jsonTemplate);
                    if ( s != null && ! s.equals("")) {
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

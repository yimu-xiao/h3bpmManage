package com.gstz;

import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/7/21 9:41
 */
public class Test {
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"userid\": \"zhangsan\",\n" +
                "    \"name\": \"张三\",\n" +
                "    \"alias\": \"jackzhang\",\n" +
                "    \"mobile\": \"13800000000\",\n" +
                "    \"department\": [1, 2],\n" +
                "    \"order\": [10, 40],\n" +
                "    \"position\": \"产品经理\",\n" +
                "    \"gender\": \"1\",\n" +
                "    \"email\": \"zhangsan@gzdev.com\",\n" +
                "    \"is_leader_in_dept\": [1, 0],\n" +
                "    \"enable\": 1,\n" +
                "    \"avatar_mediaid\": \"2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0\",\n" +
                "    \"telephone\": \"020-123456\",\n" +
                "    \"address\": \"广州市海珠区新港中路\",\n" +
                "    \"extattr\": {\n" +
                "        \"attrs\": [{\n" +
                "                \"type\": 0,\n" +
                "                \"name\": \"文本名称\",\n" +
                "                \"text\": {\n" +
                "                    \"value\": \"文本\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": 1,\n" +
                "                \"name\": \"网页名称\",\n" +
                "                \"web\": {\n" +
                "                    \"url\": \"http://www.test.com\",\n" +
                "                    \"title\": \"标题\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"to_invite\": true,\n" +
                "    \"external_position\": \"高级产品经理\",\n" +
                "    \"external_profile\": {\n" +
                "        \"external_corp_name\": \"企业简称\",\n" +
                "        \"external_attr\": [{\n" +
                "                \"type\": 0,\n" +
                "                \"name\": \"文本名称\",\n" +
                "                \"text\": {\n" +
                "                    \"value\": \"文本\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": 1,\n" +
                "                \"name\": \"网页名称\",\n" +
                "                \"web\": {\n" +
                "                    \"url\": \"http://www.test.com\",\n" +
                "                    \"title\": \"标题\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": 2,\n" +
                "                \"name\": \"测试app\",\n" +
                "                \"miniprogram\": {\n" +
                "                    \"appid\": \"wx8bd8012614784fake\",\n" +
                "                    \"pagepath\": \"/index\",\n" +
                "                    \"title\": \"my miniprogram\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(json.getBytes(StandardCharsets.UTF_8));
        String external_profile = jsonObject.getString("external_profile");
        System.out.println(external_profile);

    }
}

package com.gstz.entity.enums;

import java.util.Arrays;

/**
 * @author yimu
 * @version 1.0
 * @description:  tzywxmxx  投资业务下项目信息
 *                参数对应枚举类
 * @date 2023/8/2 13:57
 */
public enum ParamOfTzywxmxx {

    xm("xm","姓名"),
    bm("bm","部门"),
    qtxmgs("qtxmgs","近三年牵头负责的划款项目个数"),
    qtxmgm("qtxmgm","近三年牵头负责的项目划款规模"),
    qtxmsfgm("qtxmsfgm","近三年牵头负责的项目第三方划款规模"),
    schkxmgs("schkxmgs","近三年参与的划款项目个数"),
    schkxmgm("schkxmgm","近三年参与的项目划款规模"),
    xzglfsr("xzglfsr","近三年新增项目管理费收入"),
    jcglfsr("jcglfsr","近三年新增项目基础管理费收入"),
    xzcesy("xzcesy","近三年新增超额收益"),
    fzthxmgs("fzthxmgs","牵头负责投后管理项目个数（2023年6月底）"),
    fzthxmgm("fzthxmgm","牵头负责投管管理项目规模（2023年6月底）"),
    cythxmgs("cythxmgs","参与投后管理项目个数（2023年6月底）"),
    cythxmgm("cythxmgm","参与投后管理项目规模（2023年6月底）"),
    fzthxmmc("fzthxmmc","牵头负责投后管理项目名称（2023年6月底）"),
    cythxmmc("cythxmmc","参与投后管理项目名称（2023年6月底）"),
    qtxmmc("qtxmmc","近三年牵头负责的划款项目名称"),
    schkxmmc("schkxmmc","近三年参与的划款项目名称");

    private String param;
    private String chineseParam;

    ParamOfTzywxmxx(String param, String chineseParam) {
        this.param = param;
        this.chineseParam = chineseParam;
    }

    public static String getParam(String chineseParam) {
        for (ParamOfTzywxmxx c : ParamOfTzywxmxx.values()) {
            if (c.getChineseParam().equals(chineseParam) ) {
                return c.param;
            }
        }
        return null;
    }

    public static String getChineseParam(String param) {
        for (ParamOfTzywxmxx c : ParamOfTzywxmxx.values()) {
            if (c.getParam().equals(param) ) {
                return c.chineseParam;
            }
        }
        return null;
    }

    public String getChineseParam() {
        return chineseParam;
    }
    public void setChineseParam(String chineseParam) {
        this.chineseParam = chineseParam;
    }
    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }

    public static void main(String[] args) {
        System.out.println( Arrays.stream(ParamOfTzywxmxx.values()).count());
    }
}
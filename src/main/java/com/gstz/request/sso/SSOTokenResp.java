package com.gstz.request.sso;

import lombok.Data;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2022/12/7 2:03
 */
@Data
public class SSOTokenResp {

    /**
     * code : 0
     * msg : ok
     * access_token : token0000001
     * expires_in : 720
     */
    private int code;
    private String msg;
    private String access_token;
    private String expires_in;
}

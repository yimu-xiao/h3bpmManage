package com.gstz.request.user;

import lombok.Data;

import java.util.List;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/1/9 1:55
 */
@Data
public class UidsReq {
    private List<String> uids;
}

package com.gstz.entity.request.user;

import lombok.Data;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/1/9 1:24
 */
@Data
public class UserReq {

    private int page;
    private int size;
    private String department;
    private String username;
}

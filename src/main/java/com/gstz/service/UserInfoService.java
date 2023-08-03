package com.gstz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gstz.entity.UserInfo;
import com.gstz.entity.request.user.UidsReq;
import com.gstz.entity.request.user.UserReq;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2022/12/7 10:10
 */
public interface UserInfoService   extends IService<UserInfo> {
    int insert(UserInfo userInfo);

    int update(UserInfo userInfo);

    int delete(UserInfo userInfo);

    long count();

    UserInfo select(String uid);

    UserInfo selectByWecharid(String wecharid);

    IPage queryByPage(UserReq userReq);

    int deleteUser(UidsReq uidsReq);
}

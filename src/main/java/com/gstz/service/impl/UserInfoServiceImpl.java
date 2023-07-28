package com.gstz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gstz.config.contant.SimpleConstants;
import com.gstz.entity.UserInfo;
import com.gstz.mapper.UserInfoMapper;
import com.gstz.request.user.UidsReq;
import com.gstz.request.user.UserReq;
import com.gstz.service.UserInfoService;
import com.gstz.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2022/12/7 10:10
 */
@Service("userInfoService")
public class UserInfoServiceImpl  extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public int update(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo);
    }

    @Override
    public int delete(UserInfo userInfo) {
        return userInfoMapper.deleteById(userInfo);
    }

    @Override
    public long count() {
        return  userInfoMapper.selectCount(new QueryWrapper<UserInfo>().eq("1", "1"));
    }

    @Override
    public UserInfo select(String uid) {
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.select(UserInfo.class, info -> !info.getColumn().equals("order")).eq("uid", uid).eq("status", SimpleConstants.USER_STATUS_OPEN);//启用
        return userInfoMapper.selectOne(qw);
    }
    @Override
    public UserInfo selectByWecharid(String wecharid) {
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.select(UserInfo.class, info -> !info.getColumn().equals("order")).eq("wecharid", wecharid).eq("status",SimpleConstants.USER_STATUS_OPEN);//启用
        return userInfoMapper.selectOne(qw);
    }

    @Override
    public IPage queryByPage(UserReq userReq) {
        Page<UserInfo> page = new Page<>(userReq.getPage(),userReq.getSize());
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.select(UserInfo.class, info -> !info.getColumn().equals("order"));
        if (!StringUtil.isEmpty(userReq.getUsername())) {
            qw.like("name",userReq.getUsername());
        }
        if (!StringUtil.isEmpty(userReq.getDepartment())) {
            qw.like("orgname",userReq.getDepartment());
        }
        return userInfoMapper.selectPage(page, qw);
    }

    @Override
    public int deleteUser(UidsReq uidsReq) {
        for (String uid : uidsReq.getUids()) {
            userInfoMapper.delete(new QueryWrapper<UserInfo>().eq("uid",uid));
        }
        return 1;
    }
}

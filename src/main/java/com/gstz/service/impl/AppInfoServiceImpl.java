package com.gstz.service.impl;

import com.gstz.entity.AppInfo;
import com.gstz.dao.AppInfoDao;
import com.gstz.service.AppInfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 对接系统表(AppInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-07-28 10:48:42
 */
@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoDao appInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param appId 主键
     * @return 实例对象
     */
    @Override
    public AppInfo queryById(Integer appId) {
        return this.appInfoDao.queryById(appId);
    }

    /**
     * 分页查询
     *
     * @param appInfo     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<AppInfo> queryByPage(AppInfo appInfo, PageRequest pageRequest) {
        long total = this.appInfoDao.count(appInfo);
        return new PageImpl<>(this.appInfoDao.queryAllByLimit(appInfo, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param appInfo 实例对象
     * @return 实例对象
     */
    @Override
    public AppInfo insert(AppInfo appInfo) {
        this.appInfoDao.insert(appInfo);
        return appInfo;
    }

    /**
     * 修改数据
     *
     * @param appInfo 实例对象
     * @return 实例对象
     */
    @Override
    public AppInfo update(AppInfo appInfo) {
        this.appInfoDao.update(appInfo);
        return this.queryById(appInfo.getAppId());
    }

    /**
     * 通过主键删除数据
     *
     * @param appId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer appId) {
        return this.appInfoDao.deleteById(appId) > 0;
    }
}

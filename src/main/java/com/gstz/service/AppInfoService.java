package com.gstz.service;

import com.gstz.entity.AppInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 对接系统表(AppInfo)表服务接口
 *
 * @author makejava
 * @since 2023-07-28 10:48:42
 */
public interface AppInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param appId 主键
     * @return 实例对象
     */
    AppInfo queryById(Integer appId);

    /**
     * 分页查询
     *
     * @param appInfo     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<AppInfo> queryByPage(AppInfo appInfo, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param appInfo 实例对象
     * @return 实例对象
     */
    AppInfo insert(AppInfo appInfo);

    /**
     * 修改数据
     *
     * @param appInfo 实例对象
     * @return 实例对象
     */
    AppInfo update(AppInfo appInfo);

    /**
     * 通过主键删除数据
     *
     * @param appId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer appId);

}

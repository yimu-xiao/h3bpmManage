package com.gstz.dao;

import com.gstz.entity.AppInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 对接系统表(AppInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-28 10:48:42
 */
public interface AppInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param appId 主键
     * @return 实例对象
     */
    AppInfo queryById(Integer appId);

    /**
     * 查询指定行数据
     *
     * @param appInfo  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<AppInfo> queryAllByLimit(AppInfo appInfo, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param appInfo 查询条件
     * @return 总行数
     */
    long count(AppInfo appInfo);

    /**
     * 新增数据
     *
     * @param appInfo 实例对象
     * @return 影响行数
     */
    int insert(AppInfo appInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AppInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AppInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AppInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AppInfo> entities);

    /**
     * 修改数据
     *
     * @param appInfo 实例对象
     * @return 影响行数
     */
    int update(AppInfo appInfo);

    /**
     * 通过主键删除数据
     *
     * @param appId 主键
     * @return 影响行数
     */
    int deleteById(Integer appId);

}


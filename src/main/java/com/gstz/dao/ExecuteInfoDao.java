package com.gstz.dao;

import com.gstz.entity.ExecuteInfo;
import com.gstz.entity.HttpTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 执行系统表(ExecuteInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-31 14:12:15
 */
@Mapper
public interface ExecuteInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param executeId 主键
     * @return 实例对象
     */
    ExecuteInfo queryById(Integer executeId);

    /**
     * 查询指定行数据
     *
     * @param executeInfo 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<ExecuteInfo> queryAllByLimit(ExecuteInfo executeInfo, @Param("pageable") Pageable pageable);

    /**
     * 查询指定模版
     *
     * @param exeId 查询条件
     * @return 对象列表
     */
    List<HttpTemplate> queryTemByExeId( @Param("exeId") Integer exeId);

    /**
     * 统计总行数
     *
     * @param executeInfo 查询条件
     * @return 总行数
     */
    long count(ExecuteInfo executeInfo);

    /**
     * 新增数据
     *
     * @param executeInfo 实例对象
     * @return 影响行数
     */
    int insert(ExecuteInfo executeInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExecuteInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExecuteInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExecuteInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExecuteInfo> entities);

    /**
     * 修改数据
     *
     * @param executeInfo 实例对象
     * @return 影响行数
     */
    int update(ExecuteInfo executeInfo);

    /**
     * 通过主键删除数据
     *
     * @param executeId 主键
     * @return 影响行数
     */
    int deleteById(Integer executeId);

}


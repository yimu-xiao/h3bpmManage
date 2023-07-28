package com.gstz.dao;

import com.gstz.entity.HttpTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 请求模板表(HttpTemplate)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-28 10:48:43
 */
public interface HttpTemplateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param templateId 主键
     * @return 实例对象
     */
    HttpTemplate queryById(Integer templateId);

    /**
     * 查询指定行数据
     *
     * @param httpTemplate 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<HttpTemplate> queryAllByLimit(HttpTemplate httpTemplate, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param httpTemplate 查询条件
     * @return 总行数
     */
    long count(HttpTemplate httpTemplate);

    /**
     * 新增数据
     *
     * @param httpTemplate 实例对象
     * @return 影响行数
     */
    int insert(HttpTemplate httpTemplate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HttpTemplate> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HttpTemplate> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HttpTemplate> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HttpTemplate> entities);

    /**
     * 修改数据
     *
     * @param httpTemplate 实例对象
     * @return 影响行数
     */
    int update(HttpTemplate httpTemplate);

    /**
     * 通过主键删除数据
     *
     * @param templateId 主键
     * @return 影响行数
     */
    int deleteById(Integer templateId);

}


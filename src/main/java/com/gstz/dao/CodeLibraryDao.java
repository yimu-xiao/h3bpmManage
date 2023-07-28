package com.gstz.dao;

import com.gstz.entity.CodeLibrary;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 码值表(CodeLibrary)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-28 10:48:42
 */
public interface CodeLibraryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param codeNo 主键
     * @return 实例对象
     */
    CodeLibrary queryById(String codeNo);

    /**
     * 查询指定行数据
     *
     * @param codeLibrary 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<CodeLibrary> queryAllByLimit(CodeLibrary codeLibrary, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param codeLibrary 查询条件
     * @return 总行数
     */
    long count(CodeLibrary codeLibrary);

    /**
     * 新增数据
     *
     * @param codeLibrary 实例对象
     * @return 影响行数
     */
    int insert(CodeLibrary codeLibrary);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CodeLibrary> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CodeLibrary> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CodeLibrary> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CodeLibrary> entities);

    /**
     * 修改数据
     *
     * @param codeLibrary 实例对象
     * @return 影响行数
     */
    int update(CodeLibrary codeLibrary);

    /**
     * 通过主键删除数据
     *
     * @param codeNo 主键
     * @return 影响行数
     */
    int deleteById(String codeNo);

}


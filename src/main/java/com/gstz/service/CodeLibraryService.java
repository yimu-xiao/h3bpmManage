package com.gstz.service;

import com.gstz.entity.CodeLibrary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 码值表(CodeLibrary)表服务接口
 *
 * @author makejava
 * @since 2023-07-28 10:48:42
 */
public interface CodeLibraryService {

    /**
     * 通过ID查询单条数据
     *
     * @param codeNo 主键
     * @return 实例对象
     */
    CodeLibrary queryById(String codeNo);

    /**
     * 分页查询
     *
     * @param codeLibrary 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CodeLibrary> queryByPage(CodeLibrary codeLibrary, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param codeLibrary 实例对象
     * @return 实例对象
     */
    CodeLibrary insert(CodeLibrary codeLibrary);

    /**
     * 修改数据
     *
     * @param codeLibrary 实例对象
     * @return 实例对象
     */
    CodeLibrary update(CodeLibrary codeLibrary);

    /**
     * 通过主键删除数据
     *
     * @param codeNo 主键
     * @return 是否成功
     */
    boolean deleteById(String codeNo);

}

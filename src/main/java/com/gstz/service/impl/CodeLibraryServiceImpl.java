package com.gstz.service.impl;

import com.gstz.entity.CodeLibrary;
import com.gstz.dao.CodeLibraryDao;
import com.gstz.service.CodeLibraryService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 码值表(CodeLibrary)表服务实现类
 *
 * @author makejava
 * @since 2023-07-28 11:12:43
 */
@Service("codeLibraryService")
public class CodeLibraryServiceImpl implements CodeLibraryService {
    @Resource
    private CodeLibraryDao codeLibraryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param codeNo 主键
     * @return 实例对象
     */
    @Override
    public CodeLibrary queryById(String codeNo) {
        return this.codeLibraryDao.queryById(codeNo);
    }

    /**
     * 分页查询
     *
     * @param codeLibrary 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CodeLibrary> queryByPage(CodeLibrary codeLibrary, PageRequest pageRequest) {
        long total = this.codeLibraryDao.count(codeLibrary);
        return new PageImpl<>(this.codeLibraryDao.queryAllByLimit(codeLibrary, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param codeLibrary 实例对象
     * @return 实例对象
     */
    @Override
    public CodeLibrary insert(CodeLibrary codeLibrary) {
        this.codeLibraryDao.insert(codeLibrary);
        return codeLibrary;
    }

    /**
     * 修改数据
     *
     * @param codeLibrary 实例对象
     * @return 实例对象
     */
    @Override
    public CodeLibrary update(CodeLibrary codeLibrary) {
        this.codeLibraryDao.update(codeLibrary);
        return this.queryById(codeLibrary.getCodeNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param codeNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String codeNo) {
        return this.codeLibraryDao.deleteById(codeNo) > 0;
    }
}

package com.gstz.service.impl;

import com.gstz.entity.HttpTemplate;
import com.gstz.dao.HttpTemplateDao;
import com.gstz.service.HttpTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求模板表(HttpTemplate)表服务实现类
 *
 * @author makejava
 * @since 2023-07-28 11:12:44
 */
@Service("httpTemplateService")
public class HttpTemplateServiceImpl implements HttpTemplateService {
    @Resource
    private HttpTemplateDao httpTemplateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param templateId 主键
     * @return 实例对象
     */
    @Override
    public HttpTemplate queryById(Integer templateId) {
        return this.httpTemplateDao.queryById(templateId);
    }

    /**
     * 分页查询
     *
     * @param httpTemplate 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<HttpTemplate> queryByPage(HttpTemplate httpTemplate, PageRequest pageRequest) {
        long total = this.httpTemplateDao.count(httpTemplate);
        return new PageImpl<>(this.httpTemplateDao.queryAllByLimit(httpTemplate, pageRequest), pageRequest, total);
    }

    /**
     * List查询
     *
     * @param httpTemplate 筛选条件
     * @return 查询结果
     */
    @Override
    public List<HttpTemplate> queryByParams(HttpTemplate httpTemplate) {
        return this.httpTemplateDao.queryByParams(httpTemplate);
    }

    /**
     * 新增数据
     *
     * @param httpTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public HttpTemplate insert(HttpTemplate httpTemplate) {
        this.httpTemplateDao.insert(httpTemplate);
        return httpTemplate;
    }

    /**
     * 修改数据
     *
     * @param httpTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public HttpTemplate update(HttpTemplate httpTemplate) {
        this.httpTemplateDao.update(httpTemplate);
        return this.queryById(httpTemplate.getTemplateId());
    }

    /**
     * 通过主键删除数据
     *
     * @param templateId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer templateId) {
        return this.httpTemplateDao.deleteById(templateId) > 0;
    }
}

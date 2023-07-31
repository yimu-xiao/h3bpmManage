package com.gstz.service;

import com.gstz.entity.HttpTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 请求模板表(HttpTemplate)表服务接口
 *
 * @author makejava
 * @since 2023-07-28 11:12:44
 */
public interface HttpTemplateService {

    /**
     * 通过ID查询单条数据
     *
     * @param templateId 主键
     * @return 实例对象
     */
    HttpTemplate queryById(Integer templateId);

    /**
     * 分页查询
     *
     * @param httpTemplate 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<HttpTemplate> queryByPage(HttpTemplate httpTemplate, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param httpTemplate 实例对象
     * @return 实例对象
     */
    HttpTemplate insert(HttpTemplate httpTemplate);

    /**
     * 修改数据
     *
     * @param httpTemplate 实例对象
     * @return 实例对象
     */
    HttpTemplate update(HttpTemplate httpTemplate);

    /**
     * 通过主键删除数据
     *
     * @param templateId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer templateId);

}

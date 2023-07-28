package com.gstz.controller;

import com.gstz.entity.HttpTemplate;
import com.gstz.service.HttpTemplateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 请求模板表(HttpTemplate)表控制层
 *
 * @author makejava
 * @since 2023-07-28 10:48:43
 */
@RestController
@RequestMapping("httpTemplate")
public class HttpTemplateController {
    /**
     * 服务对象
     */
    @Resource
    private HttpTemplateService httpTemplateService;

    /**
     * 分页查询
     *
     * @param httpTemplate 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<HttpTemplate>> queryByPage(HttpTemplate httpTemplate, PageRequest pageRequest) {
        return ResponseEntity.ok(this.httpTemplateService.queryByPage(httpTemplate, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HttpTemplate> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.httpTemplateService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param httpTemplate 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HttpTemplate> add(HttpTemplate httpTemplate) {
        return ResponseEntity.ok(this.httpTemplateService.insert(httpTemplate));
    }

    /**
     * 编辑数据
     *
     * @param httpTemplate 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HttpTemplate> edit(HttpTemplate httpTemplate) {
        return ResponseEntity.ok(this.httpTemplateService.update(httpTemplate));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.httpTemplateService.deleteById(id));
    }

}


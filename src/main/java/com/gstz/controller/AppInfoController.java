package com.gstz.controller;

import com.gstz.entity.AppInfo;
import com.gstz.service.AppInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 对接系统表(AppInfo)表控制层
 *
 * @author makejava
 * @since 2023-07-31 11:21:28
 */
@RestController
@RequestMapping("appInfo")
public class AppInfoController {
    /**
     * 服务对象
     */
    @Resource
    private AppInfoService appInfoService;

    /**
     * 分页查询
     *
     * @param appInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<AppInfo>> queryByPage(@RequestBody AppInfo appInfo, PageRequest pageRequest) {
        return ResponseEntity.ok(this.appInfoService.queryByPage(appInfo, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<AppInfo> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.appInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param appInfo 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<AppInfo> add(@RequestBody AppInfo appInfo) {
        return ResponseEntity.ok(this.appInfoService.insert(appInfo));
    }

    /**
     * 编辑数据
     *
     * @param appInfo 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<AppInfo> edit(@RequestBody AppInfo appInfo) {
        return ResponseEntity.ok(this.appInfoService.update(appInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.appInfoService.deleteById(id));
    }

}


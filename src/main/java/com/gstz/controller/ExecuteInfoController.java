package com.gstz.controller;

import com.gstz.entity.ExecuteInfo;
import com.gstz.service.ExecuteInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 执行系统表(ExecuteInfo)表控制层
 *
 * @author makejava
 * @since 2023-07-31 14:12:14
 */
@RestController
@RequestMapping("executeInfo")
public class ExecuteInfoController {
    /**
     * 服务对象
     */
    @Resource
    private ExecuteInfoService executeInfoService;

    /**
     * 分页查询
     *
     * @param executeInfo 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ExecuteInfo>> queryByPage(ExecuteInfo executeInfo, PageRequest pageRequest) {
        return ResponseEntity.ok(this.executeInfoService.queryByPage(executeInfo, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ExecuteInfo> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.executeInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param executeInfo 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ExecuteInfo> add(ExecuteInfo executeInfo) {
        return ResponseEntity.ok(this.executeInfoService.insert(executeInfo));
    }

    /**
     * 编辑数据
     *
     * @param executeInfo 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ExecuteInfo> edit(ExecuteInfo executeInfo) {
        return ResponseEntity.ok(this.executeInfoService.update(executeInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.executeInfoService.deleteById(id));
    }

}


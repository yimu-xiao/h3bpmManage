package com.gstz.controller;

import com.gstz.entity.CodeLibrary;
import com.gstz.service.CodeLibraryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 码值表(CodeLibrary)表控制层
 *
 * @author makejava
 * @since 2023-07-28 11:12:42
 */
@RestController
@RequestMapping("codeLibrary")
public class CodeLibraryController {
    /**
     * 服务对象
     */
    @Resource
    private CodeLibraryService codeLibraryService;

    /**
     * 分页查询
     *
     * @param codeLibrary 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CodeLibrary>> queryByPage(@RequestBody  CodeLibrary codeLibrary, PageRequest pageRequest) {
        return ResponseEntity.ok(this.codeLibraryService.queryByPage(codeLibrary, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CodeLibrary> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.codeLibraryService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param codeLibrary 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CodeLibrary> add(@RequestBody CodeLibrary codeLibrary) {
        return ResponseEntity.ok(this.codeLibraryService.insert(codeLibrary));
    }

    /**
     * 编辑数据
     *
     * @param codeLibrary 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CodeLibrary> edit(@RequestBody CodeLibrary codeLibrary) {
        return ResponseEntity.ok(this.codeLibraryService.update(codeLibrary));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.codeLibraryService.deleteById(id));
    }

}


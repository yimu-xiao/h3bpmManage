package com.gstz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yimu
 * @version 1.0
 * @description: 通知触发
 * @date 2023/7/28 10:54
 */
@RestController
@RequestMapping("h3bpm")
public class h3bpmController {

    //加密一波，传过来 执行编号
    @GetMapping
    public void queryByPage() {
        // 触发对应的操作
        // 执行编号查需要进行的操作
        String exeId = "1";

    }
}

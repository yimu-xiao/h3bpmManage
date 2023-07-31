package com.gstz.service;

import com.gstz.entity.ExecuteInfo;
import com.gstz.entity.HttpTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 执行系统表(ExecuteInfo)表服务接口
 *
 * @author makejava
 * @since 2023-07-31 14:12:15
 */
public interface ExecuteInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param executeId 主键
     * @return 实例对象
     */
    ExecuteInfo queryById(Integer executeId);

    /**
     * 分页查询
     *
     * @param executeInfo 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ExecuteInfo> queryByPage(ExecuteInfo executeInfo, PageRequest pageRequest);

    /**
     * list查询
     *
     * @param executeInfo 筛选条件
     * @return 查询结果
     */
    List<ExecuteInfo> queryByParams(ExecuteInfo executeInfo);

    /**
     * 根据执行编号关联模板列表
     * @param exeId 筛选条件
     * @return 查询结果
     */
    List<HttpTemplate> queryTemByExeId(Integer exeId);

    /**
     * 新增数据
     *
     * @param executeInfo 实例对象
     * @return 实例对象
     */
    ExecuteInfo insert(ExecuteInfo executeInfo);

    /**
     * 修改数据
     *
     * @param executeInfo 实例对象
     * @return 实例对象
     */
    ExecuteInfo update(ExecuteInfo executeInfo);

    /**
     * 通过主键删除数据
     *
     * @param executeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer executeId);

}

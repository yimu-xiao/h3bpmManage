package com.gstz.service.impl;

import com.gstz.entity.ExecuteInfo;
import com.gstz.dao.ExecuteInfoDao;
import com.gstz.entity.HttpTemplate;
import com.gstz.service.ExecuteInfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 执行系统表(ExecuteInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 14:12:15
 */
@Service("executeInfoService")
public class ExecuteInfoServiceImpl implements ExecuteInfoService {
    @Resource
    private ExecuteInfoDao executeInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param executeId 主键
     * @return 实例对象
     */
    @Override
    public ExecuteInfo queryById(Integer executeId) {
        return this.executeInfoDao.queryById(executeId);
    }

    /**
     * 分页查询
     *
     * @param executeInfo 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ExecuteInfo> queryByPage(ExecuteInfo executeInfo, PageRequest pageRequest) {
        long total = this.executeInfoDao.count(executeInfo);
        return new PageImpl<>(this.executeInfoDao.queryAllByLimit(executeInfo, pageRequest), pageRequest, total);
    }

    @Override
    public List<ExecuteInfo> queryByParams(ExecuteInfo executeInfo) {
        return this.executeInfoDao.queryAllByLimit(executeInfo, null);
    }

    @Override
    public List<HttpTemplate> queryTemByExeId(Integer exeId) {
        return this.executeInfoDao.queryTemByExeId(exeId);
    }

    /**
     * 新增数据
     *
     * @param executeInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ExecuteInfo insert(ExecuteInfo executeInfo) {
        this.executeInfoDao.insert(executeInfo);
        return executeInfo;
    }

    /**
     * 修改数据
     *
     * @param executeInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ExecuteInfo update(ExecuteInfo executeInfo) {
        this.executeInfoDao.update(executeInfo);
        return this.queryById(executeInfo.getExecuteId());
    }

    /**
     * 通过主键删除数据
     *
     * @param executeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer executeId) {
        return this.executeInfoDao.deleteById(executeId) > 0;
    }
}

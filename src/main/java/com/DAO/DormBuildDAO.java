package com.DAO;

import com.Bean.DormBuild;

import java.util.List;

/**
 * 宿舍类的数据访问接口
 */

public interface DormBuildDAO {
    public abstract DormBuild findByName(String name); //通过名称查找宿舍

    public abstract void save(DormBuild dormBuild); //保存宿舍楼到数据库

    public abstract List<DormBuild> findAll(); //寻找所有的楼栋

    public abstract DormBuild findById(Integer id); //根据ID寻找楼栋

    public abstract Integer update(Integer id, String name, String remark); //根据ID修改内容

    public abstract void deleteOrActive(Integer id, Byte disabled); //根据ID修改disabled字段

    public abstract List<DormBuild> findSurplus(); //返回剩余可用的宿舍楼
}

package com.Service;

import com.Bean.DormBuild;
import com.DAO.DormBuildDAO;
import com.DAO.DormBuildDAOImpl;
import com.Util.DruidUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 宿舍业务逻辑的数据访问接口实现类
 */

public class DormBuildServiceImpl implements DormBuildService {

    private DormBuildDAO dormBuildDAO = new DormBuildDAOImpl();

    //通过名称查找宿舍
    @Override
    public DormBuild findByName(String name) {
        DormBuild result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            DormBuild build = dormBuildDAO.findByName(name);

            //4.判断
            if (build != null)
            {
                result = build;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            //7.归还连接
            DruidUtil.close(null, connection);
        }

        return result;
    }

    //保存宿舍楼到数据库
    @Override
    public void save(DormBuild dormBuild) {
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            dormBuildDAO.save(dormBuild);

            //4.提交事务
            connection.commit();
        } catch (SQLException e) {

            //5.回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            //6.归还连接
            DruidUtil.close(null, connection);
        }
    }

    //寻找所有的楼栋
    @Override
    public List<DormBuild> findAll() {
        List<DormBuild> result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            List<DormBuild> builds = dormBuildDAO.findAll();

            //4.判断
            if (builds != null)
            {
                result = builds;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            //7.归还连接
            DruidUtil.close(null, connection);
        }

        return result;
    }

    //根据ID寻找楼栋
    @Override
    public DormBuild findById(Integer id) {
        DormBuild result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            DormBuild build = dormBuildDAO.findById(id);

            //4.判断
            if (build != null)
            {
                result = build;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            //7.归还连接
            DruidUtil.close(null, connection);
        }

        return result;
    }

    //根据ID修改内容
    @Override
    public Integer update(Integer id, String name, String remark) {
        Integer result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            result = dormBuildDAO.update(id, name, remark);

            //4.提交事务
            connection.commit();
        } catch (SQLException e) {

            //5.回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            //6.归还连接
            DruidUtil.close(null, connection);
        }
        return result;
    }

    //根据ID修改disabled字段
    @Override
    public void deleteOrActive(Integer id, Byte disabled) {
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            dormBuildDAO.deleteOrActive(id, disabled);

            //4.提交事务
            connection.commit();
        } catch (SQLException e) {

            //5.回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            //6.归还连接
            DruidUtil.close(null, connection);
        }
    }

    //返回剩余可用的宿舍楼
    @Override
    public List<DormBuild> findSurplus() {
        List<DormBuild> result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            List<DormBuild> builds = dormBuildDAO.findSurplus();

            //4.判断
            if (builds != null)
            {
                result = builds;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            //7.归还连接
            DruidUtil.close(null, connection);
        }

        return result;
    }
}

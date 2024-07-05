package com.Service;

import com.Bean.User;
import com.DAO.UserDAO;
import com.DAO.UserDAOImpl;
import com.Util.DruidUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户业务逻辑的数据访问接口实现类
 */

public class UserServiceImpl implements UserService{

    private UserDAO userDAO = new UserDAOImpl();

    //通过学号和密码查找用户
    @Override
    public User findBystuCodeAndPassword(String stuCode, String password) {
        User result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            User user = userDAO.findBystuCodeAndPassword(stuCode, password);

            //4.判断
            if (user != null)
            {
                result = user;
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


    //查询所有的宿舍管理员和他们管理的楼栋
    @Override
    public List<User> findAllDormManagers() {
        List<User> result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            List<User> users = userDAO.findAllDormManagers();

            //4.判断
            if (users != null)
            {
                result = users;
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

    @Override
    public List<User> findDormManagersByName(String name) {
        List<User> result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            List<User> users = userDAO.findDormManagersByName(name);

            //4.判断
            if (users != null)
            {
                result = users;
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

    //通过性别，模糊查询宿舍管理员
    @Override
    public List<User> findDormManagersBySex(String sex) {
        List<User> result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            List<User> users = userDAO.findDormManagersBySex(sex);

            //4.判断
            if (users != null)
            {
                result = users;
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

    //根据手机号，模糊查询所有的宿管
    @Override
    public List<User> findDormManagersByTel(String tel) {
        List<User> result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            List<User> users = userDAO.findDormManagersByTel(tel);

            //4.判断
            if (users != null)
            {
                result = users;
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

    //新增宿舍管理员
    @Override
    public Integer saveManager(User user) {
        Integer result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            result = userDAO.saveManager(user);

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

    @Override
    public void removeManager(Integer id) {
        Connection connection = null;

        try{
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            userDAO.removeManager(id);

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

    //激活宿舍管理员
    @Override
    public void activeManager(Integer id) {
        Connection connection = null;

        try{
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            userDAO.activeManager(id);

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

    //根据ID，找到用户
    @Override
    public User findUserByID(Integer id) {
        User result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            User user = userDAO.findUserByID(id);

            //4.判断
            if (user != null)
            {
                result = user;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            //6.归还连接
            DruidUtil.close(null, connection);
        }

        return result;
    }

    //更新宿舍管理员
    @Override
    public Integer updateManager(User user) {
        Integer result = 0;
        Connection connection = null;

        try {
            //1.开启事务
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            Integer rowID = userDAO.updateManager(user);

            //4.判断
            if (rowID != 0)
            {
                result = rowID;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            //6.归还连接
            DruidUtil.close(null, connection);
        }

        return result;
    }

    //求出所有的学生数量
    @Override
    public Integer getStudentCount(String buildId, String searchType, String keywords) {
        Integer result = 0;
        Connection connection = null;

        try {
            //1.开启事务
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            Integer rowID = userDAO.getStudentCount(buildId, searchType, keywords);

            //4.判断
            if (rowID != 0)
            {
                result = rowID;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            //6.归还连接
            DruidUtil.close(null, connection);
        }

        return result;
    }

    //分页查询学生
    @Override
    public List<User> findUserByPage(Integer page, String buildId, String searchType, String keywords) {
        List<User> result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            List<User> users = userDAO.findUserByPage(page, buildId, searchType, keywords);

            //4.判断
            if (users != null)
            {
                result = users;
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

    //新增学生
    @Override
    public Integer saveStudent(User user) {
        Integer result = null;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            result = userDAO.saveStudent(user);

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

    //删除学生
    @Override
    public void deleteStudent(Integer id) {
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            userDAO.deleteStudent(id);

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

    //激活学生
    @Override
    public void activeStudent(Integer id) {
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            userDAO.activeStudent(id);

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

    //更新学生
    @Override
    public Integer updateStudent(User user) {
        Integer result = 0;
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            Integer rowID = userDAO.updateStudent(user);

            //4.判断
            if (rowID != 0)
            {
                result = rowID;
            }

            //5.提交事务
            connection.commit();
        } catch (SQLException e) {

            //6.回滚事务
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

    //修改密码
    @Override
    public void changePassword(Integer id, String newPassword) {
        Connection connection = null;

        try {
            //1.获取连接
            connection = DruidUtil.getConnection();

            //2.开启事务
            connection.setAutoCommit(false);

            //3.执行SQL
            userDAO.changePassword(id, newPassword);

            //4.提交事务
            connection.commit();
        } catch (SQLException e) {

            //5.回滚事务
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
}

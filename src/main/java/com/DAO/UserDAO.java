package com.DAO;

import com.Bean.User;

import java.util.List;

/**
 * 用户类的数据访问接口
 */

public interface UserDAO {

    //通过学号和密码查找用户
    public abstract User findBystuCodeAndPassword(String stuCode, String password);


    //---------------超级管理员的功能--------------------
    //查询所有的宿舍管理员和他们管理的楼栋
    public abstract List<User> findAllDormManagers();

    //根据姓名，模糊查询所有的宿管
    public abstract List<User> findDormManagersByName(String name);

    //根据性别，模糊查询所有的宿管
    public abstract List<User> findDormManagersBySex(String sex);

    //根据手机号，模糊查询所有的宿管
    public abstract List<User> findDormManagersByTel(String tel);

    //新增宿舍管理员
    public abstract Integer saveManager(User user);

    //删除宿舍管理员
    public abstract void removeManager(Integer id);

    //激活宿舍管理员
    public abstract void activeManager(Integer id);

    //根据ID，找到用户
    public abstract User findUserByID(Integer id);

    //更新宿舍管理员
    public abstract Integer updateManager(User user);

    //按照条件求出所有的学生数量
    public abstract Integer getStudentCount(String buildId, String searchType, String keywords);

    //分页查询学生
    public abstract List<User> findUserByPage(Integer page, String buildId, String searchType, String keywords);

    //新增学生
    public abstract Integer saveStudent(User user);

    //删除学生
    public abstract void deleteStudent(Integer id);

    //激活学生
    public abstract void activeStudent(Integer id);

    //更新学生
    public abstract Integer updateStudent(User user);

    //修改密码
    public abstract void changePassword(Integer id, String newPassword);

    //---------------超级管理员的功能--------------------

}

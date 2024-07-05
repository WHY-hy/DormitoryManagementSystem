package com.DAO;

import com.Bean.Count;
import com.Bean.User;
import com.Util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户类的数据访问接口实现类
 */

public class UserDAOImpl implements UserDAO{

    private QueryRunner queryRunner = new QueryRunner();

    //通过学号和密码查找用户
    @Override
    public User findBystuCodeAndPassword(String stuCode, String password) {
        //1.定义SQL语句
        String sql = "select * from tb_user where stu_code = ? and password = ? and disabled = 0";

        //2.执行SQL
        try {
            User user = queryRunner.query(DruidUtil.getConnection(), sql, new BeanHandler<>(User.class), stuCode, password);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //查询所有的宿舍管理员和他们管理的楼栋
    @Override
    public List<User> findAllDormManagers() {
        //1.定义SQL语句
        String sql = "select tb_user.*, tb_dormbuild.name from tb_user left outer join tb_dormbuild on tb_user.dormBuildId = tb_dormbuild.id where tb_user.role_id = 1";

        //2.执行SQL语句
        try {
            List<User> users = queryRunner.query(DruidUtil.getConnection(), sql, new BeanListHandler<>(User.class));
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据姓名，模糊查询所有的宿管
    @Override
    public List<User> findDormManagersByName(String name) {

        //1.定义SQL语句
        String sql = "select * from tb_user where role_id = 1 and username like ?";

        //2.执行SQL语句
        try {
            List<User> users = queryRunner.query(DruidUtil.getConnection(), sql, new BeanListHandler<>(User.class), ("%" + name + "%"));
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据性别，模糊查询所有的宿管
    @Override
    public List<User> findDormManagersBySex(String sex) {

        //1.定义SQL语句
        String sql = "select * from tb_user where role_id = 1 and sex like ?";

        //2.执行SQL语句
        try {
            List<User> users = queryRunner.query(DruidUtil.getConnection(), sql, new BeanListHandler<>(User.class), ("%" + sex + "%"));
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据手机号，模糊查询所有的宿管
    @Override
    public List<User> findDormManagersByTel(String tel) {
        //1.定义SQL语句
        String sql = "select * from tb_user where role_id = 1 and tel like ?";

        //2.执行SQL语句
        try {
            List<User> users = queryRunner.query(DruidUtil.getConnection(), sql, new BeanListHandler<>(User.class), ("%" + tel + "%"));
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //新增宿舍管理员
    @Override
    public Integer saveManager(User user) {
        //1.定义SQL语句
        String sql = "insert into tb_user (id, username, password, stu_code, dorm_Code, sex, tel, dormBuildId, role_id, create_user_id, disabled) values (null, ?, ?, ?, null, ?, ?, ?, ?, ?, ?)";

        //2.执行SQL语句
        int rowID = 0;
        try{
            rowID = queryRunner.update(DruidUtil.getConnection(), sql, user.getUsername(), user.getPassword(), user.getStu_code(), user.getSex(), user.getTel(), user.getDormBuildId(), user.getRole_id(), user.getCreate_user_id(), user.getDisabled());
        } catch (SQLException e) {
            rowID = 0;
        }
        return rowID;
    }

    //删除宿舍管理员
    @Override
    public void removeManager(Integer id) {
        //1.定义SQL语句
        String sql = "update tb_user set disabled = 1, dormBuildId = null where id = ?";

        //2.执行SQL语句
        try {
            queryRunner.update(DruidUtil.getConnection(), sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //激活宿舍管理员
    @Override
    public void activeManager(Integer id) {
        //1.定义SQL语句
        String sql = "update tb_user set disabled = 0, dormBuildId = null where id = ?";

        //2.执行SQL语句
        try {
            queryRunner.update(DruidUtil.getConnection(), sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //根据ID，找到用户
    @Override
    public User findUserByID(Integer id) {
        //1.定义SQL语句
        String sql = "select * from tb_user where id = ?";

        //2.执行SQL语句
        try {
            User user = queryRunner.query(DruidUtil.getConnection(), sql, new BeanHandler<>(User.class), id);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //更新宿舍管理员
    @Override
    public Integer updateManager(User user) {
        //1.定义SQL语句
        String sql = "update tb_user set stu_code = ?, username = ?, password = ?, sex = ?, tel = ?, dormBuildId = ? where id = ?";

        //2.执行SQL语句
        int rowID = 0;
        try {
            rowID = queryRunner.update(DruidUtil.getConnection(), sql, user.getStu_code(), user.getUsername(), user.getPassword(), user.getSex(), user.getTel(), user.getDormBuildId(), user.getId());
        } catch (SQLException e) {
            rowID = 0;
        }
        return rowID;
    }

    //求出所有的学生数量
    @Override
    public Integer getStudentCount(String buildId, String searchType, String keywords) {
        //1.定义SQL语句
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as sum from tb_user where role_id = 2");

        //2.判断
        int buildID = 0;
        if (buildId.trim().equals("")) //如果宿舍楼的ID为空，就是查询所有宿舍楼的学生
        {
            switch (searchType)
            {
                case "name": //根据姓名进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and username like ?");
                    }
                }
                break;
                case "stuCode": //根据学号进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and stu_code like ?");
                    }
                }
                break;
                case "sex": //根据性别进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and sex like ?");
                    }
                }
                break;
                case "tel": //根据手机号查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and tel like ?");
                    }
                }
                break;
            }

            //执行SQL
            if (!keywords.trim().equals("")) //如果输入内容不为空格
            {
                try {
                    Count result = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanHandler<>(Count.class), ("%" + keywords + "%"));
                    return result.getSum();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else { //如果输入内容为空格
                try {
                    Count result = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanHandler<>(Count.class));
                    return result.getSum();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else { //查询单个宿舍楼的人
            //转换id
            buildID = Integer.parseInt(buildId);
            switch (searchType)
            {
                case "name": //根据姓名进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and username like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
                case "stuCode": //根据学号进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and stu_code like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
                case "sex": //根据性别进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and sex like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
                case "tel": //根据手机号查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and tel like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
            }

            //执行SQL
            if (!keywords.trim().equals("")) //如果输入内容不为空格
            {
                try {
                    Count result = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanHandler<>(Count.class), buildID, ("%" + keywords + "%"));
                    return result.getSum();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    Count result = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanHandler<>(Count.class), buildID);
                    return result.getSum();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    //分页查询第一页的学生数量
    @Override
    public List<User> findUserByPage(Integer page, String buildId, String searchType, String keywords) {

        //1.先声明sql字符串
        StringBuilder sql = new StringBuilder();
        sql.append("select * from tb_user where role_id = 2");

        //2.判断楼栋是否为空
        int buildID = 0;
        page = (page - 1) * 3;
        if (buildId.trim().equals("")) //如果为空，查询全部楼栋的学生
        {
            switch (searchType)
            {
                case "name": //根据姓名进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and username like ?");
                    }
                }
                break;
                case "stuCode": //根据学号进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and stu_code like ?");
                    }
                }
                break;
                case "sex": //根据性别进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and sex like ?");
                    }
                }
                break;
                case "tel": //根据手机号查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and tel like ?");
                    }
                }
                break;
            }

            sql.append(" limit ?, 3");

            //执行SQL
            if (!keywords.trim().equals("")) //如果输入内容不为空格
            {
                try {
                    List<User> users = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanListHandler<>(User.class), ("%" + keywords + "%"), page);
                    return users;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    List<User> users = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanListHandler<>(User.class), page);
                    return users;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else { //如果楼栋id不为空
            //转换id
            buildID = Integer.parseInt(buildId);
            switch (searchType)
            {
                case "name": //根据姓名进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and username like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
                case "stuCode": //根据学号进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and stu_code like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
                case "sex": //根据性别进行查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and sex like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
                case "tel": //根据手机号查询
                {
                    if (!keywords.trim().equals("")) //输入内容不为空格
                    {
                        sql.append(" and dormBuildId = ? and tel like ?");
                    }else {
                        sql.append(" and dormBuildId = ?");
                    }
                }
                break;
            }

            sql.append(" limit ?, 3");

            //执行SQL
            if (!keywords.trim().equals("")) //如果输入内容不为空格
            {
                try {
                    List<User> users = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanListHandler<>(User.class), buildID, ("%" + keywords + "%"), page);
                    return users;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    List<User> users = queryRunner.query(DruidUtil.getConnection(), sql.toString(), new BeanListHandler<>(User.class), buildID, page);
                    return users;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //新增学生
    @Override
    public Integer saveStudent(User user) {
        //1.定义SQL语句
        String sql = "insert into tb_user (id, username, password, stu_code, dorm_Code, sex, tel, dormBuildId, role_id, create_user_id, disabled) values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //2.执行SQL语句
        int rowID = 0;
        try{
            rowID = queryRunner.update(DruidUtil.getConnection(), sql, user.getUsername(), user.getPassword(), user.getStu_code(), user.getDorm_Code(), user.getSex(), user.getTel(), user.getDormBuildId(), user.getRole_id(), user.getCreate_user_id(), user.getDisabled());
        } catch (SQLException e) {
            rowID = 0;
        }
        return rowID;
    }

    //删除学生
    @Override
    public void deleteStudent(Integer id) {
        //1.定义SQL语句
        String sql = "update tb_user set disabled = 1 where id = ?";

        //2.执行SQL语句
        try {
            queryRunner.update(DruidUtil.getConnection(), sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //激活学生
    @Override
    public void activeStudent(Integer id) {
        //1.定义SQL语句
        String sql = "update tb_user set disabled = 0 where id = ?";

        //2.执行SQL语句
        try {
            queryRunner.update(DruidUtil.getConnection(), sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新学生
    @Override
    public Integer updateStudent(User user) {

        //1.定义SQL语句
        String sql = "update tb_user set stu_code = ?, username = ?, sex = ?, tel = ?, password = ?, dormBuildId = ?, dorm_Code = ? where id = ?";

        //2.执行SQL语句
        int rowID = 0;
        try {
            rowID = queryRunner.update(DruidUtil.getConnection(), sql, user.getStu_code(), user.getUsername(), user.getSex(), user.getTel(), user.getPassword(), user.getDormBuildId(), user.getDorm_Code(), user.getId());
        } catch (Exception e) {
            rowID = 0;
        }

        return rowID;
    }

    //修改密码
    @Override
    public void changePassword(Integer id, String newPassword) {

        //1.定义SQL语句
        String sql = "update tb_user set password = ? where id = ?";

        //2.执行SQL语句
        try {
            queryRunner.update(DruidUtil.getConnection(), sql, newPassword, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

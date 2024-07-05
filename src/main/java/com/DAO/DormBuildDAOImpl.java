package com.DAO;

import com.Bean.DormBuild;
import com.Util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 宿舍类的数据访问接口实现类
 */

public class DormBuildDAOImpl implements DormBuildDAO{

    private QueryRunner queryRunner = new QueryRunner();

    //通过名称查找宿舍
    @Override
    public DormBuild findByName(String name) {
        //1.定义SQL语句
        String sql = "select * from tb_dormbuild where name = ?";

        //2.执行SQL
        try {
            DormBuild build = queryRunner.query(DruidUtil.getConnection(), sql, new BeanHandler<>(DormBuild.class), name);
            return build;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //保存宿舍楼到数据库
    @Override
    public void save(DormBuild dormBuild) {
        //1.定义SQL语句
        String sql = "insert into tb_dormbuild(id, name, remark, disabled) values (null, ?, ?, ?)";

        //2.执行SQL
        try {
            int row = queryRunner.update(DruidUtil.getConnection(), sql, dormBuild.getName(), dormBuild.getRemark(), dormBuild.getDisabled());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //寻找所有的楼栋
    @Override
    public List<DormBuild> findAll() {
        //1.定义SQL语句
        String sql = "select * from tb_dormbuild";

        //2.执行SQL语句
        try {
            List<DormBuild> builds = queryRunner.query(DruidUtil.getConnection(), sql, new BeanListHandler<>(DormBuild.class));
            return builds;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据ID寻找楼栋
    @Override
    public DormBuild findById(Integer id) {
        //1.定义SQL语句
        String sql = "select * from tb_dormbuild where id = ?";

        //2.执行SQL
        try {
            DormBuild build = queryRunner.query(DruidUtil.getConnection(), sql, new BeanHandler<>(DormBuild.class), id);
            return build;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据ID修改内容
    @Override
    public Integer update(Integer id, String name, String remark) {
        //1.定义SQL语句
        String sql = "update tb_dormbuild set name = ?, remark = ? where id = ?";

        //2.执行SQL
        int rowId = 0;
        try {
            rowId = queryRunner.update(DruidUtil.getConnection(), sql, name, remark, id);
        } catch (SQLException e) {
            rowId = 0; //报错就返回0
        }
        return rowId;
    }

    //根据ID修改disabled字段
    @Override
    public void deleteOrActive(Integer id, Byte disabled) {
        //1.定义SQL语句
        String sql = "update tb_dormbuild set disabled = ? where id = ?";

        //2.执行SQL
        try {
            int rowId = queryRunner.update(DruidUtil.getConnection(), sql, disabled, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //返回剩余可用的宿舍楼
    @Override
    public List<DormBuild> findSurplus() {
        //1.定义SQL语句
        String sql = "select * from tb_dormbuild where id not in (select dormBuildId from tb_user where role_id = 1 and disabled = 0 and dormBuildId >= 1) and disabled = 0";

        //2.执行SQL
        try {
            List<DormBuild> builds = queryRunner.query(DruidUtil.getConnection(), sql, new BeanListHandler<>(DormBuild.class));
            return builds;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

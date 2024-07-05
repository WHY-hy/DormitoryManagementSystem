package com.Bean;

/**
 * 宿舍楼表
 */

public class DormBuild {

    private Integer id; //宿舍楼的ID
    private String name; //宿舍楼的名称
    private String remark; //备注
    private Byte disabled; //是否删除，0-不删，1-删

    //构造函数
    public DormBuild() {
    }

    public DormBuild(Integer id, String name, String remark, Byte disabled) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.disabled = disabled;
    }

    //setter和getter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }


    //toString
    @Override
    public String toString() {
        return "DormBuild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", disabled=" + disabled +
                '}';
    }
}

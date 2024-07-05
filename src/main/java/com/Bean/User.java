package com.Bean;

/**
 * 用户类
 */

public class User {

    private Integer id; //用户ID
    private String username; //用户名
    private String password; //密码
    private String stu_code; //学号
    private String dorm_Code; //宿舍编号
    private String sex; //性别
    private String tel; //手机号
    private Integer dormBuildId; //宿舍楼ID
    private Byte role_id; //0-超级管理员，1-宿管，2-学生
    private Integer create_user_id; //创建人的id
    private Byte disabled; //是否删除，0-不删，1-删
    private String name; //用户对应的宿舍名

    //构造函数
    public User() {
    }

    public User(Integer id, String username, String password, String stu_code, String dorm_Code, String sex, String tel, Integer dormBuildId, Byte role_id, Integer create_user_id, Byte disabled, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.stu_code = stu_code;
        this.dorm_Code = dorm_Code;
        this.sex = sex;
        this.tel = tel;
        this.dormBuildId = dormBuildId;
        this.role_id = role_id;
        this.create_user_id = create_user_id;
        this.disabled = disabled;
        this.name = name;
    }

    //getter和setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStu_code() {
        return stu_code;
    }

    public void setStu_code(String stu_code) {
        this.stu_code = stu_code;
    }

    public String getDorm_Code() {
        return dorm_Code;
    }

    public void setDorm_Code(String dorm_Code) {
        this.dorm_Code = dorm_Code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public Byte getRole_id() {
        return role_id;
    }

    public void setRole_id(Byte role_id) {
        this.role_id = role_id;
    }

    public Integer getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", stu_code='" + stu_code + '\'' +
                ", dorm_Code='" + dorm_Code + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", role_id=" + role_id +
                ", create_user_id=" + create_user_id +
                ", disabled=" + disabled +
                ", dormBuildName=" + name +
                '}';
    }
}

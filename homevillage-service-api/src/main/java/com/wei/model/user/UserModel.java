package com.wei.model.user;

import com.wei.model.base.BaseModel;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/2 0002.
 */
public class UserModel extends BaseModel{
    //用户名
    private String userName;
    //登录名
    private String loginName;
    //密码
    private String password;
    //性别
    private String sex;
    //地址
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("{ id = %s,userName=%s,loginName=%s,address=%s,sex=%s,password=%s,createTime=%s}",
                                getId(),getUserName(),getLoginName(),getAddress(),getSex(),getPassword()
        ,getCreateTime());
    }

}

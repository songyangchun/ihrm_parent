package com.ihrm.domain.rcmd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "rcmd_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserNameAndMobile{

    private static final long serialVersionUID = 594829320797158219L;
    //ID
    @Id
    private String id;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;


    /**
     * 注册时间
     */
    private String createTime;

    /**
     * 用户初始头像
     */
    private String userImg;


    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户性别
     */
    private String gender;


    /**
     * 出生年月日
     */
    private Date age;


    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 联系电话
     */
    private String contactPhone;


    /**
     * 兴趣标签
     */
    private String interestLabel;

    /**
     * 兴趣类型
     */
    private String interestType;

    /**
     * 可能感兴趣的景点
     */
    private String interestSpot;




}

package com.ihrm.domain.rcmd;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "plat_rcmd_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;
    //ID
    @Id
    private String id;
    /**
     * 行程主题
     */
    private String title;
    /**
     * 出发城市
     */
    private String startCity;
    /**
     * 目标城市
     */
    private String aimCity;

    /**
     * 主要景点
     */
    private String mainPlace;
    /**
     * 出发时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;

    /**
     * 期望人数
     */
    private String userNumber;
    /**
     * 总日程数
     */
    private String workNumber;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 行程安排
     */
    private String scheduling;
    /**
     * 组团备注
     */
    private String remark;
//
//    /**
//     * 用户id
//     */
//    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 图片
     */
    private String img;

    /**
     * 出发天数
     */
    private int date;
    /**
     * 发布日期
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String creatTime;

}

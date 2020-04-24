package com.charming.project.wx.domain;

import lombok.Data;

import java.util.Date;

@Data
public class WxUser {

    /**主键ID*/
    private Long  id;
    /**识别ID*/
    private String  openid ;
    /**session*/
    private String  sessionKey ;
    /**名称*/
    private String  nickName ;
    /**头像*/
    private String  avatarUrl ;
    /**性别*/
    private String  gender ;
    /**国家*/
    private String  country ;
    /**省*/
    private String  province ;
    /**市*/
    private String  city ;
    /**加入时间*/
    private Date  joinTime ;
    /**最后一次登录时间*/
    private Date lastTime ;

}

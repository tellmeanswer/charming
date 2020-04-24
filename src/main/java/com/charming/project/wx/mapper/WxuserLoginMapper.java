package com.charming.project.wx.mapper;

import com.charming.project.wx.domain.WxUser;

public interface WxuserLoginMapper {


    public WxUser selectuserLogin(String openid);
    public int adduserLogin(WxUser wxUser);
    public int uodateuserLogin(WxUser wxUser);
}

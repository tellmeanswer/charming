package com.charming.project.wx.service;

import com.charming.framework.web.domain.AjaxResult;
import com.charming.project.wx.domain.Wxuserlogin;
import org.springframework.stereotype.Service;


public interface WxuserLoginService {

   AjaxResult wxloginuser(Wxuserlogin wxuserlogin);
   AjaxResult wxloginempower(Wxuserlogin wxuserlogin);
}

package com.charming.project.wx.controller;

import com.charming.framework.web.controller.BaseController;
import com.charming.framework.web.domain.AjaxResult;
import com.charming.project.wx.domain.Wxuserlogin;
import com.charming.project.wx.service.WxuserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/wxapp")
public class WxuserLoginController extends BaseController {

@Autowired
private WxuserLoginService wxuserLoginService;

    @PostMapping("/login")
    public AjaxResult wxlogin(@RequestBody Wxuserlogin wxuserlogin)
    {
        AjaxResult result = wxuserLoginService.wxloginuser(wxuserlogin);
        return result;
    }
    /**获取用户信息接口*/
    @PostMapping("/empower")
    public AjaxResult wxempower(@RequestBody Wxuserlogin wxuserlogin)
    {
        AjaxResult result = wxuserLoginService.wxloginempower(wxuserlogin);
        return result;
    }


}

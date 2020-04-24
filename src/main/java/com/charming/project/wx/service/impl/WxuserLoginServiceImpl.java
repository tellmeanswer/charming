package com.charming.project.wx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.charming.common.utils.ase.AseUtils;
import com.charming.common.utils.http.HttpUtils;
import com.charming.framework.web.domain.AjaxResult;
import com.charming.project.wx.domain.WxUser;
import com.charming.project.wx.domain.Wxuserlogin;
import com.charming.project.wx.mapper.WxuserLoginMapper;
import com.charming.project.wx.service.WxuserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class WxuserLoginServiceImpl implements WxuserLoginService {

    @Autowired
    private WxuserLoginMapper wxuserLoginMapper;

    @Override
    public AjaxResult wxloginuser(Wxuserlogin wxuserlogin) {

            JSONObject sessionKeyOrOpenId = getSessionKeyOrOpenId(wxuserlogin.getCode());

            String openid = sessionKeyOrOpenId.getString("openid" );

            String sessionKey = sessionKeyOrOpenId.getString( "session_key" );
            //查询用户是否授权
            WxUser wxUser = wxuserLoginMapper.selectuserLogin(openid);
            if(wxUser == null){
                return  new AjaxResult(404,openid,sessionKey);
            }


        return AjaxResult.success("成功",wxUser);

    }
    /**
     * tx
     * 服务类 获取小程序登录人信息
     * */
    @Override
    public AjaxResult wxloginempower(Wxuserlogin wxuserlogin) {
         if(wxuserlogin.getSessionKey() == null || wxuserlogin.getSessionKey()==""){
             JSONObject sessionKeyOrOpenId = getSessionKeyOrOpenId(wxuserlogin.getCode());

             wxuserlogin.setSessionKey(sessionKeyOrOpenId.getString("session_key" ));

             wxuserlogin.setOpenid(sessionKeyOrOpenId.getString("openid" ));
         }

        WxUser wxUser = wxuserLoginMapper.selectuserLogin(wxuserlogin.getOpenid());
        JSONObject userInfo = AseUtils.getUserInfo(wxuserlogin);
        WxUser user =new WxUser();
        user.setNickName(userInfo.getString("nickName"));
        user.setAvatarUrl(userInfo.getString("avatarUrl"));
        user.setCity(userInfo.getString("city"));
        user.setCountry(userInfo.getString("country"));
        user.setJoinTime(new Date());
        user.setGender(userInfo.getString("gender"));
        user.setLastTime(new Date());
        user.setOpenid(wxuserlogin.getOpenid());
        user.setProvince(userInfo.getString("province"));
        user.setSessionKey(wxuserlogin.getSessionKey());
        if(wxUser == null){
            //添加用户操作
            if(wxuserLoginMapper.adduserLogin(user) <= 0) {
               return AjaxResult.error(500,"发生错误");
            }
            return AjaxResult.success("200", user);
        }else{
            //修改用户操作
            user.setId(wxUser.getId());
            if( wxuserLoginMapper.uodateuserLogin(user) <= 0) {
                return AjaxResult.error(500,"发生错误");
            }
            return AjaxResult.success("200", user);
          }

    }



    public static JSONObject getSessionKeyOrOpenId(String code){
        //微信端登录code
        String wxCode = code;
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        String urlParam="appid=wx2d2bdaf87b16c076&secret=00958db55259ba7e082a7ead0b73263f&js_code="+code+"&grant_type=authorization_code";
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject( HttpUtils.sendGet(requestUrl,urlParam));
        return jsonObject;
    }

}

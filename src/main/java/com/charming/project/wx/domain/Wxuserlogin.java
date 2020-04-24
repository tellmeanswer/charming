package com.charming.project.wx.domain;

import lombok.Data;

@Data
public class Wxuserlogin {
    private static final long serialVersionUID = 1L;
    /** 临时登录凭证 */
    private String code;
    private String sessionKey;
    /**用户唯一ID*/
    private String openid;
    /**用户非敏感信息*/
    private String rawData;
    /**签名*/
    private String signature;
    /**用户敏感信息*/
    private String encryptedData;
    /**解密算法的向量*/
    private String iv;





}

package com.bianmin.xpayutils;

public class WechatPayReq {

    private String appId;
    private String partnerId;
    private String prepayId;
    private String packageValue;
    private String nonceStr;
    private String timeStamp;
    private String sign;
    private String sign_type;

    public WechatPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign) {
        this.appId = appId;
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.packageValue = packageValue;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.sign = sign;
        this.sign_type = WechatSignType.MD5.getSignType();
    }

    public WechatPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, WechatSignType signType) {
        this.appId = appId;
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.packageValue = packageValue;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.sign = sign;
        this.sign_type = signType.getSignType();
    }

    public WechatPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, String signType) {
        this.appId = appId;
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.packageValue = packageValue;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.sign = sign;
        this.sign_type = signType;
    }

    public String getAppId() {
        return appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public String getSign_type() {
        return sign_type;
    }
}

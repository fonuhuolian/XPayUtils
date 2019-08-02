package com.bianmin.xpayutils;

public enum WechatSignType {
    HMAC_SHA256("HMAC-SHA256"), MD5("MD5");
    private String signType;

    WechatSignType(String signType) {
        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }
}

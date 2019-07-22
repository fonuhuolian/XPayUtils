package com.bianmin.xpayutils;

public enum WechatErrorCode {

    CODE_SUCCESS("成功", 0), CODE_FAIL("错误(可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。)", -1), CODE_CANCLE("用户取消", -2);

    private String msg;
    private int code;

    WechatErrorCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}

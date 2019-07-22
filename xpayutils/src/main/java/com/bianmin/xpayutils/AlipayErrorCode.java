package com.bianmin.xpayutils;

public enum AlipayErrorCode {

    CODE_8000("正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态", 8000),
    CODE_4000("订单支付失败", 4000),
    CODE_5000("重复请求", 5000),
    CODE_6001("用户中途取消", 6001),
    CODE_6002("网络连接出错", 6002),
    CODE_6004("支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态", 6004),
    CODE_OTHER("其它支付错误", -1);

    private String msg;
    private int code;

    AlipayErrorCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

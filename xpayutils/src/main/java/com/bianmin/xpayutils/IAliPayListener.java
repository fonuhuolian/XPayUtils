package com.bianmin.xpayutils;

public interface IAliPayListener {

    void onAlipaySuccess(String resultInfo, String memo);

    void onAlipayFail(String resultInfo, String memo);
}

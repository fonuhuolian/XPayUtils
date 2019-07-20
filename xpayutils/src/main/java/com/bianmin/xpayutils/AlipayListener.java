package com.bianmin.xpayutils;

public interface AlipayListener {

    void onAlipaySuccess(String resultInfo, String memo);

    void onAlipayFail(String resultInfo, String memo, AlipayErrorCode code);
}

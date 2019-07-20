package com.bianmin.xpayutils;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class XPayUtils {


    /**
     * TODO 支付宝支付
     *
     * @param orderInfo 主要包含商户的订单信息，key="value"形式，以&连接。
     * @param listener  支付的回调监听
     */
    public static void alipay(final Activity activity, final String orderInfo, final AlipayListener listener) {

        if (activity == null || TextUtils.isEmpty(orderInfo) || listener == null)
            return;

        Thread payThread = new Thread(new Runnable() {
            @Override
            public void run() {

                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                // 支付结果
                final AlipayResult alipayResult = new AlipayResult(result);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        /**
                         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                         */
                        String resultInfo = alipayResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = alipayResult.getResultStatus();
                        String resultMemo = alipayResult.getMemo();
                        // 判断resultStatus 为9000则代表支付成功
                        if (TextUtils.equals(resultStatus, "9000")) {
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                            listener.onAlipaySuccess(resultInfo, resultMemo);
                        } else {
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。

                            AlipayErrorCode code = null;

                            if (resultStatus.equals("8000")) {
                                code = AlipayErrorCode.CODE_8000;
                            } else if (resultStatus.equals("4000")) {
                                code = AlipayErrorCode.CODE_4000;
                            } else if (resultStatus.equals("5000")) {
                                code = AlipayErrorCode.CODE_5000;
                            } else if (resultStatus.equals("6001")) {
                                code = AlipayErrorCode.CODE_6001;
                            } else if (resultStatus.equals("6002")) {
                                code = AlipayErrorCode.CODE_6002;
                            } else if (resultStatus.equals("6004")) {
                                code = AlipayErrorCode.CODE_6004;
                            } else {
                                code = AlipayErrorCode.CODE_OTHER;
                            }
                            listener.onAlipayFail(resultInfo, resultMemo, code);
                        }
                    }
                });
            }
        });
        payThread.start();
    }
}

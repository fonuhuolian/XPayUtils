package com.bianmin.xpayutils;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class PayUtils {


    public static void alipay(final Activity activity, final String orderInfo, final IAliPayListener listener) {

        if (activity == null || TextUtils.isEmpty(orderInfo) || listener == null)
            return;

        Thread payThread = new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                // 支付结果
                final PayResult payResult = new PayResult(result);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        /**
                         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                         */
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = payResult.getResultStatus();
                        String resultMemo = payResult.getMemo();
                        // 判断resultStatus 为9000则代表支付成功
                        if (TextUtils.equals(resultStatus, "9000")) {
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                            listener.onAlipaySuccess(resultInfo, resultMemo);
                        } else {
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            listener.onAlipayFail(resultInfo, resultMemo);
                        }
                    }
                });
            }
        });
        payThread.start();
    }
}

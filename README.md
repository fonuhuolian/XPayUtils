# XPayUtils

> 添加依赖

`root build.gradle `
```
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```
`module build.gradle `
```
dependencies {
    implementation 'com.github.fonuhuolian:XPayUtils:1.0.2'
}
```

- 支付宝支付
```
/**
  * TODO 支付宝支付
  *
  * @param orderInfo 主要包含商户的订单信息，key="value"形式，以&连接。（由商户服务端进行拼接）
  * @param listener  支付的回调监听
  */
XPayUtils.alipay(final Activity activity, final String orderInfo, final AlipayListener listener) ;
```

- 微信支付

1.包名目录下创建`wxapi`文件夹

2.此包下创建名为`WXPayEntryActivity`的Activity,并实现`IWXAPIEventHandler`接口
```
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI iwxapi;
    private static final String APP_ID = "您应用的app_Id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 由第三方App个性化展示支付结果
        setContentView(R.layout.activity_wxpay_entry);

        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID);
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * TODO 微信支付回调
     */
    @Override
    public void onResp(BaseResp baseResp) {

        int errCode = baseResp.errCode;

        if (errCode == WechatErrorCode.CODE_SUCCESS.getCode()) {
            // 成功

        } else if (errCode == WechatErrorCode.CODE_FAIL.getCode()) {
            // 失败

        } else if (errCode == WechatErrorCode.CODE_CANCLE.getCode()) {
            // 取消

        }
    }
}
```
3.清单文件进行注册
```
<activity
  android:name=".wxapi.WXPayEntryActivity"
  android:exported="true"
  android:launchMode="singleTop" />
```
4.发起支付
```
/**
  * TODO 微信支付
  *
  * @param req 支付需要的参数（由商户服务端返回参数）如果调用7参构造方法 默认加密类型为MD5
  */
XPayUtils.wechatPay(Activity activity, WechatPayReq req) ;
```
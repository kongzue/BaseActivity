# BaseActivity
BaseActivity是一款能够提供沉浸式状态栏，可调用黑色状态栏模式，判断了小米MIUI和flyme系统独立的黑色状态栏样式可独立调用，并提供了一些简单的吐司和生成日志的方法的基础Activity类，可以直接将您项目中的Activity继成BaseActivity使用。

## 4.0新增说明：
1.对于魅族的smartbar高度进行计算
2.由于本人常用的http请求框架是Volley，不习惯不合适的请删除代码中的如下一行，之所以默认带上的原因是继承这个BaseActivity的所有Activity都可以轻松使用Volley请求网络，我就是这么懒。
```
public RequestQueue mQueue;            //请求队列
```
3.另外新增了权限申请，使用方法如下：
```
    //使用：
    requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

    //...

    /**
    * 权限回调
    */
    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case 1:
                //权限申请成功...
                break;
        }

    }
```

使用方法很简单，将你的Activity继承这个BaseActivity即可，要实现沉浸状态栏，在onCreate方法调用
```
    setTranslucentStatus(boolean on,boolean darkmode);
```
即可，参数说明，on=是否开启沉浸式状态栏，darkmode=状态栏文字颜色模式是否为黑色
除此之外，需要在Activity的layout文件中加入
```
    <LinearLayout
        android:id="@+id/sys_statusBar"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:visibility="gone">
    </LinearLayout>
```
此布局是用于状态栏透明时透明部分的占位布局，因为Activity主布局在透明状态栏时需要向下位移一段位置，这段位置就是用此LinearLayout占位的。个人建议创建一个Layout，below此布局即可。
在添加此布局后需要调用如下代码使占位布局生效：
```
//如果sys_statusBar的父布局是RelativeLayout则调用
setStatusBarHeight();
//如果sys_statusBar的父布局是LinearLayout则调用
setStatusBarHeightByLayout(sysStatusBar);
```
其次，在BaseActivity中我是用了“me”这个词代替了this，因为在代码中常用到需要使用this的地方在内部类中或是在监听器中，此时必须使用xxActivity.this十分麻烦，在此情况下直接使用me即可，例如：
```
btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(me,IntentActivity.class);
                startActivity(intent);
            }
        });
```
附带的小工具如下：
```
//简易吐司：
toast(Obj);
//简易Log打印日志：
log(Obj);
//软键盘开关：
setIMMStatus(boolean show, EditText editText);
//dip与像素px转换：
dip2px(Context context, float dpValue);
//像素px与dip转换：
dip2px(Context context, float dpValue);
```

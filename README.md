# BaseActivity
BaseActivity是一款适配布局的框架模块，能够提供沉浸式状态栏，可调用黑色状态栏模式，判断了小米MIUI和flyme系统独立的黑色状态栏样式可独立调用，并提供了一些简单的吐司和生成日志的方法的基础Activity类，可以直接将您项目中的Activity继成BaseActivity使用。
如何更加优雅的编写代码是BaseActivity存在的主要目的，也是本代码诞生的原因。

## 6.0.1 更新说明：
- 删除冗余代码；

- 删除Volley相关调用，若需要请直接使用BaseVolley( https://github.com/kongzue/BaseVolley )或BaseOkHttp( https://github.com/kongzue/BaseOkHttp )；

- SystemBarTintManager提供了import提示；

## 6.0更新说明：
- 删除冗余代码；

- 将BaseActivity继承改为AppCompatActivity，方便权限回调；

- 取消了initStyle();方法，改为更切合原生的沉浸式适配方式；

- 取消了sys_statusBar布局适配的必要性，采用更简单的方式适配沉浸式；

- 整理代码逻辑；
### 约束
1) 6.0版本的BaseActivity为了更快更有效的完成Activity创建，因此特定的编写了一些自动化执行方法，如果开发者遵循我们的编写流程和方案，可以快速完成各Android系统UI布局的适配操作以界面的沉浸式。

    一般Activity需要完成的事项主要由以下及部分组成：

    加载布局、处理适配问题、加载数据、控件组件绑定事件

    对应的，我们在BaseActivity中提供了相对应的方法initViews()、initDatas()、setEvent()，用户继承对应的方法并重写其代码，BaseActivity会自动依次执行并对适配相关的事情进行处理。

2) 为方便适配沉浸式，请在Activity的layout布局的内容部分使用 android:fitsSystemWindows="true" 加以约束，此时内容布局会自动缩小为顶部状态栏及底部导航栏以内的位置：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/box_body"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.kongzue.baseframe.MainActivity">

    <!--背景部分-->
        
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        android:orientation="horizontal">

        <!--内容部分-->

    </LinearLayout>
</LinearLayout>
```

### 开始
现在，请跟着我的慢动作（划掉），一起来加速编写一个Activity！

首先，你需要创建一个默认的Activity如下：

```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```
接下来：

1) 先删除继承的AppCompatActivity，转而继承我们的BaseActivity，

2) 在onCreate方法中使用super.onCreate(Bundle,layoutRes)，

3) 重写三个方法initViews()、initDatas()、setEvent()，此处重写三个方法的原因是这些方法会依次自动被执行，我们保留了super.onCreate(Bundle)的原始方法，并提供了新的两个参数的onCreate方法，若使用此方法加载布局，则会直接依次执行initViews()、initDatas()和setEvent()。

默认会对状态栏进行的是白色主题的适配，如有黑色主题的需要，请单独执行setTranslucentStatus(true, true)方法即可。

点击Android Studio的Code -> Override Methods... 菜单，重写上述方法，并设置super.onCreate(savedInstanceState,R.layout.activity_main)加载布局：

```
public class MainActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState,R.layout.activity_main);
    }

    @Override
    public void initViews() {
        //此处加载组件
     
    }

    @Override
    public void initDatas() {
        //此处编写初始化代码
        
    }

    @Override
    public void setEvent() {
        //此处为组件绑定事件
        
    }
}

```
BaceActivity6.0版本旨在帮助开发者明晰代码结构与布局，轻松完成适配工作，同样的，也提供了更为完善的小工具，例如简易吐司、简易Log日志、控制键盘开启关闭、dpi与px双向计算转换、Android6.0以上权限申请、属性动画。

### 小工具调用方法说明
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
//申请权限（0x0001是返回值，需要的可以重写onRequestPermissionsResult做回调处理）：
requestPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN}, 0x0001);
//属性动画：
moveAnimation(Object obj, String perference, float aimValue, long time, long delay);
```

5.0版本说明：https://www.jianshu.com/p/ea92b765a946

4.0版本说明：http://www.jianshu.com/p/3905683c5df1

## 开源协议
```
   Copyright Kongzue BaseActivity

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

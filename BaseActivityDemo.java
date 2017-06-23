import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseActivityDemo extends BaseActivity {

    private LinearLayout boxBody;
    private LinearLayout sysStatusBar;
    private TextView txtTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState,R.layout.activity_main);
    }

    @Override
    public void initViews() {
        //此处加载组件
        boxBody = (LinearLayout) findViewById(R.id.box_body);
        sysStatusBar = (LinearLayout) findViewById(R.id.sys_statusBar);
        txtTitle = (TextView) findViewById(R.id.txt_title);
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

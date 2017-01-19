package com.xiong.tbs_x5.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.xiong.tbs_x5.R;
import com.xiong.tbs_x5.adapter.ShortcutAdapter;
import com.xiong.tbs_x5.model.Shortcut;
import com.xiong.tbs_x5.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongwenwei@aliyun.com
 * CreateTime: 2017/1/19
 * Note:
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText etKeyWord;//搜索关键字
    private TextView tvSearch;//百度一下
    private GridView gvShortcut;//快捷方式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initGridView();
    }

    private void initView() {
        etKeyWord = (EditText) findViewById(R.id.etKeyWord);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        gvShortcut = (GridView) findViewById(R.id.gvShortcut);
        tvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSearch://百度一下
                String keyWord = etKeyWord.getText().toString();
                if (TextUtils.isEmpty(keyWord)) {
                    ToastUtil.show("搜索或输入网址");
                    return;
                }
                WebActivity.gotoActivity("https://m.baidu.com/s?word=" + keyWord);
                etKeyWord.setText("");
                break;
        }
    }

    private void initGridView() {
        List<Shortcut> shortcuts = new ArrayList<>();
        int[] resId = {R.drawable.icon_github, R.drawable.icon_qq, R.drawable.icon_qqlive, R.drawable.icon_taimeiti, R.drawable.icon_36kr};
        String[] name = {"Github", "腾讯网", "腾讯视频", "钛媒体", "网址导航"};
        String[] url = {"http://github.com", "http://3g.qq.com", "http://m.v.qq.com", "http://m.tmtpost.com", "http://m.site.baidu.com/"};
        for (int i = 0; i < resId.length; i++) {
            Shortcut shortcut = new Shortcut();
            shortcut.setResId(resId[i]);
            shortcut.setName(name[i]);
            shortcut.setUrl(url[i]);
            shortcuts.add(shortcut);
        }
        ShortcutAdapter shortcutAdapter = new ShortcutAdapter(this, shortcuts);
        gvShortcut.setAdapter(shortcutAdapter);
    }
}


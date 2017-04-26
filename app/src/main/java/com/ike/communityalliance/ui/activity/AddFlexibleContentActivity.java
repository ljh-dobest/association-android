package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.SendShareFriendsGVAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.FlexiableContentBean;
import com.ike.communityalliance.wedget.DemoGridView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFlexibleContentActivity extends BaseActivity {
@BindView(R.id.ll_activity_content_back)
    LinearLayout ll_activity_content_back;
    @BindView(R.id.tv_activity_content_complie)
    TextView tv_activity_content_complie;
    @BindView(R.id.et_activity_create_content)
    EditText et_activity_create_content;
    @BindView(R.id.gv_activity_create_images)
    DemoGridView gv_activity_create_images;
    private SendShareFriendsGVAdapter adapter;
    private List<String> filePaths;
    private FlexiableContentBean flexiableContentBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flexible_content);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        adapter=new SendShareFriendsGVAdapter(mContext);
        gv_activity_create_images.setAdapter(adapter);
    }
    /**
     * 获取选取的图片文件
     * @return
     */
    private Map<String,File> initFiles() {
        Map<String,File> map=new HashMap<>();
        filePaths = adapter.getImaList();
        for (int i = 0; i < filePaths.size(); i++) {
            File file=new File(filePaths.get(i));
            String fileName=file.getName();
            map.put(fileName,file);
        }
        return map;
    }
    @OnClick({R.id.ll_activity_content_back,R.id.tv_activity_content_complie})
    public void activityContentOnclick(View view){
        switch (view.getId()) {
            case R.id.ll_activity_content_back:
                finish();
                break;
            case R.id.tv_activity_content_complie:
                String content=et_activity_create_content.getText().toString();
                Map<String,File> files=initFiles();
                flexiableContentBean=new FlexiableContentBean(content,files);
                Intent intent=new Intent();
                intent.putExtra("flexible",flexiableContentBean);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}

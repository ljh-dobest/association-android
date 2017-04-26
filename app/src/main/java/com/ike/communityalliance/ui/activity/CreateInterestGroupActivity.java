package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.wedget.MultiLineRadioGroup;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateInterestGroupActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, MultiLineRadioGroup.OnCheckedChangedListener {
@BindView(R.id.ll_create_interest_back)
    LinearLayout ll_create_interest_back;
    @BindView(R.id.tv_create_intreset_next)
    TextView tv_create_intreset_next;
     @BindView(R.id.mrg_interest_create)
    MultiLineRadioGroup mrg_interest_create;
//    @BindView(R.id.rg_interest_type1)
//    RadioGroup rg_interest_type1;
//    @BindView(R.id.rg_interest_type2)
//    RadioGroup rg_interest_type2;
//    @BindView(R.id.rg_interest_type3)
//    RadioGroup rg_interest_type3;
//    @BindView(R.id.rg_interest_type4)
//    RadioGroup rg_interest_type4;
//    @BindView(R.id.rg_interest_type5)
//    RadioGroup rg_interest_type5;
//    @BindView(R.id.ll_rg_interest_type)
//    RadioGroup ll_rg_interest_type;
    private List<FriendInfo> interestGroupMember;
    private int curHobbyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_interest_group);
        ButterKnife.bind(this);
        interestGroupMember=(List<FriendInfo>) getIntent().getSerializableExtra("interestGroupMember");
        initView();
    }

    private void initView() {
        mrg_interest_create.setOnCheckChangedListener(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        for (int i = 0; i < ll_rg_interest_type.getChildCount(); i++) {
//            RadioGroup rg = (RadioGroup) ll_rg_interest_type.getChildAt(i);
//            for (int j= 0; j < rg.getChildCount(); j++) { //j从第一个开始，跳过Textview
//                RadioButton rb= (RadioButton) rg.getChildAt(j);
//                rb.setChecked(false);
//            }
//        }
    }
    @OnClick({R.id.ll_create_interest_back,R.id.tv_create_intreset_next})
    public void onCreateInteresGroupViewClick(View view){
        switch (view.getId()) {
            case R.id.ll_create_interest_back:
                finish();
                break;
            case R.id.tv_create_intreset_next:
               if(curHobbyId!=0&&interestGroupMember!=null){
                   Intent intent = new Intent(this,CreateGroupActivity.class);
                   intent.putExtra("isCreateInterestGroup",true);
                   intent.putExtra("hobbyId",curHobbyId+"");
                   intent.putExtra("GroupMember",(Serializable) interestGroupMember);
                   startActivity(intent);
                   finish();
               }else {
                   T.showShort(this,"请选择一种类型");
               }
                break;
        }
    }
    @Override
    public void onItemChecked(MultiLineRadioGroup group, int position, boolean checked) {
       int[] item=mrg_interest_create.getCheckedItems();
        for (int i = 0; i < item.length; i++) {
            curHobbyId=item[i]+1;
        }
    }
}

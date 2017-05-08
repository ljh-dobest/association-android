package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.constant.Const;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.DateBean;
import com.ike.communityalliance.bean.EventDecorator;
import com.ike.communityalliance.bean.SameDayDecorator;
import com.ike.communityalliance.interfaces.ISignPickerView;
import com.ike.communityalliance.presenter.SignPickerPresenter;
import com.ike.communityalliance.utils.DateUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignPickerActivity extends BaseMvpActivity<ISignPickerView,SignPickerPresenter> implements ISignPickerView {
    public static int year=0;
    public static int month=0;
    public static int day=0;
   @BindView(R.id.my_datepicker)
   MaterialCalendarView picker;
    @BindView(R.id.btn_signpicker_sign)
    Button btn_signpicker_sign;
    @BindView(R.id.tv_signpicker_back)
    TextView tv_signpicker_back;
    private List<Date> tmp=new ArrayList<>();
    private Calendar cal;
    private String text="连续签到3天,奖励3贡献值";
    private SharedPreferences sp;
    private String userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_picker);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        userId=sp.getString(Const.LOGIN_ID,"");
        cal = Calendar.getInstance();
        year= cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day= cal.get(Calendar.DATE);
        presenter.getSignPickerData(userId);
    }

    private void initDate() {
        //设置当前时间
        picker.setCurrentDate(cal);
        picker.state()
                .edit()
                //设置一周的第一天是周日还是周一
                .setFirstDayOfWeek(Calendar.SUNDAY)
                //设置日期范围
                .setMinimumDate(CalendarDay.from(1900,1,1))
                .setMaximumDate(CalendarDay.from(9999,12,10))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        //设置周的文本
        picker.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});
        //设置年月的title
        picker.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                StringBuffer buffer = new StringBuffer();
                int yearOne = day.getYear();
                int monthOne = day.getMonth() + 1;
                buffer.append(yearOne).append("年").append(monthOne).append("月");
                return buffer;
            }
        });
          picker.addDecorators(new SameDayDecorator(),new EventDecorator(tmp));
       picker.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
        picker.setTileHeightDp(45);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public SignPickerPresenter initPresenter() {
        return new SignPickerPresenter();
    }
    
    @OnClick({R.id.btn_signpicker_sign,R.id.tv_signpicker_back})
    public void onSignViewClick(View v) {
        switch(v.getId()) {
            case R.id.btn_signpicker_sign:
                signIn();
                setWasSigned();
                break;
            case R.id.tv_signpicker_back:
                    finish();
                break;
        }
    }

    private void signIn() {
        presenter.sign(userId);
    }

    private void setWasSigned() {
        btn_signpicker_sign.setClickable(false);
        btn_signpicker_sign.setText("已签到");
        btn_signpicker_sign.setBackgroundColor(0x99999999);
    }

    @Override
    public void setSingPickerData(List<DateBean> data) {
        String dateStr = DateUtils.currentTime();
        Date parse = DateUtils.parseDate(dateStr,"yyyy-MM-dd");
        for (int i = 0; i < data.size(); i++) {
            String str=data.get(i).getDate();
           Date date = DateUtils.parseDate(str,"yyyy-MM-dd");
            if(date.equals(parse)){
                setWasSigned();
            }
            tmp.add(date);
        }
        initDate();
    }


    @Override
    public void showErrorString(String errorString) {
        T.showShort(this,errorString);
    }

    @Override
    public void succeedToSign(String msg) {
        showComfirmDialog(msg);
    }


    public void showComfirmDialog(String msg) {
        final AlertDialog ComfirmDialog = new AlertDialog.Builder(this,R.style.DialogStyle).create();
        ComfirmDialog.show();
        Window window = ComfirmDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setContentView(R.layout.comfirm_dialog_layout);
        Button btn_comfirm_dialog_comfirm = (Button) window.findViewById(R.id.btn_comfirm_dialog_comfirm);
        TextView tv_comfirm_dialog_title1= (TextView) window.findViewById(R.id.tv_comfirm_dialog_title1);
        TextView tv_comfirm_dialog_title2= (TextView) window.findViewById(R.id.tv_comfirm_dialog_title2);
        tv_comfirm_dialog_title1.setText(msg);
        tv_comfirm_dialog_title2.setVisibility(View.GONE);
        ImageView iv_comfirm_dialog_cancel= (ImageView) window.findViewById(R.id.iv_comfirm_dialog_cancel);
        btn_comfirm_dialog_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComfirmDialog.dismiss();
            }
        });
        iv_comfirm_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComfirmDialog.dismiss();
            }
        });
    }
}

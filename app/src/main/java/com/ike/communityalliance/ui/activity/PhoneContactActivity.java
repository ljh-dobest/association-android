package com.ike.communityalliance.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.ContastsInfo;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneContactActivity extends BaseActivity {
    @BindView(R.id.ll_phone_contasts_back)
    AutoLinearLayout llPhoneContastsBack;
    @BindView(R.id.rv_phone_contacts)
    RecyclerView rvPhoneContacts;
    private List<ContastsInfo> list = new ArrayList<ContastsInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_contact);
        ButterKnife.bind(this);
        InitPermisson();
        getContasts();
        initRv();
    }

    private void InitPermisson() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    1);
        }
    }

    private void getContasts() {
        try {
            Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
            String contactName;
            String contactNumber;
            while (cursor.moveToNext()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                ContastsInfo contactsInfo = new ContastsInfo(contactName, contactNumber);
                if (contactName != null)
                    list.add(contactsInfo);
            }
            cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initRv() {

    }

    @OnClick(R.id.ll_phone_contasts_back)
    public void onViewClicked() {
        finish();
    }
}

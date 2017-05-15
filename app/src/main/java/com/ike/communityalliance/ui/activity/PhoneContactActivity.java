package com.ike.communityalliance.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.PhoneContactRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.ContastsInfo;
import com.ike.communityalliance.wedget.ItemDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneContactActivity extends BaseActivity implements PhoneContactRvAdapter.OnItemClickLitener {
    @BindView(R.id.ll_phone_contasts_back)
    LinearLayout llPhoneContastsBack;
    @BindView(R.id.rv_phone_contacts)
    RecyclerView rvPhoneContacts;
    private List<ContastsInfo> list = new ArrayList<ContastsInfo>();
    private PhoneContactRvAdapter adapter;
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
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, 1);
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
               String myContactNumber=contactNumber.replace("+","").replace(" ","");
                ContastsInfo contactsInfo = new ContastsInfo(contactName, myContactNumber);
                if (contactName != null)
                    list.add(contactsInfo);
            }
            cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initRv() {
        adapter=new PhoneContactRvAdapter(this);
        adapter.setmDatas(list);
        adapter.setOnItemClickLitener(this);
        rvPhoneContacts.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvPhoneContacts.setLayoutManager(lm);
        rvPhoneContacts.setHasFixedSize(true);
        rvPhoneContacts.addItemDecoration(new ItemDivider(mContext, ItemDivider.VERTICAL_LIST));
    }

    @OnClick(R.id.ll_phone_contasts_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(View view, int position) {
        ContastsInfo contastsInfo=list.get(position);
        Intent intent=new Intent(this,UserDetailActivity.class);
        intent.putExtra("contastsInfo",contastsInfo);
        intent.putExtra("isPhoneContact",true);
        startActivity(intent);
    }
}

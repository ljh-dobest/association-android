package com.ike.communityalliance.message.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.ConversationActivity;
import com.ike.communityalliance.ui.activity.GroupFlexibleActivity;
import com.ike.communityalliance.constant.Const;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Min on 2016/12/21.
 */

public class GroupFlexiblePlugin implements IPluginModule {
    private Context context;

    /**
     * 初始化实例
     *
     * @param context 上下文
     */
    public void init(Context context) {
        this.context=context;
    }

    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context,R.mipmap.group_fiexible);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.group_flexible);
    }

    @Override
    public void onClick(Fragment currentFragment, final RongExtension extension) {
                  Intent intent=new Intent(context, GroupFlexibleActivity.class);
        intent.putExtra("actCreator", context.getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, ""));
        intent.putExtra("groupId", ConversationActivity.mTargetId);
            context.startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}

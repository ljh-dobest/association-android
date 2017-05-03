package com.ike.communityalliance.message.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.ConversationActivity;
import com.ike.communityalliance.ui.activity.GroupVoteActivity;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by Min on 2016/12/21.
 */

public class GroupVotePlugin implements IPluginModule {
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
        return ContextCompat.getDrawable(context,R.mipmap.group_vote);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.vote);
    }

    @Override
    public void onClick(Fragment currentFragment, final RongExtension extension) {
        Intent intent4 = new Intent(context, GroupVoteActivity.class);
               intent4.putExtra("conversationType", Conversation.ConversationType.GROUP.getValue());
               intent4.putExtra("group_id", ConversationActivity.mTargetId);
                context.startActivity(intent4);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}

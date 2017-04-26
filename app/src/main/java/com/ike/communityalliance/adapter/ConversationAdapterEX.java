package com.ike.communityalliance.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.adapter.MessageListAdapter;

/**
 * Created by Min on 2017/3/18.
 */

public class ConversationAdapterEX extends MessageListAdapter{
    public ConversationAdapterEX(Context context) {
        super(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {


        return super.newView(context, position, group);
    }

    @Override
    public void bindView(View v,int position,UIMessage data){
    }


}

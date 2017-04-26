package com.ike.communityalliance.message.provider;

import android.content.Context;
import android.net.Uri;

import com.ike.communityalliance.bean.Groups;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.GroupsDAOImpl;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Min on 2017/2/27.
 */

public class MyGroupInfoProvider implements RongIM.GroupInfoProvider {
    private Context context;
    private static MyGroupInfoProvider imcontext;
    private List<Groups> groups;
    private GroupsDAOImpl groupsDAO;
    private String userId;

    public MyGroupInfoProvider(Context context) {
        this.context = context;
        groups=new ArrayList<Groups>();
        userId =context.getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
      groupsDAO=new GroupsDAOImpl(context);
        groups=groupsDAO.findAll(userId);
        initListener();
    }


    public static void init(Context context) {

        if (imcontext == null) {
            synchronized (MyGroupInfoProvider.class) {

                if (imcontext == null) {
                    imcontext = new MyGroupInfoProvider(context);
                }
            }
        }

    }

    private void initListener() {
        RongIM.setGroupInfoProvider(this, true);
    }

    @Override
    public Group getGroupInfo(String s) {
        if(groups!=null){
            for (Groups f:groups){
                if(f.getGroupId().equals(s)){
                    return new Group(f.getGroupId(), f.getGroupName(), Uri.parse(f.getGroupPortraitUrl()));
                }
            }
        }
        return null;
    }


}

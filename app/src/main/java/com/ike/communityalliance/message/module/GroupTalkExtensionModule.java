package com.ike.communityalliance.message.module;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.RongExtension;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by Min on 2016/12/21.
 */

public class GroupTalkExtensionModule extends DefaultExtensionModule {
    private MyStartRecognizePlugin myStartRecognizePlugin;
//    private MyEndRecognizePlugin myEndRecognizePlugin;
    private LocationPlugin locationPlugin;
    private GroupFlexiblePlugin groupFlexiblePlugin;
//    private TestImagePlugin testImagePlugin;
   private MyImagePlugin myImagePlugin;
    private MyFilePluginPlugin myFilePluginPlugin;
    private GroupVotePlugin groupVotePlugin;
    private VedioPlugin vedioPlugin;
    @Override
    public void onAttachedToExtension(RongExtension extension) {
//        testImagePlugin=new TestImagePlugin();
        myImagePlugin=new MyImagePlugin();
        myStartRecognizePlugin=new MyStartRecognizePlugin();
        locationPlugin = new LocationPlugin();
        groupFlexiblePlugin=new GroupFlexiblePlugin();
        groupVotePlugin=new GroupVotePlugin();
        myFilePluginPlugin=new MyFilePluginPlugin();
        vedioPlugin=new VedioPlugin();
        myStartRecognizePlugin.init(extension.getContext());
        locationPlugin.init(extension.getContext());
        groupFlexiblePlugin.init(extension.getContext());
        groupVotePlugin.init(extension.getContext());
        vedioPlugin.init(extension.getContext());
        //testImagePlugin.init(extension.getContext(),extension.getConversationType());
        super.onAttachedToExtension(extension);
    }

    @Override
    public void onDetachedFromExtension() {
        super.onDetachedFromExtension();
    }

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules =  new ArrayList<IPluginModule>();
        pluginModules.add(myImagePlugin);
//        pluginModules.add(testImagePlugin);
        pluginModules.add(vedioPlugin);
        pluginModules.add(myStartRecognizePlugin);
        pluginModules.add(locationPlugin);
        pluginModules.add(groupFlexiblePlugin);
        pluginModules.add(groupVotePlugin);
        pluginModules.add(myFilePluginPlugin);
        return pluginModules;

    }

    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}

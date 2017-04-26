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

public class TalkExtensionModule extends DefaultExtensionModule {
    private MyStartRecognizePlugin myStartRecognizePlugin;
    private LocationPlugin locationPlugin;
    private MyImagePlugin myImagePlugin;
//  private TestImagePlugin testImagePlugin;
    private MyFilePluginPlugin myFilePluginPlugin;
    private VedioPlugin vedioPlugin;
    @Override
    public void onAttachedToExtension(RongExtension extension) {
//        recognize = new RecognizePlugin();
        myStartRecognizePlugin=new MyStartRecognizePlugin();
//        myEndRecognizePlugin=new MyEndRecognizePlugin();
        locationPlugin = new LocationPlugin();
//        recognize.init(extension.getContext());
        myImagePlugin=new MyImagePlugin();
//        testImagePlugin=new TestImagePlugin();
        myFilePluginPlugin=new MyFilePluginPlugin();
        vedioPlugin=new VedioPlugin();
        myStartRecognizePlugin.init(extension.getContext());
//        myEndRecognizePlugin.init(extension.getContext());
        locationPlugin.init(extension.getContext());
        vedioPlugin.init(extension.getContext());
//       testImagePlugin.init(extension.getContext(),extension.getConversationType());
        super.onAttachedToExtension(extension);
    }

    @Override
    public void onDetachedFromExtension() {
        super.onDetachedFromExtension();
    }

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules =  new ArrayList<IPluginModule>();
//        pluginModules.add(recognize);
      pluginModules.add(myImagePlugin);
//        pluginModules.add(testImagePlugin);
        pluginModules.add(vedioPlugin);
        pluginModules.add(myStartRecognizePlugin);
//        pluginModules.add(myEndRecognizePlugin);
        pluginModules.add(locationPlugin);
        pluginModules.add(myFilePluginPlugin);
        return pluginModules;

    }

    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}

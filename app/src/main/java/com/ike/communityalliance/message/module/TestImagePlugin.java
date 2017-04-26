package com.ike.communityalliance.message.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;

/**
 * Created by Min on 2016/12/21.
 */

public class TestImagePlugin implements IPluginModule {
    private Context context;
    private Conversation.ConversationType type;
    private String path;

    /**
     * 初始化实例
     *
     * @param context 上下文
     */
    public void init(Context context, Conversation.ConversationType type)
    {
        this.context=context;
        this.type=type;
    }

    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.mipmap.picture);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.picture);
    }

    @Override
    public void onClick(Fragment currentFragment, final RongExtension extension) {
        //自定义方法的单选
        RxGalleryFinal
                .with(context)
                .image()
                .radio()
                .crop()
                .imageLoader(ImageLoaderType.PICASSO)
                .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        path = imageRadioResultEvent.getResult().getOriginalPath();
                        //发送图片消息
                        File imageFileSource = new File(path);
                        File imageFileThumb = new File(context.getCacheDir(), "thumb.jpg");
                 try {
                         // 读取图片。
                           InputStream is =new FileInputStream(imageFileSource);
                            Bitmap bmpSource = BitmapFactory.decodeStream(is);
                            // 创建缩略图变换矩阵。
                            Matrix m = new Matrix();
                            m.setRectToRect(new RectF(0, 0, bmpSource.getWidth(), bmpSource.getHeight()), new RectF(0, 0, 160, 160), Matrix.ScaleToFit.CENTER);
                            // 生成缩略图。
                            Bitmap bmpThumb = Bitmap.createBitmap(bmpSource, 0, 0, bmpSource.getWidth(), bmpSource.getHeight(), m, true);
                            imageFileThumb.createNewFile();
                            FileOutputStream fosThumb = new FileOutputStream(imageFileThumb);
                            // 保存缩略图。
                            bmpThumb.compress(Bitmap.CompressFormat.JPEG, 60, fosThumb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ImageMessage imgMsg = ImageMessage.obtain(Uri.fromFile(imageFileThumb), Uri.fromFile(imageFileSource));
                        RongIM.getInstance().sendImageMessage(type, extension.getTargetId(), imgMsg, "图片消息", "图片消息", new RongIMClient.SendImageMessageCallback() {

                            @Override
                            public void onAttached(Message message) {
                                //保存数据库成功
                            }
                            @Override
                            public void onError(Message message, RongIMClient.ErrorCode code) {
                                //发送失败
                                T.showShort(context,"发送失败:"+code.getValue());
                            }

                            @Override
                            public void onSuccess(Message message) {
                                //发送成功
                                T.showShort(context,"发送成功");
                            }

                            @Override
                            public void onProgress(Message message, int progress) {
                                //发送进度
                            }
                        });
                    }
                })
                .openGallery();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


}

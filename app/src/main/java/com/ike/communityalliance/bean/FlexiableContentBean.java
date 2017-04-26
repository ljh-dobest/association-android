package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Min on 2017/4/11.
 */

public class FlexiableContentBean implements Parcelable {
    private String content;
    private Map<String,File> files;

    public FlexiableContentBean(String content, Map<String, File> files) {
        this.content = content;
        this.files = files;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeInt(this.files.size());
        for (Map.Entry<String, File> entry : this.files.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeSerializable(entry.getValue());
        }
    }

    protected FlexiableContentBean(Parcel in) {
        this.content = in.readString();
        int filesSize = in.readInt();
        this.files = new HashMap<String, File>(filesSize);
        for (int i = 0; i < filesSize; i++) {
            String key = in.readString();
            File value = (File) in.readSerializable();
            this.files.put(key, value);
        }
    }

    public static final Parcelable.Creator<FlexiableContentBean> CREATOR = new Parcelable.Creator<FlexiableContentBean>() {
        @Override
        public FlexiableContentBean createFromParcel(Parcel source) {
            return new FlexiableContentBean(source);
        }

        @Override
        public FlexiableContentBean[] newArray(int size) {
            return new FlexiableContentBean[size];
        }
    };
}

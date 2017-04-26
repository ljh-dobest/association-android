package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2016/12/23.
 */

public class Image implements Parcelable {
 private String userPortraitUrl;

    public Image(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userPortraitUrl);
    }

    protected Image(Parcel in) {
        this.userPortraitUrl = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}

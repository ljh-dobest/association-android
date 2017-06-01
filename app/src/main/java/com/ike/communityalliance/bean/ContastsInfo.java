package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

import static io.rong.imlib.statistics.UserData.name;

/**
 * Created by Min on 2017/5/12.
 */

public class ContastsInfo implements Parcelable {
    private String nickname;
    private String mobile;

    public ContastsInfo(String nickname, String mobile) {
        this.nickname = nickname;
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nickname);
        dest.writeString(this.mobile);
    }

    protected ContastsInfo(Parcel in) {
        this.nickname = in.readString();
        this.mobile = in.readString();
    }

    public static final Creator<ContastsInfo> CREATOR = new Creator<ContastsInfo>() {
        @Override
        public ContastsInfo createFromParcel(Parcel source) {
            return new ContastsInfo(source);
        }

        @Override
        public ContastsInfo[] newArray(int size) {
            return new ContastsInfo[size];
        }
    };
}

package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2017/5/12.
 */

public class ContastsInfo implements Parcelable {
    private String name;
    private String number;


    public ContastsInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.number);
    }

    protected ContastsInfo(Parcel in) {
        this.name = in.readString();
        this.number = in.readString();
    }

    public static final Parcelable.Creator<ContastsInfo> CREATOR = new Parcelable.Creator<ContastsInfo>() {
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

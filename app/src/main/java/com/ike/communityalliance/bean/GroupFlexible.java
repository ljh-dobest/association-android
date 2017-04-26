package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min on 2016/12/6.
 */

public class GroupFlexible implements Parcelable {
    private String activesId;
    private String activesTitle;
    private String activesImage;
    private String activesLimit;
    private String activesStart;
    private String activesEnd;
    private String activesAddress;
    private String activesContent;
    private String activesClosing;
    private String publisher;
    private String joinUserNumber;
    private List<String> activesImages;
    private List<JoinUserBean> joinUsers;

    public GroupFlexible(String activesId, String activesTitle, String activesImage, String activesLimit, String activesStart, String activesEnd, String activesAddress, String activesContent, String activesClosing, String publisher, String joinUserNumber, List<String> activesImages, List<JoinUserBean> joinUsers) {
        this.activesId = activesId;
        this.activesTitle = activesTitle;
        this.activesImage = activesImage;
        this.activesLimit = activesLimit;
        this.activesStart = activesStart;
        this.activesEnd = activesEnd;
        this.activesAddress = activesAddress;
        this.activesContent = activesContent;
        this.activesClosing = activesClosing;
        this.publisher = publisher;
        this.joinUserNumber = joinUserNumber;
        this.activesImages = activesImages;
        this.joinUsers = joinUsers;
    }

    public String getActivesId() {
        return activesId;
    }

    public void setActivesId(String activesId) {
        this.activesId = activesId;
    }

    public String getActivesTitle() {
        return activesTitle;
    }

    public void setActivesTitle(String activesTitle) {
        this.activesTitle = activesTitle;
    }

    public String getActivesImage() {
        return activesImage;
    }

    public void setActivesImage(String activesImage) {
        this.activesImage = activesImage;
    }

    public String getActivesLimit() {
        return activesLimit;
    }

    public void setActivesLimit(String activesLimit) {
        this.activesLimit = activesLimit;
    }

    public String getActivesStart() {
        return activesStart;
    }

    public void setActivesStart(String activesStart) {
        this.activesStart = activesStart;
    }

    public String getActivesEnd() {
        return activesEnd;
    }

    public void setActivesEnd(String activesEnd) {
        this.activesEnd = activesEnd;
    }

    public String getActivesAddress() {
        return activesAddress;
    }

    public void setActivesAddress(String activesAddress) {
        this.activesAddress = activesAddress;
    }

    public String getActivesContent() {
        return activesContent;
    }

    public void setActivesContent(String activesContent) {
        this.activesContent = activesContent;
    }

    public String getActivesClosing() {
        return activesClosing;
    }

    public void setActivesClosing(String activesClosing) {
        this.activesClosing = activesClosing;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getJoinUserNumber() {
        return joinUserNumber;
    }

    public void setJoinUserNumber(String joinUserNumber) {
        this.joinUserNumber = joinUserNumber;
    }

    public List<String> getActivesImages() {
        return activesImages;
    }

    public void setActivesImages(List<String> activesImages) {
        this.activesImages = activesImages;
    }

    public List<JoinUserBean> getJoinUsers() {
        return joinUsers;
    }

    public void setJoinUsers(List<JoinUserBean> joinUsers) {
        this.joinUsers = joinUsers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.activesId);
        dest.writeString(this.activesTitle);
        dest.writeString(this.activesImage);
        dest.writeString(this.activesLimit);
        dest.writeString(this.activesStart);
        dest.writeString(this.activesEnd);
        dest.writeString(this.activesAddress);
        dest.writeString(this.activesContent);
        dest.writeString(this.activesClosing);
        dest.writeString(this.publisher);
        dest.writeString(this.joinUserNumber);
        dest.writeStringList(this.activesImages);
        dest.writeList(this.joinUsers);
    }

    protected GroupFlexible(Parcel in) {
        this.activesId = in.readString();
        this.activesTitle = in.readString();
        this.activesImage = in.readString();
        this.activesLimit = in.readString();
        this.activesStart = in.readString();
        this.activesEnd = in.readString();
        this.activesAddress = in.readString();
        this.activesContent = in.readString();
        this.activesClosing = in.readString();
        this.publisher = in.readString();
        this.joinUserNumber = in.readString();
        this.activesImages = in.createStringArrayList();
        this.joinUsers = new ArrayList<JoinUserBean>();
        in.readList(this.joinUsers, JoinUserBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<GroupFlexible> CREATOR = new Parcelable.Creator<GroupFlexible>() {
        @Override
        public GroupFlexible createFromParcel(Parcel source) {
            return new GroupFlexible(source);
        }

        @Override
        public GroupFlexible[] newArray(int size) {
            return new GroupFlexible[size];
        }
    };
}

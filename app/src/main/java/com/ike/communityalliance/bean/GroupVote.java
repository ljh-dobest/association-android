package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min on 2016/12/23.
 */

public class GroupVote implements Parcelable {
    private String userId;
    private String nickname;
    private String userPortraitUrl;
    private String voteId;
    private String voteTitle;
    private String endTime;
    private int status;
    private String voteImage;
    private int mode;
    private int timeStatus;
    private String joinUsersNumber;
    private List<OptionBean> option;
    private List<JoinUsers> joinUsers;

    public GroupVote(String userId, String nickname, String userPortraitUrl, String voteId, String voteTitle, String endTime, int status, String voteImage, int mode, int timeStatus, String joinUsersNumber, List<OptionBean> option, List<JoinUsers> joinUsers) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.voteId = voteId;
        this.voteTitle = voteTitle;
        this.endTime = endTime;
        this.status = status;
        this.voteImage = voteImage;
        this.mode = mode;
        this.timeStatus = timeStatus;
        this.joinUsersNumber = joinUsersNumber;
        this.option = option;
        this.joinUsers = joinUsers;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVoteImage() {
        return voteImage;
    }

    public void setVoteImage(String voteImage) {
        this.voteImage = voteImage;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(int timeStatus) {
        this.timeStatus = timeStatus;
    }

    public String getJoinUsersNumber() {
        return joinUsersNumber;
    }

    public void setJoinUsersNumber(String joinUsersNumber) {
        this.joinUsersNumber = joinUsersNumber;
    }

    public List<OptionBean> getOption() {
        return option;
    }

    public void setOption(List<OptionBean> option) {
        this.option = option;
    }

    public List<JoinUsers> getJoinUsers() {
        return joinUsers;
    }

    public void setJoinUsers(List<JoinUsers> joinUsers) {
        this.joinUsers = joinUsers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.nickname);
        dest.writeString(this.userPortraitUrl);
        dest.writeString(this.voteId);
        dest.writeString(this.voteTitle);
        dest.writeString(this.endTime);
        dest.writeInt(this.status);
        dest.writeString(this.voteImage);
        dest.writeInt(this.mode);
        dest.writeInt(this.timeStatus);
        dest.writeString(this.joinUsersNumber);
        dest.writeList(this.option);
        dest.writeList(this.joinUsers);
    }

    protected GroupVote(Parcel in) {
        this.userId = in.readString();
        this.nickname = in.readString();
        this.userPortraitUrl = in.readString();
        this.voteId = in.readString();
        this.voteTitle = in.readString();
        this.endTime = in.readString();
        this.status = in.readInt();
        this.voteImage = in.readString();
        this.mode = in.readInt();
        this.timeStatus = in.readInt();
        this.joinUsersNumber = in.readString();
        this.option = new ArrayList<OptionBean>();
        in.readList(this.option, OptionBean.class.getClassLoader());
        this.joinUsers = new ArrayList<JoinUsers>();
        in.readList(this.joinUsers, JoinUsers.class.getClassLoader());
    }

    public static final Parcelable.Creator<GroupVote> CREATOR = new Parcelable.Creator<GroupVote>() {
        @Override
        public GroupVote createFromParcel(Parcel source) {
            return new GroupVote(source);
        }

        @Override
        public GroupVote[] newArray(int size) {
            return new GroupVote[size];
        }
    };
}

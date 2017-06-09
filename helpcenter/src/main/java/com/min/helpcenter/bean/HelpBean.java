package com.min.helpcenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Min on 2017/3/28.
 */

public class HelpBean implements Serializable{

    private static final long serialVersionUID = 2L;
    private String userId;
    private String nickname;
    private String userPortraitUrl;
    private String id;
    private String title;
    private String content;
    private String time;
    private int contributionCoin;
    private int helpNumber;
    private String likes;
    private String likesStatus;
    private AnswersBean adopt;
    private String commentNumber;
    private List<AnswersBean> answers;

    public HelpBean(String userId, String nickname, String userPortraitUrl, String id, String title, String content, String time, int contributionCoin, int helpNumber) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.contributionCoin = contributionCoin;
        this.helpNumber = helpNumber;
    }

    public HelpBean(String userId, String nickname, String userPortraitUrl, String title, String content, String time, List<AnswersBean> answers) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.title = title;
        this.content = content;
        this.time = time;
        this.answers = answers;
    }

    public HelpBean(String userId, String nickname, String userPortraitUrl, String id, String title, String content, String time, int contributionCoin, int helpNumber, String likes, String likesStatus, AnswersBean adopt, String commentNumber, List<AnswersBean> answers) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.contributionCoin = contributionCoin;
        this.helpNumber = helpNumber;
        this.likes = likes;
        this.likesStatus = likesStatus;
        this.adopt = adopt;
        this.commentNumber = commentNumber;
        this.answers = answers;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public AnswersBean getAdopt() {
        return adopt;
    }

    public void setAdopt(AnswersBean adopt) {
        this.adopt = adopt;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLikesStatus() {
        return likesStatus;
    }

    public void setLikesStatus(String likesStatus) {
        this.likesStatus = likesStatus;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getContributionCoin() {
        return contributionCoin;
    }

    public void setContributionCoin(int contributionCoin) {
        this.contributionCoin = contributionCoin;
    }

    public int getHelpNumber() {
        return helpNumber;
    }

    public void setHelpNumber(int helpNumber) {
        this.helpNumber = helpNumber;
    }
}

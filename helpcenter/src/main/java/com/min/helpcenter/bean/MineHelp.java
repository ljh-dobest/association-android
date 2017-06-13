package com.min.helpcenter.bean;

/**
 * Created by Min on 2017/4/1.
 */

public class MineHelp {
    private String id;
    private String title;
    private String content;
    private String contributionCoin;
    private String answers;
    private String time;

    public MineHelp(String id, String title, String content, String contributionCoin, String answers, String time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.contributionCoin = contributionCoin;
        this.answers = answers;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getContributionCoin() {
        return contributionCoin;
    }

    public void setContributionCoin(String contributionCoin) {
        this.contributionCoin = contributionCoin;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}

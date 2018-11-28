package com.java.polling.PollingWeb.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ChoicesVO{

    private String url;
    private String choice;
    private int votes;

    public ChoicesVO() {
    }

    public ChoicesVO(String url, String choice, int votes) {
        this.url = url;
        this.choice = choice;
        this.votes = votes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Choices{" +
                "url='" + url + '\'' +
                ", choice='" + choice + '\'' +
                ", votes=" + votes +
                '}';
    }
}

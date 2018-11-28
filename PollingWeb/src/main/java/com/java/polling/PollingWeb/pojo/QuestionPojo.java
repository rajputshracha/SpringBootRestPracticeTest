package com.java.polling.PollingWeb.pojo;

import com.java.polling.PollingWeb.entity.PollChoices;
import java.util.Date;
import java.util.Set;

public class QuestionPojo {

    private String question;
    private Date published_at;
    private Set<PollChoices> choices;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Date published_at) {
        this.published_at = published_at;
    }

    public Set<PollChoices> getChoices() {
        return choices;
    }

    public void setChoices(Set<PollChoices> choices) {
        this.choices = choices;
    }

    public class choice{
        private String choice;
        private int votes;

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
    }
}

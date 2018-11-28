package com.java.polling.PollingWeb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PollChoices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quest_id;
   private String choice;
    private int votes;
    @ManyToOne
    @JoinColumn(name="ID", referencedColumnName="ID")
    private PollQuestion question;

    public PollChoices(String choice, int votes) {
        this.choice = choice;
        this.votes = votes;
    }

    public PollChoices(String choice) {
        this.choice = choice;
    }

    public PollChoices() {
    }

    public Long getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(Long quest_id) {
        this.quest_id = quest_id;
    }

    public PollQuestion getQuestion() {
        return question;
    }

    public void setQuestion(PollQuestion question) {
        this.question = question;
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
        return "PollChoices{" +
                "choice='" + choice + '\'' +
                ", votes=" + votes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PollChoices that = (PollChoices) o;
        return Objects.equals(quest_id, that.quest_id) &&
                Objects.equals(choice, that.choice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quest_id, choice);
    }
}

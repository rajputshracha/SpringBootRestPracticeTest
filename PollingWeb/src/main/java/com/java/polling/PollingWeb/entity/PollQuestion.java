package com.java.polling.PollingWeb.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class PollQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String question;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date published_at;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID")
    private Set<PollChoices> choices;



    public PollQuestion(String question, Set<PollChoices> choices) {
        this.question = question;
        this.choices = choices;
    }

    public PollQuestion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<PollChoices> getChoices() {
        return choices;
    }

    public void setChoices(Set<PollChoices> choices) {
        this.choices = choices;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Date published_at) {
        this.published_at = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PollQuestion that = (PollQuestion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question);
    }
}

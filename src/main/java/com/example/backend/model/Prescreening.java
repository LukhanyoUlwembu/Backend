package com.example.backend.model;


import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Prescreening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long preScreeningId;
    private String question;

    private Date created;

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    public long getPreScreeningId() {
        return this.preScreeningId;
    }

    public void setPreScreeningId(long preScreeningId) {
        this.preScreeningId = preScreeningId;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}

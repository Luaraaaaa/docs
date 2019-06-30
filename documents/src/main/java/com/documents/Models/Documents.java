package com.documents.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "documents")
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "number")
    private long number;

    @OneToOne
    @JoinColumn(name = "type")
    private Documents_type documentsType;

    @Column(name = "agency")
    private String agency;

    @Column(name = "issue_date")
    private String issue_date;

    @Column(name = "exp_date")
    private String exp_date;

    public Documents() {
    }

    public Documents(Users user_id, long number, Documents_type type, String agency, String issue_date, String exp_date) {
        this.user = user_id;
        this.number = number;
        this.documentsType = type;
        this.agency = agency;
        this.issue_date = issue_date;
        this.exp_date = exp_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user_id) {
        this.user = user_id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", user_id=" + user +
                ", number=" + number +
                ", type=" + documentsType +
                ", agency='" + agency + '\'' +
                ", issue_date=" + issue_date +
                ", exp_date=" + exp_date +
                '}';
    }

    public Documents_type getDocumentsType() {
        return documentsType;
    }

    public void setDocumentsType(Documents_type documentsType) {
        this.documentsType = documentsType;
    }
}

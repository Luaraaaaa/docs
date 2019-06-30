package com.documents.Models;

import javax.persistence.*;

@Entity
@Table(name = "documents_type")
public class Documents_type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Documents_type{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

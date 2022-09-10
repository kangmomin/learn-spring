package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item {

    private String author;
    private String isbm;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbm() {
        return isbm;
    }

    public void setIsbm(String isbm) {
        this.isbm = isbm;
    }
}

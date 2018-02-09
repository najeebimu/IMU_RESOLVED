package com.softorea.schoolsen.models;

import java.io.Serializable;

public class ProvisionFreeTextBookModel implements Serializable {

    private static final long serialVersionUID = 46543446;
    String formiD = "Null";
    String classNo = "Null";
    String subjectName = "Null";
    String bookDemand = "Null";
    String bookRecieved = "Null";
    String stdWithFTB = "Null";
    String surplusBooks = "Null";
    String booksReturnToOffice = "Null";

    public String getFormiD() {
        return formiD;
    }

    public void setFormiD(String formiD) {
        this.formiD = formiD;
    }


    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getBookDemand() {
        return bookDemand;
    }

    public void setBookDemand(String bookDemand) {
        this.bookDemand = bookDemand;
    }

    public String getBookRecieved() {
        return bookRecieved;
    }

    public void setBookRecieved(String bookRecieved) {
        this.bookRecieved = bookRecieved;
    }

    public String getStdWithFTB() {
        return stdWithFTB;
    }

    public void setStdWithFTB(String stdWithFTB) {
        this.stdWithFTB = stdWithFTB;
    }

    public String getSurplusBooks() {
        return surplusBooks;
    }

    public void setSurplusBooks(String surplusBooks) {
        this.surplusBooks = surplusBooks;
    }

    public String getBooksReturnToOffice() {
        return booksReturnToOffice;
    }

    public void setBooksReturnToOffice(String booksReturnToOffice) {
        this.booksReturnToOffice = booksReturnToOffice;
    }

}

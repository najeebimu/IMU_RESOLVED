package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsSanctionedPosts {
    private int id;
    private String emisCode;
    private String positioncode;
    private String designation;
    private String subject;
    private String BPS;
    private String specifyothers;
    private String noOfSanctionedPosts;

    public DetailsSanctionedPosts() {
    }

    public DetailsSanctionedPosts(String emisCode, String positioncode, String designation, String subject, String BPS, String specifyothers, String noOfSanctionedPosts) {
        this.emisCode = emisCode;
        this.positioncode = positioncode;
        this.designation = designation;
        this.subject = subject;
        this.BPS = BPS;
        this.specifyothers = specifyothers;
        this.noOfSanctionedPosts = noOfSanctionedPosts;
    }

    public DetailsSanctionedPosts(int id, String emisCode, String positioncode, String designation, String subject, String BPS, String specifyothers, String noOfSanctionedPosts) {
        this.id = id;
        this.emisCode = emisCode;
        this.positioncode = positioncode;
        this.designation = designation;
        this.subject = subject;
        this.BPS = BPS;
        this.specifyothers = specifyothers;
        this.noOfSanctionedPosts = noOfSanctionedPosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmisCode() {
        return emisCode;
    }

    public void setEmisCode(String emisCode) {
        this.emisCode = emisCode;
    }

    public String getPositioncode() {
        return positioncode;
    }

    public void setPositioncode(String positioncode) {
        this.positioncode = positioncode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBPS() {
        return BPS;
    }

    public void setBPS(String BPS) {
        this.BPS = BPS;
    }

    public String getSpecifyothers() {
        return specifyothers;
    }

    public void setSpecifyothers(String specifyothers) {
        this.specifyothers = specifyothers;
    }

    public String getNoOfSanctionedPosts() {
        return noOfSanctionedPosts;
    }

    public void setNoOfSanctionedPosts(String noOfSanctionedPosts) {
        this.noOfSanctionedPosts = noOfSanctionedPosts;
    }

}


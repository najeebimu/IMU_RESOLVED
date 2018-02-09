package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsSanctionedNonteachingPosts {
    private int id;
    private String emisCode;
    private String positioncode;
    private String designation;
    private String BPS;
    private String others;
    private String noOfSanctionedPosts;

    public DetailsSanctionedNonteachingPosts() {
    }

    public DetailsSanctionedNonteachingPosts(String emisCode, String positioncode, String designation, String BPS, String others, String noOfSanctionedPosts) {
        this.emisCode = emisCode;
        this.positioncode = positioncode;
        this.designation = designation;
        this.BPS = BPS;
        this.others = others;
        this.noOfSanctionedPosts = noOfSanctionedPosts;
    }

    public DetailsSanctionedNonteachingPosts(int id, String emisCode, String positioncode, String designation, String BPS, String others, String noOfSanctionedPosts) {
        this.id = id;
        this.emisCode = emisCode;
        this.positioncode = positioncode;
        this.designation = designation;
        this.BPS = BPS;
        this.others = others;
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

    public String getBPS() {
        return BPS;
    }

    public void setBPS(String BPS) {
        this.BPS = BPS;
    }

    public String getSpecifyothers() {
        return others;
    }

    public void setSpecifyothers(String others) {
        this.others = others;
    }

    public String getNoOfSanctionedPosts() {
        return noOfSanctionedPosts;
    }

    public void setNoOfSanctionedPosts(String noOfSanctionedPosts) {
        this.noOfSanctionedPosts = noOfSanctionedPosts;
    }

}


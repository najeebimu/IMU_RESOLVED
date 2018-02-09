package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class RosterDetails {
    private int id;
    private String emis;
    private String visit;
    private String school;
    private String formId;
    private String month;

    public RosterDetails() {
    }

    public RosterDetails(String emis, String visit, String school, String formId, String month) {
        this.emis = emis;
        this.visit = visit;
        this.school = school;
        this.formId = formId;
        this.month = month;
    }

    public RosterDetails(int id, String emis, String visit, String school, String formId,String month) {
        this.id = id;
        this.emis = emis;
        this.visit = visit;
        this.school = school;
        this.formId = formId;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmis() {
        return emis;
    }

    public void setEmis(String emis) {
        this.emis = emis;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

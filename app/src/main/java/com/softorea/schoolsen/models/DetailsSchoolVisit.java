package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsSchoolVisit {
    private int id;
    private String emisCode;
    private String visitdate;
    private String visitby;
    private String designationother;

    public DetailsSchoolVisit() {
    }

    public DetailsSchoolVisit(String emisCode, String visitdate, String visitby, String designationother) {
        this.emisCode = emisCode;
        this.visitdate = visitdate;
        this.visitby = visitby;
        this.designationother = designationother;
    }

    public DetailsSchoolVisit(int id, String emisCode, String visitdate, String visitby, String designationother) {
        this.id = id;
        this.emisCode = emisCode;
        this.visitdate = visitdate;
        this.visitby = visitby;
        this.designationother = designationother;
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

    public String getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(String visitdate) {
        this.visitdate = visitdate;
    }

    public String getVisitby() {
        return visitby;
    }

    public void setVisitby(String visitby) {
        this.visitby = visitby;
    }

    public String getDesignationother() {
        return designationother;
    }

    public void setDesignationother(String designationother) {
        this.designationother = designationother;
    }

}


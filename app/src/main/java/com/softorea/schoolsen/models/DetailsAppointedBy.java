package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsAppointedBy {
    private int id;
    private String emisCode;
    private String name;
    private String cnic;
    private String appointedby;
    private String appointedDate;

    public DetailsAppointedBy() {
    }

    public DetailsAppointedBy(String emisCode, String name, String cnic, String appointedby, String appointedDate) {
        this.emisCode = emisCode;
        this.name = name;
        this.cnic = cnic;
        this.appointedby = appointedby;
        this.appointedDate = appointedDate;
    }

    public DetailsAppointedBy(int id, String emisCode, String name, String cnic, String appointedby, String appointedDate) {
        this.id = id;
        this.emisCode = emisCode;
        this.name = name;
        this.cnic = cnic;
        this.appointedby = appointedby;
        this.appointedDate = appointedDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getAppointedby() {
        return appointedby;
    }

    public void setAppointedby(String appointedby) {
        this.appointedby = appointedby;
    }

    public String getAppointedDate() {
        return appointedDate;
    }

    public void setAppointedDate(String appointedDate) {
        this.appointedDate = appointedDate;
    }

}


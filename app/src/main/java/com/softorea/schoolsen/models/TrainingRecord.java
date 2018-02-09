package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class TrainingRecord {
    private int id;
    private String emisCode;
    private String name;
    private String cnic;
    private String title;
    private String year;
    private String duration;
    private String conductedby;
    private String attendedas;
    private int teacherid;

    public TrainingRecord() {
    }

    public TrainingRecord(String emisCode, String name, String cnic, String title, String year, String duration, String conductedby, String attendedas, int teacherid) {
        this.emisCode = emisCode;
        this.name = name;
        this.cnic = cnic;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.conductedby = conductedby;
        this.attendedas = attendedas;
        this.teacherid = teacherid;
    }

    public TrainingRecord(int id, String emisCode, String name, String cnic, String title, String year, String duration, String conductedby, String attendedas, int teacherid) {
        this.id = id;
        this.emisCode = emisCode;
        this.name = name;
        this.cnic = cnic;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.conductedby = conductedby;
        this.attendedas = attendedas;
        this.teacherid = teacherid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getConductedby() {
        return conductedby;
    }

    public void setConductedby(String conductedby) {
        this.conductedby = conductedby;
    }

    public String getAttendedas() {
        return attendedas;
    }

    public void setAttendedas(String attendedas) {
        this.attendedas = attendedas;
    }
}

package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsVacant {
    private int id;
    private String emisCode;
    private String vacantdesignation;
    private String vacantseats;

    public DetailsVacant() {
    }

    public DetailsVacant(String emisCode, String vacantdesignation, String vacantseats) {
        this.emisCode = emisCode;
        this.vacantdesignation = vacantdesignation;
        this.vacantseats = vacantseats;
    }

    public DetailsVacant(int id, String emisCode, String vacantdesignation, String vacantseats) {
        this.id = id;
        this.emisCode = emisCode;
        this.vacantdesignation = vacantdesignation;
        this.vacantseats = vacantseats;
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

    public String getVacantdesignation() {
        return vacantdesignation;
    }

    public void setVacantdesignation(String vacantdesignation) {
        this.vacantdesignation = vacantdesignation;
    }

    public String getVacantseats() {
        return vacantseats;
    }

    public void setVacantseats(String vacantseats) {
        this.vacantseats = vacantseats;
    }

}


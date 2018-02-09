package com.softorea.schoolsen.lists;

/**
 * Created by Softorea on 5/25/2017.
 */

public class SchoolInformationDetails {
    private int id;
    private String emiscode;
    private String schoolname;
    private String gender;
    private String level;
    private String ddocode;
    private String district;
    private String tehsil;
    private String ucname;
    private String location;
    private String schoolzone;
    private String nano;
    private String pkno;
    private String circleofficename;
    private String ADOName;
    private String ADONo;

    public SchoolInformationDetails() {
    }

    public SchoolInformationDetails(String emiscode, String schoolname, String gender, String level,
                                    String ddocode, String district, String tehsil, String ucname,
                                    String location, String schoolzone, String nano, String pkno, String circleofficename,
                                    String ADOName, String ADONo) {
        this.emiscode = emiscode;
        this.schoolname = schoolname;
        this.gender = gender;
        this.level = level;
        this.ddocode = ddocode;
        this.district = district;
        this.tehsil = tehsil;
        this.ucname = ucname;
        this.location = location;
        this.schoolzone = schoolzone;
        this.nano = nano;
        this.pkno = pkno;
        this.circleofficename = circleofficename;
        this.ADOName = ADOName;
        this.ADONo = ADONo;
    }

    public SchoolInformationDetails(int id, String emiscode, String schoolname, String gender, String level,
                                    String ddocode, String district, String tehsil, String ucname,
                                    String location, String schoolzone, String nano, String pkno, String circleofficename,
                                    String ADOName, String ADONo) {
        this.id = id;
        this.emiscode = emiscode;
        this.schoolname = schoolname;
        this.gender = gender;
        this.level = level;
        this.ddocode = ddocode;
        this.district = district;
        this.tehsil = tehsil;
        this.ucname = ucname;
        this.location = location;
        this.schoolzone = schoolzone;
        this.nano = nano;
        this.pkno = pkno;
        this.circleofficename = circleofficename;
        this.ADOName = ADOName;
        this.ADONo = ADONo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmiscode() {
        return emiscode;
    }

    public void setEmiscode(String emiscode) {
        this.emiscode = emiscode;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDdocode() {
        return ddocode;
    }

    public void setDdocode(String ddocode) {
        this.ddocode = ddocode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getUcname() {
        return ucname;
    }

    public void setUcname(String ucname) {
        this.ucname = ucname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchoolzone() {
        return schoolzone;
    }

    public void setSchoolzone(String schoolzone) {
        this.schoolzone = schoolzone;
    }

    public String getNano() {
        return nano;
    }

    public void setNano(String nano) {
        this.nano = nano;
    }

    public String getPkno() {
        return pkno;
    }

    public void setPkno(String pkno) {
        this.pkno = pkno;
    }

    public String getCircleofficename() {
        return circleofficename;
    }

    public void setCircleofficename(String circleofficename) {
        this.circleofficename = circleofficename;
    }

    public String getADOName() {
        return ADOName;
    }

    public void setADOName(String ADOName) {
        this.ADOName = ADOName;
    }

    public String getADONo() {
        return ADONo;
    }

    public void setADONo(String ADONo) {
        this.ADONo = ADONo;
    }
}

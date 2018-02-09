package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsProxyTeacher {
    private int id;
    private String emisCode;
    private String name;
    private String cnic;
    private String proxyname;
    private String proxycnic;
    private String proxyno;
    private String designation;
    private String proxytimeSince;

    public DetailsProxyTeacher() {
    }

    public DetailsProxyTeacher(String emisCode, String name, String cnic, String proxyname, String proxycnic,
                               String proxyno, String designation, String proxytimeSince) {
        this.emisCode = emisCode;
        this.name = name;
        this.cnic = cnic;
        this.proxyname = proxyname;
        this.proxycnic = proxycnic;
        this.proxyno = proxyno;
        this.designation = designation;
        this.proxytimeSince = proxytimeSince;
    }

    public DetailsProxyTeacher(int id, String emisCode, String name, String cnic, String proxyname, String proxycnic,
                               String proxyno, String designation, String proxytimeSince) {
        this.id = id;
        this.emisCode = emisCode;
        this.name = name;
        this.cnic = cnic;
        this.proxyname = proxyname;
        this.proxycnic = proxycnic;
        this.proxyno = proxyno;
        this.designation = designation;
        this.proxytimeSince = proxytimeSince;
        //this.school=school;
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

    public String getProxyName() {
        return proxyname;
    }

    public void setProxyName(String proxyname) {
        this.proxyname = proxyname;
    }

    public String getProxycnic() {
        return proxycnic;
    }

    public void setProxycnic(String proxycnic) {
        this.proxycnic = proxycnic;
    }

    public String getProxyno() {
        return proxyno;
    }

    public void setProxyno(String proxyno) {
        this.proxyno = proxyno;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProxytimeSince() {
        return proxytimeSince;
    }

    public void setProxytimeSince(String proxytimeSince) {
        this.proxytimeSince = proxytimeSince;
    }
    /*public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }*/
}


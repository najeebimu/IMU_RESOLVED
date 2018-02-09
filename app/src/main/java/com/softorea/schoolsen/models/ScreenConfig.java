package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class ScreenConfig {
    private int id;
    private String An_DisabledStudent;
    private String An_EnrollmentByAge;
    private String An_EnrollmentByGroup;
    private String An_FTB;
    private String An_Furniture;
    private String An_Indicator;
    private String An_SantionedPosts;
    private String An_SecurityMeasures;
    private String Bi_BuildingCondition;
    private String Bi_Cleanliness;
    private String Bi_Commodities;
    private String Bi_Guides;
    private String Bi_ITLab;
    private String Bi_Infrastructure;
    private String Bi_NatureOfConstruction;
    private String Bi_PTC;
    private String Bi_SchoolArea;
    private String Bi_Stipend;
    private String SchoolInformation;

    public ScreenConfig() {
    }

    public ScreenConfig(String An_DisabledStudent, String An_EnrollmentByAge, String An_EnrollmentByGroup, String An_FTB,
                                    String An_Furniture, String An_Indicator, String An_SantionedPosts, String An_SecurityMeasures,
                                    String Bi_BuildingCondition, String Bi_Cleanliness, String Bi_Commodities,
                        String Bi_Guides, String Bi_ITLab, String Bi_Infrastructure, String Bi_NatureOfConstruction,
                        String Bi_PTC,String Bi_SchoolArea, String Bi_Stipend, String SchoolInformation) {
        this.An_DisabledStudent = An_DisabledStudent;
        this.An_EnrollmentByAge = An_EnrollmentByAge;
        this.An_EnrollmentByGroup = An_EnrollmentByGroup;
        this.An_FTB = An_FTB;
        this.An_Furniture = An_Furniture;
        this.An_Indicator = An_Indicator;
        this.An_SantionedPosts = An_SantionedPosts;
        this.An_SecurityMeasures = An_SecurityMeasures;
        this.Bi_BuildingCondition = Bi_BuildingCondition;
        this.Bi_Cleanliness = Bi_Cleanliness;
        this.Bi_Commodities = Bi_Commodities;
        this.Bi_Guides = Bi_Guides;
        this.Bi_ITLab = Bi_ITLab;
        this.Bi_Infrastructure = Bi_Infrastructure;
        this.Bi_NatureOfConstruction = Bi_NatureOfConstruction;
        this.Bi_PTC = Bi_PTC;
        this.Bi_SchoolArea = Bi_SchoolArea;
        this.Bi_Stipend = Bi_Stipend;
        this.SchoolInformation = SchoolInformation;
    }

    public ScreenConfig(int id, String An_DisabledStudent, String An_EnrollmentByAge, String An_EnrollmentByGroup, String An_FTB,
                        String An_Furniture, String An_Indicator, String An_SantionedPosts, String An_SecurityMeasures,
                        String Bi_BuildingCondition, String Bi_Cleanliness, String Bi_Commodities,
                        String Bi_Guides, String Bi_ITLab, String Bi_Infrastructure, String Bi_NatureOfConstruction,
                        String Bi_PTC,String Bi_SchoolArea, String Bi_Stipend, String SchoolInformation) {
        this.id = id;
        this.An_DisabledStudent = An_DisabledStudent;
        this.An_EnrollmentByAge = An_EnrollmentByAge;
        this.An_EnrollmentByGroup = An_EnrollmentByGroup;
        this.An_FTB = An_FTB;
        this.An_Furniture = An_Furniture;
        this.An_Indicator = An_Indicator;
        this.An_SantionedPosts = An_SantionedPosts;
        this.An_SecurityMeasures = An_SecurityMeasures;
        this.Bi_BuildingCondition = Bi_BuildingCondition;
        this.Bi_Cleanliness = Bi_Cleanliness;
        this.Bi_Commodities = Bi_Commodities;
        this.Bi_Guides = Bi_Guides;
        this.Bi_ITLab = Bi_ITLab;
        this.Bi_Infrastructure = Bi_Infrastructure;
        this.Bi_NatureOfConstruction = Bi_NatureOfConstruction;
        this.Bi_PTC = Bi_PTC;
        this.Bi_SchoolArea = Bi_SchoolArea;
        this.Bi_Stipend = Bi_Stipend;
        this.SchoolInformation = SchoolInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAn_DisabledStudent() {
        return An_DisabledStudent;
    }

    public void setAn_DisabledStudent(String An_DisabledStudent) {
        this.An_DisabledStudent = An_DisabledStudent;
    }

    public String getAn_EnrollmentByAge() {
        return An_EnrollmentByAge;
    }

    public void setAn_EnrollmentByAge(String An_EnrollmentByAge) {
        this.An_EnrollmentByAge = An_EnrollmentByAge;
    }

    public String getAn_EnrollmentByGroup() {
        return An_EnrollmentByGroup;
    }

    public void setAn_EnrollmentByGroup(String An_EnrollmentByGroup) {
        this.An_EnrollmentByGroup = An_EnrollmentByGroup;
    }

    public String getAn_FTB() {
        return An_FTB;
    }

    public void setAn_FTB(String An_FTB) {
        this.An_FTB = An_FTB;
    }

    public String getAn_Furniture() {
        return An_Furniture;
    }

    public void setAn_Furniture(String An_Furniture) {
        this.An_Furniture = An_Furniture;
    }

    public String getAn_Indicator() {
        return An_Indicator;
    }

    public void setAn_Indicator(String An_Indicator) {
        this.An_Indicator = An_Indicator;
    }

    public String getAn_SantionedPosts() {
        return An_SantionedPosts;
    }

    public void setAn_SantionedPosts(String An_SantionedPosts) {
        this.An_SantionedPosts = An_SantionedPosts;
    }

    public String getAn_SecurityMeasures() {
        return An_SecurityMeasures;
    }

    public void setAn_SecurityMeasures(String An_SecurityMeasures) {
        this.An_SecurityMeasures = An_SecurityMeasures;
    }

    public String getBi_BuildingCondition() {
        return Bi_BuildingCondition;
    }

    public void setBi_BuildingCondition(String Bi_BuildingCondition) {
        this.Bi_BuildingCondition = Bi_BuildingCondition;
    }

    public String getBi_Cleanliness() {
        return Bi_Cleanliness;
    }

    public void setBi_Cleanliness(String Bi_Cleanliness) {
        this.Bi_Cleanliness = Bi_Cleanliness;
    }

    public String getBi_Commodities() {
        return Bi_Commodities;
    }

    public void setBi_Commodities(String Bi_Commodities) {
        this.Bi_Commodities = Bi_Commodities;
    }

    public String getBi_Guides() {
        return Bi_Guides;
    }

    public void setBi_Guides(String Bi_Guides) {
        this.Bi_Guides = Bi_Guides;
    }

    public String getBi_ITLab() {
        return Bi_ITLab;
    }

    public void setBi_ITLab(String Bi_ITLab) {
        this.Bi_ITLab = Bi_ITLab;
    }

    public String getBi_Infrastructure() {
        return Bi_Infrastructure;
    }

    public void setBi_Infrastructure(String Bi_Infrastructure) {
        this.Bi_Infrastructure = Bi_Infrastructure;
    }

    public String getBi_NatureOfConstruction() {
        return Bi_NatureOfConstruction;
    }

    public void setBi_NatureOfConstruction(String Bi_NatureOfConstruction) {
        this.Bi_NatureOfConstruction = Bi_NatureOfConstruction;
    }

    public String getBi_PTC() {
        return Bi_PTC;
    }

    public void setBi_PTC(String Bi_PTC) {
        this.Bi_PTC = Bi_PTC;
    }

    public String getBi_SchoolArea() {
        return Bi_SchoolArea;
    }

    public void setBi_SchoolArea(String Bi_SchoolArea) {
        this.Bi_SchoolArea = Bi_SchoolArea;
    }

    public String getBi_Stipend() {
        return Bi_Stipend;
    }

    public void setBi_Stipend(String Bi_Stipend) {
        this.Bi_Stipend = Bi_Stipend;
    }

    public String getSchoolInformation() {
        return SchoolInformation;
    }

    public void setSchoolInformation(String SchoolInformation) {
        this.SchoolInformation = SchoolInformation;
    }
}

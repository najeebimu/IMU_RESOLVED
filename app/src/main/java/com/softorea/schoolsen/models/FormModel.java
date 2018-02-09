package com.softorea.schoolsen.models;


import java.io.Serializable;
import java.util.ArrayList;

public class FormModel implements Serializable {

    private static final long serialVersionUID = 46543440;
    ////////////////////////////////////Enrolment Record Qulaity//////////////////////////
    public static int Roastercount = 0;
    public static int schoolClosed = 0;

    ////////////////////////////////////////free Text books Quality/////////////////////////////////
    public static int formsaved = 0;
    //----------------------------------List---------------------------------------\\
    public ArrayList<EnrollmentModel> enrollmentList = new ArrayList<EnrollmentModel>();

    //////////////////////////////////////////Furniture Quality///////////////////////////////////
    public ArrayList<ProvisionFreeTextBookModel> provisionFreeBooksList = new ArrayList<ProvisionFreeTextBookModel>();
    public ArrayList<ItemSelectedList> itemSelectedList = new ArrayList<ItemSelectedList>();

    ////////////////////////////////////////// PTC Quality///////////////////////////////////////
    public ArrayList<ItemSelectedList> provisionSelectedList = new ArrayList<ItemSelectedList>();
    public ArrayList<ItemSelectedListSubject> subjectSelectedList = new ArrayList<ItemSelectedListSubject>();
    String enrolmentRecordShown = "Null";
    String enrolmentRecordQuality = "Null";
    String freeTextBooksRecordShown = "Null";


    ///////////////////////////////////////////////School Info///////////////////////////////////////
    String freeTextBooksRecordQuality = "Null";
    String furnitureRecordShown = "Null";
    String furnitureRecordQuality = "Null";
    String ptcRecordShown = "Null";
    String ptcRecordQuality = "Null";
    String ptcBoardAvail = "Null";
    int currentPosition;
    int nextPostion;
    int pictureTaken = 0;
    String lat = "Null";
    String lng = "Null";
    String laccuracy = "Null";
    String time1 = "Null";
    String isMock = "false";
    String provider = "Null";
    String lat2 = "Null";
    String lng2 = "Null";
    String laccuracy2 = "Null";
    String provider2 = "Null";
    String lat3 = "Null";
    String lng3 = "Null";
    String laccuracy3 = "Null";
    String lat4 = "Null";
    String lng4 = "Null";
    String laccuracy4 = "Null";
    String emisCode = "Null";
    String SchoolName = "Null";
    String genderSchool = "Null";
    String District = "Null";
    String Tehsil = "Null";
    String location = "Null";
    String pkNo = "Null";
    String NaNo = "Null";
    String ucName = "Null";
    String circleOffice = "Null";
    String season = "Null";
    String level = "Null";
    String otherStatus = "Null";
    String otherVisit = "Null";
    String FlagCheck = "Null";
    String GrantBoardCheck = "Null";
    String VillageCouncil = "Null";
    int teacherListComplete = 0;
    int enrolmentListComplete = 0;
    int schemeWorkComplete = 0;
    int stipenedListComplete = 0;
    int nonTeacherListComplete = 0;
    String startTime = "Null";
    String endTime = "Null";
    String changeTime = "false";
    String monitoringDate = "Null";
    int dialogStatus = -1;
    String BuildingCondition = "NUll";
    //////////////////////////////////////////////////////////////////////////////////////////
    String upgradeDate = "Null";
    String visitType = "Null";
    String yearOfEstablish = "Null";
    String nameOfADO = "Null";
    String contactNo = "Null";
    String SchoolStatus = "Open";
    String SchoolStatusDetail = "Open";
    String schoolStatusOther = "Null";
    String buildingStatus = "Null";
    String buildingStatusDetail = "Null";
    String occupiedDetail = "Null";
    String occupiedDate = "Null";
    String occupiedBy = "Null";
    String classRoomNnumbers = "Null";
    String varandaNumbers = "Null";
    String htcOfficeNumbers = "Null";
    String SciemceLab = "Null";
    String computerLab = "Null";
    String staffRooms = "Null";
    String infraWashRooms = "NUll";
    String infraOthers = "Null";
    String dangerousClassRooms = "NUll";
    String libraryNumbers = "Null";
    String clerkRoomNumbers = "Null";
    String examinationHall = "Null";
    String playGround = "Null";
    String headOfInstituteName = "Null";
    String designationHD = "Null";
    String headMasterPhone = "Null";
    String areaOfSchoolApRecord = "Null";
    String areaCovered = "Null";
    String blackBoard = "Null";
    String teacherTables = "Null";
    String teacherChairs = "Null";
    String officeTable = "Null";
    String stdTabletChair = "Null";
    String officeChairs = "Null";
    String studentDeskTwoSeat = "Null";
    String studentDeskThree = "Null";
    String stdChairs = "Null";
    String stdBenchTwoSeat = "Null";
    String stdBenchThreeSeat = "Null";
    String stdAlmirah = "Null";
    String stdTAT = "Null";
    String bankAccountNo = "Null";
    String bankName = "Null";
    String bankBranch = "Null";
    String balance30June = "Null";
    String expenditureCurrentYear = "Null";
    String fundsRecievedFromGovCurrentYear = "Null";
    String fundsRecievedFromOthersCurrentYear = "Null";
    String presentBalance = "Null";
    String bankStatementShown = "Null";
    String noMeetingLastMonth = "Null";
    String recordQuality = "null";
    String useOfTeacherGuides = "Null";
    String electricAvail = "Null";
    String electricFunc = "Null";
    String electricSRc = "Null";
    String waterAvail = "Null";
    String waterFunc = "Null";
    String srcWater = "Null";
    String drinkable = "Null";
    String notDrinkAvail = "Null";
    String toiletAvail = "Null";
    String toiletFunction = "Null";
    String numberOfStudentToilet = "Null";
    String numberOfTeacherToilet = "Null";
    String numberOfCommonToilet = "Null";
    String numberOfStudentToiletFunctioning = "Null";
    String numberOfTeacherToiletFunctioning = "Null";
    String numberOfCommonToiletFunctioning = "Null";
    String boundaryAvail = "Null";
    String boundaryFunction = "Null";
    String boudaryWallHeight = "Null";
    String securityGuards = "Null";
    String chowkidar = "Null";
    String weapons = "Null";
    String metalDetattors = "Null";
    String barbWire = "Null";
    String glassSpikes = "Null";
    String enntranceBlock = "Null";
    String sosAvailable = "Null";
    String sosSubmit = "Null";
    String sosFunction = "Null";
    String schemeName = "Null";
    String schemeStartDate = "Null";
    String schemeStatus = "Null";
    String schemeScoopOfWork = "Null";
    String schoolClean = "Null";
    String stdClean = "Null";
    String ptmExist = "Null";
    String ptmLastMonth = "Null";
    String remarks = "Null";
    String totalTeacher = "Null";
    String teacherPresent = "Null";
    String GCSClassAvailable = "Null";
    String GCSNumberOfClassRooms = "Null";
    String GCSNumberOfVaranda = "Null";
    String GCSWashRoomAvailable = "Null";
    String GCSWashroomFunctional = "Null";
    String GCSDrinkingWaterAvailable = "Null";
    String GCSDrinkingWaterFunctional = "Null";
    String GCSElectricityAvailable = "Null";
    String GCSElectricityFunctional = "Null";

    public String getPtcBoardAvail() {
        return ptcBoardAvail;
    }

    public void setPtcBoardAvail(String ptcBoardAvail) {
        this.ptcBoardAvail = ptcBoardAvail;
    }

    public String getLat3() {
        return lat3;
    }

    public void setLat3(String lat3) {
        this.lat3 = lat3;
    }

    public String getLng3() {
        return lng3;
    }

    public void setLng3(String lng3) {
        this.lng3 = lng3;
    }

    public String getLaccuracy3() {
        return laccuracy3;
    }

    public void setLaccuracy3(String laccuracy3) {
        this.laccuracy3 = laccuracy3;
    }

    public String getLat4() {
        return lat4;
    }

    public void setLat4(String lat4) {
        this.lat4 = lat4;
    }

    public String getLng4() {
        return lng4;
    }

    public void setLng4(String lng4) {
        this.lng4 = lng4;
    }

    public String getLaccuracy4() {
        return laccuracy4;
    }

    public void setLaccuracy4(String laccuracy4) {
        this.laccuracy4 = laccuracy4;
    }

    public String isMock() {
        return isMock;
    }

    public void setMock(String isMock) {
        this.isMock = isMock;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getLat2() {
        return lat2;
    }

    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }

    public String getLng2() {
        return lng2;
    }

    public void setLng2(String lng2) {
        this.lng2 = lng2;
    }

    public String getLaccuracy2() {
        return laccuracy2;
    }

    public void setLaccuracy2(String laccuracy2) {
        this.laccuracy2 = laccuracy2;
    }

    public String getProvider2() {
        return provider2;
    }

    public void setProvider2(String provider2) {
        this.provider2 = provider2;
    }

    public ArrayList<ItemSelectedList> getProvisionSelectedList() {
        return provisionSelectedList;
    }

    public void setProvisionSelectedList(
            ArrayList<ItemSelectedList> provisionSelectedList) {
        this.provisionSelectedList = provisionSelectedList;
    }

    public String getBuildingCondition() {
        return BuildingCondition;
    }

    public void setBuildingCondition(String buildingCondition) {
        BuildingCondition = buildingCondition;
    }

    public String getVillageCouncil() {
        return VillageCouncil;
    }

    public void setVillageCouncil(String villageCouncil) {
        VillageCouncil = villageCouncil;
    }

    public String getFlagCheck() {
        return FlagCheck;
    }

    public void setFlagCheck(String flagCheck) {
        FlagCheck = flagCheck;
    }

    public String getGrantBoardCheck() {
        return GrantBoardCheck;
    }

    public void setGrantBoardCheck(String grantBoardCheck) {
        GrantBoardCheck = grantBoardCheck;
    }

    //***********************************PTC Fragment **********************************\\

    public int getNonTeacherListComplete() {
        return nonTeacherListComplete;
    }

    public void setNonTeacherListComplete(int nonTeacherListComplete) {
        this.nonTeacherListComplete = nonTeacherListComplete;
    }

    public String getLaccuracy() {
        return laccuracy;
    }

    public void setLaccuracy(String laccuracy) {
        this.laccuracy = laccuracy;
    }

    public int getPictureTaken() {
        return pictureTaken;
    }

    public void setPictureTaken(int pictureTaken) {
        this.pictureTaken = pictureTaken;
    }

    public int getStipenedListComplete() {
        return stipenedListComplete;
    }

    public void setStipenedListComplete(int stipenedListComplete) {
        this.stipenedListComplete = stipenedListComplete;
    }

    public String getFreeTextBooksRecordShown() {
        return freeTextBooksRecordShown;
    }

    public void setFreeTextBooksRecordShown(String freeTextBooksRecordShown) {
        this.freeTextBooksRecordShown = freeTextBooksRecordShown;
    }

    public String getFreeTextBooksRecordQuality() {
        return freeTextBooksRecordQuality;
    }

    //**********************************Use Of Teacher Guides*****************************\\

    public void setFreeTextBooksRecordQuality(String freeTextBooksRecordQuality) {
        this.freeTextBooksRecordQuality = freeTextBooksRecordQuality;
    }


    //*********************************School Facility*****************************\\

    public String getFurnitureRecordShown() {
        return furnitureRecordShown;
    }

    public void setFurnitureRecordShown(String furnitureRecordShown) {
        this.furnitureRecordShown = furnitureRecordShown;
    }

    public String getFurnitureRecordQuality() {
        return furnitureRecordQuality;
    }

    public void setFurnitureRecordQuality(String furnitureRecordQuality) {
        this.furnitureRecordQuality = furnitureRecordQuality;
    }

    public String getPtcRecordShown() {
        return ptcRecordShown;
    }

    public void setPtcRecordShown(String ptcRecordShown) {
        this.ptcRecordShown = ptcRecordShown;
    }

    public String getPtcRecordQuality() {
        return ptcRecordQuality;
    }

    public void setPtcRecordQuality(String ptcRecordQuality) {
        this.ptcRecordQuality = ptcRecordQuality;
    }

    public String getEnrolmentRecordShown() {
        return enrolmentRecordShown;
    }

    public void setEnrolmentRecordShown(String enrolmentRecordShown) {
        this.enrolmentRecordShown = enrolmentRecordShown;
    }

    public String getEnrolmentRecordQuality() {
        return enrolmentRecordQuality;
    }

    public void setEnrolmentRecordQuality(String enrolmentRecordQuality) {
        this.enrolmentRecordQuality = enrolmentRecordQuality;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getNextPostion() {
        return nextPostion;
    }

    public void setNextPostion(int nextPostion) {
        this.nextPostion = nextPostion;
    }

    public int getSchemeWorkComplete() {
        return schemeWorkComplete;
    }

    public void setSchemeWorkComplete(int schemeWorkComplete) {
        this.schemeWorkComplete = schemeWorkComplete;
    }


    //*********************************Security Measures*****************************\\

    public String getMonitoringDate() {
        return monitoringDate;
    }

    public void setMonitoringDate(String monitoringDate) {
        this.monitoringDate = monitoringDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public int getEnrolmentListComplete() {
        return enrolmentListComplete;
    }

    public void setEnrolmentListComplete(int enrolmentListComplete) {
        this.enrolmentListComplete = enrolmentListComplete;
    }

    public int getTeacherListComplete() {
        return teacherListComplete;
    }


    //*********************************Planing and Development*****************************\\

    public void setTeacherListComplete(int teacherListComplete) {
        this.teacherListComplete = teacherListComplete;
    }

    public String getEmisCode() {
        return emisCode;
    }

    public void setEmisCode(String emisCode) {
        this.emisCode = emisCode;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    //*********************************Security Measures*****************************\\

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getGenderSchool() {
        return genderSchool;
    }


    //*********************************Security Measures*****************************\\

    public void setGenderSchool(String genderSchool) {
        this.genderSchool = genderSchool;
    }

    public String getDistrict() {
        return District;
    }

    //*********************************Security Measures*****************************\\

    public void setDistrict(String district) {
        District = district;
    }

    //**************************************Teacher Count**********************************\\

    public String getTehsil() {
        return Tehsil;
    }

    public void setTehsil(String tehsil) {
        Tehsil = tehsil;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPkNo() {
        return pkNo;
    }

    public void setPkNo(String pkNo) {
        this.pkNo = pkNo;
    }

    public String getNaNo() {
        return NaNo;
    }
    //---------------------------------------------------------------------------------\\

    public void setNaNo(String naNo) {
        NaNo = naNo;
    }

    public String getUcName() {
        return ucName;
    }

    public void setUcName(String ucName) {
        this.ucName = ucName;
    }

    public String getCircleOffice() {
        return circleOffice;
    }

    public void setCircleOffice(String circleOffice) {
        this.circleOffice = circleOffice;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(String occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public String getStdTabletChair() {
        return stdTabletChair;
    }

    public void setStdTabletChair(String stdTabletChair) {
        this.stdTabletChair = stdTabletChair;
    }

    public int getDialogStatus() {
        return dialogStatus;
    }

    public void setDialogStatus(int dialogStatus) {
        this.dialogStatus = dialogStatus;
    }

    public String getGCSClassAvailable() {
        return GCSClassAvailable;
    }

    public void setGCSClassAvailable(String gCSClassAvailable) {
        GCSClassAvailable = gCSClassAvailable;
    }

    public String getGCSNumberOfClassRooms() {
        return GCSNumberOfClassRooms;
    }

    public void setGCSNumberOfClassRooms(String gCSNumberOfClassRooms) {
        GCSNumberOfClassRooms = gCSNumberOfClassRooms;
    }

    public String getGCSNumberOfVaranda() {
        return GCSNumberOfVaranda;
    }

    public void setGCSNumberOfVaranda(String gCSNumberOfVaranda) {
        GCSNumberOfVaranda = gCSNumberOfVaranda;
    }

    public String getGCSWashRoomAvailable() {
        return GCSWashRoomAvailable;
    }

    public void setGCSWashRoomAvailable(String gCSWashRoomAvailable) {
        GCSWashRoomAvailable = gCSWashRoomAvailable;
    }

    public String getGCSWashroomFunctional() {
        return GCSWashroomFunctional;
    }

    public void setGCSWashroomFunctional(String gCSWashroomFunctional) {
        GCSWashroomFunctional = gCSWashroomFunctional;
    }

    public String getGCSDrinkingWaterAvailable() {
        return GCSDrinkingWaterAvailable;
    }

    public void setGCSDrinkingWaterAvailable(String gCSDrinkingWaterAvailable) {
        GCSDrinkingWaterAvailable = gCSDrinkingWaterAvailable;
    }

    public String getGCSDrinkingWaterFunctional() {
        return GCSDrinkingWaterFunctional;
    }

    public void setGCSDrinkingWaterFunctional(String gCSDrinkingWaterFunctional) {
        GCSDrinkingWaterFunctional = gCSDrinkingWaterFunctional;
    }

    public String getGCSElectricityAvailable() {
        return GCSElectricityAvailable;
    }

    public void setGCSElectricityAvailable(String gCSElectricityAvailable) {
        GCSElectricityAvailable = gCSElectricityAvailable;
    }

    public String getGCSElectricityFunctional() {
        return GCSElectricityFunctional;
    }

    public void setGCSElectricityFunctional(String gCSElectricityFunctional) {
        GCSElectricityFunctional = gCSElectricityFunctional;
    }

    public String getPtmExist() {
        return ptmExist;
    }

    public void setPtmExist(String ptmExist) {
        this.ptmExist = ptmExist;
    }

    public String getDangerousClassRooms() {
        return dangerousClassRooms;
    }

    public void setDangerousClassRooms(String dangerousClassRooms) {
        this.dangerousClassRooms = dangerousClassRooms;
    }

    public String getOtherStatus() {
        return otherStatus;
    }

    public void setOtherStatus(String otherStatus) {
        this.otherStatus = otherStatus;
    }

    public String getOtherVisit() {
        return otherVisit;
    }

    public void setOtherVisit(String otherVisit) {
        this.otherVisit = otherVisit;
    }

    public String getTotalTeacher() {
        return totalTeacher;
    }

    public void setTotalTeacher(String totalTeacher) {
        this.totalTeacher = totalTeacher;
    }

    public String getTeacherPresent() {
        return teacherPresent;
    }

    public void setTeacherPresent(String teacherPresent) {
        this.teacherPresent = teacherPresent;
    }

    public String getInfraWashRooms() {
        return infraWashRooms;
    }

    public void setInfraWashRooms(String infraWashRooms) {
        this.infraWashRooms = infraWashRooms;
    }

    public String getInfraOthers() {
        return infraOthers;
    }

    public void setInfraOthers(String infraOthers) {
        this.infraOthers = infraOthers;
    }

    public String getNumberOfStudentToiletFunctioning() {
        return numberOfStudentToiletFunctioning;
    }

    public void setNumberOfStudentToiletFunctioning(
            String numberOfStudentToiletFunctioning) {
        this.numberOfStudentToiletFunctioning = numberOfStudentToiletFunctioning;
    }

    public String getNumberOfTeacherToiletFunctioning() {
        return numberOfTeacherToiletFunctioning;
    }

    public void setNumberOfTeacherToiletFunctioning(
            String numberOfTeacherToiletFunctioning) {
        this.numberOfTeacherToiletFunctioning = numberOfTeacherToiletFunctioning;
    }

    public String getNumberOfCommonToiletFunctioning() {
        return numberOfCommonToiletFunctioning;
    }

    public void setNumberOfCommonToiletFunctioning(
            String numberOfCommonToiletFunctioning) {
        this.numberOfCommonToiletFunctioning = numberOfCommonToiletFunctioning;
    }

    public String getSosFunction() {
        return sosFunction;
    }

    public void setSosFunction(String sosFunction) {
        this.sosFunction = sosFunction;
    }

    public String getRecordQuality() {
        return recordQuality;
    }

    public void setRecordQuality(String recordQuality) {
        this.recordQuality = recordQuality;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSchoolStatusOther() {
        return schoolStatusOther;
    }

    public void setSchoolStatusOther(String schoolStatusOther) {
        this.schoolStatusOther = schoolStatusOther;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPtmLastMonth() {
        return ptmLastMonth;
    }

    public void setPtmLastMonth(String ptmLastMonth) {
        this.ptmLastMonth = ptmLastMonth;
    }


    public String getHeadOfInstituteName() {
        return headOfInstituteName;
    }

    public void setHeadOfInstituteName(String headOfInstituteName) {
        this.headOfInstituteName = headOfInstituteName;
    }

    public String getSchoolClean() {
        return schoolClean;
    }

    public void setSchoolClean(String schoolClean) {
        this.schoolClean = schoolClean;
    }

    public String getStdClean() {
        return stdClean;
    }

    public void setStdClean(String stdClean) {
        this.stdClean = stdClean;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeStartDate() {
        return schemeStartDate;
    }

    public void setSchemeStartDate(String schemeStartDate) {
        this.schemeStartDate = schemeStartDate;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getSchemeScoopOfWork() {
        return schemeScoopOfWork;
    }

    public void setSchemeScoopOfWork(String schemeScoopOfWork) {
        this.schemeScoopOfWork = schemeScoopOfWork;
    }

    public String getBoudaryWallHeight() {
        return boudaryWallHeight;
    }

    public void setBoudaryWallHeight(String boudaryWallHeight) {
        this.boudaryWallHeight = boudaryWallHeight;
    }

    public String getSecurityGuards() {
        return securityGuards;
    }

    public void setSecurityGuards(String securityGuards) {
        this.securityGuards = securityGuards;
    }

    public String getChowkidar() {
        return chowkidar;
    }

    public void setChowkidar(String chowkidar) {
        this.chowkidar = chowkidar;
    }

    public String getWeapons() {
        return weapons;
    }

    public void setWeapons(String weapons) {
        this.weapons = weapons;
    }

    public String getMetalDetattors() {
        return metalDetattors;
    }

    public void setMetalDetattors(String metalDetattors) {
        this.metalDetattors = metalDetattors;
    }

    public String getBarbWire() {
        return barbWire;
    }

    public void setBarbWire(String barbWire) {
        this.barbWire = barbWire;
    }

    public String getGlassSpikes() {
        return glassSpikes;
    }

    public void setGlassSpikes(String glassSpikes) {
        this.glassSpikes = glassSpikes;
    }

    public String getEnntranceBlock() {
        return enntranceBlock;
    }

    public void setEnntranceBlock(String enntranceBlock) {
        this.enntranceBlock = enntranceBlock;
    }

    public String getSosAvailable() {
        return sosAvailable;
    }

    public void setSosAvailable(String sosAvailable) {
        this.sosAvailable = sosAvailable;
    }

    public String getSosSubmit() {
        return sosSubmit;
    }

    public void setSosSubmit(String sosSubmit) {
        this.sosSubmit = sosSubmit;
    }

    public String getElectricAvail() {
        return electricAvail;
    }

    public void setElectricAvail(String electricAvail) {
        this.electricAvail = electricAvail;
    }

    public String getElectricFunc() {
        return electricFunc;
    }

    public void setElectricFunc(String electricFunc) {
        this.electricFunc = electricFunc;
    }

    public String getWaterAvail() {
        return waterAvail;
    }

    public void setWaterAvail(String waterAvail) {
        this.waterAvail = waterAvail;
    }

    public String getWaterFunc() {
        return waterFunc;
    }

    public void setWaterFunc(String waterFunc) {
        this.waterFunc = waterFunc;
    }

    public String getSrcWater() {
        return srcWater;
    }

    public void setSrcWater(String srcWater) {
        this.srcWater = srcWater;
    }

    public String getDrinkable() {
        return drinkable;
    }

    public void setDrinkable(String drinkable) {
        this.drinkable = drinkable;
    }

    public String getNotDrinkAvail() {
        return notDrinkAvail;
    }

    public void setNotDrinkAvail(String notDrinkAvail) {
        this.notDrinkAvail = notDrinkAvail;
    }

    public String getToiletAvail() {
        return toiletAvail;
    }

    public void setToiletAvail(String toiletAvail) {
        this.toiletAvail = toiletAvail;
    }

    public String getToiletFunction() {
        return toiletFunction;
    }

    public void setToiletFunction(String toiletFunction) {
        this.toiletFunction = toiletFunction;
    }

    public String getNumberOfStudentToilet() {
        return numberOfStudentToilet;
    }

    public void setNumberOfStudentToilet(String numberOfStudentToilet) {
        this.numberOfStudentToilet = numberOfStudentToilet;
    }

    public String getNumberOfTeacherToilet() {
        return numberOfTeacherToilet;
    }

    public void setNumberOfTeacherToilet(String numberOfTeacherToilet) {
        this.numberOfTeacherToilet = numberOfTeacherToilet;
    }

    public String getNumberOfCommonToilet() {
        return numberOfCommonToilet;
    }

    public void setNumberOfCommonToilet(String numberOfCommonToilet) {
        this.numberOfCommonToilet = numberOfCommonToilet;
    }

    public String getBoundaryAvail() {
        return boundaryAvail;
    }

    public void setBoundaryAvail(String boundaryAvail) {
        this.boundaryAvail = boundaryAvail;
    }

    public String getBoundaryFunction() {
        return boundaryFunction;
    }

    public void setBoundaryFunction(String boundaryFunction) {
        this.boundaryFunction = boundaryFunction;
    }

    public String getUseOfTeacherGuides() {
        return useOfTeacherGuides;
    }

    public void setUseOfTeacherGuides(String useOfTeacherGuides) {
        this.useOfTeacherGuides = useOfTeacherGuides;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBalance30June() {
        return balance30June;
    }

    public void setBalance30June(String balance30June) {
        this.balance30June = balance30June;
    }

    public String getExpenditureCurrentYear() {
        return expenditureCurrentYear;
    }

    public void setExpenditureCurrentYear(String expenditureCurrentYear) {
        this.expenditureCurrentYear = expenditureCurrentYear;
    }

    public String getFundsRecievedFromGovCurrentYear() {
        return fundsRecievedFromGovCurrentYear;
    }

    public void setFundsRecievedFromGovCurrentYear(
            String fundsRecievedFromGovCurrentYear) {
        this.fundsRecievedFromGovCurrentYear = fundsRecievedFromGovCurrentYear;
    }

    public String getFundsRecievedFromOthersCurrentYear() {
        return fundsRecievedFromOthersCurrentYear;
    }

    public void setFundsRecievedFromOthersCurrentYear(
            String fundsRecievedFromOthersCurrentYear) {
        this.fundsRecievedFromOthersCurrentYear = fundsRecievedFromOthersCurrentYear;
    }

    public String getPresentBalance() {
        return presentBalance;
    }

    public void setPresentBalance(String presentBalance) {
        this.presentBalance = presentBalance;
    }

    public String getBankStatementShown() {
        return bankStatementShown;
    }

    public void setBankStatementShown(String bankStatementShown) {
        this.bankStatementShown = bankStatementShown;
    }

    public String getNoMeetingLastMonth() {
        return noMeetingLastMonth;
    }

    public void setNoMeetingLastMonth(String noMeetingLastMonth) {
        this.noMeetingLastMonth = noMeetingLastMonth;
    }

    public String getBlackBoard() {
        return blackBoard;
    }

    public void setBlackBoard(String blackBoard) {
        this.blackBoard = blackBoard;
    }

    public String getTeacherTables() {
        return teacherTables;
    }

    public void setTeacherTables(String teacherTables) {
        this.teacherTables = teacherTables;
    }

    public String getTeacherChairs() {
        return teacherChairs;
    }

    public void setTeacherChairs(String teacherChairs) {
        this.teacherChairs = teacherChairs;
    }

    public String getOfficeTable() {
        return officeTable;
    }

    public void setOfficeTable(String officeTable) {
        this.officeTable = officeTable;
    }

    public String getOfficeChairs() {
        return officeChairs;
    }

    public void setOfficeChairs(String officeChairs) {
        this.officeChairs = officeChairs;
    }

    public String getStudentDeskTwoSeat() {
        return studentDeskTwoSeat;
    }

    public void setStudentDeskTwoSeat(String studentDeskTwoSeat) {
        this.studentDeskTwoSeat = studentDeskTwoSeat;
    }

    public String getStudentDeskThree() {
        return studentDeskThree;
    }

    public void setStudentDeskThree(String studentDeskThree) {
        this.studentDeskThree = studentDeskThree;
    }

    public String getStdChairs() {
        return stdChairs;
    }

    public void setStdChairs(String stdChairs) {
        this.stdChairs = stdChairs;
    }

    public String getStdBenchTwoSeat() {
        return stdBenchTwoSeat;
    }

    public void setStdBenchTwoSeat(String stdBenchTwoSeat) {
        this.stdBenchTwoSeat = stdBenchTwoSeat;
    }

    public String getStdBenchThreeSeat() {
        return stdBenchThreeSeat;
    }

    public void setStdBenchThreeSeat(String stdBenchThreeSeat) {
        this.stdBenchThreeSeat = stdBenchThreeSeat;
    }

    public String getStdAlmirah() {
        return stdAlmirah;
    }

    public void setStdAlmirah(String stdAlmirah) {
        this.stdAlmirah = stdAlmirah;
    }

    public String getStdTAT() {
        return stdTAT;
    }

    public void setStdTAT(String stdTAT) {
        this.stdTAT = stdTAT;
    }

    public ArrayList<ProvisionFreeTextBookModel> getProvisionFreeBooksList() {
        return provisionFreeBooksList;
    }

    public void setProvisionFreeBooksList(
            ArrayList<ProvisionFreeTextBookModel> provisionFreeBooksList) {
        this.provisionFreeBooksList = provisionFreeBooksList;
    }

    public String getAreaOfSchoolApRecord() {
        return areaOfSchoolApRecord;
    }

    public void setAreaOfSchoolApRecord(String areaOfSchoolApRecord) {
        this.areaOfSchoolApRecord = areaOfSchoolApRecord;
    }

    public String getAreaCovered() {
        return areaCovered;
    }

    public void setAreaCovered(String areaCovered) {
        this.areaCovered = areaCovered;
    }

    public String getDesignationHD() {
        return designationHD;
    }

    public void setDesignationHD(String designationHD) {
        this.designationHD = designationHD;
    }

    public String getHeadMasterPhone() {
        return headMasterPhone;
    }

    public void setHeadMasterPhone(String headMasterPhone) {
        this.headMasterPhone = headMasterPhone;
    }

    public String getClassRoomNnumbers() {
        return classRoomNnumbers;
    }

    public void setClassRoomNnumbers(String classRoomNnumbers) {
        this.classRoomNnumbers = classRoomNnumbers;
    }

    public String getVarandaNumbers() {
        return varandaNumbers;
    }

    public void setVarandaNumbers(String varandaNumbers) {
        this.varandaNumbers = varandaNumbers;
    }

    public String getHtcOfficeNumbers() {
        return htcOfficeNumbers;
    }

    public void setHtcOfficeNumbers(String htcOfficeNumbers) {
        this.htcOfficeNumbers = htcOfficeNumbers;
    }

    public String getSciemceLab() {
        return SciemceLab;
    }

    public void setSciemceLab(String sciemceLab) {
        SciemceLab = sciemceLab;
    }

    public String getComputerLab() {
        return computerLab;
    }

    public void setComputerLab(String computerLab) {
        this.computerLab = computerLab;
    }

    public String getStaffRooms() {
        return staffRooms;
    }

    public void setStaffRooms(String staffRooms) {
        this.staffRooms = staffRooms;
    }

    public String getLibraryNumbers() {
        return libraryNumbers;
    }

    public void setLibraryNumbers(String libraryNumbers) {
        this.libraryNumbers = libraryNumbers;
    }

    public String getClerkRoomNumbers() {
        return clerkRoomNumbers;
    }

    public void setClerkRoomNumbers(String clerkRoomNumbers) {
        this.clerkRoomNumbers = clerkRoomNumbers;
    }

    public String getExaminationHall() {
        return examinationHall;
    }

    public void setExaminationHall(String examinationHall) {
        this.examinationHall = examinationHall;
    }

    public String getPlayGround() {
        return playGround;
    }

    public void setPlayGround(String playGround) {
        this.playGround = playGround;
    }

    public String getOccupiedDetail() {
        return occupiedDetail;
    }

    public void setOccupiedDetail(String occupiedDetail) {
        this.occupiedDetail = occupiedDetail;
    }

    public String getOccupiedDate() {
        return occupiedDate;
    }

    public void setOccupiedDate(String occupiedDate) {
        this.occupiedDate = occupiedDate;
    }

    public String getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(String buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public String getBuildingStatusDetail() {
        return buildingStatusDetail;
    }

    public void setBuildingStatusDetail(String buildingStatusDetail) {
        this.buildingStatusDetail = buildingStatusDetail;
    }

    public String getSchoolStatusDetail() {
        return SchoolStatusDetail;
    }

    public void setSchoolStatusDetail(String schoolStatusDetail) {
        SchoolStatusDetail = schoolStatusDetail;
    }

    public String getUpgradeDate() {
        return upgradeDate;
    }

    public void setUpgradeDate(String upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    public String getSchoolStatus() {
        return SchoolStatus;
    }

    public void setSchoolStatus(String schoolStatus) {
        SchoolStatus = schoolStatus;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getYearOfEstablish() {
        return yearOfEstablish;
    }

    public void setYearOfEstablish(String yearOfEstablish) {
        this.yearOfEstablish = yearOfEstablish;
    }

    public String getNameOfADO() {
        return nameOfADO;
    }

    public void setNameOfADO(String nameOfADO) {
        this.nameOfADO = nameOfADO;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getElectricSRc() {
        return electricSRc;
    }

    public void setElectricSRc(String electricSRc) {
        this.electricSRc = electricSRc;
    }

    public static class model {

    }


}

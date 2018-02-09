package com.softorea.schoolsen.m_monthly;

import java.io.Serializable;


public class Grant implements Serializable {

    private static final long serialVersionUID = 46653449;


    String emisCode = "Null";
    String grantType = "Null";
    String year = "Null";
    String amount = "Null";
    int FaceCode;

    String StartDate = "Null";
    String type = "Null";
    String financial = "Null";
    String workStatus = "Null";
    String workGrading = "Null";
    String remarks = "Null";
    String recordStatus = "Null";

    String fundsReceived = "Null";
    String ammountCorrect = "Null";
    String ammountEnter = "Null";


    public int getFaceCode() {
        return FaceCode;
    }

    public void setFaceCode(int faceCode) {
        FaceCode = faceCode;
    }

    public String getEmisCode() {
        return emisCode;
    }

    public void setEmisCode(String emisCode) {
        this.emisCode = emisCode;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFinancial() {
        return financial;
    }

    public void setFinancial(String financial) {
        this.financial = financial;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkGrading() {
        return workGrading;
    }

    public void setWorkGrading(String workGrading) {
        this.workGrading = workGrading;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getFundsReceived() {
        return fundsReceived;
    }

    public void setFundsReceived(String fundsReceived) {
        this.fundsReceived = fundsReceived;
    }

    public String getAmmountCorrect() {
        return ammountCorrect;
    }

    public void setAmmountCorrect(String ammountCorrect) {
        this.ammountCorrect = ammountCorrect;
    }

    public String getAmmountEnter() {
        return ammountEnter;
    }

    public void setAmmountEnter(String ammountEnter) {
        this.ammountEnter = ammountEnter;
    }
}

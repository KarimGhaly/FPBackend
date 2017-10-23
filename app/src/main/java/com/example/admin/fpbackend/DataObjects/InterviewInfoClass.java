package com.example.admin.fpbackend.DataObjects;

import java.util.Date;

/**
 * Created by Admin on 10/23/2017.
 */

public class InterviewInfoClass {
    private String interviewTitle;
    private Date interviewDateTime;
    private String interviewName;
    private String clientName;
    private String vendorName;
    private String projectCityState;
    private String projectDuration;
    private String availabilityDate;
    private String clientWebsite;
    private String pdfMoreDetailsLink;
    private String mentor;

    public InterviewInfoClass() {
    }

    public InterviewInfoClass(String interviewTitle, Date interviewDateTime, String interviewName, String clientName, String vendorName, String projectCityState, String projectDuration, String availabilityDate, String clientWebsite, String pdfMoreDetailsLink, String mentor) {
        this.interviewTitle = interviewTitle;
        this.interviewDateTime = interviewDateTime;
        this.interviewName = interviewName;
        this.clientName = clientName;
        this.vendorName = vendorName;
        this.projectCityState = projectCityState;
        this.projectDuration = projectDuration;
        this.availabilityDate = availabilityDate;
        this.clientWebsite = clientWebsite;
        this.pdfMoreDetailsLink = pdfMoreDetailsLink;
        this.mentor = mentor;
    }

    public String getInterviewTitle() {
        return interviewTitle;
    }

    public void setInterviewTitle(String interviewTitle) {
        this.interviewTitle = interviewTitle;
    }

    public Date getInterviewDateTime() {
        return interviewDateTime;
    }

    public void setInterviewDateTime(Date interviewDateTime) {
        this.interviewDateTime = interviewDateTime;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getProjectCityState() {
        return projectCityState;
    }

    public void setProjectCityState(String projectCityState) {
        this.projectCityState = projectCityState;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(String availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getClientWebsite() {
        return clientWebsite;
    }

    public void setClientWebsite(String clientWebsite) {
        this.clientWebsite = clientWebsite;
    }

    public String getPdfMoreDetailsLink() {
        return pdfMoreDetailsLink;
    }

    public void setPdfMoreDetailsLink(String pdfMoreDetailsLink) {
        this.pdfMoreDetailsLink = pdfMoreDetailsLink;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }
}

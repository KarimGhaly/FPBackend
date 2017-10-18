package com.example.admin.fpbackend.ConsultantRecord;

/**
 * Created by Admin on 10/12/2017.
 */

public class AccountClass {
    String Consultant_Refrence;
    String Housing_Refrence;
    String Team_Refrence;
    String TrainingManager_Refrence;
    String MarketingManager_Refrence;
    String Instructor_Refrence;

    public AccountClass() {
    }

    public AccountClass(String consultant_Refrence, String housing_Refrence, String team_Refrence, String trainingManager_Refrence, String marketingManager_Refrence, String instructor_Refrence) {
        Consultant_Refrence = consultant_Refrence;
        Housing_Refrence = housing_Refrence;
        Team_Refrence = team_Refrence;
        TrainingManager_Refrence = trainingManager_Refrence;
        MarketingManager_Refrence = marketingManager_Refrence;
        Instructor_Refrence = instructor_Refrence;
    }

    public String getConsultant_Refrence() {
        return Consultant_Refrence;
    }

    public void setConsultant_Refrence(String consultant_Refrence) {
        Consultant_Refrence = consultant_Refrence;
    }

    public String getHousing_Refrence() {
        return Housing_Refrence;
    }

    public void setHousing_Refrence(String housing_Refrence) {
        Housing_Refrence = housing_Refrence;
    }

    public String getTeam_Refrence() {
        return Team_Refrence;
    }

    public void setTeam_Refrence(String team_Refrence) {
        Team_Refrence = team_Refrence;
    }

    public String getTrainingManager_Refrence() {
        return TrainingManager_Refrence;
    }

    public void setTrainingManager_Refrence(String trainingManager_Refrence) {
        TrainingManager_Refrence = trainingManager_Refrence;
    }

    public String getMarketingManager_Refrence() {
        return MarketingManager_Refrence;
    }

    public void setMarketingManager_Refrence(String marketingManager_Refrence) {
        MarketingManager_Refrence = marketingManager_Refrence;
    }

    public String getInstructor_Refrence() {
        return Instructor_Refrence;
    }

    public void setInstructor_Refrence(String instructor_Refrence) {
        Instructor_Refrence = instructor_Refrence;
    }
}

package com.example.admin.fpbackend.DataObjects;

/**
 * Created by Admin on 10/22/2017.
 */

public class GradedAssignmentInfoClass {
    String titleAssignment;
    float grade;
    String feedback;

    public GradedAssignmentInfoClass() {
    }

    public GradedAssignmentInfoClass(String titleAssignment, float grade, String feedback) {
        this.titleAssignment = titleAssignment;
        this.grade = grade;
        this.feedback = feedback;
    }

    public String getTitleAssignment() {
        return titleAssignment;
    }

    public void setTitleAssignment(String titleAssignment) {
        this.titleAssignment = titleAssignment;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

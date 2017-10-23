package com.example.admin.fpbackend.DataObjects;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 10/23/2017.
 */

public class TodayAssigmentInfoClass {
    String title;
    String description;
    Date dueDate;
    String sylbusLink;

    public TodayAssigmentInfoClass() {
    }

    public TodayAssigmentInfoClass(String title, String description, Date dueDate, String sylbusLink) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.sylbusLink = sylbusLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getSylbusLink() {
        return sylbusLink;
    }

    public void setSylbusLink(String sylbusLink) {
        this.sylbusLink = sylbusLink;
    }
}

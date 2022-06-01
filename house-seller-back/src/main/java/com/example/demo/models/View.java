package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "views")
public class View {
    @Id
    private String id;
    private String userID;
    private String houseID;
    private Date viewDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID(String houseID) {
        this.houseID = houseID;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    @Override
    public String toString() {
        return "View{" +
                "id='" + id + '\'' +
                ", userID='" + userID + '\'' +
                ", houseID='" + houseID + '\'' +
                ", viewDate=" + viewDate +
                '}';
    }
}

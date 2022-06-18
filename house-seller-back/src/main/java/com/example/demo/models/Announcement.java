package com.example.demo.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "announcements")
public class Announcement {

    @Id
    private String id;
    private String owner;
    private int price;
    private int reduction;
    private String type; /* This will dictate wich object will be in announcementObject. Frontend will make in mutliple steps the creation. */
    private Object announcementObject;
//    Check in frontend how to1 recognise between house object and appartment object to make a difference for containers.


    public Announcement() {
    }

    public Announcement(String id, String owner, int price, int reduction, String type, Object announcementObject) {
        this.id = id;
        this.owner = owner;
        this.price = price;
        this.reduction = reduction;
        this.type = type;
        this.announcementObject = announcementObject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getAnnouncementObject() {
        return announcementObject;
    }

    public void setAnnouncementObject(Object announcementObject) {
        this.announcementObject = announcementObject;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", price=" + price +
                ", reduction=" + reduction +
                ", type='" + type + '\'' +
                ", announcementObject=" + announcementObject +
                '}';
    }
}

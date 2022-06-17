package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "appartments")
public class Appartment {
    @Id
    private String id;
    private String status;
    private String address;
    private String owner;
    private int price;
    private int reduction;
    private String description;
    private Characteristics characteristics;

    public Appartment() {
    }

    public Appartment(String id, String status, String address, String owner, int price, int reduction, String description, Characteristics characteristics) {
        this.id = id;
        this.status = status;
        this.address = address;
        this.owner = owner;
        this.price = price;
        this.reduction = reduction;
        this.description = description;
        this.characteristics = characteristics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "Appartments{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", owner='" + owner + '\'' +
                ", price=" + price +
                ", reduction=" + reduction +
                ", description='" + description + '\'' +
                ", characteristics=" + characteristics +
                '}';
    }
}

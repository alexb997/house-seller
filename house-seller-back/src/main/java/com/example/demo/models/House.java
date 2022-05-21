package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "houses")
public class House {
    @Id
    private String id;
    private int number;
    private String status;
    private String dimensions;
    private String address;
    private String owner;
    private int price;

    public House() {
    }

    public House(int number, String status, String dimensions, String address, String owner, int price) {
        this.number = number;
        this.status = status;
        this.dimensions = dimensions;
        this.address = address;
        this.owner = owner;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
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

    public House updateWith(House house) {
        House updated = new House(
                house.number,
                house.status,
                house.address,
                house.dimensions,
                house.owner,
                house.price
        );
        return updated;
    }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", status='" + status + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", address='" + address + '\'' +
                ", owner='" + owner + '\'' +
                ", price=" + price +
                '}';
    }
}

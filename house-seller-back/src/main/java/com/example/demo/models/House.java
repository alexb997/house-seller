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
    private int reduction;
    private String description;
    private Characteristics characteristics;


    public House() {
    }

    public House(int number, String status, String dimensions, String address, String owner, int price, int reduction, String description,Characteristics characteristics) {
        this.number = number;
        this.status = status;
        this.dimensions = dimensions;
        this.address = address;
        this.owner = owner;
        this.price = price;
        this.description = description;
        this.reduction = reduction;
        this.characteristics= characteristics;
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

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public House updateWith(House house) {
        House updated = new House(
                house.number,
                house.status,
                house.dimensions,
                house.address,
                house.owner,
                house.price,
                house.reduction,
                house.description,
                house.characteristics
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

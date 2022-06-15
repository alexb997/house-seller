package com.example.demo.models;

public class Characteristics {

    String area;
    Integer usableSurface;
    Integer builtSurface;
    Integer surface;
    Integer bedroomsNumber;
    Integer roomsNumber;
    Integer bathroomsNumber;
    Integer parkingSpots;
    Integer constructionYear;
    String maxHeight;
    Integer balconiesNumber;

    public Characteristics() {
    }

    public Characteristics(String area, Integer usableSurface, Integer builtSurface, Integer surface, Integer bedroomsNumber, Integer roomsNumber, Integer bathroomsNumber, Integer parkingSpots, Integer constructionYear, String maxHeight, Integer balconiesNumber) {
        this.area = area;
        this.usableSurface = usableSurface;
        this.builtSurface = builtSurface;
        this.surface = surface;
        this.bedroomsNumber = bedroomsNumber;
        this.roomsNumber = roomsNumber;
        this.bathroomsNumber = bathroomsNumber;
        this.parkingSpots = parkingSpots;
        this.constructionYear = constructionYear;
        this.maxHeight = maxHeight;
        this.balconiesNumber = balconiesNumber;
    }

    @Override
    public String toString() {
        return "Characteristics{" +
                "area='" + area + '\'' +
                ", usableSurface=" + usableSurface +
                ", builtSurface=" + builtSurface +
                ", surface=" + surface +
                ", bedroomsNumber=" + bedroomsNumber +
                ", roomsNumber=" + roomsNumber +
                ", bathroomsNumber=" + bathroomsNumber +
                ", parkingSpots=" + parkingSpots +
                ", constructionYear=" + constructionYear +
                ", maxHeight='" + maxHeight + '\'' +
                ", balconiesNumber=" + balconiesNumber +
                '}';
    }
}

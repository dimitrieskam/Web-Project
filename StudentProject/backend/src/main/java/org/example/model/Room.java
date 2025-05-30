package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.model.enumerations.RoomType;


@Entity
@Table(name = "room")
public class Room {
    @Id
    private String name;

    private String locationDescription;

    private String equipmentDescription;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Long capacity;

    public Room() {
    }

    public Room(String name, String locationDescription, String equipmentDescription, RoomType type, Long capacity) {
        this.name = name;
        this.locationDescription = locationDescription;
        this.equipmentDescription = equipmentDescription;
        this.type = type;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}

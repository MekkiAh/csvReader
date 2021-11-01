package com.example.csvreader;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Car {
    @Id
    int id ;
    String manufacturer ;
    String model ;
    String type ;

    public String getManufacturer() {
        return manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.mercadolivre.desafioquality.models;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class House {

    @NotEmpty(message = "Property name cannot be empty.")
    @Size(max = 30, message = "The length of the name cannot exceed 30 characters.")
    @Pattern(regexp = "^[A-Z][ [A-z]+]*", message = "The property name must start with a capital letter")
    private String prop_name;

    @NotEmpty(message = "Neighborhood name cannot be empty.")
    @Size(max = 45, message = "The neighborhood length cannot exceed 45 characters.")
    private String prop_district;

    @Valid
    private List<Room> rooms;

    public House() {
    }

    public House(String prop_name, String prop_district, List<Room> rooms) {
        this.prop_name = prop_name;
        this.prop_district = prop_district;
        this.rooms = rooms;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public String getProp_district() {
        return prop_district;
    }

    public void setProp_district(String prop_district) {
        this.prop_district = prop_district;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}

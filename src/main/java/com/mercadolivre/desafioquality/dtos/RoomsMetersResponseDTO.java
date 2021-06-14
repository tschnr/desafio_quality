package com.mercadolivre.desafioquality.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class RoomsMetersResponseDTO {
    @NotEmpty(message = "Property name cannot be empty.")
    @Size(max = 30, message = "The length of the name cannot exceed 30 characters.")
    @Pattern(regexp = "^[A-Z].*", message = "The property name must start with a capital letter")
    private String prop_name;

    @NotEmpty(message = "Neighborhood name cannot be empty.")
    @Size(max = 45, message = "The neighborhood length cannot exceed 45 characters.")
    private String prop_district;

    @Valid
    List<RoomDTO> rooms;

    public RoomsMetersResponseDTO(@NotEmpty(message = "Property name cannot be empty.") @Size(max = 30, message = "The length of the name cannot exceed 30 characters.") @Pattern(regexp = "^[A-Z][ [A-z]+]*", message = "The property name must start with a capital letter") String prop_name, String prop_district, List<RoomDTO> rooms) {
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

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }
}

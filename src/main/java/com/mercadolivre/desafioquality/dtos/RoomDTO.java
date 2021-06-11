package com.mercadolivre.desafioquality.dtos;

import javax.validation.constraints.*;

public class RoomDTO {

    @NotEmpty(message = "Room name cannot be empty.")
    @Size(max = 30, message = "Room length cannot exceed 30 characters.")
    @Pattern(regexp = "^[A-Z][ [A-z]+]*", message = "The room name must start with a capital letter.")
    String room_name;

    @NotNull(message = "Room width cannot be empty.")
    @Max(value = 25, message = "The maximum width allowed per room is 25 meters.")
    Double room_width;

    @NotNull(message = "Room length cannot be empty.")
    @Max(value = 25, message = "The maximum length allowed per room is 33 meters.")
    Double room_length;

    @NotNull
    Double total_meters;

    public RoomDTO(){

    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public Double getRoom_width() {
        return room_width;
    }

    public void setRoom_width(Double room_width) {
        this.room_width = room_width;
    }

    public Double getRoom_length() {
        return room_length;
    }

    public void setRoom_length(Double room_length) {
        this.room_length = room_length;
    }

    public Double getTotal_meters() {
        return total_meters;
    }

    public void setTotal_meters(Double total_meters) {
        this.total_meters = total_meters;
    }


}

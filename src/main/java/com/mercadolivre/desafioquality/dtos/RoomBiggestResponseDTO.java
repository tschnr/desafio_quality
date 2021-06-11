package com.mercadolivre.desafioquality.dtos;

import com.mercadolivre.desafioquality.models.House;
import com.mercadolivre.desafioquality.models.Room;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RoomBiggestResponseDTO {
    @NotNull
    private Room room_biggest;

    @Valid
    private House house;

    public RoomBiggestResponseDTO(@NotNull Room room_biggest, @Valid House house) {
        this.room_biggest = room_biggest;
        this.house = house;
    }

    public Room getRoom_biggest() {
        return room_biggest;
    }

    public void setRoom_biggest(Room room_biggest) {
        this.room_biggest = room_biggest;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}

package com.mercadolivre.desafioquality.dtos;

import com.mercadolivre.desafioquality.models.House;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HouseValueResponseDTO {
    @NotNull
    private Double value_house;

    @Valid
    private House house;

    public HouseValueResponseDTO(@NotNull Double value_house, @Valid House house) {
        this.value_house = value_house;
        this.house = house;
    }

    public Double getValue_house() {
        return value_house;
    }

    public void setValue_house(Double value_house) {
        this.value_house = value_house;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}

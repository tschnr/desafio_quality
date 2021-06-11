package com.mercadolivre.desafioquality.dtos;

import com.mercadolivre.desafioquality.models.House;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HouseTotalMetersResponseDTO {
    @NotNull
    private Double meters_total;

    @Valid
    private House house;

    public HouseTotalMetersResponseDTO() {
    }

    public HouseTotalMetersResponseDTO(@NotNull Double meters_total, @Valid House house) {
        this.meters_total = meters_total;
        this.house = house;
    }

    public Double getMeters_total() {
        return meters_total;
    }

    public void setMeters_total(Double meters_total) {
        this.meters_total = meters_total;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}

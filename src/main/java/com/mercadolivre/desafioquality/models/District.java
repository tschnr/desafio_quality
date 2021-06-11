package com.mercadolivre.desafioquality.models;

public class District {
    private String district;
    private Double value_district;

    public District(Double value_district, String district) {
        this.value_district = value_district;
        this.district = district;
    }

    public Double getValue_district() {
        return value_district;
    }

    public void setValue_district(Double value_district) {
        this.value_district = value_district;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

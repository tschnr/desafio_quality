package com.mercadolivre.desafioquality.repositories;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DistrictRepository {

     public HashMap<String, Double> districts(){

        HashMap<String, Double> district = new HashMap<>();
        district.put("Colonia", 800.);
        district.put("Bertoldo", 600.);
        district.put("Alvorada", 700.);

        return district;
    }

}

package com.mercadolivre.desafioquality.controllers;


import com.mercadolivre.desafioquality.dtos.HouseTotalMetersResponseDTO;
import com.mercadolivre.desafioquality.dtos.HouseValueResponseDTO;
import com.mercadolivre.desafioquality.dtos.RoomBiggestResponseDTO;
import com.mercadolivre.desafioquality.dtos.RoomsMetersResponseDTO;
import com.mercadolivre.desafioquality.models.House;
import com.mercadolivre.desafioquality.services.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService){
        this.houseService = houseService;
    }

    @PostMapping("/calculate-meters")
    public ResponseEntity<HouseTotalMetersResponseDTO> calculateTotalHouseMeters(@Valid @RequestBody House house){

       HouseTotalMetersResponseDTO houseTotalMetersResponseDTO = houseService.calculateTotalHouseMeters(house);
       return ResponseEntity.ok().body(houseTotalMetersResponseDTO);
    }

    @PostMapping("/calculate-value")
    public ResponseEntity<HouseValueResponseDTO> calculateTotalHouseValue(@Valid @RequestBody House house){

        HouseValueResponseDTO houseTotalMetersResponseDTO = houseService.calculateHouseValue(house);
        return ResponseEntity.ok().body(houseTotalMetersResponseDTO);
    }

    @PostMapping("/room-biggest")
    public ResponseEntity<RoomBiggestResponseDTO> roomBiggest(@Valid @RequestBody House house){

        RoomBiggestResponseDTO houseTotalMetersResponseDTO = houseService.roomBiggest(house);
        return ResponseEntity.ok().body(houseTotalMetersResponseDTO);
    }

    @PostMapping("/room-meters")
    public ResponseEntity<RoomsMetersResponseDTO> roomMeters(@Valid @RequestBody House house){

        RoomsMetersResponseDTO houseTotalMetersResponseDTO = houseService.roomMeters(house);
        return ResponseEntity.ok().body(houseTotalMetersResponseDTO);
    }

}

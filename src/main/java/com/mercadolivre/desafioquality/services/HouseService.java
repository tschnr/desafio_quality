package com.mercadolivre.desafioquality.services;

import com.mercadolivre.desafioquality.dtos.HouseTotalMetersResponseDTO;
import com.mercadolivre.desafioquality.dtos.HouseValueResponseDTO;
import com.mercadolivre.desafioquality.dtos.RoomBiggestResponseDTO;
import com.mercadolivre.desafioquality.dtos.RoomsMetersResponseDTO;
import com.mercadolivre.desafioquality.models.House;
import org.springframework.stereotype.Service;

@Service
public interface HouseService {

    HouseTotalMetersResponseDTO calculateTotalHouseMeters(House house);

    HouseValueResponseDTO calculateHouseValue(House house);

    RoomBiggestResponseDTO roomBiggest(House house);

    RoomsMetersResponseDTO roomMeters(House house);
}

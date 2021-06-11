package com.mercadolivre.desafioquality.services;

import com.mercadolivre.desafioquality.dtos.*;
import com.mercadolivre.desafioquality.exceptions.DistrictNotFound;
import com.mercadolivre.desafioquality.repositories.DistrictRepository;
import com.mercadolivre.desafioquality.models.House;
import com.mercadolivre.desafioquality.models.Room;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService{

    DistrictRepository districtRepository;

    public HouseServiceImpl(DistrictRepository districtRepository){
        this.districtRepository = districtRepository;
    }

    @Override
    public HouseTotalMetersResponseDTO calculateTotalHouseMeters(House house) {

        validDistrict(house);
        Double calculateMeters = house.getRooms().stream().mapToDouble(Room::squareMeters).sum();

        return new HouseTotalMetersResponseDTO(calculateMeters, house);
    }

    @Override
    public HouseValueResponseDTO calculateHouseValue(House house) {

        validDistrict(house);
        Double valueDistrict =  districtRepository.districts().get(house.getProp_district());
        Double valueHouse = house.getRooms().stream().mapToDouble(x -> x.squareMeters() * valueDistrict).sum();

        return new HouseValueResponseDTO(valueHouse, house);
    }

    @Override
    public RoomBiggestResponseDTO roomBiggest(House house) {

        validDistrict(house);
        Room room = house.getRooms().stream().max(Comparator.comparing(Room::squareMeters)).orElse(null);
        return new RoomBiggestResponseDTO(room, house);
    }

    @Override
    public RoomsMetersResponseDTO roomMeters(House house) {

        validDistrict(house);
        List<RoomDTO> room = house.getRooms().stream().map(this::convertToRoomDTO).collect(Collectors.toList());
        return new RoomsMetersResponseDTO(house.getProp_name(), house.getProp_district(), room);
    }

    public void validDistrict(House house) {
        if (!districtRepository.districts().containsKey(house.getProp_district())) throw new DistrictNotFound();
    }

    private RoomDTO convertToRoomDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_name(room.getRoom_name());
        roomDTO.setRoom_width(room.getRoom_width());
        roomDTO.setRoom_length(room.getRoom_length());
        roomDTO.setTotal_meters(room.squareMeters());

        return roomDTO;
    }

}

package com.mercadolivre.desafioquality.unit;

import com.mercadolivre.desafioquality.dtos.HouseTotalMetersResponseDTO;
import com.mercadolivre.desafioquality.dtos.HouseValueResponseDTO;
import com.mercadolivre.desafioquality.dtos.RoomBiggestResponseDTO;
import com.mercadolivre.desafioquality.exceptions.DistrictNotFound;
import com.mercadolivre.desafioquality.models.House;
import com.mercadolivre.desafioquality.models.Room;
import com.mercadolivre.desafioquality.repositories.DistrictRepository;
import com.mercadolivre.desafioquality.services.HouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith({SpringExtension.class})
@Import({HouseServiceImpl.class, DistrictRepository.class})
public class HouseServiceImplTest {

    @Autowired
    HouseServiceImpl houseServiceImpl;

    static House house;

    @BeforeEach
     void initHouse(){
        house = new House();
        house.setProp_name("Casa das casas");
        house.setProp_district("Colonia");

        List<Room> rooms = List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Sala", 4., 4.),
                new Room("Cozinha", 6., 5.),
                new Room("Quintal", 8., 10.),
                new Room("Quarto secundario", 4., 4.)
        );

        house.setRooms(rooms);
    }


    @Test
    public void testCalculateTotalHouseMetersEquals(){
        HouseTotalMetersResponseDTO houseTotalMetersResponseDTO =  houseServiceImpl.calculateTotalHouseMeters(house);

        double totalMeters = houseTotalMetersResponseDTO.getMeters_total();

        assertEquals(257., totalMeters);
    }


    @Test
    public void testDistrictExists(){

        assertDoesNotThrow(() -> houseServiceImpl.validDistrict(house));
    }

    @Test
    public void testDistrictIfNotExist(){
        house.setProp_district("Santa Luzia");

        assertThrows(DistrictNotFound.class, () -> houseServiceImpl.validDistrict(house));
    }


    @Test
    public void testRoomBiggestEquals(){
        RoomBiggestResponseDTO roomBiggestResponseDTO = houseServiceImpl.roomBiggest(house);

        String roomBiggest = roomBiggestResponseDTO.getRoom_biggest().getRoom_name();

        assertEquals("Lavanderia", roomBiggest);
    }

    @Test
    public void testRoomBiggestNotEquals(){
        RoomBiggestResponseDTO roomBiggestResponseDTO = houseServiceImpl.roomBiggest(house);

        String roomBiggest = roomBiggestResponseDTO.getRoom_biggest().getRoom_name();

        assertNotEquals("Quarto principal", roomBiggest);
    }

    @Test
    public void testCalculateRoomsMetersEquals(){
        houseServiceImpl.roomMeters(house).getRooms().forEach(
                district -> {
                    Double totalArea = district.getRoom_length() * district.getRoom_width();
                    assertEquals(totalArea, district.getTotal_meters());
                });
    }

    @Test
    public void testCalculateRoomsMetersNotEquals(){
        houseServiceImpl.roomMeters(house).getRooms().forEach(
                district -> {
                    Double totalArea = district.getRoom_length() * district.getRoom_width() + 1.;
                    assertNotEquals(totalArea, district.getTotal_meters());
                });
    }

    @Test
    public void testValueHouseEquals(){
        HouseValueResponseDTO houseValueResponseDTO = houseServiceImpl.calculateHouseValue(house);

        Double valueTotal = houseValueResponseDTO.getValue_house();

        assertEquals(205600., valueTotal);

    }

    @Test
    public void testValueHouseNotEquals(){
        HouseValueResponseDTO houseValueResponseDTO = houseServiceImpl.calculateHouseValue(house);

        Double valueTotal = houseValueResponseDTO.getValue_house();

        assertNotEquals(205600.01, valueTotal);

    }
}

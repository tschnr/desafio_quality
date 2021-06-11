package com.mercadolivre.desafioquality.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafioquality.models.House;
import com.mercadolivre.desafioquality.models.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void calculateTotalHouseMetersWithoutPropName() throws Exception {

        House house = new House();
        house.setProp_district("Colonia");
        house.setRooms(List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Quintal", 8., 10.)
        ));

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/calculate-meters").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andExpect(
                jsonPath("$.prop_name")
                        .value("Property name cannot be empty.")
        );
    }

    @Test
    public void calculateTotalHouseMetersNotCapitalLetterName() throws Exception {

        House house = new House();
        house.setProp_name("casinha");
        house.setProp_district("Colonia");
        house.setRooms(List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Quintal", 8., 10.)
        ));

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/calculate-meters").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.prop_name")
                                .value("The property name must start with a capital letter")
                );
    }


    @Test
    public void calculateTotalHouseGood() throws Exception {

        House house = new House();
        house.setProp_name("Casa de todas as casas");
        house.setProp_district("Colonia");
        house.setRooms(List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Quintal", 8., 10.)
        ));

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/calculate-meters").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void calculateHouseValueGood() throws Exception {

        House house = new House();
        house.setProp_name("Casa de todas as casas");
        house.setProp_district("Colonia");
        house.setRooms(List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Quintal", 8., 10.)
        ));

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/calculate-value").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(
                jsonPath("$.value_house")
                        .value(156000.)
        );
    }


    @Test
    public void calculateHouseValueDistrictNotFound() throws Exception {

        House house = new House();
        house.setProp_name("Casa de todas as casas");
        house.setProp_district("Santa Luzia");
        house.setRooms(List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Quintal", 8., 10.)
        ));

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/calculate-value").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void roomBiggestWithoutRooms() throws Exception {

        House house = new House();
        house.setProp_name("Casa de todas as casas");
        house.setProp_district("Colonia");

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/room-biggest").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void roomBiggestGood() throws Exception {

        House house = new House();
        house.setProp_name("Casa de todas as casas");
        house.setProp_district("Colonia");
        house.setRooms(List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Quintal", 8., 10.)
        ));

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/room-biggest").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.room_biggest.room_name").value("Lavanderia"));
    }

    @Test
    public void roomMetersRoomWidthString() throws Exception {

        String payload = "{\"prop_name\": \"Casa de todas as casa\"," +
                " \"prop_district\": \"Colonia\", \"rooms\": [" +
                " {\"room_name\": \"Lavanderia\", \"room_width\": \"Abc\", \"room_length\": 10.0 }," +
                " {\"room_name\": \"Quarto Principal\", \"room_width\": 5.0, \"room_length\": 3.0 }, " +
                " {\"room_name\": \"Quintal\", \"room_width\": 8.0, \"room_length\": 10.0 }" +
                "]}";

        this.mockMvc.perform(
                post("/room-biggest").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void roomMetersRoomLengthString() throws Exception {

        String payload = "{\"prop_name\": \"Casa de todas as casa\"," +
                " \"prop_district\": \"Colonia\", \"rooms\": [" +
                " {\"room_name\": \"Lavanderia\", \"room_width\": 10.0, \"room_length\": \"asdaq \" }," +
                " {\"room_name\": \"Quarto Principal\", \"room_width\": 5.0, \"room_length\": 3.0 }, " +
                " {\"room_name\": \"Quintal\", \"room_width\": 8.0, \"room_length\": 10.0 }" +
                "]}";

        this.mockMvc.perform(
                post("/room-biggest").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void roomMetersGood() throws Exception {

        House house = new House();
        house.setProp_name("Casa de todas as casas");
        house.setProp_district("Colonia");
        house.setRooms(List.of(
                new Room("Lavanderia", 10., 10.),
                new Room("Quarto principal", 5., 3.),
                new Room("Quintal", 8., 10.)
        ));

        byte[] payload = objectMapper.writeValueAsBytes(house);

        this.mockMvc.perform(
                post("/room-meters").content(payload).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.rooms[0].total_meters").value(100));
    }


}

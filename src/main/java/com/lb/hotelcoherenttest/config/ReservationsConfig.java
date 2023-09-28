package com.lb.hotelcoherenttest.config;

import com.lb.hotelcoherenttest.dto.Reservation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Data
@Slf4j
public class ReservationsConfig {

    private Set<Reservation> reservationList = new HashSet<>();
    //public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
    @Bean
    public Set<Reservation> loadReservations() {
        log.info("Initializing reservations list");
        reservationList.add(new Reservation(1, "Luis Bautista", 1,
                Collections.singleton(LocalDate.parse("2023-09-25"))));

        reservationList.add(new Reservation(2, "Oziel Bautista", 1,
                Collections.singleton(LocalDate.parse("2023-09-25"))));

        return reservationList;
    }
}

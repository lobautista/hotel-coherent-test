package com.lb.hotelcoherenttest.persistence;

import com.lb.hotelcoherenttest.config.ReservationsConfig;
import com.lb.hotelcoherenttest.dto.InputReservation;
import com.lb.hotelcoherenttest.dto.Reservation;
import com.lb.hotelcoherenttest.exceptions.ClientNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class ReservationsPersistence {
    private ReservationsConfig reservationsConfig;

    public ReservationsPersistence(ReservationsConfig reservationsConfig) {
        this.reservationsConfig = reservationsConfig;
    }

    public Set<Reservation> getAllReservationsDetails() {
        return reservationsConfig.getReservationList();
    }

    public Reservation saveReservationDetails(Reservation reservation) {
        reservationsConfig.getReservationList().add(reservation);
        return reservation;
    }

    public Reservation updateReservationDetails(long id, InputReservation inputReservation) throws ClientNotFoundException {
        Reservation reservationById = reservationsConfig.getReservationList().stream()
                .filter(res -> id == res.getId()).findFirst().orElseThrow(() -> new ClientNotFoundException(id));

        reservationById.setClientFullName(inputReservation.getClientFullName());
        reservationById.setRoomNumber(inputReservation.getRoomNumber());
        reservationById.setReservationDates(inputReservation.getReservationDates());

        return reservationById;
    }
}

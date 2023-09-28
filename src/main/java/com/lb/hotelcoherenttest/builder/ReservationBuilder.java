package com.lb.hotelcoherenttest.builder;

import com.lb.hotelcoherenttest.dto.InputReservation;
import com.lb.hotelcoherenttest.dto.Reservation;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReservationBuilder {
    public Reservation buildReservation(InputReservation inputReservation, long id){
        Reservation reservation = new Reservation();
        reservation.setId(id + 1);
        reservation.setClientFullName(inputReservation.getClientFullName());
        reservation.setRoomNumber(inputReservation.getRoomNumber());
        reservation.setReservationDates(inputReservation.getReservationDates());
        return reservation;
    }
}

package com.lb.hotelcoherenttest.service;

import com.lb.hotelcoherenttest.builder.ReservationBuilder;
import com.lb.hotelcoherenttest.dto.InputReservation;
import com.lb.hotelcoherenttest.exceptions.ClientNotFoundException;
import com.lb.hotelcoherenttest.persistence.ReservationsPersistence;
import com.lb.hotelcoherenttest.dto.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReservationsServiceImpl implements ReservationsService {

    private ReservationsPersistence reservationsPersistence;
    private ReservationBuilder reservationBuilder;

    public ReservationsServiceImpl(ReservationsPersistence reservationsPersistence, ReservationBuilder reservationBuilder) {
        this.reservationsPersistence = reservationsPersistence;
        this.reservationBuilder = reservationBuilder;
    }

    @Override
    public Set<Reservation> getAllReservations() {
        return reservationsPersistence.getAllReservationsDetails();
    }

    @Override
    public Optional<Reservation> saveReservation(InputReservation inputReservation) {
        if (isValidRoom(inputReservation)) {
            Reservation reservation = reservationBuilder.buildReservation(inputReservation,
                    reservationsPersistence.getAllReservationsDetails().size());
            return Optional.of(reservationsPersistence.saveReservationDetails(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> updateReservation(long id, InputReservation inputReservation) throws ClientNotFoundException {
        if (isValidRoom(inputReservation)) {
            return Optional.of(reservationsPersistence.updateReservationDetails(id, inputReservation));
        }
        return Optional.empty();
    }

    private boolean isValidRoom(InputReservation inputReservation) {
        Set<Reservation> roomsReservation = reservationsPersistence.getAllReservationsDetails().stream()
                .filter(res -> res.getRoomNumber() == inputReservation.getRoomNumber())
                .collect(Collectors.toSet());

        if (!roomsReservation.isEmpty()) {
            return isValidDate(inputReservation, roomsReservation);
        }
        return true;
    }

    private boolean isValidDate(InputReservation inputReservation, Set<Reservation> roomsReservation) {
        Set<LocalDate> dates = new HashSet<>();

        for (Reservation res : roomsReservation) {
            dates.addAll(res.getReservationDates());
        }

        for (LocalDate date : inputReservation.getReservationDates()) {
            if (dates.contains(date)) {
                return false;
            }
        }
        return true;
    }
}

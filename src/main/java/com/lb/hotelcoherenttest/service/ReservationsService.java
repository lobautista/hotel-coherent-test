package com.lb.hotelcoherenttest.service;

import com.lb.hotelcoherenttest.dto.InputReservation;
import com.lb.hotelcoherenttest.dto.Reservation;

import java.util.Optional;
import java.util.Set;

public interface ReservationsService {
    Set<Reservation> getAllReservations();
    Optional<Reservation> saveReservation(InputReservation reservation);
    Optional<Reservation> updateReservation(long id, InputReservation reservation) throws ClassNotFoundException;
}

package com.lb.hotelcoherenttest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class InputReservation {
    private String clientFullName;
    private int roomNumber;
    private Set<LocalDate> reservationDates;
}

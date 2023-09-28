package com.lb.hotelcoherenttest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reservation {

    private long id;
    private String clientFullName;
    private int roomNumber;
    private Set<LocalDate> reservationDates;
    private String errorMessage;

    public Reservation(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Reservation(long id, String clientFullName, int roomNumber, Set<LocalDate> reservationDates) {
        this.id = id;
        this.clientFullName = clientFullName;
        this.roomNumber = roomNumber;
        this.reservationDates = reservationDates;
    }
}

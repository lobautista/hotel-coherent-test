package com.lb.hotelcoherenttest.controller;

import com.lb.hotelcoherenttest.dto.ErrorResponse;
import com.lb.hotelcoherenttest.dto.InputReservation;
import com.lb.hotelcoherenttest.service.ReservationsService;
import com.lb.hotelcoherenttest.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/reservation")
@Slf4j
public class ReserveController {

    private ReservationsService reservationsService;

    public ReserveController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping("getAllReservations")
    public ResponseEntity getAllReservations() {
        return new ResponseEntity(reservationsService.getAllReservations(), HttpStatus.OK);
    }

    @PostMapping("sendReservation")
    public ResponseEntity sendReservation(@RequestBody InputReservation reservation) {
        Optional response = reservationsService.saveReservation(reservation);
        if (response.isPresent()) {
            return new ResponseEntity(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse(Constants.VALID_INPUT_DATA_ERROR_MESSAGE), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("updateReservation")
    public ResponseEntity updateReservation(@RequestParam long id, @RequestBody InputReservation reservation) throws ClassNotFoundException {
        Optional response = reservationsService.updateReservation(id, reservation);
        if (response.isPresent()) {
            return new ResponseEntity(response.get(), HttpStatus.CREATED);
        }

        return new ResponseEntity(new ErrorResponse(Constants.VALID_INPUT_DATA_ERROR_MESSAGE), HttpStatus.BAD_REQUEST);
    }
}

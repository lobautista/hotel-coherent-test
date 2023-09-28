package com.lb.hotelcoherenttest.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(long id){
        super(String.format("Client with id %d not found", id));
    }
}

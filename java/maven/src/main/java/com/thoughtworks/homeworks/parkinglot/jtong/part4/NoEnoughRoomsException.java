package com.thoughtworks.homeworks.parkinglot.jtong.part4;

import java.util.NoSuchElementException;


public class NoEnoughRoomsException extends RuntimeException {
    public NoEnoughRoomsException(NoSuchElementException e) {

    }

    public NoEnoughRoomsException() {
    }
}

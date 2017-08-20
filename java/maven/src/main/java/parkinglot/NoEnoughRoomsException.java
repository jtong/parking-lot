package parkinglot;

import java.util.NoSuchElementException;


public class NoEnoughRoomsException extends RuntimeException {
    public NoEnoughRoomsException(NoSuchElementException e) {

    }

    public NoEnoughRoomsException() {
    }
}

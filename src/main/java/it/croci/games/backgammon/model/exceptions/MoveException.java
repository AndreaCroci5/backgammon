package it.croci.games.backgammon.model.exceptions;

public class MoveException extends Exception {

    public MoveException() {
        super("Illegal move");
    }
}

package it.croci.games.backgammon.model;

import it.croci.games.backgammon.model.exceptions.MoveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Circuit {
    //ATTRIBUTES
    private Map<Integer, Integer> boardCircuitAmount;
    private Map<Integer, PositionStatus> boardCircuitStatus;


    //CONSTRUCTOR

    public Circuit(Map<Integer, Integer> boardCircuitAmount, Map<Integer, PositionStatus> boardCircuitStatus) {
        this.boardCircuitAmount = boardCircuitAmount;
        this.boardCircuitStatus = boardCircuitStatus;
    }

    public Circuit() {
        this.boardCircuitAmount = new HashMap<>();
        this.boardCircuitStatus = new HashMap<>();

        //Circuit initialization
        initCircuit(this.boardCircuitAmount, this.boardCircuitStatus);
    }

    //GETTER AND SETTER


    public Map<Integer, Integer> getBoardCircuitAmount() {
        return boardCircuitAmount;
    }

    public void setBoardCircuitAmount(Map<Integer, Integer> boardCircuitAmount) {
        this.boardCircuitAmount = boardCircuitAmount;
    }

    public Map<Integer, PositionStatus> getBoardCircuitStatus() {
        return boardCircuitStatus;
    }

    public void setBoardCircuitStatus(Map<Integer, PositionStatus> boardCircuitStatus) {
        this.boardCircuitStatus = boardCircuitStatus;
    }

    //PUBLIC METHODS

    //Initialization of the board
    public void initCircuit(Map<Integer, Integer> boardCircuit, Map<Integer, PositionStatus> boardCircuitStatus) {
        for (int i=0; i<24; i++) {
            switch (i){
                case 0:
                    boardCircuit.put(i, 2);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_1);
                    break;
                case 5:
                    boardCircuit.put(i, 5);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_2);
                    break;
                case 7:
                    boardCircuit.put(i, 3);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_2);
                    break;
                case 11:
                    boardCircuit.put(i, 5);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_1);
                    break;
                case 12:
                    boardCircuit.put(i, 5);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_2);
                    break;
                case 16:
                    boardCircuit.put(i, 3);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_1);
                    break;
                case 18:
                    boardCircuit.put(i, 5);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_1);
                    break;
                case 23:
                    boardCircuit.put(i, 2);
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_2);
                    break;
                default:
                    boardCircuit.put(i, 0);
                    boardCircuitStatus.put(i, PositionStatus.FREE);
                    break;
            }
        }
    }


    //Dice move-set
    public int rollTheDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }


    public int dicePositionTranslator(PositionStatus player, int dice, int counterPosition) {
        if (player == PositionStatus.PLAYER_1)
            return counterPosition + dice;
        else
            return counterPosition - dice;
    }


    //Counters move-set

    public void moveCounter (PositionStatus player, int desiredPosition, int counterPosition) throws MoveException {
        //Exception check for circuitBoards bound
        if ((desiredPosition >= 24 || desiredPosition < 0) && (counterPosition >= 24 || counterPosition < 0)) {
            throw new MoveException();
        }

        //Counter chosen presence check
        if (this.boardCircuitAmount.get(counterPosition) != 0) {
            //Legal move check by checking on the counter picked and on the desired move
            if (checkCounterPresence(player, counterPosition) && checkCounterMove(player, desiredPosition)) {
                //Adjustment on initial position movement by reducing the total count of checkers in that position
                int startingPositionAmount = this.boardCircuitAmount.get(counterPosition) - 1;
                this.boardCircuitAmount.put(counterPosition, startingPositionAmount);
                if (startingPositionAmount == 0)
                    this.boardCircuitStatus.put(counterPosition, PositionStatus.FREE);
                //Adjustment on final position (destination/desired) by adding the total count of checkers in that position
                int destinationPositionAmount = this.boardCircuitAmount.get(desiredPosition) + 1;
                this.boardCircuitAmount.put(desiredPosition, destinationPositionAmount);
                if (this.boardCircuitStatus.get(desiredPosition) == PositionStatus.FREE)
                    this.boardCircuitStatus.put(desiredPosition, player);
            } else {
                throw new MoveException();
            }
        } else {
            throw new MoveException();
        }

    }

    public boolean checkCounterPresence(PositionStatus player, int counterPosition) {
        int checkersInAPosition = this.boardCircuitAmount.get(counterPosition);
        PositionStatus positionStatus = this.boardCircuitStatus.get(counterPosition);

        return checkersInAPosition != 0 && positionStatus == player;
    }

    public boolean checkCounterMove (PositionStatus player, int desiredPosition) {
        PositionStatus positionStatus = this.boardCircuitStatus.get(desiredPosition);

        return positionStatus == PositionStatus.FREE || positionStatus == player;
    }


}

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
                    boardCircuitStatus.put(i, PositionStatus.PLAYER_1);
                    break;
            }
        }
    }

}

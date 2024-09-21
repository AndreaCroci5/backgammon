package it.croci.games.backgammon.model;

import java.util.HashMap;
import java.util.Map;

public class Circuit {
    //ATTRIBUTES
    private Map<Integer, Integer> boardCircuit;


    //CONSTRUCTOR

    public Circuit(Map<Integer, Integer> boardCircuit) {
        this.boardCircuit = boardCircuit;
    }

    public Circuit() {
        this.boardCircuit = new HashMap<>();
        //Circuit initialization
        initCircuit(this.boardCircuit);
    }

    //GETTER AND SETTER


    public Map<Integer, Integer> getBoardCircuit() {
        return boardCircuit;
    }

    public void setBoardCircuit(Map<Integer, Integer> boardCircuit) {
        this.boardCircuit = boardCircuit;
    }



    //PUBLIC METHODS
    public void initCircuit(Map<Integer, Integer> boardCircuit) {
        for (int i=0; i<24; i++) {
            switch (i){
                case 0:
                    boardCircuit.put(i, 2);
                    break;
                case 11:
                    boardCircuit.put(i, 5);
                    break;
                case 16:
                    boardCircuit.put(i, 3);
                    break;
                case 18:
                    boardCircuit.put(i, 5);
                    break;
                default:
                    boardCircuit.put(i, 0);
                    break;
            }
        }
    }
}

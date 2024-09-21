package it.croci.games.backgammon.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {

    @Test
    void initPosition() {
        Circuit circuit = new Circuit();

        Map<Integer, Integer> boardCircuit = circuit.getBoardCircuit();

        //Test that the token are placed correctly
        assertEquals(2, (int) boardCircuit.get(0));
        assertEquals(5, (int) boardCircuit.get(11));
        assertEquals(3, (int) boardCircuit.get(16));
        assertEquals(5, (int) boardCircuit.get(18));
    }

    @Test
    void initTokenAmount() {
        Circuit circuit = new Circuit();

        Map<Integer, Integer> boardCircuit = circuit.getBoardCircuit();
        int tokenAmount = 0;
        for (Map.Entry<Integer, Integer> entry: boardCircuit.entrySet()) {
            tokenAmount += entry.getValue();
        }

        //Test that the token amount is correct (15)
        assertEquals(15, tokenAmount);
    }

}
package it.croci.games.backgammon.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {

    @Test
    void initPosition() {
        Circuit circuit = new Circuit();

        Map<Integer, Integer> boardCircuit = circuit.getBoardCircuitAmount();

        //Test that the counters are placed correctly
        //First player
        assertEquals(2, boardCircuit.get(0));
        assertEquals(5, boardCircuit.get(11));
        assertEquals(3, boardCircuit.get(16));
        assertEquals(5, boardCircuit.get(18));
        //Second player
        assertEquals(5, boardCircuit.get(5));
        assertEquals(3, boardCircuit.get(7));
        assertEquals(5, boardCircuit.get(12));
        assertEquals(2, boardCircuit.get(23));
    }

    @Test
    void initCountersAmount() {
        Circuit circuit = new Circuit();

        Map<Integer, Integer> boardCircuit = circuit.getBoardCircuitAmount();
        int countersAmount = 0;
        for (Map.Entry<Integer, Integer> entry: boardCircuit.entrySet()) {
            countersAmount += entry.getValue();
        }

        //Test that the counter amount is correct (30)
        assertEquals(30, countersAmount);
    }

    @Test
    void initPositionStatus() {
        Circuit circuit = new Circuit();

        Map<Integer, PositionStatus> boardCircuitStatus = circuit.getBoardCircuitStatus();

        //Test that the counters are placed correctly
        //First player
        assertEquals(PositionStatus.PLAYER_1, boardCircuitStatus.get(0));
        assertEquals(PositionStatus.PLAYER_1, boardCircuitStatus.get(11));
        assertEquals(PositionStatus.PLAYER_1, boardCircuitStatus.get(16));
        assertEquals(PositionStatus.PLAYER_1, boardCircuitStatus.get(18));
        //Second player
        assertEquals(PositionStatus.PLAYER_2, boardCircuitStatus.get(5));
        assertEquals(PositionStatus.PLAYER_2, boardCircuitStatus.get(7));
        assertEquals(PositionStatus.PLAYER_2, boardCircuitStatus.get(12));
        assertEquals(PositionStatus.PLAYER_2, boardCircuitStatus.get(23));

    }
}
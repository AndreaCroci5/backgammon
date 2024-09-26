package it.croci.games.backgammon.model;

import it.croci.games.backgammon.model.exceptions.MoveException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {

    @Test
    void initPosition () {
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
    void initCountersAmount () {
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
    void initPositionStatus () {
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

    @Test
    void diceRoll () {
        Circuit circuit = new Circuit();
        int roll = -4;

        for (int i = 0; i<20; i++) {
            roll = circuit.rollTheDice();
            assertTrue(roll>0 && roll<7);
        }
    }

    @Test
    void player1DiceRoll () {
        Circuit circuit = new Circuit();

        //Move translation by placing the counter in the position 2 (2+1=3 in reality)
        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_1, 4, 2);
        assertEquals(6, positionTranslated);
    }

    @Test
    void player2DiceRoll () {
        Circuit circuit = new Circuit();

        //Move translation by placing the counter in the position 2 (2+1=3 in reality)
        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_2, 4, 7);
        assertEquals(3, positionTranslated);
    }



    @Test
    void checkCorrectCheckerPick () {
        Circuit circuit = new Circuit();

        //TRUE CHECK
        int counterPositionP1 = 11;
        int counterPositionP2 = 12;

        assertTrue(circuit.checkCounterPresence(PositionStatus.PLAYER_1, counterPositionP1));
        assertTrue(circuit.checkCounterPresence(PositionStatus.PLAYER_2, counterPositionP2));

    }

    @Test
    void checkUnCorrectCheckerPick () {
        Circuit circuit = new Circuit();

        //FALSE CHECK
        int counterPosition = 1;

        assertFalse(circuit.checkCounterPresence(PositionStatus.PLAYER_1, counterPosition));
        assertFalse(circuit.checkCounterPresence(PositionStatus.PLAYER_2, counterPosition));
    }

    @Test
    void checkLegalMove () {
        Circuit circuit = new Circuit();

        //Move made by a player
        //Artificial dice roll
        int dice = 3;
        int counterPosition = 0;

        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_1, dice, counterPosition);

        assertTrue(circuit.checkCounterMove(PositionStatus.PLAYER_1, positionTranslated));

    }

    @Test
    void checkLegalMoveOnOwnedPosition () {
        Circuit circuit = new Circuit();

        //Move made by a player
        //Artificial dice roll
        int dice = 16;
        int counterPosition = 0;

        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_1, dice, counterPosition);

        assertTrue(circuit.checkCounterMove(PositionStatus.PLAYER_1, positionTranslated));

    }

    @Test
    void checkIllegalMoveP1 () {
        Circuit circuit = new Circuit();

        //Move made by player 1
        //Artificial dice roll
        int dice = 5;
        int counterPosition = 0;

        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_1, dice, counterPosition);

        assertFalse(circuit.checkCounterMove(PositionStatus.PLAYER_1, positionTranslated));

    }

    @Test
    void checkIllegalMoveP2 () {
        Circuit circuit = new Circuit();

        //Move made by player 2
        //Artificial dice roll
        int dice = 7;
        int counterPosition = 7;

        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_2, dice, counterPosition);

        assertFalse(circuit.checkCounterMove(PositionStatus.PLAYER_2, positionTranslated));
    }

    @Test
    void legalMovePlayer1 () {
        Circuit circuit = new Circuit();

        //Data Setup for the move made by Player 1
        int dice = 3;
        int counterPosition = 0;
        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_1, dice, counterPosition);

        //Effective move
        try{
            circuit.moveCounter(PositionStatus.PLAYER_1, positionTranslated, counterPosition);
        } catch (MoveException e) {
            fail("The test shouldn't fail");
        }


        int amountStartingPosition = circuit.getBoardCircuitAmount().get(0);
        int amountFinalPosition = circuit.getBoardCircuitAmount().get(3);
        PositionStatus statusStartingPosition = circuit.getBoardCircuitStatus().get(0);
        PositionStatus statusFinalPosition = circuit.getBoardCircuitStatus().get(3);
        assertEquals(1, amountStartingPosition);
        assertEquals(1, amountFinalPosition);
        assertTrue(statusStartingPosition == PositionStatus.PLAYER_1);
        assertTrue(statusFinalPosition == PositionStatus.PLAYER_1);

    }

    @Test
    void legalMovePlayer2 () {
        Circuit circuit = new Circuit();

        //Data Setup for the move made by Player 1
        int dice = 3;
        int counterPosition = 23;
        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_2, dice, counterPosition);

        //Effective move
        try{
            circuit.moveCounter(PositionStatus.PLAYER_2, positionTranslated, counterPosition);
        } catch (MoveException e) {
            fail("The test shouldn't fail");
        }


        int amountStartingPosition = circuit.getBoardCircuitAmount().get(23);
        int amountFinalPosition = circuit.getBoardCircuitAmount().get(20);
        PositionStatus statusStartingPosition = circuit.getBoardCircuitStatus().get(23);
        PositionStatus statusFinalPosition = circuit.getBoardCircuitStatus().get(20);
        assertEquals(1, amountStartingPosition);
        assertEquals(1, amountFinalPosition);
        assertTrue(statusStartingPosition == PositionStatus.PLAYER_2);
        assertTrue(statusFinalPosition == PositionStatus.PLAYER_2);

    }

    @Test
    void illegalMovePlayer2 () {
        Circuit circuit = new Circuit();

        //Data Setup for the move made by Player 1
        int dice = 5;
        int counterPosition = 5;
        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_2, dice, counterPosition);

        //Effective move test
        assertThrows(MoveException.class, () -> {
            circuit.moveCounter(PositionStatus.PLAYER_2, positionTranslated, counterPosition);
        });

    }

    @Test
    void illegalMovePlayer1 () {
        Circuit circuit = new Circuit();

        //Data Setup for the move made by Player 1
        int dice = 12;
        int counterPosition = 0;
        int positionTranslated = circuit.dicePositionTranslator(PositionStatus.PLAYER_1, dice, counterPosition);

        //Effective move test
        assertThrows(MoveException.class, () -> {
            circuit.moveCounter(PositionStatus.PLAYER_1, positionTranslated, counterPosition);
        });

    }


}
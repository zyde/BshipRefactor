package src.test.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.logic.GameState;

public class GameStateInitializationTest {

    private GameState state;

    @Before
    public void setupfixture() {
	state = new GameState();
    }

    @Test
    public void IsNotGameOver() {

	assertFalse(state.IsGameOver());
    }

    @Test
    public void NoShipsAreDeployed() {
	assertFalse(state.isBothPlayerAndAgentShipsDeployed());
    }

    @Test
    public void isPlayerTurn() {
	assertTrue(state.isPlayerTurn());
    }

    @Test
    public void notAgentTurn() {
	assertFalse(state.agentTurn);
    }

}

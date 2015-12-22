package src.test.java.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.main.java.logic.Grid;
import src.main.java.logic.ships.Ship;
import src.main.java.logic.ships.Submarine;


public class GridTest {
    
    private Grid grid;
    
    @Before
    public void SetupGrid(){
	grid = new Grid(10, 10);
	grid.addSub(0, 0, 0);
    }
    
    @Test
    public void checkSubNotSunkTest() {
	assertEquals(false, grid.isSubmarineSunk());
    }
    
    @Test
    public void checkSubSunkTest() {
	grid.shot(0, 0);
	grid.shot(0, 1);
	grid.shot(0, 2);	
	assertEquals(true, grid.isSubmarineSunk());
    }
}

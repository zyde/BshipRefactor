package src.main.java.ships;

/*
 * Author: Michael
 * Created: 07 December 2004 15:52:31
 * Modified: 07 December 2004 15:52:31
 */

import java.io.Serializable;

import src.main.java.Grid;

public abstract class Ship implements Serializable {
    public Grid board = null;
    private int segments;

    public void Ship() {
    }

    public boolean isSunk() {
	if (segments == 0)
	    return true;
	else
	    return false;
    }

    /**
     * Reduces the number of undamaged segments of the ship by one when called.
     * 
     * 
     * 
     * public void scoreHit() { segments = segments -1;
     * 
     * if (segments < 0 ) throw new
     * IllegalArgumentException("Segments var is less than 0");
     * 
     * }
     */
}

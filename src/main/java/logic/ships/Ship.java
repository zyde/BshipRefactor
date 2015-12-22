package src.main.java.logic.ships;

/*
 * Author: Michael
 * Created: 07 December 2004 15:52:31
 * Modified: 07 December 2004 15:52:31
 */

import java.io.Serializable;

import src.main.java.logic.Grid;

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

}

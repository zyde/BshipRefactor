package src.main.java.logic;

/*
 * Author: Michael Okarimia
 * Created: 10 November 2004 15:43:46
 * Modified: 10 November 2004 15:43:46
 * This program creates a grid that a game of 
 * Battleships can be played on
 * Improvements from code1 are:
 * 1: Destroyer ship now added
 * 2: Horizontal and vertical positioning is determined by int not char
 * 3: Grid is now Serializable
 */

import java.io.Serializable;

import src.main.java.logic.ships.Air;
import src.main.java.logic.ships.Battle;
import src.main.java.logic.ships.Dest;
import src.main.java.logic.ships.Mine;
import src.main.java.logic.ships.Sub;

public class Grid implements Serializable {
    // two dimensional array to hold the board data
    private int[][] board;

    private int numberOfRows;
    private int numberOfColumns;

    private boolean minePlaced = false;
    private boolean subPlaced = false;
    private boolean destPlaced = false;
    private boolean battlePlaced = false;
    private boolean airPlaced = false;
    private boolean allShipsSunk = false;

    private Mine minesweeper;
    private Sub submarine;
    private Dest destroyer;
    private Battle battleship;
    private Air aircraftCarrier;


    public Grid(int widthOfGrid, int heigthOfGrid) {
	
	setNumberOfRows(widthOfGrid);
	setNumberOfColumns(heigthOfGrid);

	initialize(widthOfGrid, heigthOfGrid);
    }





    private void initialize(int widthOfGrid, int heigthOfGrid) {
	createBoard();

	for (int a = 0; widthOfGrid < numberOfRows; widthOfGrid++)
	    for (int b = 0; heigthOfGrid < numberOfColumns; heigthOfGrid++)
		board[a][b] = 0;
    }





    private void createBoard() {
	board = new int[numberOfRows][numberOfColumns];
    }
    

    
    

    /**
     * Checks the grid references and returns a boolean value if there is a ship
     * on that spot
     * 
     * @param columnIndex
     *            the column of the grid reference
     * @param rowIndex
     *            the row of the grid reference
     * @return a boolean value, true if the grid contains a ship and false if it
     *         contains either a miss or empty
     */
    public boolean isSpotVacant(int columnIndex, int rowIndex) {
	int index = this.getGridVal(columnIndex, rowIndex);

	if (index > 1 && index < 8) {
	    return true;
	}
	return false;
    }

    /**
     * Checks if all ships are sunk
     */
    public boolean allShipsSunk() {
	if ((minesweeper.isSunk() && submarine.isSunk() && destroyer.isSunk()
		&& battleship.isSunk() && aircraftCarrier.isSunk()))
	    allShipsSunk = true;

	return allShipsSunk;
    }

    public boolean isMineShipSunk() {
	return minesweeper.isSunk();
    }

    public boolean isSubmarineSunk() {
	return submarine.isSunk();
    }

    public boolean isDestroyerSunk() {
	return destroyer.isSunk();
    }

    public boolean isBattleShipSunk() {
	return battleship.isSunk();
    }

    public boolean isAircraftcarrierSunk() {
	return aircraftCarrier.isSunk();
    }

    public boolean isMineShipPlaced() {
	return minePlaced;
    }

    public void setMineshipPlaced() {
	minePlaced = true;
    }

    public boolean addMineship(int i, int j, int s) {
	minesweeper = new Mine(this, i, j, s);
	return isMineShipPlaced();
    }

    public boolean isSubmarinePlaced() {
	return subPlaced;
    }

    public void setSubmarinePlaced() {
	subPlaced = true;
    }

    public boolean addSub(int i, int j, int s) {
	submarine = new Sub(this, i, j, s);
	return isSubmarinePlaced();
    }

    public boolean isDestroyerPlaced() {
	return destPlaced;
    }

    public void setDestroyerPlaced() {
	destPlaced = true;
    }

    public boolean addDestroyer(int i, int j, int s) {
	destroyer = new Dest(this, i, j, s);
	return isDestroyerPlaced();
    }

    public boolean isBattleShipPlaced() {
	return battlePlaced;
    }

    public void setBattleshipPlaced() {
	battlePlaced = true;
    }

    public boolean addBattleship(int i, int j, int s) {
	battleship = new Battle(this, i, j, s);
	return isBattleShipPlaced();
    }

    public boolean checkAirPlaced() {
	return airPlaced;
    }

    public void setAirPlaced() {
	airPlaced = true;
    }
    
    public boolean isAirPlaced() {
	return airPlaced;
    }

    public boolean allShipsPlaced() {

	if ((isMineShipPlaced() && isSubmarinePlaced() && isDestroyerPlaced()
		&& isBattleShipPlaced() && checkAirPlaced())) {
	    return true;
	} else {
	    return false;
	}
    }

    public boolean addAir(int i, int j, int s) {
	aircraftCarrier = new Air(this, i, j, s);
	return checkAirPlaced();
    }

    /**
     * This method is used by the ship classes to add the ships to the grid.
     * Sets the value of a grid location to a specified integer. The grid
     * location must be set to (zero) 0.
     * 
     * @param i
     *            the row index
     * @param j
     *            the column index
     * @param value
     *            the value of the square
     */
    public void set(int i, int j, int value) {
	if (i > numberOfRows || j > numberOfColumns)
	    throw new IllegalArgumentException(
		    "Number is bigger that the grid size");
	if (i < 0 || j < 0 || value < 0)
	    throw new IllegalArgumentException("Number cannot be negative");
	if (board[i][j] != 0)
	    throw new IllegalArgumentException("Initial Position occupied");
	if (value == 0)
	    throw new IllegalArgumentException("Number cannot = 0");
	board[i][j] = value;
    }

    /**
     * This method is used by the shot() method to update the grid. Sets the
     * value of a grid location to a specified integer. The grid location must
     * be set to (zero) 0.
     * 
     * @param i
     *            the row index
     * @param j
     *            the column index
     * @param value
     *            the value of the square
     */
    public void update(int i, int j, int value) {
	if (i > numberOfRows || j > numberOfColumns)
	    throw new IllegalArgumentException(
		    "Number is bigger that the grid size");
	if (i < 0 || j < 0)
	    throw new IllegalArgumentException("Number cannot be negative");
	if (value == 0)
	    throw new IllegalArgumentException("Number cannot = 0");
	board[i][j] = value;
    }

    /**
     * Returns the value of the given grid index
     * 
     * @param i
     *            the row index
     * @param j
     *            the column index
     */
    public int getGridVal(int i, int j) {
	if (i < 0 || j < 0)
	    throw new IllegalArgumentException("Number cannot be negative");
	if (i > numberOfRows || j > numberOfColumns)
	    throw new IllegalArgumentException(
		    "Number is bigger that the grid size");
	return board[i][j];
    }

    /**
     * Fires a shot on the grid
     */
    public boolean shot(int i, int j) {
	int sqr = this.getGridVal(i, j);
	String output = ("Shot at " + i + "," + j + " value of square is " + sqr);
	boolean hit = false;

	switch (sqr) {
	case 0:
	    hit = false;
	    output = ("Shot at " + i + "," + j + " MISS"
		    + " value of square is " + sqr);
	    this.update(i, j, 1);
	    break;
	case 1:
	    hit = false;
	    output = ("Shot at " + i + "," + j
		    + " INVALID shot already taken here"
		    + " value of square is " + sqr);
	    break;

	case 2:
	    minesweeper.scoreHit();

	    if (minesweeper.isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK Minesweeper"
			+ " value of square is " + sqr);

	    else if (minesweeper.isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 3:
	    submarine.isSunk();
	    submarine.scoreHit();

	    if (submarine.isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK Submarine"
			+ " value of square is " + sqr);

	    else if (submarine.isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 4:
	    battleship.scoreHit();

	    if (battleship.isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK Battleship"
			+ " value of square is " + sqr);

	    else if (battleship.isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 5:
	    aircraftCarrier.scoreHit();

	    if (aircraftCarrier.isSunk() == true)
		output = ("Shot at " + i + "," + j
			+ " HIT & SUNK Aircraft Carrier"
			+ " value of square is " + sqr);

	    else if (aircraftCarrier.isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 7:
	    destroyer.scoreHit();

	    if (destroyer.isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK destroyer"
			+ " value of square is " + sqr);

	    else if (destroyer.isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	default:
	    output = ("Shot at " + i + "," + j
		    + " ERROR shot is either already hit, or incorect"
		    + " value of square is " + sqr);
	    break;
	}

	if (sqr < 0)
	    output = ("Shot at "
		    + i
		    + ","
		    + j
		    + " ERROR location contains a sunk ship. Value of square is " + sqr);
	return hit;

    }

    /**
     * Creates a string representation of the game board like so: |000| |050|
     * |000|
     * 
     * @return the string representation
     */
    public String toString() {
	String r = "";

	// change these to ROWS to use the default
	for (int i = 0; i < numberOfRows; i++) {
	    r = r + "|";
	    for (int j = 0; j < numberOfColumns; j++)
		// change this to CoLumns for default
		r = r + board[i][j];
	    r = r + "|\n";
	}
	return r;
    }

    /**
     * Returns a string output of the status of the ships on the grid, whever
     * they are sunk or not.
     */
    public String printIsSunk() {
	String MINESWEEPER = ("Minesweeper is intact");
	String SUBMARINE = ("Submarine is intact");
	String DESTROYER = ("Destroyer is intact");
	String BATTLESHIP = ("Battleship is intact");
	String AIRCRAFTCARRIER = ("Aircraft Carrier is intact");

	if (minesweeper.isSunk() == true)
	    MINESWEEPER = ("Minesweeper is SUNK");

	if (submarine.isSunk() == true)
	    SUBMARINE = ("Submarine is SUNK");

	if (destroyer.isSunk() == true)
	    DESTROYER = ("Destroyer is SUNK");

	if (battleship.isSunk() == true)
	    BATTLESHIP = ("Battleship is SUNK");

	if (aircraftCarrier.isSunk() == true)
	    AIRCRAFTCARRIER = ("Aircraft Carrier is SUNK");

	return (MINESWEEPER + "\n" + SUBMARINE + "\n" + DESTROYER + "\n"
		+ BATTLESHIP + "\n" + AIRCRAFTCARRIER);
    }

    /**
     * Returns the a string returning the value of each ship's IsPlaced flag. If
     * a ship is placed this flag will change to true and the this method will
     * return a string confirming this.
     */
    public String printIsPlaced() {
	System.out.println("The following ships are now placed ");
	String Minesweeper = "Minesweeper NOT Placed";
	String Destroyer = "Destroyer NOT Placed";
	String Submarine = "Submarine NOT placed";
	String Battleship = "Battleship NOT placed";
	String AircraftCarrier = "Aircraft Carrier NOT placed";

	if (minePlaced == true)
	    Minesweeper = "Minesweeper has been placed";

	if (destPlaced == true)
	    Destroyer = "Destroyer has been placed";

	if (subPlaced == true)
	    Submarine = "Submarine has been placed";

	if (battlePlaced == true)
	    Battleship = "Battleship has been placed";

	if (isAirPlaced() == true)
	    AircraftCarrier = "Aircraft Carrier has been placed";

	return Minesweeper + "\n" + Destroyer + "\n" + Submarine + "\n"
		+ Battleship + "\n" + AircraftCarrier;
    }
    
    public int getNumberOfColumns() {
	return numberOfColumns;
    }

    public int getNumberOfRows() {
	return numberOfRows;
    }
    
    public void setNumberOfColumns(int numberOfColumns) {
	this.numberOfColumns = numberOfColumns;
    }
    
    public void setNumberOfRows(int numberOfRows) {
	this.numberOfRows = numberOfRows;
    }

}

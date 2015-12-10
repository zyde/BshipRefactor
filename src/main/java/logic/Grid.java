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

    private GridData data = new GridData();


    public Grid(int widthOfGrid, int heigthOfGrid) {
	
	setNumberOfRows(widthOfGrid);
	setNumberOfColumns(heigthOfGrid);

	initialize(widthOfGrid, heigthOfGrid);
    }





    private void initialize(int widthOfGrid, int heigthOfGrid) {
	createBoard();

	for (int a = 0; widthOfGrid < data.getNumberOfRows(); widthOfGrid++)
	    for (int b = 0; heigthOfGrid < data.getNumberOfColumns(); heigthOfGrid++)
		data.getGameDataBoard()[a][b] = 0;
    }





    private void createBoard() {
	data.setGameDataBoard(new int[data.getNumberOfRows()][data.getNumberOfColumns()]);
    }
    

    
   
    public boolean isSpotReserved(int columnIndex, int rowIndex) {
	int index = getGridVal(columnIndex, rowIndex);

	if (hasInsertedValue(index)) {
	    return true;
	}
	return false;
    }





    private boolean hasInsertedValue(int index) {
	return index > 1 && index < 8;
    }

    
    public boolean areShipsSunk() {
	return isMineShipSunk() && isSubmarineSunk()
		&& isDestroyerSunk() && isBattleShipSunk();
    }


    public boolean isMineShipPlaced() {
	return data.isMinePlaced();
    }

    public void setMineshipPlaced() {
	data.setMinePlaced(true);
    }

    public boolean addMineship(int i, int j, int s) {
	data.setMinesweeper(new Mine(this, i, j, s));
	return isMineShipPlaced();
    }

    public boolean isSubmarinePlaced() {
	return data.isSubPlaced();
    }

    public void setSubmarinePlaced() {
	data.setSubPlaced(true);
    }

    public boolean addSub(int i, int j, int s) {
	data.setSubmarine(new Sub(this, i, j, s));
	return isSubmarinePlaced();
    }

    public boolean isDestroyerPlaced() {
	return data.isDestPlaced();
    }

    public void setDestroyerPlaced() {
	data.setDestPlaced(true);
    }

    public boolean addDestroyer(int i, int j, int s) {
	data.setDestroyer(new Dest(this, i, j, s));
	return isDestroyerPlaced();
    }

    public boolean isBattleShipPlaced() {
	return data.isBattlePlaced();
    }

    public void setBattleshipPlaced() {
	data.setBattlePlaced(true);
    }

    public boolean addBattleship(int i, int j, int s) {
	data.setBattleship(new Battle(this, i, j, s));
	return isBattleShipPlaced();
    }

    public boolean checkAirPlaced() {
	return data.isAirPlaced();
    }

    public void setAirPlaced() {
	data.setAirPlaced(true);
    }
    
    public boolean isAirPlaced() {
	return data.isAirPlaced();
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
	data.setAircraftCarrier(new Air(this, i, j, s));
	return checkAirPlaced();
    }


    public void setShip(int rowIndex, int columnIndex, int newSpotValue) {
	validateNumberForGrid(rowIndex, columnIndex);
	validateNumberNegative(rowIndex, columnIndex, newSpotValue);
	validateOccupied(rowIndex, columnIndex);
	validateZero(newSpotValue);
	insertSpotValue(rowIndex, columnIndex, newSpotValue);
    }





    private void validateZero(int newSpotValue) {
	if (isZero(newSpotValue))
	    throw new IllegalArgumentException("Number cannot = 0");
    }





    private void validateOccupied(int rowIndex, int columnIndex) {
	if (isPositionOccupied(rowIndex, columnIndex))
	    throw new IllegalArgumentException("Initial Position occupied");
    }





    private void validateNumberNegative(int rowIndex, int columnIndex,
	    int newSpotValue) {
	if (isNumberNegative(rowIndex, columnIndex, newSpotValue))
	    throw new IllegalArgumentException("Number cannot be negative");
    }

    private void validateNumberNegative(int rowIndex, int columnIndex) {
	if (isNumberNegative(rowIndex, columnIndex))
	    throw new IllegalArgumentException("Number cannot be negative");
    }



    private void validateNumberForGrid(int rowIndex, int columnIndex) {
	if (isNumberBiggerThanGridSize(rowIndex, columnIndex))
	    throw new IllegalArgumentException("Number is bigger that the grid size");
    }





    private void insertSpotValue(int rowIndex, int columnIndex,
	    int newSpotValue) {
	data.getGameDataBoard()[rowIndex][columnIndex] = newSpotValue;
    }





    private boolean isZero(int newSpotValue) {
	return newSpotValue == 0;
    }





    private boolean isPositionOccupied(int rowIndex, int columnIndex) {
	return data.getGameDataBoard()[rowIndex][columnIndex] != 0;
    }





    private boolean isNumberNegative(int rowIndex, int columnIndex,
	    int newSpotValue) {
	return rowIndex < 0 || columnIndex < 0 || newSpotValue < 0;
    }
    
    private boolean isNumberNegative(int rowIndex, int columnIndex) {
	return rowIndex < 0 || columnIndex < 0;
    }





    private boolean isNumberBiggerThanGridSize(int rowIndex, int columnIndex) {
	return rowIndex > data.getNumberOfRows() || columnIndex > data.getNumberOfColumns();
    }

    public void update(int rowIndex, int columnIndex, int newSpotValue) {
	validateNumberForGrid(rowIndex, columnIndex);
	validateNumberNegative(rowIndex, columnIndex);
	validateZero(newSpotValue);
	insertSpotValue(rowIndex, columnIndex, newSpotValue);
    }

    
    public int getGridVal(int rowIndex, int columnIndex) {
	if (isNumberNegative(rowIndex, columnIndex))
	    throw new IllegalArgumentException("Number cannot be negative");
	validateNumberForGrid(rowIndex, columnIndex);
	return data.getGameDataBoard()[rowIndex][columnIndex];
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
	    data.getMinesweeper().scoreHit();

	    if (data.getMinesweeper().isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK Minesweeper"
			+ " value of square is " + sqr);

	    else if (data.getMinesweeper().isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 3:
	    data.getSubmarine().isSunk();
	    data.getSubmarine().scoreHit();

	    if (data.getSubmarine().isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK Submarine"
			+ " value of square is " + sqr);

	    else if (data.getSubmarine().isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 4:
	    data.getBattleship().scoreHit();

	    if (data.getBattleship().isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK Battleship"
			+ " value of square is " + sqr);

	    else if (data.getBattleship().isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 5:
	    data.getAircraftCarrier().scoreHit();

	    if (data.getAircraftCarrier().isSunk() == true)
		output = ("Shot at " + i + "," + j
			+ " HIT & SUNK Aircraft Carrier"
			+ " value of square is " + sqr);

	    else if (data.getAircraftCarrier().isSunk() == false)
		output = ("Shot at " + i + "," + j + " HIT "
			+ " value of square is " + sqr);
	    this.update(i, j, (sqr - 8));
	    hit = true;
	    break;

	case 7:
	    data.getDestroyer().scoreHit();

	    if (data.getDestroyer().isSunk() == true)
		output = ("Shot at " + i + "," + j + " HIT & SUNK destroyer"
			+ " value of square is " + sqr);

	    else if (data.getDestroyer().isSunk() == false)
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
	for (int i = 0; i < data.getNumberOfRows(); i++) {
	    r = r + "|";
	    for (int j = 0; j < data.getNumberOfColumns(); j++)
		// change this to CoLumns for default
		r = r + data.getGameDataBoard()[i][j];
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

	if (data.getMinesweeper().isSunk() == true)
	    MINESWEEPER = ("Minesweeper is SUNK");

	if (data.getSubmarine().isSunk() == true)
	    SUBMARINE = ("Submarine is SUNK");

	if (data.getDestroyer().isSunk() == true)
	    DESTROYER = ("Destroyer is SUNK");

	if (data.getBattleship().isSunk() == true)
	    BATTLESHIP = ("Battleship is SUNK");

	if (data.getAircraftCarrier().isSunk() == true)
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

	if (data.isMinePlaced() == true)
	    Minesweeper = "Minesweeper has been placed";

	if (data.isDestPlaced() == true)
	    Destroyer = "Destroyer has been placed";

	if (data.isSubPlaced() == true)
	    Submarine = "Submarine has been placed";

	if (data.isBattlePlaced() == true)
	    Battleship = "Battleship has been placed";

	if (isAirPlaced() == true)
	    AircraftCarrier = "Aircraft Carrier has been placed";

	return Minesweeper + "\n" + Destroyer + "\n" + Submarine + "\n"
		+ Battleship + "\n" + AircraftCarrier;
    }
    
    public int getNumberOfColumns() {
	return data.getNumberOfColumns();
    }

    public int getNumberOfRows() {
	return data.getNumberOfRows();
    }
    
    public void setNumberOfColumns(int numberOfColumns) {
	this.data.setNumberOfColumns(numberOfColumns);
    }
    
    public void setNumberOfRows(int numberOfRows) {
	this.data.setNumberOfRows(numberOfRows);
    }
    
    public boolean isMineShipSunk() {
	return data.getMinesweeper().isSunk();
    }

    public boolean isSubmarineSunk() {
	return data.getSubmarine().isSunk();
    }

    public boolean isDestroyerSunk() {
	return data.getDestroyer().isSunk();
    }

    public boolean isBattleShipSunk() {
	return data.getBattleship().isSunk();
    }

    public boolean isAircraftcarrierSunk() {
	return data.getAircraftCarrier().isSunk();
    }

}

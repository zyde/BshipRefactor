package src.main.logic;

import java.io.Serializable;

import src.main.exceptions.PositionExceedsBoardException;
import src.main.exceptions.PositionOccupiedException;
import src.main.logic.ships.AircraftCarrier;
import src.main.logic.ships.Battleship;
import src.main.logic.ships.Destroyer;
import src.main.logic.ships.Minesweeper;
import src.main.logic.ships.Ship;
import src.main.logic.ships.Submarine;

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

    public boolean isSpotReserved(int columnIndex, int rowIndex) {
	int index = getGridVal(columnIndex, rowIndex);

	if (hasInsertedValue(index)) {
	    return true;
	}
	return false;
    }

    public boolean areShipsSunk() {
	return isMineShipSunk() && isSubmarineSunk() && isDestroyerSunk()
		&& isBattleShipSunk();
    }

    public boolean isMineShipPlaced() {
	return data.isMinePlaced();
    }

    public void setMineshipPlaced() {
	data.setMinePlaced(true);
    }

    public boolean addMineship(int i, int j, int s) {
	boolean isHorizontal = (s == 0);
	try {
	    data.setMinesweeper(new Minesweeper(this, i, j, isHorizontal));
	} catch (PositionOccupiedException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s minesweeper here, position is occupied \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	} catch (PositionExceedsBoardException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s minesweeper here, ship will not fit on grid \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	}
	return isMineShipPlaced();
    }

    public boolean isSubmarinePlaced() {
	return data.isSubPlaced();
    }

    public void setSubmarinePlaced() {
	data.setSubPlaced(true);
    }

    public boolean addSub(int i, int j, int s) {
	boolean isHorizontal = (s == 0);
	try {
	    data.setSubmarine(new Submarine(this, i, j, isHorizontal));
	} catch (PositionOccupiedException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s submarine here, position is occupied \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	} catch (PositionExceedsBoardException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s submarine here, ship will not fit on grid \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	}
	return isSubmarinePlaced();
    }

    public boolean isDestroyerPlaced() {
	return data.isDestPlaced();
    }

    public void setDestroyerPlaced() {
	data.setDestPlaced(true);
    }

    public boolean addDestroyer(int i, int j, int s) {
	boolean isHorizontal = (s == 0);
	try {
	    data.setDestroyer(new Destroyer(this, i, j, isHorizontal));
	} catch (PositionOccupiedException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s destroyer here, position is occupied \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	} catch (PositionExceedsBoardException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s destroyer here, ship will not fit on grid \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	}
	return isDestroyerPlaced();
    }

    public boolean isBattleShipPlaced() {
	return data.isBattlePlaced();
    }

    public void setBattleshipPlaced() {
	data.setBattlePlaced(true);
    }

    public boolean addBattleship(int i, int j, int s) {
	boolean isHorizontal = (s == 0);
	try {
	    data.setBattleship(new Battleship(this, i, j, isHorizontal));
	} catch (PositionOccupiedException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s battleship here, position is occupied \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	} catch (PositionExceedsBoardException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s battleship here, ship will not fit on grid \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	}
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
	}
	return false;
    }

    public boolean addAir(int i, int j, int s) {
	boolean isHorizontal = (s == 0);
	try {
	    data.setAircraftCarrier(new AircraftCarrier(this, i, j, isHorizontal));
	} catch (PositionOccupiedException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s aircraft carrier here, position is occupied \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	} catch (PositionExceedsBoardException Exception) {
	    System.out.println(String.format(
		    "Cannot place %s aircraft carrier here, ship will not fit on grid \n",
		    (isHorizontal ? "horizontal" : "vertical")));
	}
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
	    throw new IllegalArgumentException(
		    "Number is bigger that the grid size");
    }

    private void insertSpotValue(int rowIndex, int columnIndex, int newSpotValue) {
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
	return rowIndex > data.getNumberOfRows()
		|| columnIndex > data.getNumberOfColumns();
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
    public boolean shot(int rowIndex, int columnIndex) {
	int square = getGridVal(rowIndex, columnIndex);
	String outputString = "Shot at " + rowIndex + "," + columnIndex
		+ " value of square is " + square;
	boolean hit = false;

	switch (square) {
	case 0:
	    hit = false;
	    outputString = missHit(rowIndex, columnIndex, square);
	    break;
	case 1:
	    hit = false;
	    outputString = squareTakenHit(rowIndex, columnIndex, square);
	    break;

	case 2:
	    outputString = mineSweeperHit(rowIndex, columnIndex, square);
	    hit = true;
	    break;

	case 3:
	    outputString = submarineHit(rowIndex, columnIndex, square);
	    hit = true;
	    break;

	case 4:
	    outputString = battleShipHit(rowIndex, columnIndex, square);
	    hit = true;
	    break;

	case 5:
	    outputString = aircraftCarrierHit(rowIndex, columnIndex, square);
	    hit = true;
	    break;

	case 7:
	    outputString = destroyerHit(rowIndex, columnIndex, square);
	    hit = true;
	    break;

	default:
	    outputString = incorrectHit(rowIndex, columnIndex, square);
	    break;
	}

	if (square < 0)
	    outputString = sunkShipHit(rowIndex, columnIndex, square);
	return hit;

    }

    private String incorrectHit(int rowIndex, int columnIndex, int square) {
	String outputString;
	outputString = "Shot at " + rowIndex + "," + columnIndex
		+ " ERROR shot is either already hit, or incorect"
		+ " value of square is " + square;
	return outputString;
    }

    private String sunkShipHit(int rowIndex, int columnIndex, int square) {
	String outputString;
	outputString = "Shot at " + rowIndex + "," + columnIndex
		+ " ERROR location contains a sunk ship. Value of square is "
		+ square;
	return outputString;
    }

    private String destroyerHit(int rowIndex, int columnIndex, int square) {
	String outputString = "";
	data.getDestroyer().scoreHit();

	if (data.getDestroyer().isSunk() == true)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex
		    + " HIT & SUNK destroyer" + " value of square is " + square);

	else if (data.getDestroyer().isSunk() == false)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex + " HIT "
		    + " value of square is " + square);
	this.update(rowIndex, columnIndex, (square - 8));
	return outputString;
    }

    private String aircraftCarrierHit(int rowIndex, int columnIndex, int square) {
	String outputString = "";
	data.getAircraftCarrier().scoreHit();

	if (data.getAircraftCarrier().isSunk() == true)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex
		    + " HIT & SUNK Aircraft Carrier" + " value of square is " + square);

	else if (data.getAircraftCarrier().isSunk() == false)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex + " HIT "
		    + " value of square is " + square);
	this.update(rowIndex, columnIndex, (square - 8));
	return outputString;
    }

    private String battleShipHit(int rowIndex, int columnIndex, int square) {
	String outputString = "";
	data.getBattleship().scoreHit();

	if (data.getBattleship().isSunk() == true)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex
		    + " HIT & SUNK Battleship" + " value of square is " + square);

	else if (data.getBattleship().isSunk() == false)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex + " HIT "
		    + " value of square is " + square);
	this.update(rowIndex, columnIndex, (square - 8));

	return outputString;
    }

    private String submarineHit(int rowIndex, int columnIndex, int square) {
	String outputString = "";
	data.getSubmarine().isSunk();
	data.getSubmarine().scoreHit();

	if (data.getSubmarine().isSunk() == true)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex
		    + " HIT & SUNK Submarine" + " value of square is " + square);

	else if (data.getSubmarine().isSunk() == false)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex + " HIT "
		    + " value of square is " + square);
	this.update(rowIndex, columnIndex, (square - 8));

	return outputString;
    }

    private String mineSweeperHit(int rowIndex, int columnIndex, int square) {
	String outputString = "";
	data.getMinesweeper().scoreHit();

	if (data.getMinesweeper().isSunk() == true)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex
		    + " HIT & SUNK Minesweeper" + " value of square is " + square);

	else if (data.getMinesweeper().isSunk() == false)
	    outputString = ("Shot at " + rowIndex + "," + columnIndex + " HIT "
		    + " value of square is " + square);
	this.update(rowIndex, columnIndex, (square - 8));
	return outputString;
    }

    private String squareTakenHit(int rowIndex, int columnIndex, int square) {
	String outputString;
	outputString = ("Shot at " + rowIndex + "," + columnIndex
		+ " INVALID shot already taken here" + " value of square is " + square);
	return outputString;
    }

    private String missHit(int rowIndex, int columnIndex, int square) {
	String outputString;
	outputString = ("Shot at " + rowIndex + "," + columnIndex + " MISS"
		+ " value of square is " + square);
	this.update(rowIndex, columnIndex, 1);
	return outputString;
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

    private void createBoard() {
	data.setGameDataBoard(new int[data.getNumberOfRows()][data
		.getNumberOfColumns()]);
    }

    private boolean hasInsertedValue(int index) {
	return index > 1 && index < 8;
    }

    public boolean isShipPlaced(Ship ship) {
	Class<? extends Ship> shipclass = ship.getClass();
	if (shipclass.equals(AircraftCarrier.class))
	    return data.isAirPlaced();
	if (shipclass.equals(Battleship.class))
	    return data.isBattlePlaced();
	if (shipclass.equals(Destroyer.class))
	    return data.isDestPlaced();
	if (shipclass.equals(Submarine.class))
	    return data.isSubPlaced();
	if (shipclass.equals(Minesweeper.class))
	    return data.isMinePlaced();
	return false;
    }

    public void setShipAsPlaced(Ship ship) {
	Class<? extends Ship> shipClass = ship.getClass();
	if (shipClass.equals(AircraftCarrier.class)) {
	    data.setAirPlaced(true);
	}
	if (shipClass.equals(Battleship.class)) {
	    data.setBattlePlaced(true);
	}
	if (shipClass.equals(Destroyer.class)) {
	    data.setDestPlaced(true);
	}
	if (shipClass.equals(Submarine.class)) {
	    data.setSubPlaced(true);
	}
	if (shipClass.equals(Minesweeper.class)) {
	    data.setMinePlaced(true);
	}

    }

    @Override
    public String toString() {
	String result = "";

	for (int i = 0; i < data.getNumberOfRows(); i++) {
	    result = result + "|";
	    for (int j = 0; j < data.getNumberOfColumns(); j++)
		result = result + data.getGameDataBoard()[i][j];
	    result = result + "|\n";
	}
	return result;
    }

}

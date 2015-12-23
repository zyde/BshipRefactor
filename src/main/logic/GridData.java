package src.main.logic;

import src.main.logic.ships.AircraftCarrier;
import src.main.logic.ships.Battleship;
import src.main.logic.ships.Destroyer;
import src.main.logic.ships.Minesweeper;
import src.main.logic.ships.Submarine;

public class GridData {
    private int[][] gameDataBoard;
    private int numberOfRows;
    private int numberOfColumns;
    private boolean minePlaced;
    private boolean subPlaced;
    private boolean destPlaced;
    private boolean battlePlaced;
    private boolean airPlaced;
    private Minesweeper minesweeper;
    private Submarine submarine;
    private Destroyer destroyer;
    private Battleship battleship;
    private AircraftCarrier aircraftCarrier;

    
    
    public GridData(){
	this.minePlaced = false;
	this.subPlaced = false;
	this.destPlaced = false;
	this.battlePlaced = false;
	this.airPlaced = false;
    }
    public GridData(boolean minePlaced, boolean subPlaced, boolean destPlaced,
	    boolean battlePlaced, boolean airPlaced) {
	this.minePlaced = minePlaced;
	this.subPlaced = subPlaced;
	this.destPlaced = destPlaced;
	this.battlePlaced = battlePlaced;
	this.airPlaced = airPlaced;
    }

    public int[][] getGameDataBoard() {
	return gameDataBoard;
    }

    public void setGameDataBoard(int[][] gameDataBoard) {
	this.gameDataBoard = gameDataBoard;
    }

    public int getNumberOfRows() {
	return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
	this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
	return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
	this.numberOfColumns = numberOfColumns;
    }

    public boolean isMinePlaced() {
	return minePlaced;
    }

    public void setMinePlaced(boolean minePlaced) {
	this.minePlaced = minePlaced;
    }

    public boolean isSubPlaced() {
	return subPlaced;
    }

    public void setSubPlaced(boolean subPlaced) {
	this.subPlaced = subPlaced;
    }

    public boolean isDestPlaced() {
	return destPlaced;
    }

    public void setDestPlaced(boolean destPlaced) {
	this.destPlaced = destPlaced;
    }

    public boolean isBattlePlaced() {
	return battlePlaced;
    }

    public void setBattlePlaced(boolean battlePlaced) {
	this.battlePlaced = battlePlaced;
    }

    public boolean isAirPlaced() {
	return airPlaced;
    }

    public void setAirPlaced(boolean airPlaced) {
	this.airPlaced = airPlaced;
    }

    public Minesweeper getMinesweeper() {
	return minesweeper;
    }

    public void setMinesweeper(Minesweeper minesweeper) {
	this.minesweeper = minesweeper;
    }

    public Submarine getSubmarine() {
	return submarine;
    }

    public void setSubmarine(Submarine submarine) {
	this.submarine = submarine;
    }

    public Destroyer getDestroyer() {
	return destroyer;
    }

    public void setDestroyer(Destroyer destroyer) {
	this.destroyer = destroyer;
    }

    public Battleship getBattleship() {
	return battleship;
    }

    public void setBattleship(Battleship battleship) {
	this.battleship = battleship;
    }

    public AircraftCarrier getAircraftCarrier() {
	return aircraftCarrier;
    }

    public void setAircraftCarrier(AircraftCarrier aircraftCarrier) {
	this.aircraftCarrier = aircraftCarrier;
    }
}
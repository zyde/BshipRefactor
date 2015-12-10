package src.main.java.logic;

import src.main.java.logic.ships.Air;
import src.main.java.logic.ships.Battle;
import src.main.java.logic.ships.Dest;
import src.main.java.logic.ships.Mine;
import src.main.java.logic.ships.Sub;

public class GridData {
    private int[][] gameDataBoard;
    private int numberOfRows;
    private int numberOfColumns;
    private boolean minePlaced;
    private boolean subPlaced;
    private boolean destPlaced;
    private boolean battlePlaced;
    private boolean airPlaced;
    private Mine minesweeper;
    private Sub submarine;
    private Dest destroyer;
    private Battle battleship;
    private Air aircraftCarrier;

    
    
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

    public Mine getMinesweeper() {
	return minesweeper;
    }

    public void setMinesweeper(Mine minesweeper) {
	this.minesweeper = minesweeper;
    }

    public Sub getSubmarine() {
	return submarine;
    }

    public void setSubmarine(Sub submarine) {
	this.submarine = submarine;
    }

    public Dest getDestroyer() {
	return destroyer;
    }

    public void setDestroyer(Dest destroyer) {
	this.destroyer = destroyer;
    }

    public Battle getBattleship() {
	return battleship;
    }

    public void setBattleship(Battle battleship) {
	this.battleship = battleship;
    }

    public Air getAircraftCarrier() {
	return aircraftCarrier;
    }

    public void setAircraftCarrier(Air aircraftCarrier) {
	this.aircraftCarrier = aircraftCarrier;
    }
}
package src.main.exceptions;

/*
 * Author: Michael
 * Created: 11 November 2004 19:58:04
 * Modified: 11 November 2004 19:58:04
 */

public class PositionOccupiedException extends RuntimeException {

    public PositionOccupiedException() {
    }

    public PositionOccupiedException(String reason) {
	super(reason);
    }

}

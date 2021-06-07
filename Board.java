import java.util.Arrays;

public class Board {

    public static final int NB_CELLS = 64; // number of cells on a chess board
    public static final int NB_COLUMNS = 8;
    public static final int NB_LINES = 8;

    private Cell[] cells;

    public Board() {
        this.cells = new Cell[NB_CELLS];
        int col = 1;
        int line = 1;
        Boolean color = false; // false is black, true is white
        for(int i = 0; i < NB_CELLS; i++) {
            this.cells[i] = new Cell(intToPosition(col, line), color);
            line = line >= 8 ? 1 : line + 1;
            col = line == 1 ? col + 1 : col;
            color = line == 1 ? color : !color;
        }
    }

    // getters
    
    public Cell[] getCells() { // this getter was written only for testing purposes, not to be included in production
        return this.cells;
    }

    public Cell getCell(String pos) {
        if(!isCorrectPosition(pos)) { return null; }
        return this.cells[calcIndexFromPosition(pos)];
    }
    
    public Cell getCell(int col, int line) {
        if(!isCorrectPosition(col, line)) { return null;}
        return this.cells[calcIndexFromPosition(intToPosition(col, line))];
    }

    // functions used to manipulate this.cells

    public String addPiece(Piece p, String pos) { // affect a 'Piece' to a 'Cell'
        if(Board.isCorrectPosition(pos) && p != null) {
            int index = calcIndexFromPosition(pos); // calculate the index
            p.setPosition(pos);
            this.cells[index].setPiece(p);
            return this.cells[index].getPosition();
        } else {
            return null;
        }
    }

    public Piece removePiece(String pos) {
        if(Board.isCorrectPosition(pos)) {
            int index = calcIndexFromPosition(pos); // calculate the index
            Piece p = this.cells[index].getPiece();
            p.setPosition(null);
            p.setState(0);
            this.cells[index].setPiece(null);
            return p;
        } else {
            return null;
        }
    }

    public Piece removePiece(String pos, int newState) {
        Piece p = removePiece(pos);
        if(p != null) { p.setState(newState); }
        return p;
    }

    public Piece movePiece(String currentPos, String newPos) { 
        if(Board.isCorrectPosition(currentPos) && Board.isCorrectPosition(newPos)) { // if both String arguments are correctly formatted
            // get the index of both cells in the 'this.cells[]' array
            int currentIndex = calcIndexFromPosition(currentPos);
            int newIndex = calcIndexFromPosition(newPos);
            if(this.cells[currentIndex].getPiece() != null) { // if there's a 'Piece' at the 'currentPos'
                // get the Piece at the 'currentPos'
                Piece currentPosP = this.cells[currentIndex].getPiece();
                // check whether there's a Piece at the 'newPos'
                Piece newPosP = this.cells[newIndex].getPiece();
                // notify the current Piece that it's been moved
                currentPosP.setPosition(newPos);
                // if there's a Piece at 'newPos', take it off the board by setting its state to unused
                if(newPosP != null) { newPosP.setState(0); }
                // free the cell at 'currentPos'
                this.cells[currentIndex].setPiece(null);
                // assign the Piece to its new cell
                this.cells[newIndex].setPiece(currentPosP);
                return newPosP; // return the 'Piece' that was at 'newPos' if there was one, otherwise we're returning null
            } 
        }
        return null; // return null if any of the if conditions aren't true
    }

    public Piece undoMove(String currentPos, String prevPos, Piece prevPiece) {
        if(Board.isCorrectPosition(currentPos) && Board.isCorrectPosition(prevPos)) { // if both String arguments are correctly formatted
            // get the index of both cells in the 'this.cells[]' array
            int currentIndex = calcIndexFromPosition(currentPos);
            int prevIndex = calcIndexFromPosition(prevPos);
            if(this.cells[currentIndex].getPiece() != null && this.cells[prevIndex].getPiece() == null) { // if there's a 'Piece' at the 'currentPos' & no Piece at 'prevPos'
                // get the Piece at the 'currentPos'
                Piece currentPosP = this.cells[currentIndex].getPiece();
                // notify the current Piece that it's been moved back
                currentPosP.setPosition(prevPos);
                if(prevPiece != null) { prevPiece.setPosition(currentPos); }
                // make sure both Pieces have their state set to 'used'
                currentPosP.setState(1);
                if(prevPiece != null) { prevPiece.setState(1); }
                // assign each Piece to the correct cell on the board
                this.cells[currentIndex].setPiece(prevPiece);
                this.cells[prevIndex].setPiece(currentPosP);
                return currentPosP;
            }
        } 
        return null; // return null if any of the if conditions aren't true
    }

    public String toFile() {
        String state = "";
        for(Cell c : this.cells) {
            state += c.toFile() + "\n";
        }
        return state;
    }

    // utility

    private int calcIndexFromPosition(String pos) {
        if(Board.isCorrectPosition(pos)) { // if the 'pos' is correctly formatted
            int col = (int) pos.charAt(0); // isolate the column and convert it to the corresponding ASCII code
            int line = Integer.parseInt(pos.substring(1)); // isolate the line number 
            return (col - 97) * 8 + line - 1; // calculate the index & return it
        } else { // return -1 if 'pos' isn't correctly formatted
            return -1;
        }
    }

    // class methods

    public static boolean isCorrectPosition(String pos) {
        if(pos == null) { return false; }
        if(pos.length() != 2) { return false; }
        return ((int) pos.charAt(0) >= 97 && (int) pos.charAt(0) <= 104) && (Integer.parseInt(pos.substring(1)) >= 1 && Integer.parseInt(pos.substring(1)) <= 8);
    }

    public static boolean isCorrectPosition(int col, int line) {
        return col >= 1 && col <= 8 && line >= 1 && line <= 8;
    }

    public static String intToPosition(int col, int line) {
        return isCorrectPosition(col, line) ?  Character.toString((char) 96 + col) + String.valueOf(line) : null;
    }

    public static int[] positionToInt(String pos) {
        int[] p = new int[2];
        if(isCorrectPosition(pos)) {
            p[0] = -96 + (int) pos.charAt(0); // column
            p[1] = Integer.parseInt(pos.substring(1)); // line
            return p;
        }
        return null;
    }

    public static boolean isOnLeftUpDiagonal(String pos, String dest) {
        return Arrays.asList(getleftUpDiagonalPath(pos)).contains(dest);
    }

    public static boolean isOnLeftDownDiagonal(String pos, String dest) {
        return Arrays.asList(getleftDownDiagonalPath(pos)).contains(dest);
    }

    public static boolean isOnRightUpDiagonal(String pos, String dest) {
        return Arrays.asList(getRightUpDiagonalPath(pos)).contains(dest);
    }

    public static boolean isOnRightDownDiagonal(String pos, String dest) {
        return Arrays.asList(getRightDownDiagonalPath(pos)).contains(dest);
    }

    public static String[] getleftUpDiagonalPath(String pos) {
        String cells = "";
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        col--;
        line++;
        while(isCorrectPosition(col, line)) {
            cells += intToPosition(col, line) + ",";
            col--;
            line++;
        }
        return cells.split(",");
    }

    public static String[] getleftDownDiagonalPath(String pos) {
        String cells = "";
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        col--;
        line--;
        while(isCorrectPosition(col, line)) {
            cells += intToPosition(col, line) + ",";
            col--;
            line--;
        }
        return cells.split(",");
    }

    public static String[] getRightUpDiagonalPath(String pos) {
        String cells = "";
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        col++;
        line++;
        while(isCorrectPosition(col, line)) {
            cells += intToPosition(col, line) + ",";
            col++;
            line++;
        }
        return cells.split(",");
    }

    public static String[] getRightDownDiagonalPath(String pos) {
        String cells = "";
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        col++;
        line--;
        while(isCorrectPosition(col, line)) {
            cells += intToPosition(col, line) + ",";
            col++;
            line--;
        }
        return cells.split(",");
    }

    public static String farthestLeftUpDiagonally(String pos) {
        if(!isCorrectPosition(pos)) {
            return null;
        }
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        int fcol = col;
        int fline = line;
        while(isCorrectPosition(col, line)) {
            fcol = col;
            fline = line;
            col--;
            line++;
        }
        return Board.intToPosition(fcol, fline);
    }

    public static String farthestLeftDownDiagonally(String pos) {
        if(!isCorrectPosition(pos)) {
            return null;
        }
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        int fcol = col;
        int fline = line;
        while(isCorrectPosition(col, line)) {
            fcol = col;
            fline = line;
            col--;
            line--;
        }
        return Board.intToPosition(fcol, fline);
    }

    public static String farthestRightUpDiagonally(String pos) {
        if(!isCorrectPosition(pos)) {
            return null;
        }
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        int fcol = col;
        int fline = line;
        while(isCorrectPosition(col, line)) {
            fcol = col;
            fline = line;
            col++;
            line++;
        }
        return Board.intToPosition(fcol, fline);
    }

    public static String farthestRightDownDiagonally(String pos) {
        if(!isCorrectPosition(pos)) {
            return null;
        }
        int col = positionToInt(pos)[0];
        int line = positionToInt(pos)[1];
        int fcol = col;
        int fline = line;
        while(isCorrectPosition(col, line)) {
            fcol = col;
            fline = line;
            col++;
            line--;
        }
        return Board.intToPosition(fcol, fline);
    }
}

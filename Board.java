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
            return pos;
        } else {
            return null;
        }
    }

    public Piece removePiece(String pos) {
        if(Board.isCorrectPosition(pos)) {
            int index = calcIndexFromPosition(pos); // calculate the index
            Piece p = this.cells[index].getPiece();
            p.setPosition(null);
            this.cells[index].setPiece(null);
            return p;
        } else {
            return null;
        }
    }

    public Piece movePiece(String currentPos, String newPos) { // todo
        if(Board.isCorrectPosition(currentPos) && Board.isCorrectPosition(newPos)) {
            int currentIndex = calcIndexFromPosition(currentPos);
            int newIndex = calcIndexFromPosition(newPos);
            if(this.cells[currentIndex].getPiece() != null) { // if there's a 'Piece' at the 'currentPos'
                Piece currentPosP = this.cells[currentIndex].getPiece();
                Piece newPosP = this.cells[newIndex].getPiece();
                currentPosP.setPosition(newPos);
                newPosP.setPosition(null);
                this.cells[currentIndex].setPiece(null);
                this.cells[newIndex].setPiece(currentPosP);
                return newPosP != null ? newPosP : currentPosP; // return the 'Piece' that was at 'newPos' if there was one, otherwise return the 'Piece' that was at 'currentPos'
            } 
        }
        return null; // return null if any of the if conditions aren't true
    }

    // utility

    private int calcIndexFromPosition(String pos) {
        if(Board.isCorrectPosition(pos)) {
            int col = (int) pos.charAt(0); // isolate the column and convert it to the corresponding ASCII code
            int line = Integer.parseInt(pos.substring(1)); // isolate the line number 
            return (col - 97) * 8 + line - 1; // calculate the index & return it
        } else {
            return -1;
        }
    }

    // class methods

    public static boolean isCorrectPosition(String pos) {
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

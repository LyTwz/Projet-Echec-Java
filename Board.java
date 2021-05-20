public class Board {

    public static final int NB_CELLS = 64; // number of cells on a chess board

    private Cell[] cells;

    public Board() {
        this.cells = new Cell[NB_CELLS];
        int col = 97;
        int line = 1;
        for(int i = 0; i < NB_CELLS; i++) {
            this.cells[i] = new Cell(Character.toString((char) col) + String.valueOf(line));
            line = line >= 8 ? 1 : line + 1;
            col = line == 1 ? col + 1 : col;
        }
    }

    // getters
    /*
    public Cell[] getCells() { // this getter was written only for testing purposes, not to be included in production
        return this.cells;
    }
    */

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

}

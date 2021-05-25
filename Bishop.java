public class Bishop extends Piece{
    
    public Bishop(boolean clr, int stt) {
        super(clr, stt);
    }

    public Bishop(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
    }

    // other functions --> 

    public String[] getNextMoves() { // todo 
        String moves = "";
        String diagonalLeft = "";
        String diagonalRight = "";
        String pos = this.getPosition();
        // list left up diagonal
        int col = Board.positionToInt(Board.farthestLeftUpDiagonally(pos))[0];
        int line = Board.positionToInt(Board.farthestLeftUpDiagonally(pos))[1];
        if(!Board.intToPosition(col, line).equals(pos)) { diagonalLeft += Board.intToPosition(col, line) + ","; }
        if(Board.isCorrectPosition(col + 1, line - 1)) { col++; line--; }
        while(!Board.intToPosition(col, line).equals(Board.farthestRightDownDiagonally(pos))) { // while we've not reached the end of the board
            if(!Board.intToPosition(col, line).equals(pos)) { diagonalLeft += Board.intToPosition(col, line) + ","; }
            col++;
            line--;
        }
        diagonalLeft += Board.intToPosition(col, line) + ",";
        // list right up diagonal
        col = Board.positionToInt(Board.farthestRightUpDiagonally(pos))[0];
        line = Board.positionToInt(Board.farthestRightUpDiagonally(pos))[1];
        if(!Board.intToPosition(col, line).equals(pos)) { diagonalRight += Board.intToPosition(col, line) + ","; }
        if(Board.isCorrectPosition(col - 1, line - 1)) { col--; line--; }
        while(!Board.intToPosition(col, line).equals(Board.farthestLeftDownDiagonally(pos))) { // while we've not reached the end of the board
            if(!Board.intToPosition(col, line).equals(pos)) { diagonalRight += Board.intToPosition(col, line) + ","; }
            col--;
            line--;
        }
        diagonalRight += Board.intToPosition(col, line) + ",";
        moves = diagonalLeft + diagonalRight.substring(0, diagonalRight.length()-1);
        return moves.split(",");
    }
}

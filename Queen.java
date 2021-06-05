public class Queen extends Piece {

    public Queen(boolean clr, int stt) {
        super(clr, stt);
        this.setRepresentation(clr ? Piece.WHITE_QUEEN_UNICODE : Piece.BLACK_QUEEN_UNICODE);
    }

    public Queen(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
        this.setRepresentation("Q");
    }

    // other functions --> 

    public String[] getNextMoves() {
        String moves = "";
        // combine the bishop's moving pattern with the rook's
        // bishop --> 
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
        moves += diagonalLeft + diagonalRight;
        // rook --> 
        col = Board.positionToInt(pos)[0];
        line = Board.positionToInt(pos)[1];
        int l = 8;
        int c = 1;
        // list the cells on the current column
        while(l >= 1) {
            if(l != line) { moves += Board.intToPosition(col, l) + ","; }
            l--;
        }
        // list the cells on the current line
        while(c <= 8) {
            if(c != col) { moves += Board.intToPosition(c, line) + ","; }
            c++;
        } 
        return moves.substring(0, moves.length() - 1).split(",");
    }

    public String[] getValidMoves(Board b) {
        String moves = String.join(",", this.getNextMoves());
        String pos = this.getPosition();
        // todo
        // to remove move from list -> moves = moves.replace("e3,", "");
        return moves.split(",");
    }

    @Override
    public String toString() {
        return "Queen[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

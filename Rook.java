public class Rook extends Piece {
    
    public Rook(boolean clr, int stt) {
        super(clr, stt);
    }

    public Rook(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
    }

    // other functions --> 

    public String[] getNextMoves() { // todo 
        String moves = "";
        String pos = this.getPosition();
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];
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
}

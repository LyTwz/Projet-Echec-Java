public class Rook extends Piece {
    
    public Rook(boolean clr, int stt) {
        super(clr, stt);
        this.setRepresentation(clr ? Piece.WHITE_ROOK_UNICODE : Piece.BLACK_ROOK_UNICODE);
    }

    public Rook(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
        this.setRepresentation("R");
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
        // System.out.println("Rook's got its next moves ready.");
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
        return "Rook[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

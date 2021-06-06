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

    private String[] getColumnPath() {
        String pos = this.getPosition();
        String path = "";
        int col = Board.positionToInt(pos)[0];
        // list the cells on the current column
        for(int l = 8; l >= 1; l--) {
            path += Board.intToPosition(col, l) + ",";
        }
        return path.split(",");
    }

    private String[] getLinePath() {
        String pos = this.getPosition();
        String path = "";
        int line = Board.positionToInt(pos)[1];
        // list the cells on the current column
        for(int c = 1; c <= 8; c++) {
            path += Board.intToPosition(line, c) + ",";
        }
        return path.split(",");
    }

    public String[] getValidMoves(Board b) {
        String validNextMoves = String.join(",", this.getNextMoves());
        String[] nextMoves = this.getNextMoves();
        String pos = this.getPosition();
        int currentCol = Board.positionToInt(pos)[0];
        int currentLine = Board.positionToInt(pos)[1];
        for(String dest : nextMoves) {
            Piece p = b.getCell(dest).getPiece();
            if(p != null) { // if there's already a piece at the destination
                validNextMoves = p.getColor() == this.getColor() ? validNextMoves.replace(dest + ",", "") : validNextMoves; // don't move there if it's the same color
            }
            int col = Board.positionToInt(dest)[0];
            int line = Board.positionToInt(dest)[1];
            // check whether there are pieces obstructing our way
            String[] path;
            if(col == currentCol && line != currentLine) { // if the destination is in the same column
                path = this.getColumnPath();
            } else {
                path = this.getLinePath();
            }
            for(String c : path) {
                validNextMoves = b.getCell(c).getPiece() != null ? validNextMoves.replace(dest + ",", "") : validNextMoves;
            }
        }
        return validNextMoves.split(",");
    }

    @Override
    public String toString() {
        return "Rook[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

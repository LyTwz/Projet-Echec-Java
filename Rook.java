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

    private String[] getColumnPath(String dest) {
        String pos = this.getPosition();
        String path = "";
        int destCol = Board.positionToInt(dest)[0];
        int destLine = Board.positionToInt(dest)[1];
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];
        if(col != destCol) { return null; } // check that the destination is on the same column
        // list the cells on the current column, until we reach the destination
        if(destLine < line) { // if the destination is lower on the board
            for(int l = destLine; l <= line; l++) {
                path += Board.intToPosition(col, l) + ",";
            }
        } else { // if it's higher
            for(int l = destLine; l >= line; l--) {
                path += Board.intToPosition(col, l) + ",";
            }
        }
        return path.split(",");
    }

    private String[] getLinePath(String dest) {
        String pos = this.getPosition();
        String path = "";
        int destCol = Board.positionToInt(dest)[0];
        int destLine = Board.positionToInt(dest)[1];
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];
        if(line != destLine) { return null; } // check that the destination is on the same line
        // list the cells on the current line, until we reach the destination
        if(destCol < col) { // if the destination is on the left
            for(int c = destCol; c <= col; c++) {
                path += Board.intToPosition(c, line) + ",";
            }
        } else { // if it's on the right
            for(int c = destLine; c >= line; c--) {
                path += Board.intToPosition(c, line) + ",";
            }
        }
        return path.split(",");
    }

    private String[] getPath(String dest) { // chooses the right path to get from this.getPosition() to 'dest'
        if(Board.isCorrectPosition(dest)) {
            int currentCol = Board.positionToInt(this.getPosition())[0];
            int currentLine = Board.positionToInt(this.getPosition())[1];
            int col = Board.positionToInt(dest)[0];
            int line = Board.positionToInt(dest)[1];
            // return the correct path depending on whether the destination is on the same line or column as 'this'
            return col == currentCol && line != currentLine ? this.getColumnPath(dest) : this.getLinePath(dest); 
        }
        return null;
    }

    public String[] getValidMoves(Board b) {
        String validNextMoves = String.join(",", this.getNextMoves());
        String[] nextMoves = this.getNextMoves();
        for(String dest : nextMoves) {
            Piece p = b.getCell(dest).getPiece();
            if(p != null) { // if there's already a piece at the destination
                validNextMoves = p.getColor() == this.getColor() ? validNextMoves.replace(dest + ",", "") : validNextMoves; // don't move there if it's the same color
            }
            // check whether there are pieces obstructing our way
            String[] path = this.getPath(dest);
            // remove from validNextMoves all moves that involve jumping over another Piece
            boolean jump = false;
            for(String c : path) {
                if(b.getCell(c).getPiece() != null) { jump = true; break; }
            }
            validNextMoves = jump ? validNextMoves.replace(dest + ",", "") : validNextMoves;
        }
        return validNextMoves.split(",");
    }

    @Override
    public String toString() {
        return "Rook[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

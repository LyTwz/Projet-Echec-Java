import java.util.Arrays;

public class Queen extends Piece {

    public Queen(boolean clr, int stt) {
        super(clr, stt);
        this.setRepresentation(clr ? Piece.WHITE_QUEEN_UNICODE : Piece.BLACK_QUEEN_UNICODE);
    }

    public Queen(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
        this.setRepresentation(clr ? Piece.WHITE_QUEEN_UNICODE : Piece.BLACK_QUEEN_UNICODE);
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
        moves = moves.replaceAll(this.getPosition(), "");
        moves = moves.replaceAll(",,", ",");
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

    private String[] getleftUpDiagonalPath(String dest) {
        String pos = this.getPosition();
        int destCol = Board.positionToInt(dest)[0];
        int destLine = Board.positionToInt(dest)[1];
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];
        if(!Board.isOnLeftUpDiagonal(pos, dest)) { return null; } // check that the destination is on the left up diagonal
        String path = String.join(",", Board.getleftUpDiagonalPath(pos));
        path = path.substring(0, path.indexOf(dest) + dest.length());
        return path.split(",");
    }

    private String[] getleftDownDiagonalPath(String dest) {
        String pos = this.getPosition();
        int destCol = Board.positionToInt(dest)[0];
        int destLine = Board.positionToInt(dest)[1];
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];
        if(!Board.isOnLeftDownDiagonal(pos, dest)) { return null; } // check that the destination is on the left up diagonal
        String path = String.join(",", Board.getleftDownDiagonalPath(pos));
        path = path.substring(0, path.indexOf(dest) + dest.length());
        return path.split(",");
    }

    private String[] getRightUpDiagonalPath(String dest) {
        String pos = this.getPosition();
        int destCol = Board.positionToInt(dest)[0];
        int destLine = Board.positionToInt(dest)[1];
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];
        if(!Board.isOnRightUpDiagonal(pos, dest)) { return null; } // check that the destination is on the left up diagonal
        String path = String.join(",", Board.getRightUpDiagonalPath(pos));
        path = path.substring(0, path.indexOf(dest) + dest.length());
        return path.split(",");
    }

    private String[] getRightDownDiagonalPath(String dest) {
        String pos = this.getPosition();
        int destCol = Board.positionToInt(dest)[0];
        int destLine = Board.positionToInt(dest)[1];
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];
        if(!Board.isOnRightDownDiagonal(pos, dest)) { return null; } // check that the destination is on the left up diagonal
        String path = String.join(",", Board.getRightDownDiagonalPath(pos));
        path = path.substring(0, path.indexOf(dest) + dest.length());
        return path.split(",");
    }

    public String[] getPath(String dest) { // chooses the right path to get from this.getPosition() to 'dest'
        String[] path;
        if(Board.isOnLeftUpDiagonal(this.getPosition(), dest)) { // if the destination is on the left up diagonal
            path = this.getleftUpDiagonalPath(dest);
        } else if(Board.isOnLeftDownDiagonal(this.getPosition(), dest)) { // left down diagonal
            path = this.getleftDownDiagonalPath(dest);
        } else if(Board.isOnRightUpDiagonal(this.getPosition(), dest)) { // right up diagonal
            path = this.getRightUpDiagonalPath(dest);
        } else if(Board.isOnRightDownDiagonal(this.getPosition(), dest)) { // right down diagonal
            path = this.getRightDownDiagonalPath(dest);
        } else {
            int currentCol = Board.positionToInt(this.getPosition())[0];
            int currentLine = Board.positionToInt(this.getPosition())[1];
            int col = Board.positionToInt(dest)[0];
            int line = Board.positionToInt(dest)[1];
            path = col == currentCol && line != currentLine ? this.getColumnPath(dest) : this.getLinePath(dest);
        }
        return path;
    }

    public String[] getValidMoves(Board b) {
        String validNextMoves = String.join(",", this.getNextMoves());
        String[] nextMoves = this.getNextMoves();
        for(String dest : nextMoves) {
            Piece p = b.getCell(dest).getPiece();
            if(p != null) { // if there's already a piece at the destination
                validNextMoves = p.getColor() == this.getColor() ? validNextMoves.replace(dest, "") : validNextMoves; // don't move there if it's the same color
            }
            // check whether there are pieces obstructing our way
            String[] path = this.getPath(dest);
            // remove from validNextMoves all moves that involve jumping over another Piece
            boolean jump = false;
            for(String c : path) {
                if(b.getCell(c).getPiece() != null && !c.equals(dest) && !c.equals(this.getPosition())) { jump = true; break; }
            }
            validNextMoves = jump ? validNextMoves.replace(dest, "") : validNextMoves;
        }
        return validNextMoves.split(",");
    }

    @Override
    public String toString() {
        return "Queen[color= " + this.getColor() + ", state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

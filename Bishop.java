public class Bishop extends Piece {
    
    public Bishop(boolean clr, int stt) {
        super(clr, stt);
        this.setRepresentation(clr ? Piece.WHITE_BISHOP_UNICODE : Piece.BLACK_BISHOP_UNICODE);
    }

    public Bishop(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
        this.setRepresentation("B");
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
        if(!Board.intToPosition(col, line).equals(pos)) { diagonalLeft += Board.intToPosition(col, line) + ","; } // if we're not already at the edge, add the farthest cell diagonally to the list
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

    public String[] getleftUpDiagonalPath(String dest) {
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

    public String[] getleftDownDiagonalPath(String dest) {
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

    public String[] getRightUpDiagonalPath(String dest) {
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

    public String[] getRightDownDiagonalPath(String dest) {
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
        } else /* if(Board.isOnRightDownDiagonal(this.getPosition(), dest)) */ { // right down diagonal
            path = this.getRightDownDiagonalPath(dest);
        }
        return path;
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
        return "Bishop[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

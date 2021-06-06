import java.util.Arrays;

public class Pawn extends Piece {
    
    private static final int[][] firstMovesWhite = { {1,1}, {-1,1},{0,1}, {0,2}};

    private static final int[][] firstMovesBlack = {  {1,-1},{-1,-1},{0,-1}, {0,-2} };

    private static final int[][] nextMovesWhite = { {1,1}, {-1,1},{0,1} };

    private static final int[][] nextMovesBlack = { {1,-1},{-1,-1},{0,-1} };


    public Pawn(boolean clr, int stt) {
        super(clr, stt);
        this.setRepresentation(clr ? Piece.WHITE_PAWN_UNICODE : Piece.BLACK_PAWN_UNICODE);
    }

    public Pawn(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
        this.setRepresentation("P");
    }

    // other functions --> 

    public String[] getNextMoves() { // todo 
        String string = "";
        String pos = this.getPosition();
        int col = Board.positionToInt(pos)[0];
        int line = Board.positionToInt(pos)[1];

        if(this.getColor() == true && line == 2) {
            for(int i = 0; i < firstMovesWhite.length; i++){
                int posA = col + firstMovesWhite[i][0];
                int posB = line + firstMovesWhite[i][1];
                string += Board.isCorrectPosition(posA, posB) ? Board.intToPosition(posA,posB) + "," : "";
            }
        }
        if(this.getColor() == false && line == 7){
            for(int i = 0; i < firstMovesBlack.length; i++){
                int posA = col + firstMovesBlack[i][0];
                int posB = line + firstMovesBlack[i][1];
                string += Board.isCorrectPosition(posA, posB) ? Board.intToPosition(posA,posB) + "," : "";
            }
        }
        if(this.getColor() == true && line != 2){
            for(int i = 0; i < nextMovesWhite.length; i++){
                int posA = line + nextMovesWhite[i][0];
                int posB = col + nextMovesWhite[i][1];
                string += Board.isCorrectPosition(posA, posB) ? Board.intToPosition(posA,posB) + "," : "";
            }
        }

        if(this.getColor() == false && line != 7){
            for(int i = 0; i < nextMovesBlack.length; i++){
                int posA = col + nextMovesBlack[i][0];
                int posB = line + nextMovesBlack[i][1];
                string += Board.isCorrectPosition(posA, posB) ? Board.intToPosition(posA,posB) + "," : "";
            }
        
        }

        String[] final_string = string.split(",");
        return final_string;


    }

    public String[] getPath(String dest) {
        return null;
    }

    public String[] getValidMoves(Board b) {
        String validMoves = "";
        String[] moves = this.getNextMoves();
        // System.out.println(Arrays.toString(moves));
        for(String dest : moves) {
            if(Board.isOnLeftUpDiagonal(this.getPosition(), dest) || Board.isOnRightUpDiagonal(this.getPosition(), dest)) { // if the destination is on the right or left diagonal
                if(b.getCell(dest).getPiece() != null) { // if there's a Piece at the destination
                    validMoves += b.getCell(dest).getPiece().getColor() != this.getColor() ?  dest + "," : "";  // don't move there if the Piece is the same color as we are
                }
            } else if(Board.isOnRightDownDiagonal(this.getPosition(), dest) || Board.isOnLeftDownDiagonal(this.getPosition(), dest)) {
                if(b.getCell(dest).getPiece() != null) { // if there's a Piece at the destination
                    validMoves += b.getCell(dest).getPiece().getColor() != this.getColor() ?  dest + "," : "";  // don't move there if the Piece is the same color as we are
                }
            } 
            else {
                int destCol = Board.positionToInt(dest)[0];
                int destLine = Board.positionToInt(dest)[1]; 
                int currentLine = Board.positionToInt(this.getPosition())[1];
                if(Math.abs(destLine - currentLine) == 2) {
                    int intermidiateLine = this.getColor() ? 1 : -1;
                    if(b.getCell(dest).getPiece() == null && b.getCell(destCol, destLine-intermidiateLine).getPiece() == null) {
                        validMoves += dest + ",";
                    } 
                } else {
                    if(b.getCell(dest).getPiece() == null) {
                        validMoves += dest + ",";
                    }
                }
            }
        }
        // System.out.println(this.getType() + "'s next valid moves -> " + validMoves);
        return validMoves.split(",");
    }

    @Override
    public String toString() {
        return "Pawn[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

public class Pawn extends Piece {
    
    private static final int[][] firstMovesWhite = { {0,1}, {0,2} };

    private static final int[][] firstMovesBlack = { {0,-1}, {0,-2} };

    private static final int[][] nextMovesWhite = { {0,1}, {1,1}, {-1,1}};

    private static final int[][] nextMovesBlack = { {0,-1},{1,-1},{-1,-1} };


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
        String col = pos.substring(1,2);
        int col2 = Integer.valueOf(col);
        int [] convertedPos = Board.positionToInt(pos);
        int a = convertedPos[0];
        int b = convertedPos[1];

        if(this.getColor() == true && col2 == 2){
            for(int i = 0; i < firstMovesWhite.length; i++){
                int posA = a + firstMovesWhite[i][0];
                // System.out.println(i);
                int posB = b + firstMovesWhite[i][1];
                string += Board.intToPosition(posA,posB) + ",";
            }
        }
        if(this.getColor() == false && col2 == 7){
            for(int i = 0; i < firstMovesBlack.length; i++){
                int posA = a + firstMovesBlack[i][0];
                // System.out.println(i);
                int posB = b + firstMovesBlack[i][1];
                string += Board.intToPosition(posA,posB) + ",";
            }
        }
        if(this.getColor() == true && col2 != 7){
            for(int i = 0; i < nextMovesWhite.length; i++){
                int posA = a + nextMovesWhite[i][0];
                // System.out.println(i);
                int posB = b + nextMovesWhite[i][1];
                string += Board.intToPosition(posA,posB) + ",";
            }
        }

        if(this.getColor() == false && col2 != 7){
            for(int i = 0; i < nextMovesBlack.length; i++){
                int posA = a + nextMovesBlack[i][0];
                // System.out.println(i);
                int posB = b + nextMovesBlack[i][1];
                string += Board.intToPosition(posA,posB) + ",";
            }
        
        }

        String[] final_string = string.split(",");
        return final_string;


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
        return "Pawn[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

public class Pawn extends Piece {
    
    private static final int[][] firstMovesWhite = { {0,1}, {0,2} };

    private static final int[][] firstMovesBlack = { {0,-1}, {0,-2} };

    private static final int[][] nextMovesWhite = { {0,1} };

    private static final int[][] nextMovesBlack = { {0,-1} };

    private static final int[][] eatMovesWhite = { {1,1}, {-1,1} };

    private static final int[][] eatMovesBlack = { {1,-1},{-1,-1} };


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
        String final_string = "";
        String pos = this.getPosition();
        String col = pos.substring(1,2);
        int col2 = Integer.valueOf(col);
        int [] convertedPos = Board.positionToInt(pos);
        int a = convertedPos[0];
        int b = convertedPos[1];

        if(this.getColor() == true && col2 == 2){
            for(int i = 0; i < firstMovesWhite.length;){
                int posA = a + firstMovesWhite[i][0];
                System.out.println(i);
                int posB = b + firstMovesWhite[i][1];
                final_string += Board.intToPosition(posA,posB) + ",";
            }
        }
        if(this.getColor() == false && col2 == 7){
            for(int i = 0; i < firstMovesBlack.length;){
                int posA = a + firstMovesBlack[i][0];
                System.out.println(i);
                int posB = b + firstMovesBlack[i][1];
                final_string += Board.intToPosition(posA,posB) + ",";
            }
        }
        if(this.getColor() == true && col2 != 7){
            for(int i = 0; i < nextMovesWhite.length;){
                int posA = a + nextMovesWhite[i][0];
                System.out.println(i);
                int posB = b + nextMovesWhite[i][1];
                final_string += Board.intToPosition(posA,posB) + ",";
            }
        }

        if(this.getColor() == false && col2 != 7){
            for(int i = 0; i < nextMovesBlack.length;){
                int posA = a + nextMovesBlack[i][0];
                System.out.println(i);
                int posB = b + nextMovesBlack[i][1];
                final_string += Board.intToPosition(posA,posB) + ",";
            }
        
        }

        
        return final_string.split(",");


    }

    @Override
    public String toString() {
        return "Pawn[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }
}

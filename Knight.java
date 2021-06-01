public class Knight extends Piece {
    
    private static final int[][] possibleMoves = { {1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};

    public Knight(boolean clr, int stt) {
        super(clr, stt);
        this.setRepresentation(clr ? Piece.WHITE_KNIGHT_UNICODE : Piece.BLACK_KNIGHT_UNICODE);
    }

    public Knight(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
        this.setRepresentation("k");
    }

    // other functions --> 

    public String[] getNextMoves() { // todo 
        String final_string = "";
        String pos = this.getPosition();
        int [] convertedPos = Board.positionToInt(pos);
        int a = convertedPos[0];
        int b = convertedPos[1];

        for(int i = 0; i < possibleMoves.length; i ++){
            int posA = a + possibleMoves[i][0];
            System.out.println(i);
            int posB = b + possibleMoves[i][1];
            final_string += Board.intToPosition(posA,posB) + ",";

    }
    return final_string.split(",");
    }
}

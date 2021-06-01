public class King extends Piece {
    
    private static final int[][] possibleMoves = {  {1,0},{0,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,-1},{-1,1}};
    
    public King(boolean clr, int stt) {
        super(clr, stt);
        this.setRepresentation(clr ? Piece.WHITE_KING_UNICODE : Piece.BLACK_KING_UNICODE);
    }

    public King(boolean clr, int stt, String pos) {
        super(clr, stt, pos);
        this.setRepresentation("K");
    }

    // other functions --> 

    @Override
    public String[] getNextMoves() {
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

    @Override
    public String toString() {
        return "King[state=" + this.getState() + ", position=" + this.getPosition() + "]";
    }

}
